package com.example.crud.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ProductDTO {
    @NotBlank
    @Size(max = 30)
    private String productName;
    @NotNull
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "99999.99")
    private BigDecimal productValue;
    @NotBlank
    @Size(max = 100)
    private String description;

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
}
