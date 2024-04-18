package com.ali.service.impl;

import com.ali.controller.model.PlayerData;
import com.ali.persistence.model.PlayerDataEntity;
import com.ali.persistence.repo.PlayerDataRepository;
import com.ali.service.PlayerDataService;
import com.ali.service.error.DuplicateUserException;
import com.ali.service.error.GameNotExistingException;
import com.ali.service.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class PlayerDataServiceImpl implements PlayerDataService {
    @Autowired
    PlayerDataRepository repo;

    @Override
    public PlayerDataEntity register(PlayerData playerData) {
        String username = playerData.getUsername();
        String password = hashPassword(playerData.getPassword());
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
        String password = hashPassword(playerData.getPassword());
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
        if (!playerData.getPfp().equals("")) {
            entity.setPfp(playerData.getPfp());
        }
        if (!playerData.getHighlights().getVid1().equals("")) {
            entity.setHighlight1(playerData.getHighlights().getVid1());
        }
        if (!playerData.getHighlights().getVid2().equals("")) {
            entity.setHighlight2(playerData.getHighlights().getVid2());
        }
        if (!playerData.getHighlights().getVid3().equals("")) {
            entity.setHighlight3(playerData.getHighlights().getVid3());
        }
        if (!playerData.getHighlights().getVid4().equals("")) {
            entity.setHighlight4(playerData.getHighlights().getVid4());
        }
        if (!playerData.getHighlights().getVid5().equals("")) {
            entity.setHighlight5(playerData.getHighlights().getVid5());
        }
        entity = repo.save(entity);
        return entity;
    }

    @Override
    public List<PlayerDataEntity> listPlayers(String game, String currUsername) {
        List<PlayerDataEntity> playerDataEntityList;
        switch (game) {
            case "bs": playerDataEntityList = repo.findAllByBs(true); break;
            case "lol": playerDataEntityList = repo.findAllByLol(true); break;
            case "fort": playerDataEntityList = repo.findAllByFort(true); break;
            case "valo": playerDataEntityList = repo.findAllByValo(true); break;
            default: throw new GameNotExistingException();
        }
        for (int i = 0; i < playerDataEntityList.size(); i++) {
            if (playerDataEntityList.get(i).getUsername().equals(currUsername)) {
                playerDataEntityList.remove(i);
                continue;
            }
            PlayerDataEntity playerDataEntity = playerDataEntityList.get(i);
            playerDataEntity.setPassword(null);
            playerDataEntityList.set(i, playerDataEntity);
        }
            return playerDataEntityList;
    }

    @Override
    public PlayerDataEntity findPlayer(String username) {
        PlayerDataEntity player = repo.findByUsername(username);
        player.setPassword("");
        return player;
    }

    @Override
    public List<PlayerDataEntity> searchPlayers(String username) {
        List<PlayerDataEntity> players = repo.findByUsernameContaining(username);
        return players;
    }

    private static String hashPassword(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
