package com.example.crud.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_state", uniqueConstraints = {
        @UniqueConstraint(name = "ukuf", columnNames = "uf"),
        @UniqueConstraint(name = "ukname", columnNames = "name")
})
public class State implements Serializable {
    private static final long serialVersionUID = 8566463614248045823L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, length = 16)
    private Long id;
    @Column(nullable = false, length = 2)
    private String uf;
    @Column(nullable = false, length = 50)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
