package com.example.crud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="tb_product")
public class Product implements Serializable {
    private static final long serialVersionUID = -7430992601373677788L;

    @Id
    @GeneratedValue
    @Column(nullable = false, length = 16)
    private UUID id;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal value;
    @Column(nullable = false, length = 100)
    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
