package es.uma.taw.arkhammovies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController extends BaseController {

    @GetMapping("/register")
    public String doRegister(Model model) {
        model.addAttribute("option", 0);

        return "user";
    }

    @GetMapping("/login")
    public String doLogin(Model model) {
        model.addAttribute("option", 1);

        return "user";
    }

}
