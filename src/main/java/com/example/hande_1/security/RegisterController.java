package com.example.hande_1.security;

import com.example.hande_1.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    RegisterController(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
        this.userRepository=userRepository;
    }

    @PostMapping
    public  String Registration (UserRegistrationForm userRegistrationForm){
        //todo:: check exists

        this.userRepository.save_UserJDBC(userRegistrationForm.toUserJDBC(this.passwordEncoder));
        return "redirect:/login";
    }

    @GetMapping
    public  String GetForm (Model model){
        model.addAttribute("UserRegistrationForm", new UserRegistrationForm());
        return "register";
    }

}
