package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.*;
import es.uma.taw.arkhammovies.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    protected String doUser(Model model, int option) {
        model.addAttribute("option", option);
        model.addAttribute("user", new UserDTO());
        String formAction = option == 0 ? "process_register" : "process_login";
        model.addAttribute("formAction", formAction);

        return "user";
    }

    @GetMapping("/register")
    public String doRegister(Model model) {
        return doUser(model, 0);
    }

    @GetMapping("/login")
    public String doLogin(Model model) {
        return doUser(model, 1);
    }

    @PostMapping("/process_register")
    public String doProcessRegister(@ModelAttribute("user") UserDTO user,
                                    Model model,
                                    HttpSession session) {

        if (user.getNickname().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
            model.addAttribute("error", "Por favor, rellena todos los campos");
            return doUser(model, 0);
        } else if (this.userService.findUserByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "El usuario ya existe");
            return doUser(model, 0);
        } else {
            this.userService.registerUser(user);
            session.setAttribute("user", user);
            return "redirect:/";
        }
    }

    @PostMapping("/process_login")
    public String doProcessLogin(@ModelAttribute("user") UserDTO user,
                                 Model model,
                                 HttpSession session) {
        
        if (user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
            model.addAttribute("error", "Por favor, rellena todos los campos");
            return doUser(model, 1);
        }
        UserDTO userDTO = this.userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if (userDTO == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            return doUser(model, 1);
        } else {
            session.setAttribute("user", userDTO);
            return "redirect:/";
        }
    }
}
