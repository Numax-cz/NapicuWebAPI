package com.napicu.napicuwebapi.Database;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "NapicuBiosWaitList")
public class NapicuBiosWaitListDatabase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Email
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }
}
