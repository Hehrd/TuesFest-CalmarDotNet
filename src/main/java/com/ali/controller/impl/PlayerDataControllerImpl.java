package com.ali.controller.impl;

import com.ali.controller.PlayerDataController;
import com.ali.controller.model.PlayerData;
import com.ali.persistence.service.impl.PlayerDataServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/teamplayer")
public class PlayerDataControllerImpl implements PlayerDataController {

    @Autowired
    PlayerDataServiceImpl service;

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
    public ModelAndView showSettingsPage() {
        ModelAndView mav = new ModelAndView("settings");
        return mav;
    }

    @Override
    public HttpStatus register(PlayerData playerData) {
//        HttpStatus response = service.register(playerData);
//        if (response == HttpStatus.OK) {
//            session.setAttribute("username", playerData.getUsername());
//        } else {
//            session.setAttribute("username", null);
//        }
        return service.register(playerData);
    }

    @Override
    public HttpStatus login(HttpSession session, PlayerData playerData) {
        HttpStatus response = service.login(playerData);
        if (response == HttpStatus.OK) {
            session.setAttribute("username", playerData.getUsername());
        } else {
            session.setAttribute("username", null);
        }
        return response;
    }

    @Override
    public HttpStatus updateData(HttpSession session, PlayerData playerData) {
        String currUsername = (String)session.getAttribute("username");
        if (currUsername == null) {
            return HttpStatus.UNAUTHORIZED;
        }
        playerData.setUsername(currUsername);
        service.updateData(playerData);
        return HttpStatus.OK;
    }
}
