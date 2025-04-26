package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dao.MovieRepository;
import es.uma.taw.arkhammovies.entity.Movie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//Controlador para tareas comunes que no encajan en ningun rol, ej ver datos de una pelicula o buscar una pelicula concreta en la pagina
@Controller
@RequestMapping("/movies")
public class CommonController extends BaseController{

    @Autowired protected MovieRepository movieRepository;

    //Usado en la pestaña de ver más
    //0 -> peliculas mas populares, 1 -> recomendadas para usuario, 2 -> mas recientes
    @GetMapping("/list")
    public String getExtendedList(HttpSession session, Model model, @RequestParam(value = "criteria", defaultValue = "0") Integer criteria) {
        List<Movie> completeList;

        //todo añadir lista de usuario
        if(criteria == 2){
            completeList = movieRepository.getMoviesSortedByReleaseDate();
        }else{
            completeList = movieRepository.getMoviesSortedByPopularity();
        }

        model.addAttribute("movieList", completeList);
        model.addAttribute("criteria", criteria);

        return "movieList";
    }


    @GetMapping("/movie")
    public String getMovie(HttpSession session, Model model, @RequestParam(value = "id") Integer id) {
        Movie movie = movieRepository.findById(id).get();

        model.addAttribute("movie", movie);

        return "movie";
    }

}
