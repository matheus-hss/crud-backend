package com.example.crud.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_city")
public class City implements Serializable {
    private static final long serialVersionUID = 2944565779255336322L;

    @Id
    @GeneratedValue
    @Column(nullable = false, length = 16)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @ManyToOne
    @JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fkcity_state"))
    private State state;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
