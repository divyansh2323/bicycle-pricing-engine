package com.example.bicyclepricing.repository;

import com.example.bicyclepricing.entity.ConfigurationPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationPartRepository extends JpaRepository<ConfigurationPart, Long> {
}
