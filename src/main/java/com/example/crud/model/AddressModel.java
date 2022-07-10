package com.example.crud.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigInteger;

@Embeddable
public class AddressModel implements Serializable {
    private static final long serialVersionUID = 4382639141950226761L;

    @Column(nullable = false, length = 8)
    private String zipCode;
    @Column(nullable = false, length = 50)
    private String street;
    @Column(nullable = false, precision = 5)
    private BigInteger num;
    @Column(nullable = false, length = 50)
    private String neighborhood;
    @Column(nullable = false, length = 50)
    private String city;
    @Column(nullable = false, length = 2)
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

    public BigInteger getNum() {
        return num;
    }

    public void setNum(BigInteger num) {
        this.num = num;
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
