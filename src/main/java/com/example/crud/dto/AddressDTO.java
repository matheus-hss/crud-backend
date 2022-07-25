package com.example.crud.dto;

import com.example.crud.model.City;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigInteger;

public class AddressDTO {
    @NotBlank
    @Size(max = 50)
    private String street;
    @NotNull
    @Min(value = 1)
    @Max(value = 99999)
    private BigInteger number;
    @NotBlank
    @Size(max = 50)
    private String neighborhood;
    @NotNull
    @Valid
    private CityDTO city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }
}
