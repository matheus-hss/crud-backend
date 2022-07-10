package com.example.crud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="tb_product")
public class ProductModel implements Serializable {
    private static final long serialVersionUID = -7430992601373677788L;

    @Id
    @GeneratedValue
    @Column(nullable = false, length = 16)
    private UUID id;
    @Column(nullable = false, length = 30)
    private String productName;
    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal productValue;
    @Column(nullable = false, length = 100)
    private String description;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "lab_id", nullable = false, foreignKey = @ForeignKey(name = "fkproduct_lab"))
    private LabModel lab;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductValue() {
        return productValue;
    }

    public void setProductValue(BigDecimal productValue) {
        this.productValue = productValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LabModel getLab() {
        return lab;
    }

    public void setLab(LabModel lab) {
        this.lab = lab;
    }
}
