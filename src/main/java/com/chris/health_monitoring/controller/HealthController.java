
package com.chris.health_monitoring.controller;

import com.chris.health_monitoring.model.HealthData;
import com.chris.health_monitoring.service.HealthDataService;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/health"})
@CrossOrigin({"*"})
public class HealthController {
   private final HealthDataService service;

   public HealthController(HealthDataService service) {
      this.service = service;
   }

   @PostMapping({"/data"})
   public HealthData saveData(@RequestBody HealthData data) {
      return this.service.saveData(data);
   }

   @GetMapping({"/data"})
   public List<HealthData> getAllData() {
      return this.service.getAllData();
   }

   @GetMapping({"/data/patient/{patientId}"})
   public List<HealthData> getPatientHistory(@PathVariable Long patientId) {
      return this.service.getPatientHistory(patientId);
   }

   @DeleteMapping({"/data/{id}"})
   public ResponseEntity<Map<String, String>> deleteData(@PathVariable Long id) {
      try {
         this.service.deleteData(id);
         return ResponseEntity.ok(Map.of("message", "Record deleted"));
      } catch (RuntimeException e) {
         return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
      }
   }
}
