package com.example.bicyclepricing;

import com.example.bicyclepricing.dto.ConfigurationPartDto;
import com.example.bicyclepricing.dto.CreateConfigurationRequest;
import com.example.bicyclepricing.entity.CycleConfiguration;
import com.example.bicyclepricing.entity.Part;
import com.example.bicyclepricing.exception.ResourceNotFoundException;
import com.example.bicyclepricing.repository.ConfigurationPartRepository;
import com.example.bicyclepricing.repository.CycleConfigurationRepository;
import com.example.bicyclepricing.repository.PartRepository;
import com.example.bicyclepricing.service.CycleConfigurationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CycleConfigurationServiceTests {

    private CycleConfigurationRepository configurationRepository;
    private PartRepository partRepository;
    private ConfigurationPartRepository configurationPartRepository;
    private CycleConfigurationService configurationService;

    @BeforeEach
    void setUp() {
        configurationRepository = Mockito.mock(CycleConfigurationRepository.class);
        partRepository = Mockito.mock(PartRepository.class);
        configurationPartRepository = Mockito.mock(ConfigurationPartRepository.class);
        configurationService = new CycleConfigurationService(configurationRepository, partRepository, configurationPartRepository);
    }

    @Test
    void createConfiguration_shouldComputeTotalPrice() {
        Part part = new Part("Frame", "Structure", new BigDecimal("200.00"));
        part.setId(1L);
        when(partRepository.findById(1L)).thenReturn(Optional.of(part));
        when(configurationRepository.save(any(CycleConfiguration.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CreateConfigurationRequest request = new CreateConfigurationRequest("Road Bike", List.of(new ConfigurationPartDto(1L, 2)));
        var dto = configurationService.create(request);

        assertThat(dto.getName()).isEqualTo("Road Bike");
        assertThat(dto.getTotalPrice()).isEqualByComparingTo(new BigDecimal("400.00"));
    }

    @Test
    void createConfiguration_whenPartMissing_shouldThrow() {
        when(partRepository.findById(99L)).thenReturn(Optional.empty());
        CreateConfigurationRequest request = new CreateConfigurationRequest("Broken", List.of(new ConfigurationPartDto(99L, 1)));
        assertThrows(ResourceNotFoundException.class, () -> configurationService.create(request));
    }
}
