package com.hacktues.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "users")
@Entity
public class CredentialEntity {

    public CredentialEntity() {
        this.username = "";
        this.password = "";
        this.valorant = false;
        this.leagueoflegends = false;
        this.rocketleague = false;
        this.brawlstars = false;
        this.discord = "";
        this.aboutme = "";
    }

    public CredentialEntity(String username, String password) {
        this.username = username;
        this.password = password;
        this.valorant = false;
        this.leagueoflegends = false;
        this.rocketleague = false;
        this.brawlstars = false;
        this.discord = "";
        this.aboutme = "";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column (nullable = false)
    private boolean valorant;

    @Column (nullable = false)
    private boolean leagueoflegends;

    @Column (nullable = false)
    private boolean rocketleague;

    @Column (nullable = false)
    private boolean brawlstars;

    @Column (nullable = false)
    private String discord;

    @Column (nullable = false)
    private String aboutme;
}
