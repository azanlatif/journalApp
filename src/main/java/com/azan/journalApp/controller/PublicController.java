package com.azan.journalApp.controller;

import com.azan.journalApp.entity.User;
import com.azan.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public boolean createUser(@RequestBody User user) {
        userService.saveNewUser(user);
        return true;
    }
}
