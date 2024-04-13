package com.ali.service;

import com.ali.controller.model.PlayerData;
import com.ali.persistence.model.PlayerDataEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface PlayerDataService {
    PlayerDataEntity register(PlayerData playerData);

    PlayerDataEntity login(PlayerData playerData);

    PlayerDataEntity updateData(PlayerData playerData);

    List<PlayerDataEntity> listPlayers(String game, String currUsername);
}
