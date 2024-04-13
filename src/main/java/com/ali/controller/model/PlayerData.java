package com.ali.controller.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PlayerData {
    private int id;

    private String username;

    private String password;

    private boolean bs;

    private boolean valo;

    private boolean lol;

    private boolean fort;

    private String discord;

    private String aboutme;

    private String pfp;
}
