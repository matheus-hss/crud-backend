package com.example.crud.dto;

import javax.validation.constraints.*;
import java.math.BigInteger;

public class AddressDTO {
    @NotBlank
    @Size(max = 8)
    private String zipCode;
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
    @NotBlank
    @Size(max = 50)
    private String city;
    @NotBlank
    @Size(max = 2)
    private String state;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
