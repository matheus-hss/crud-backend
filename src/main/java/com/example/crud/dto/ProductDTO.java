package com.example.crud.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ProductDTO {
    @NotBlank
    @Size(max = 30)
    private String name;
    @NotNull
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "99999.99")
    private BigDecimal value;
    @NotBlank
    @Size(max = 100)
    private String description;

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
