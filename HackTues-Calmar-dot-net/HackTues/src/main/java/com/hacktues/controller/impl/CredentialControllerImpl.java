package com.hacktues.controller.impl;

import com.hacktues.controller.CredentialController;
import com.hacktues.controller.model.Credential;
import com.hacktues.persistence.model.CredentialEntity;
import com.hacktues.persistence.service.impl.CredentialServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/hacktues")
public class CredentialControllerImpl implements CredentialController {
    @Autowired
    private CredentialServiceImpl service;
    @Override
    public ModelAndView showMainPage() {
        return service.showPage();
    }

    @Override
    public ModelAndView showSettings(HttpSession session) {
        String viewName;
        String username = (String)session.getAttribute("username");
        if (username == null) {
            viewName = "index";
        } else {
            viewName = "settings";
        }

        ModelAndView mav = new ModelAndView(viewName);
        return mav;
    }

    @Override
    public ModelAndView showLogin() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @Override
    public ModelAndView showSignup() {
        ModelAndView mav = new ModelAndView("signUp");
        return mav;
    }

    @Override
    public ModelAndView showFrontpage(HttpSession session) {
        String viewName;
        if ((String)session.getAttribute("username") == null) {
            viewName = "index";
        } else {
            viewName = "frontpage";
        }

        ModelAndView mav = new ModelAndView(viewName);
        return mav;
    }

    @Override
    public HttpStatus login(HttpSession session, Credential credentials) {
        //String username = (String)session.getAttribute("username");
        HttpStatus result = service.login(credentials);
        if (result == HttpStatus.OK) {
            session.setAttribute("username", credentials.getUsername());
        }
        return result;
    }

    @Override
    public HttpStatus register(HttpSession session, Credential credentials) {
            if (service.register(credentials) == HttpStatus.OK) {
                session.setAttribute("username", credentials.getUsername());
                return HttpStatus.OK;
            }

        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public HttpStatus updateData(HttpSession session, Credential credential) {
        String username = (String)session.getAttribute("username");
        if (username == null) {
            return HttpStatus.UNAUTHORIZED;
        }
        credential.setUsername(username);
        service.updateData(credential);
        return HttpStatus.OK;
    }

    @Override
    public String getTags(HttpSession session) {
        String username = (String)session.getAttribute("username");
        if (username == null) {
            return "{}";
        }
        String result = service.getTags(username);
        return result;
    }

    @Override
    public Iterable<CredentialEntity> listAll() {
        return service.listAll();
    }

//    @Override
//    public List<CredentialEntity> listBrawlStars() {
//        return service.listBrawlStars();
//    }

//    @Override
//    public List<CredentialEntity> listValorant() {
//        return service.listValorant();
//
//    }
//
//    @Override
//    public List<CredentialEntity> listLeagueOfLegends() {
//        return service.listLeagueoflegends();
//
//    }
//
//    @Override
//    public List<CredentialEntity> listRocketLeague() {
//        return service.listRocketLeague();
//    }

    @Override
    public HttpStatus test(HttpSession session) {
        Credential credential = new Credential();
        credential.setDiscord("ala bala");
        return updateData(session, credential);
    }


}
