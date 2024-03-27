package com.hacktues.controller.model;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;

@Data
public class Credential {
    private String username;
    private String password;
    private boolean valorant;
    private boolean league_of_legends;
    private boolean rocket_league;
    private boolean brawl_stars;
    private String discord;
    private String about_me;
}
