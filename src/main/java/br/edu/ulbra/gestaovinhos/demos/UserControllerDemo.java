package br.edu.ulbra.gestaovinhos.demos;

import br.edu.ulbra.gestaovinhos.input.UserInput;
import br.edu.ulbra.gestaovinhos.model.Role;
import br.edu.ulbra.gestaovinhos.model.User;
import br.edu.ulbra.gestaovinhos.repository.RoleRepository;
import br.edu.ulbra.gestaovinhos.repository.UserRepository;
import br.edu.ulbra.gestaovinhos.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Controller
@RequestMapping("/demo/user")
public class UserControllerDemo {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    private ModelMapper mapper = new ModelMapper();

    @GetMapping()
    public ModelAndView listUserDemo(){
        ModelAndView mv = new ModelAndView("demo/user/list");
        List<User> usuarios = (List<User>) userRepository.findAll();
        mv.addObject("users", usuarios);
        return mv;
    }

    private ModelAndView userForm(UserInput userInput){
        ModelAndView mv = new ModelAndView("demo/user/new");
        mv.addObject("user", userInput);
        mv.addObject("roles", roleRepository.findAll());
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newUser(){
        return this.userForm(new UserInput());
    }

    @PostMapping(value="/new")
    public ModelAndView newUser(UserInput userInput){
        if (userInput.getPassword() != null && userInput.getPassword().length() > 0 && !userInput.getPassword().equals(userInput.getPasswordConfirm())){
            ModelAndView mv = this.userForm(userInput);
            mv.addObject("error", "Password don't match!");
            return mv;
        }

        User user = mapper.map(userInput, User.class);
        Role role = roleRepository.findOne(userInput.getRoleId());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userService.save(user);
        return new ModelAndView("redirect:/demo/user/?usercreated=true");
    }

    @GetMapping("/{id}")
    public ModelAndView viewUserDemo(@PathVariable(name="id") Long id){
        User usuario = userRepository.findOne(id);
        UserInput userInput = mapper.map(usuario, UserInput.class);
        Set<Role> roles = usuario.getRoles();
        if (roles.size() > 0){
            userInput.setRoleId(roles.iterator().next().getId());
        }
        ModelAndView mv = this.userForm(userInput);
        mv.setViewName("demo/user/update");
        return mv;
    }

    @PostMapping("/{id}")
    public ModelAndView updateUserDemo(@PathVariable(name="id") Long id, UserInput userInput){
        User usuario = userRepository.findOne(id);
        if (userInput.getPassword() != null && userInput.getPassword().length() > 0 && !userInput.getPassword().equals(userInput.getPasswordConfirm())){
            ModelAndView mv = this.userForm(userInput);
            mv.addObject("error", "Password don't match!");
            return mv;
        }
        usuario.setUsername(userInput.getUsername());
        usuario.setPassword(userInput.getPassword());
        usuario.setName(userInput.getName());
        Role role = roleRepository.findOne(userInput.getRoleId());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        usuario.setRoles(roles);

        userService.save(usuario);
        return new ModelAndView("redirect:/demo/user/?usercreated=true");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteUserDemo(@PathVariable(name="id") Long id){
        User usuario = userRepository.findOne(id);
        userRepository.delete(usuario);
        return new ModelAndView("redirect:/demo/user/?usercreated=true");

    }

}
