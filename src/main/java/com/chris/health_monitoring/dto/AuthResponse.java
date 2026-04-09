// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.chris.health_monitoring.dto;

public class AuthResponse {
   private String token;
   private String role;
   private boolean mustResetPassword;
   private String username;

   public AuthResponse(String token, String role, boolean mustResetPassword, String username) {
      this.token = token;
      this.role = role;
      this.mustResetPassword = mustResetPassword;
      this.username = username;
   }

   public String getToken() {
      return this.token;
   }

   public String getRole() {
      return this.role;
   }

   public boolean isMustResetPassword() {
      return this.mustResetPassword;
   }

   public String getUsername() {
      return this.username;
   }
}
