package br.edu.ulbra.gestaovinhos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/vinho")
public class AdminVinhoController {

    @RequestMapping()
    public String listaVinhos(){
        return "admin/vinho/lista";
    }

    @GetMapping("/novo")
    public String novoVinhoForm(){
        return "admin/vinho/novo";
    }

    @PostMapping("/novo")
    public String novoVinho(){
        return "redirect:/admin/vinho";
    }

    @GetMapping("/{id}")
    public String detalheVinho(@PathVariable("id") Long idVinho){
        return "admin/vinho/detalhe";
    }

    @PostMapping("/{id}")
    public String salvarVinho(@PathVariable("id") Long idVinho){
        return "redirect:/admin/vinho";
    }


}
