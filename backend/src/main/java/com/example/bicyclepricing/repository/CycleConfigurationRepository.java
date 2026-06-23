package com.example.bicyclepricing.repository;

import com.example.bicyclepricing.entity.CycleConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleConfigurationRepository extends JpaRepository<CycleConfiguration, Long> {
}
