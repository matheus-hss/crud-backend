package com.example.crud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_user", uniqueConstraints = {
        @UniqueConstraint(name = "ukemail", columnNames = "email"),
        @UniqueConstraint(name = "ukcpf", columnNames = "cpf")
})
public class User extends Person implements Serializable {
    private static final long serialVersionUID = 2923699399660607683L;

    @Id
    @GeneratedValue
    @Column(nullable = false, length = 16)
    private UUID id;
    @Column(nullable = false, length = 30)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany
    @JoinTable(name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fkuser_role_user")),
            inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fkuser_role_role")))
    private List<Role> roles;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
