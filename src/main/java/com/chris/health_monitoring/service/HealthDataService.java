// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.chris.health_monitoring.service;

import com.chris.health_monitoring.model.HealthData;
import com.chris.health_monitoring.repository.HealthDataRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HealthDataService {
   private final HealthDataRepository repository;

   public HealthDataService(HealthDataRepository repository) {
      this.repository = repository;
   }

   public HealthData saveData(HealthData data) {
      data.setTimestamp(LocalDateTime.now());
      return (HealthData)this.repository.save(data);
   }

   public List<HealthData> getAllData() {
      return this.repository.findAll();
   }

   public List<HealthData> getPatientHistory(Long patientId) {
      return this.repository.findByPatientIdOrderByTimestampDesc(patientId);
   }

   public void deleteData(Long id) {
      if (!this.repository.existsById(id)) {
         throw new RuntimeException("Record not found");
      } else {
         this.repository.deleteById(id);
      }
   }
}
