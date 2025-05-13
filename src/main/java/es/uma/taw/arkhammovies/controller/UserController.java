package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.*;
import es.uma.taw.arkhammovies.entity.User;
import es.uma.taw.arkhammovies.service.MovieService;
import es.uma.taw.arkhammovies.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

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

    @PostMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
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
            user.setRole(1);
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

    @PostMapping("/atras")
    public String doAtras(Model model) {

        return "redirect:/";
    }

    @GetMapping("/{}")
    public String doPerfil(Model model, HttpSession session) {
        UserDTO userDTO = (UserDTO)session.getAttribute("user");
        List<MovieDTO> likedMovies;
        List<MovieDTO> savedMovies;

        if (userDTO.getMoviesLiked() == null) {
            likedMovies = new ArrayList<>();
        } else {
            likedMovies = this.movieService.findMoviesById(userDTO.getMoviesLiked());
        }

        if (userDTO.getMoviesSaved() == null) {
            savedMovies = new ArrayList<>();
        } else {
            savedMovies = this.movieService.findMoviesById(userDTO.getMoviesSaved());
        }

        model.addAttribute("likedMovies", likedMovies);
        model.addAttribute("savedMovies", savedMovies);

        return "profile";
    }

    @PostMapping("/vetar")
    public String doVetar(Model model, HttpSession session,
                          @RequestParam (required = false) String email) {

        if (email!=null) {
            if (!email.isEmpty()) {
                boolean eliminado = userService.removeUserByEmail(email);
                String mensaje = eliminado?"El usuario ha sido eliminado":"No se ha encontrado ningún usuario con ese email";
                model.addAttribute("mensaje", mensaje);
            }
            else{
                model.addAttribute("mensaje", "Debes introducir un email");
            }
        }

        return "vetar";
    }
}
