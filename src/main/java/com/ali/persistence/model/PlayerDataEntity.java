package com.ali.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "playerdata")
@Data
@NoArgsConstructor
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

    @Column(columnDefinition = "TEXT")
    private String pfp;

    @Column(columnDefinition = "TEXT")
    private String highlight1;

    @Column(columnDefinition = "TEXT")
    private String highlight2;

    @Column(columnDefinition = "TEXT")
    private String highlight3;

    @Column(columnDefinition = "TEXT")
    private String highlight4;

    @Column(columnDefinition = "TEXT")
    private String highlight5;

    public PlayerDataEntity(String username, String password) {
        this.username = username;
        this.password = password;
        this.bs = false;
        this.valo = false;
        this.lol = false;
        this.fort = false;
        this.discord = "";
        this.aboutme = "";
        this.pfp = "";
    }
}
