package com.ali.controller.impl;

import com.ali.controller.PlayerDataController;
import com.ali.controller.model.PlayerData;
import com.ali.controller.model.Username;
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

import java.util.ArrayList;
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
            String username = (String)session.getAttribute("username");
            PlayerDataEntity player = playerDataService.findPlayer(username);
            mav.addObject("player" ,player);
            String pfp = "data:image/jpg;base64," + player.getPfp();
            String highlightData1 = "data:video/mp4;base64," + player.getHighlight1();
            String highlightData2 = "data:video/mp4;base64," + player.getHighlight2();
            String highlightData3 = "data:video/mp4;base64," + player.getHighlight3();
            String highlightData4 = "data:video/mp4;base64," + player.getHighlight4();
            String highlightData5 = "data:video/mp4;base64," + player.getHighlight5();
            mav.addObject("pfp", pfp);
            mav.addObject("highlight1", highlightData1);
            mav.addObject("highlight2", highlightData2);
            mav.addObject("highlight3", highlightData3);
            mav.addObject("highlight4", highlightData4);
            mav.addObject("highlight5", highlightData5);
            return mav;
        }
    }

    @Override
    public ResponseEntity<PlayerDataEntity> register(HttpSession session, PlayerData playerData) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<PlayerDataEntity> responseEntity;
        playerData.setUsername(playerData.getUsername().toLowerCase());
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
        playerData.setUsername(playerData.getUsername().toLowerCase());
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
        ResponseEntity<PlayerDataEntity> responseEntity;
        if (currUsername == null) {
            responseEntity = new ResponseEntity<>(playerDataService.updateData(playerData), HttpStatus.UNAUTHORIZED) ;
            return responseEntity;
        }
        responseEntity = new ResponseEntity<>(playerDataService.updateData(playerData), headers, HttpStatus.OK) ;
        return responseEntity;
    }

    @Override
    public ModelAndView listPlayers(HttpSession session, String game) {
        String currUsername = (String)session.getAttribute("username");
        Iterable<PlayerDataEntity> playerDataEntityList = playerDataService.listPlayers(game, currUsername);
        ModelAndView mav = new ModelAndView("list");
        mav.addObject("players", playerDataEntityList);
        return mav;
    }

    @Override
    public ModelAndView showPlayerPage(String username) {
        ModelAndView mav = new ModelAndView("player");
        PlayerDataEntity player = playerDataService.findPlayer(username);
        String imageData = "data:image/jpg;base64," + player.getPfp();
        String highlightData1 = "data:video/mp4;base64," + player.getHighlight1();
        String highlightData2 = "data:video/mp4;base64," + player.getHighlight2();
        String highlightData3 = "data:video/mp4;base64," + player.getHighlight3();
        String highlightData4 = "data:video/mp4;base64," + player.getHighlight4();
        String highlightData5 = "data:video/mp4;base64," + player.getHighlight5();
        mav.addObject("player", player);
        mav.addObject("imageData", imageData);
        mav.addObject("highlight1", highlightData1);
        mav.addObject("highlight2", highlightData2);
        mav.addObject("highlight3", highlightData3);
        mav.addObject("highlight4", highlightData4);
        mav.addObject("highlight5", highlightData5);
        return mav;
    }

    @Override
    public ModelAndView searchPlayers(String username) {
        List<PlayerDataEntity> players = playerDataService.searchPlayers(username);
        List<String> usernames = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            usernames.add(i, players.get(i).getUsername());
        }
        ModelAndView mav = new ModelAndView("frontpage");
        mav.addObject("username", username);
        mav.addObject("users", usernames);
        return mav;
    }

    @Override
    public ResponseEntity<PlayerDataEntity> logOut(HttpSession session) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity responseEntity = new ResponseEntity(null, headers, HttpStatus.OK);
        session.setAttribute("username", null);
        return responseEntity;
    }
}
