package br.edu.ulbra.gestaovinhos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/usuario")
public class AdminUserController {

    @RequestMapping()
    public String listaUsuarios(){
        return "admin/usuario/lista";
    }

    @GetMapping("/novo")
    public String novoUsuarioForm(){
        return "admin/usuario/novo";
    }

    @PostMapping("/novo")
    public String novoUsuario(){
        return "redirect:/admin/usuario";
    }

    @GetMapping("/{id}")
    public String detalheUsuario(@PathVariable("id") Long idUsuario){
        return "admin/usuario/detalhe";
    }

    @PostMapping("/{id}")
    public String salvarUsuario(@PathVariable("id") Long idUsuario){
        return "redirect:/admin/usuario";
    }

    @PostMapping("/{id}/resetSenha")
    public String resetarSenhaUsuario(@PathVariable("id") Long idUsuario){
        return "redirect:/admin/usuario/" + idUsuario;
    }


}
