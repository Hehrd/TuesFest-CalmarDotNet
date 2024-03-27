package com.ali.persistence.service.impl;

import com.ali.controller.model.PlayerData;
import com.ali.persistence.model.PlayerDataEntity;
import com.ali.persistence.repo.PlayerDataRepository;
import com.ali.persistence.service.PlayerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PlayerDataServiceImpl implements PlayerDataService {
    @Autowired
    PlayerDataRepository repo;

    @Override
    public HttpStatus register(PlayerData playerData) {
        String username = playerData.getUsername();
        String password = playerData.getPassword();
        if (repo.existsByUsername(username) && repo.existsByPassword(password)) {
            return HttpStatus.BAD_REQUEST;
        }
        PlayerDataEntity entity = new  PlayerDataEntity(username, password);
        repo.save(entity);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus login(PlayerData playerData) {
        String username = playerData.getUsername();
        String password = playerData.getPassword();
        if (repo.existsByUsername(username) && repo.existsByPassword(password)) {
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public HttpStatus updateData(PlayerData playerData) {
        PlayerDataEntity entity = repo.findByUsername(playerData.getUsername());
        entity.setValo(playerData.isValo());
        entity.setLol(playerData.isLol());
        entity.setFort(playerData.isFort());
        entity.setBs(playerData.isBs());
        entity.setDiscord(playerData.getDiscord());
        entity.setAboutme(playerData.getAboutme());
        repo.save(entity);
        return HttpStatus.OK;
    }
}
