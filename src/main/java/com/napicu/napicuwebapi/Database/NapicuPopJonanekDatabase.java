package com.napicu.napicuwebapi.Database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NapicuPopJonanek")
public class NapicuPopJonanekDatabase {
    @Id
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
