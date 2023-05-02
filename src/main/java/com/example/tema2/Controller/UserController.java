package com.example.tema2.Controller;

import com.example.tema2.Model.User;
import com.example.tema2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/createUser")
    public String createUser(){

        return "createUser";
    }

    record UserAux(String name, String username, String password, String role) {}
    @PostMapping("/createUser")
    public void createUser(@RequestBody UserAux userAux){
        String name = userAux.name;
        String username = userAux.username;
        String password = userAux.password;
        String role = userAux.role;
        userService.createAccount(name, username, password, role);
    }


}
