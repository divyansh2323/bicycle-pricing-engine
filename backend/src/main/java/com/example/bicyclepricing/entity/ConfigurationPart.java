package com.example.bicyclepricing.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "configuration_parts")
public class ConfigurationPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "configuration_id")
    private CycleConfiguration configuration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "part_id")
    private Part part;

    private Integer quantity;

    public ConfigurationPart() {
    }

    public ConfigurationPart(Part part, Integer quantity) {
        this.part = part;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CycleConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(CycleConfiguration configuration) {
        this.configuration = configuration;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        if (part == null || part.getPrice() == null || quantity == null) {
            return BigDecimal.ZERO;
        }
        return part.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
