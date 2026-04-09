package com.chris.health_monitoring.repository;

import com.chris.health_monitoring.model.HealthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthDataRepository extends JpaRepository<HealthData, Long> {

    List<HealthData> findByPatientIdOrderByTimestampDesc(Long patientId);

    List<HealthData> findTopByPatientIdOrderByTimestampDesc(Long patientId);
}