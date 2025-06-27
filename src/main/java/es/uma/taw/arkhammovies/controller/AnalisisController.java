package es.uma.taw.arkhammovies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/analisis")
public class AnalisisController extends BaseController{
    @GetMapping("/inicio")
    public String inicio(Model model) {

        return "analisis";
    }
}
