package es.uma.taw.arkhammovies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController extends BaseController {
    @GetMapping("/")
    public String prueba(){
        return "index";
    }
}
