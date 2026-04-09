// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.chris.health_monitoring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
   @Autowired
   private JavaMailSender mailSender;
   @Value("${spring.mail.username}")
   private String fromEmail;

   public EmailService() {
   }

   public void sendCredentials(String toEmail, String username, String tempPassword) {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom(this.fromEmail);
      message.setTo(toEmail);
      message.setSubject("Your Health Monitoring System Account");
      message.setText("Hello " + username + ",\n\nYour account has been created by the administrator.\n\nYour login credentials:\nUsername: " + username + "\nTemporary Password: " + tempPassword + "\n\nPlease login and reset your password immediately.\n\nLogin here: http://localhost:3000\n\nRegards,\nHealth Monitoring System");
      this.mailSender.send(message);
   }
}
