package br.edu.ulbra.gestaovinhos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UserController
{

    @GetMapping("/novo")
    public String newUserForm(){
        return "user/new";
    }

    @PostMapping("/novo")
    public String newUser(){
        return "redirect:/";
    }
}
