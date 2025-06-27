package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.service.GenreService;
import es.uma.taw.arkhammovies.service.MovieService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/movie")
public class MovieController extends BaseController {

    @Autowired
    MovieService movieService;

    @Autowired
    GenreService genreService;

    @GetMapping("/new")
    public String doCreate(Model model, HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        MovieDTO movieDTO = new MovieDTO();
        boolean esEditar = false;

        model.addAttribute("generos", genreService.getAllGenres());
        model.addAttribute("movie", movieDTO);
        model.addAttribute("esEditar", esEditar);

        return "savemovie";
    }

    @PostMapping("/save")
    public String doSave(@ModelAttribute("movie") MovieDTO movie,
                         Model model,
                         @RequestParam("esEditar") boolean esEditar,
                         HttpSession session) {

        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if (movie.getTitle().isEmpty() || movie.getReleaseDate() == null) {
            model.addAttribute("generos", genreService.getAllGenres());
            model.addAttribute("error",
                    "Por favor, rellene todos los campos obligatorios");
            model.addAttribute("esEditar", esEditar);

            return "savemovie";
        }

        if (movie.getPhotoUrl().isEmpty()) {
            movie.setPhotoUrl("https://donaldthompson.com/wp-content/uploads/2024/10/placeholder-image-vertical.png");
        }

        this.movieService.saveMovie(movie);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String doEdit(@RequestParam("id") Integer id, Model model, HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        MovieDTO movie = this.movieService.findMovie(id);
        boolean esEditar = true;

        model.addAttribute("generos", genreService.getAllGenres());
        model.addAttribute("movie", movie);
        model.addAttribute("esEditar", esEditar);

        return "savemovie";
    }

    @PostMapping("/delete")
    public String doDelete(@RequestParam("id") Integer id, HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        this.movieService.deleteMovieById(id);

        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
