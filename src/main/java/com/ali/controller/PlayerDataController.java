package com.ali.controller;

import com.ali.controller.model.PlayerData;
import com.ali.controller.model.Username;
import com.ali.persistence.model.PlayerDataEntity;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface PlayerDataController {

    @RequestMapping
    @ResponseStatus(HttpStatus.OK)
    ModelAndView showMainPage();

    @RequestMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    ModelAndView showLoginPage();

    @RequestMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    ModelAndView showRegisterPage();

    @RequestMapping("/frontpage")
    @ResponseStatus(HttpStatus.OK)
    ModelAndView showFrontpage(HttpSession session);

    @RequestMapping("/settings")
    @ResponseStatus(HttpStatus.OK)
    ModelAndView showSettingsPage(HttpSession session);

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PlayerDataEntity> register(HttpSession session, @RequestBody PlayerData playerData);

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PlayerDataEntity> login(HttpSession session, @RequestBody PlayerData playerData);

    @RequestMapping(value = "/settings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PlayerDataEntity> updateData(HttpSession session, @RequestBody PlayerData playerData);

    @RequestMapping(value = "/playerlist/{game}")
    @ResponseStatus(HttpStatus.OK)
    ModelAndView listPlayers(HttpSession session, @PathVariable String game);

    @RequestMapping(value = "/profile/{username}")
    @ResponseStatus(HttpStatus.OK)
    ModelAndView showPlayerPage(@PathVariable String username);

    @RequestMapping(value = "/frontpage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ModelAndView searchPlayers(@RequestBody Username username);
}
