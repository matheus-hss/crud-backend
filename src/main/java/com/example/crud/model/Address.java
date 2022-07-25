package com.example.crud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Embeddable
public class Address implements Serializable {
    private static final long serialVersionUID = 4382639141950226761L;

    @Column(nullable = false, length = 50)
    private String street;
    @Column(nullable = false, precision = 5)
    private BigInteger number;
    @Column(nullable = false, length = 50)
    private String neighborhood;
    @OneToOne
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "fkuser_city"))
    private City city;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
