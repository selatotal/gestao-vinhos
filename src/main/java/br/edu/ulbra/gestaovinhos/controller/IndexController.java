package br.edu.ulbra.gestaovinhos.controller;

import br.edu.ulbra.gestaovinhos.service.interfaces.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private SecurityService securityService;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("loggedUser", securityService.findLoggedInUser());
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView loginForm(){
        ModelAndView mv = new ModelAndView("login/login");
        return mv;
    }

    @RequestMapping("/inicio")
    public String home() {
        return "home";
    }
}
