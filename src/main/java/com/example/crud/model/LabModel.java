package com.example.crud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_lab", uniqueConstraints = {@UniqueConstraint(name = "ukemail", columnNames = "email")})
public class LabModel implements Serializable {
    private static final long serialVersionUID = -2284632754488970947L;

    @Id
    @GeneratedValue
    @Column(nullable = false, length = 16)
    private UUID id;
    @Column(nullable = false, length = 30)
    private String labName;
    @Column(nullable = false, length = 30)
    private String email;
    @Column(nullable = false, length = 11)
    private String tel;
    @Embedded
    private AddressModel address;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }
}
