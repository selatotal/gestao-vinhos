package br.edu.ulbra.gestaovinhos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vinhos")
public class VinhoController {

    @RequestMapping("/minhalista")
    public String minhaLista(){
        return "vinhos/listarVinhos";
    }

    @RequestMapping("/vinho/{id}")
    public String detalhe(@PathVariable("id") Long idVinho){
        return "vinhos/detalhe";
    }

    @GetMapping("/vinho/{id}/avaliar")
    public String avaliarForm(@PathVariable("id") Long idVinho){
        return "vinhos/avaliar";
    }

    @PostMapping("/vinho/{id}/avaliar")
    public String enviarAvaliacao(@PathVariable("id") Long idVinho){
        return "redirect:/vinho/"+idVinho;
    }



}
