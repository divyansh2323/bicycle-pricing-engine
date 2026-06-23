package com.example.bicyclepricing.service;

import com.example.bicyclepricing.dto.ConfigurationPartDto;
import com.example.bicyclepricing.dto.CycleConfigurationDto;
import com.example.bicyclepricing.dto.CreateConfigurationRequest;
import com.example.bicyclepricing.dto.PartPriceBreakdownDto;
import com.example.bicyclepricing.entity.ConfigurationPart;
import com.example.bicyclepricing.entity.CycleConfiguration;
import com.example.bicyclepricing.entity.Part;
import com.example.bicyclepricing.exception.ResourceNotFoundException;
import com.example.bicyclepricing.repository.ConfigurationPartRepository;
import com.example.bicyclepricing.repository.CycleConfigurationRepository;
import com.example.bicyclepricing.repository.PartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CycleConfigurationService {

    private final CycleConfigurationRepository configurationRepository;
    private final PartRepository partRepository;
    private final ConfigurationPartRepository configurationPartRepository;

    public CycleConfigurationService(CycleConfigurationRepository configurationRepository,
                                     PartRepository partRepository,
                                     ConfigurationPartRepository configurationPartRepository) {
        this.configurationRepository = configurationRepository;
        this.partRepository = partRepository;
        this.configurationPartRepository = configurationPartRepository;
    }

    public List<CycleConfigurationDto> findAll() {
        return configurationRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public CycleConfigurationDto findById(Long id) {
        return configurationRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Configuration not found with id " + id));
    }

    @Transactional
    public CycleConfigurationDto create(CreateConfigurationRequest request) {
        CycleConfiguration configuration = new CycleConfiguration(request.getName());
        configuration = configurationRepository.save(configuration);
        persistParts(configuration, request.getParts());
        return mapToDto(configuration);
    }

    @Transactional
    public CycleConfigurationDto update(Long id, CreateConfigurationRequest request) {
        CycleConfiguration configuration = configurationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuration not found with id " + id));
        configuration.setName(request.getName());
        configuration.getParts().clear();
        configurationRepository.save(configuration);
        persistParts(configuration, request.getParts());
        return mapToDto(configuration);
    }

    public void delete(Long id) {
        configurationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuration not found with id " + id));
        configurationRepository.deleteById(id);
    }

    public List<PartPriceBreakdownDto> getPriceBreakdown(Long id) {
        CycleConfiguration configuration = configurationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuration not found with id " + id));

        return configuration.getParts().stream()
                .map(this::mapToBreakdown)
                .collect(Collectors.toList());
    }

    private void persistParts(CycleConfiguration configuration, List<ConfigurationPartDto> partDtos) {
        if (partDtos == null) {
            return;
        }
        List<ConfigurationPart> createdParts = partDtos.stream()
                .map(dto -> {
                    Part part = partRepository.findById(dto.getPartId())
                            .orElseThrow(() -> new ResourceNotFoundException("Part not found with id " + dto.getPartId()));
                    ConfigurationPart configPart = new ConfigurationPart(part, dto.getQuantity());
                    configPart.setConfiguration(configuration);
                    return configPart;
                })
                .collect(Collectors.toList());
        configuration.getParts().addAll(createdParts);
        configurationRepository.save(configuration);
        configurationPartRepository.saveAll(createdParts);
    }

    private CycleConfigurationDto mapToDto(CycleConfiguration configuration) {
        List<ConfigurationPartDto> partDtos = configuration.getParts().stream()
                .map(part -> new ConfigurationPartDto(part.getPart().getId(), part.getQuantity()))
                .collect(Collectors.toList());
        return new CycleConfigurationDto(configuration.getId(), configuration.getName(), partDtos, configuration.getTotalPrice());
    }

    private PartPriceBreakdownDto mapToBreakdown(ConfigurationPart configPart) {
        Part part = configPart.getPart();
        BigDecimal totalPrice = configPart.getTotalPrice();
        return new PartPriceBreakdownDto(part.getId(), part.getName(), part.getCategory(), part.getPrice(), configPart.getQuantity(), totalPrice);
    }
}
