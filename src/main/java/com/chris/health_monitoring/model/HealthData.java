// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.chris.health_monitoring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(
   name = "health_data"
)
public class HealthData {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   private Long patientId;
   private int heartRate;
   private double temperature;
   private LocalDateTime timestamp;

   public HealthData() {
   }

   public HealthData(Long patientId, int heartRate, double temperature) {
      this.patientId = patientId;
      this.heartRate = heartRate;
      this.temperature = temperature;
   }

   public Long getId() {
      return this.id;
   }

   public Long getPatientId() {
      return this.patientId;
   }

   public void setPatientId(Long patientId) {
      this.patientId = patientId;
   }

   public int getHeartRate() {
      return this.heartRate;
   }

   public void setHeartRate(int heartRate) {
      this.heartRate = heartRate;
   }

   public double getTemperature() {
      return this.temperature;
   }

   public void setTemperature(double temperature) {
      this.temperature = temperature;
   }

   public LocalDateTime getTimestamp() {
      return this.timestamp;
   }

   public void setTimestamp(LocalDateTime timestamp) {
      this.timestamp = timestamp;
   }
}
