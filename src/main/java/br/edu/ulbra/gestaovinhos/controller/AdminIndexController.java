package br.edu.ulbra.gestaovinhos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    @RequestMapping()
    public String index(){
        return "admin/index";
    }

    @PostMapping("/login")
    public String login(){
        return "redirect:/admin/inicio";
    }

    @RequestMapping("/inicio")
    public String home(){
        return "admin/inicio";
    }
}
