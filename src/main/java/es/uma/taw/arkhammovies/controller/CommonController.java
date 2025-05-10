package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dao.MovieRepository;
import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.entity.Movie;
import es.uma.taw.arkhammovies.service.MovieService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//Controlador para tareas comunes que no encajan en ningun rol, ej ver datos de una pelicula o buscar una pelicula concreta en la pagina
@Controller
@RequestMapping("/movies")
public class CommonController extends BaseController{

    @Autowired protected MovieService movieService;

    //Usado en la pestaña de ver más
    //0 -> peliculas mas populares, 1 -> recomendadas para usuario, 2 -> mas recientes
    @GetMapping("/list")
    public String getExtendedList(HttpSession session, Model model, @RequestParam(value = "criteria", defaultValue = "0") Integer criteria) {
        List<MovieDTO> completeList;

        //todo añadir lista de usuario
        if(criteria == 2){
            completeList = movieService.getMoviesSortedByReleaseDate();
        }else{
            completeList = movieService.getMoviesSortedByPopularity();
        }

        model.addAttribute("movieList", completeList);
        model.addAttribute("criteria", criteria);

        return "movieList";
    }


    @GetMapping("/movie")
    public String getMovie(HttpSession session, Model model, @RequestParam(value = "id") Integer id) {
        MovieDTO movie = movieService.findMovie(id);

        model.addAttribute("movie", movie);

        return "movie";
    }

    @PostMapping("/moviesbyTitle")
    public String postMovie(HttpSession session, Model model, @RequestParam(value = "title") String title, @RequestParam(value = "criteria", defaultValue = "4") Integer criteria) {
        List<MovieDTO> movies = movieService.getMoviesByTitle(title);

        model.addAttribute("movieList", movies);
        model.addAttribute("criteria", criteria);
        model.addAttribute("title", title);

        return "movieList";
    }

}
