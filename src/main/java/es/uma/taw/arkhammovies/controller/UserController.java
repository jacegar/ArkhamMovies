package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.*;
import es.uma.taw.arkhammovies.service.MovieService;
import es.uma.taw.arkhammovies.service.ReviewService;
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

    @Autowired
    private ReviewService reviewService;

    protected String doUser(Model model, int option, UserDTO user) {
        model.addAttribute("option", option);
        model.addAttribute("user", user);
        String formAction = option == 0 ? "process_register" : "process_login";
        model.addAttribute("formAction", formAction);

        return "user";
    }

    @GetMapping("/register")
    public String doRegister(Model model) {
        return doUser(model, 0, new UserDTO());
    }

    @GetMapping("/login")
    public String doLogin(Model model) {
        return doUser(model, 1, new UserDTO());
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
            return doUser(model, 0, user);
        } else if (this.userService.findUserByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "El correo ya está en uso");
            return doUser(model, 0, user);
        } else if (this.userService.findUserByNickname(user.getNickname()) != null) {
            model.addAttribute("error", "El alias ya está en uso");
            return doUser(model, 0, user);
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

        if (user.getPassword().isEmpty() || user.getNickname().isEmpty()) {
            model.addAttribute("error", "Por favor, rellena todos los campos");
            return doUser(model, 1, user);
        }
        UserDTO userDTO = this.userService.findUserByNicknameAndPassword(user.getNickname(), user.getPassword());
        if (userDTO == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            return doUser(model, 1, user);
        } else {
            session.setAttribute("user", userDTO);
            return "redirect:/";
        }
    }

    @PostMapping("/atras")
    public String doAtras(Model model) {

        return "redirect:/";
    }

    @GetMapping("/{nickname}")
    public String doPerfil(@PathVariable("nickname") String nickname,
                           Model model,
                           HttpSession session) {

        UserDTO userDTO = this.userService.findUserByNickname(nickname);
        List<MovieDTO> likedMovies;
        List<MovieDTO> savedMovies;
        List<ReviewDTO> reviews;

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

        if (userDTO.getReviews() == null) {
            reviews = new ArrayList<>();
        } else {
            reviews = this.reviewService.findByUserId(userDTO.getId());
        }

        Double mediaReviews = null;
        if (!reviews.isEmpty()){
            mediaReviews = 0.0;
            for (ReviewDTO r : reviews){
                mediaReviews+= r.getScore()==null?0:r.getScore();
            }
            mediaReviews/=reviews.size();
        }


        Integer minutos = 0;
        for (MovieDTO m : likedMovies){
            minutos+= m.getRuntime()==null?0:m.getRuntime();
        }

        model.addAttribute("avgScore", mediaReviews);
        model.addAttribute("minutes", minutos);
        model.addAttribute("likedMovies", likedMovies);
        model.addAttribute("savedMovies", savedMovies);
        model.addAttribute("user", userDTO);

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

    @GetMapping("/inicio")
    public String doUsersPage() { return "usersPage"; }

    @PostMapping("/search")
    public String doSearchUser(Model model, @RequestParam("busqueda") String busqueda) {
        if (busqueda.isEmpty()) return "redirect:/user/inicio";

        List<UserDTO> usuarios = this.userService.findUsersBySearch(busqueda);

        model.addAttribute("busqueda", busqueda);
        model.addAttribute("usuarios", usuarios);

        return "usersPage";
    }
}
