package com.example.bicyclepricing.dto;

import java.util.List;

public class CreateConfigurationRequest {
    private String name;
    private List<ConfigurationPartDto> parts;

    public CreateConfigurationRequest() {
    }

    public CreateConfigurationRequest(String name, List<ConfigurationPartDto> parts) {
        this.name = name;
        this.parts = parts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ConfigurationPartDto> getParts() {
        return parts;
    }

    public void setParts(List<ConfigurationPartDto> parts) {
        this.parts = parts;
    }
}
