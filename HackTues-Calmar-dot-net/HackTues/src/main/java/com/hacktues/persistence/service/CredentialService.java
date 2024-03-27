package com.hacktues.persistence.service;

import com.hacktues.controller.model.Credential;
import com.hacktues.persistence.model.CredentialEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface CredentialService {
    ModelAndView showPage();

    HttpStatus login(Credential credentials);

    HttpStatus register(Credential credentials);

    HttpStatus updateData(Credential credential);

    String getTags(String username);

    Iterable<CredentialEntity> listAll();

//    List<CredentialEntity> listBrawlStars();
//    List<CredentialEntity> listValorant();
//    List<CredentialEntity> listLeagueoflegends();
//    List<CredentialEntity> listRocketLeague();

}
