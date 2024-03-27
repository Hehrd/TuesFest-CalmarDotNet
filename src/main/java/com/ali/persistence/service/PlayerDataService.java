package com.ali.persistence.service;

import com.ali.controller.model.PlayerData;
import com.ali.persistence.model.PlayerDataEntity;
import org.springframework.http.HttpStatus;

public interface PlayerDataService {
    HttpStatus register(PlayerData playerData);

    HttpStatus login(PlayerData playerData);

    HttpStatus updateData(PlayerData playerData);
}
