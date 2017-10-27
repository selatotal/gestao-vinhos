package br.edu.ulbra.gestaovinhos.demos;

import br.edu.ulbra.gestaovinhos.input.UserInput;
import br.edu.ulbra.gestaovinhos.model.User;
import br.edu.ulbra.gestaovinhos.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/demo/user")
public class UserControllerDemo {

    @Autowired
    private UserRepository userRepository;

    private final ModelMapper mapper = new ModelMapper();

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
        userRepository.save(user);
        return new ModelAndView("redirect:/demo/user/?usercreated=true");
    }

    @GetMapping("/{id}")
    public ModelAndView viewUserDemo(@PathVariable(name="id") Long id){
        User usuario = userRepository.findOne(id);
        UserInput userInput = mapper.map(usuario, UserInput.class);
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
        userRepository.save(usuario);
        return new ModelAndView("redirect:/demo/user/?usercreated=true");
    }

    @GetMapping("/{id}/delete")
    public String deleteUserDemo(@PathVariable(name="id") Long id){
        User usuario = userRepository.findOne(id);
        userRepository.delete(usuario);
        return "demo/user/delete";
    }
}
