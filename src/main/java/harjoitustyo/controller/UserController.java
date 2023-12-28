package harjoitustyo.controller;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import harjoitustyo.entity.Users;
import harjoitustyo.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("list", userRepository.findAll());
        return "users";
    }

    @PostMapping("/")
    public String create(@RequestParam String username, @RequestParam String passwordHash) {
        userRepository.save(new Users(username, passwordHash));
        return "redirect:/users/";
    }
}
