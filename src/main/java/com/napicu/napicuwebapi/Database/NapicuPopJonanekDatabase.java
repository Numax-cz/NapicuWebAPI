package com.napicu.napicuwebapi.Database;

import javax.persistence.*;

@Entity
@Table(name = "NapicuPopJonanek")
public class NapicuPopJonanekDatabase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer counter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
