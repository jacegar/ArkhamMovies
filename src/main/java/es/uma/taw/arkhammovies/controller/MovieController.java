package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/new")
    public String doCreate(Model model) {
        MovieDTO movieDTO = new MovieDTO();
        boolean esEditar = false;

        model.addAttribute("movie", movieDTO);
        model.addAttribute("esEditar", esEditar);

        return "savemovie";
    }

    @PostMapping("/save")
    public String doSave(@ModelAttribute("movie") MovieDTO movie,
                         Model model) {
        if (movie.getTitle().isEmpty() || movie.getReleaseDate() == null) {
            model.addAttribute("error",
                    "Por favor, rellene todos los campos");

            return "savemovie";
        } else {
            this.movieService.saveMovie(movie);
            return "redirect:/";
        }
    }

    @PostMapping("/edit")
    public String doEdit(@RequestParam("id") Integer id, Model model) {
        MovieDTO movie = this.movieService.findMovie(id);
        boolean esEditar = true;

        model.addAttribute("movie", movie);
        model.addAttribute("esEditar", esEditar);

        return "savemovie";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
