package com.example.crud.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LabDTO {
    @NotBlank
    @Size(max = 30)
    private String labName;
    @NotBlank
    @Size(max = 30)
    @Email
    private String email;
    @NotBlank
    @Size(max = 11)
    private String tel;
    @NotNull
    @Valid
    private AddressDTO address;

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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
