package com.example.bicyclepricing.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cycle_configurations")
public class CycleConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "configuration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConfigurationPart> parts = new ArrayList<>();

    public CycleConfiguration() {
    }

    public CycleConfiguration(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ConfigurationPart> getParts() {
        return parts;
    }

    public void setParts(List<ConfigurationPart> parts) {
        this.parts = parts;
    }

    public BigDecimal getTotalPrice() {
        return parts.stream()
                .map(ConfigurationPart::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
