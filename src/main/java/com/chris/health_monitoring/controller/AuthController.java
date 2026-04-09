package com.chris.health_monitoring.controller;

import com.chris.health_monitoring.dto.AuthRequest;
import com.chris.health_monitoring.dto.AuthResponse;
import com.chris.health_monitoring.dto.ResetPasswordRequest;
import com.chris.health_monitoring.service.AuthService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

   @Autowired
   private AuthService service;

   @PostMapping("/login")
   public AuthResponse login(@RequestBody AuthRequest request) {
      String[] result = service.login(request.getUsername(), request.getPassword());
      return new AuthResponse(result[0], result[1], Boolean.parseBoolean(result[2]), request.getUsername());
   }

   @PostMapping("/reset-password")
   public ResponseEntity<Map<String, String>> resetPassword(@RequestBody ResetPasswordRequest request) {
      try {
         service.resetPassword(request.getUsername(), request.getNewPassword());
         return ResponseEntity.ok(Map.of("message", "Password reset successfully"));
      } catch (RuntimeException e) {
         return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
      }
   }
}