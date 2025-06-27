package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.MovieCharacterDTO;
import es.uma.taw.arkhammovies.dto.MoviecrewDTO;
import es.uma.taw.arkhammovies.dto.PersonDTO;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.service.MovieService;
import es.uma.taw.arkhammovies.service.MoviecrewService;
import es.uma.taw.arkhammovies.service.PersonService;
import es.uma.taw.arkhammovies.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

//Autor: Juan Acevedo García (100%)

//Controlador para crear, editar y eliminar moviecrews
@Controller
@RequestMapping("/moviecrew")
public class MoviecrewController extends BaseController{
    @Autowired protected MoviecrewService moviecrewService;
    @Autowired protected PersonService personService;
    @Autowired protected MovieService movieService;

    @GetMapping("/new")
    public String doCreate(Model model, HttpSession session,
                           @RequestParam(required = false) Integer movieId,
                           @RequestParam (required = false) Integer personId) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        MoviecrewDTO moviecrew = new MoviecrewDTO();

        // Si se añade desde una película, esta ya estará seleccionada a la hora de crear el personaje
        if(movieId != null){
            moviecrew.setMovieId(movieId);
        }

        // Si se añade desde una persona, este ya estará seleccionado a la hora de crear el personaje
        if(personId != null){
            moviecrew.setPersonId(personId);
        }

        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("people", personService.getPeopleByName(""));
        model.addAttribute("moviecrew", moviecrew);
        model.addAttribute("esEditar", false);

        return "savemoviecrew";
    }

    @GetMapping("/edit")
    public String doEdit(@RequestParam("movieId") Integer movieId,
                         @RequestParam("personId") Integer personId,
                         Model model,
                         HttpSession session) {

        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        MoviecrewDTO moviecrew = moviecrewService.getMovieCrewById(movieId, personId);
        boolean esEditar = true;

        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("people", personService.getPeopleByName(""));
        model.addAttribute("moviecrew", moviecrew);
        model.addAttribute("esEditar", esEditar);

        return "savemoviecrew";
    }

    @PostMapping("/save")
    public String doSave(@ModelAttribute("moviecrew") MoviecrewDTO moviecrew,
                         Model model,
                         @RequestParam("esEditar") boolean esEditar,
                         HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if (moviecrew.getJob().isEmpty()) {
            model.addAttribute("movies", movieService.getAllMovies());
            model.addAttribute("people", personService.getPeopleByName(""));
            model.addAttribute("error",
                    "Por favor, rellene todos los campos obligatorios");
            model.addAttribute("esEditar", esEditar);
            model.addAttribute("moviecrew", moviecrew);

            return "savemoviecrew";
        }

        this.moviecrewService.saveMoviecrew(moviecrew);
        return "redirect:/movies/movie?id=" + moviecrew.getMovieId();
    }

    @PostMapping("/delete")
    public String doDelete(@RequestParam("movieId") Integer movieId,
                           @RequestParam("personId") Integer personId,
                           HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }else {
            this.moviecrewService.deleteMovieCrewById(movieId, personId);
        }

        return "redirect:/movies/movie?id=" + movieId;
    }
}
