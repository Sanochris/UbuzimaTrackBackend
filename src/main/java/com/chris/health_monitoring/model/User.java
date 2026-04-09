
package com.chris.health_monitoring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
   name = "users",
   uniqueConstraints = {@UniqueConstraint(
   columnNames = {"username"}
), @UniqueConstraint(
   columnNames = {"email"}
)}
)
public class User {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @Column(
      nullable = false,
      unique = true
   )
   private String username;
   @JsonIgnore
   @Column(
      nullable = false
   )
   private String password;
   @Column(
      nullable = false
   )
   private String role;
   @Column(
      nullable = false,
      unique = true
   )
   private String email;
   @Column(
      nullable = false
   )
   private boolean mustResetPassword = false;

   public User() {
   }

   public User(String username, String password, String role, String email, boolean mustResetPassword) {
      this.username = username;
      this.password = password;
      this.role = role;
      this.email = email;
      this.mustResetPassword = mustResetPassword;
   }

   public Long getId() {
      return this.id;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getRole() {
      return this.role;
   }

   public void setRole(String role) {
      this.role = role;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public boolean isMustResetPassword() {
      return this.mustResetPassword;
   }

   public void setMustResetPassword(boolean v) {
      this.mustResetPassword = v;
   }
}
