package com.arimattitoivonen.questlog.web;

import com.arimattitoivonen.questlog.domain.AppUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppUserController {

    private AppUserRepository appUserRepository;

    public AppUserController (AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

}
