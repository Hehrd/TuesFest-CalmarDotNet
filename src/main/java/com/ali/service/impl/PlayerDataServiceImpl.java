package com.ali.service.impl;

import com.ali.controller.model.PlayerData;
import com.ali.persistence.model.PlayerDataEntity;
import com.ali.persistence.repo.PlayerDataRepository;
import com.ali.service.PlayerDataService;
import com.ali.service.error.DuplicateUserException;
import com.ali.service.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerDataServiceImpl implements PlayerDataService {
    @Autowired
    PlayerDataRepository repo;

    @Override
    public PlayerDataEntity register(PlayerData playerData) {
        String username = playerData.getUsername();
        String password = playerData.getPassword();
        if (repo.existsByUsername(username)) {
            throw new DuplicateUserException();
        }
        PlayerDataEntity entity = new  PlayerDataEntity(username, password);
        entity = repo.save(entity);
        return entity;
    }

    @Override
    public PlayerDataEntity login(PlayerData playerData) {
        String username = playerData.getUsername();
        String password = playerData.getPassword();
        PlayerDataEntity entity = repo.findByUsernameAndPassword(username, password);
        if (entity != null) {
            entity = repo.findByUsername(username);
            return entity;
        }
        throw new UserNotFoundException();
    }

    @Override
    public PlayerDataEntity updateData(PlayerData playerData) {
        PlayerDataEntity entity = repo.findByUsername(playerData.getUsername());
        entity.setValo(playerData.isValo());
        entity.setLol(playerData.isLol());
        entity.setFort(playerData.isFort());
        entity.setBs(playerData.isBs());
        entity.setDiscord(playerData.getDiscord());
        entity.setAboutme(playerData.getAboutme());
        entity = repo.save(entity);
        return entity;
    }

    @Override
    public List<PlayerDataEntity> listPlayers(String game) {
        List<PlayerDataEntity> playerDataEntityList = repo.findAllByBs(true);
        return playerDataEntityList;
    }
}
