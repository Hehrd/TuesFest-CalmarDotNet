package com.ali.controller;

import com.ali.controller.model.PlayerData;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/settings")
    @ResponseStatus(HttpStatus.OK)
    ModelAndView showSettingsPage();

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    HttpStatus register(HttpSession session, PlayerData playerData);

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    HttpStatus login(HttpSession session, PlayerData playerData);

    @RequestMapping(value = "/settings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    HttpStatus updateData(HttpSession session, PlayerData playerData);
}
