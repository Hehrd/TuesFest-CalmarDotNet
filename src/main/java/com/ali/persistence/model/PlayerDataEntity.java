package com.ali.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "playerdata")
@Data
public class PlayerDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private boolean bs;

    @Column
    private boolean valo;

    @Column
    private boolean lol;

    @Column
    private boolean fort;

    @Column
    private String discord;

    @Column
    private String aboutme;

    public PlayerDataEntity(String username, String password) {
        this.username = username;
        this.password = password;
        this.bs = false;
        this.valo = false;
        this.lol = false;
        this.fort = false;
        this.discord = "";
        this.aboutme = "";
    }
}
