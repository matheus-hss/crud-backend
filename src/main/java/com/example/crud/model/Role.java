package com.example.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_role", uniqueConstraints = {@UniqueConstraint(name = "ukname", columnNames = "name")})
public class Role implements Serializable, GrantedAuthority {
    private static final long serialVersionUID = 5007148055431859198L;

    @Id
    @GeneratedValue
    @Column(nullable = false, length = 16)
    private Long id;
    @Column(nullable = false, length = 10)
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Transient
    @Override
    public String getAuthority() {
        return name;
    }
}
