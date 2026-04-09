// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.chris.health_monitoring.service;

import com.chris.health_monitoring.model.User;
import com.chris.health_monitoring.repository.UserRepository;
import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
   @Autowired
   private UserRepository repo;
   @Autowired
   private PasswordEncoder passwordEncoder;
   @Autowired
   private JwtService jwtService;
   @Autowired
   private TokenBlacklistService tokenBlacklistService;
   @Autowired
   private EmailService emailService;
   private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
   private static final int PASSWORD_LENGTH = 10;

   public AuthService() {
   }

   private String generateTempPassword() {
      SecureRandom random = new SecureRandom();
      StringBuilder sb = new StringBuilder(10);

      for(int i = 0; i < 10; ++i) {
         sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%".length())));
      }

      return sb.toString();
   }

   @Transactional
   public void createUser(String username, String email, String role) {
      if (this.repo.existsByUsername(username)) {
         throw new RuntimeException("Username already taken");
      } else if (this.repo.existsByEmail(email)) {
         throw new RuntimeException("Email already registered");
      } else {
         String assignedRole = role != null && !role.isBlank() ? role.toUpperCase() : "USER";
         if (!assignedRole.equals("ADMIN") && !assignedRole.equals("USER")) {
            throw new RuntimeException("Invalid role. Must be ADMIN or USER");
         } else {
            String tempPassword = this.generateTempPassword();
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(this.passwordEncoder.encode(tempPassword));
            user.setRole(assignedRole);
            user.setMustResetPassword(true);

            try {
               this.emailService.sendCredentials(email, username, tempPassword);
            } catch (Exception e) {
               throw new RuntimeException("Failed to send credentials email: " + e.getMessage());
            }

            this.repo.save(user);
         }
      }
   }

   public String[] login(String username, String rawPassword) {
      User user = (User)this.repo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
      if (!this.passwordEncoder.matches(rawPassword, user.getPassword())) {
         throw new RuntimeException("Invalid password");
      } else {
         String token = this.jwtService.generateToken(user.getUsername(), user.getRole());
         return new String[]{token, user.getRole(), String.valueOf(user.isMustResetPassword())};
      }
   }

   public void resetPassword(String username, String newPassword) {
      User user = (User)this.repo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
      if (newPassword != null && newPassword.length() >= 6) {
         user.setPassword(this.passwordEncoder.encode(newPassword));
         user.setMustResetPassword(false);
         this.repo.save(user);
      } else {
         throw new RuntimeException("Password must be at least 6 characters");
      }
   }

   public void logout(String token) {
      this.tokenBlacklistService.blacklist(token);
   }
}
