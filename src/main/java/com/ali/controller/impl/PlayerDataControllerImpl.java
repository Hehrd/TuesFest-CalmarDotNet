package com.ali.controller.impl;

import com.ali.controller.PlayerDataController;
import com.ali.controller.model.PlayerData;
import com.ali.persistence.model.PlayerDataEntity;
import com.ali.service.error.DuplicateUserException;
import com.ali.service.error.UserNotFoundException;
import com.ali.service.error.UserUnauthorizedException;
import com.ali.service.impl.PlayerDataServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api/teamplayer")
public class PlayerDataControllerImpl implements PlayerDataController {

    @Autowired
    PlayerDataServiceImpl playerDataService;

    @Override
    public ModelAndView showMainPage() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @Override
    public ModelAndView showLoginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @Override
    public ModelAndView showRegisterPage() {
        ModelAndView mav = new ModelAndView("register");
        return mav;
    }

    @Override
    public ModelAndView showFrontpage(HttpSession session) {
        if(session.getAttribute("username") == null) {
            ModelAndView mav = new ModelAndView("index");
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("frontpage");
            return mav;
        }
    }

    @Override
    public ModelAndView showSettingsPage(HttpSession session) {
        if(session.getAttribute("username") == null) {
            ModelAndView mav = new ModelAndView("index");
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("settings");
            return mav;
        }
    }

    @Override
    public ResponseEntity<PlayerDataEntity> register(HttpSession session, PlayerData playerData) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<PlayerDataEntity> responseEntity;
        try {
            PlayerDataEntity entity = playerDataService.register(playerData);
            responseEntity = new ResponseEntity<>(entity, headers,HttpStatus.CREATED);
            session.setAttribute("username", playerData.getUsername());
        } catch (DuplicateUserException e) {
            responseEntity = new ResponseEntity<>(null, headers,HttpStatus.BAD_REQUEST);
            session.setAttribute("username", playerData.getUsername());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<PlayerDataEntity> login(HttpSession session, PlayerData playerData) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<PlayerDataEntity> responseEntity;
        try {
            PlayerDataEntity entity = playerDataService.login(playerData);
            responseEntity = new ResponseEntity<>(entity, headers, HttpStatus.OK);
            session.setAttribute("username", playerData.getUsername());
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<>(null, headers,HttpStatus.UNAUTHORIZED);
            session.setAttribute("username", null);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<PlayerDataEntity> updateData(HttpSession session, PlayerData playerData) {
        String currUsername = (String)session.getAttribute("username");
        playerData.setUsername(currUsername);
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<PlayerDataEntity> responseEntity = new ResponseEntity<>(playerDataService.updateData(playerData), HttpStatus.OK) ;
        if (currUsername == null) {
            return responseEntity = new ResponseEntity<>(playerDataService.updateData(playerData), HttpStatus.UNAUTHORIZED) ;
        }
        playerData.setUsername(currUsername);
        responseEntity = new ResponseEntity<>(playerDataService.updateData(playerData), headers, HttpStatus.OK) ;
        return responseEntity;
    }

    @Override
    public ModelAndView listPlayers(String game) {
        List<PlayerDataEntity> playerDataEntityList = playerDataService.listPlayers(game);
        ModelAndView mav = new ModelAndView("list");
        return mav;
    }
}
