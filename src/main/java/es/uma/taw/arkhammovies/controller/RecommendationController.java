package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.service.MovieService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//Controlador de recomendación
@Controller
public class RecommendationController extends BaseController{
    @Autowired protected MovieService movieService;


    @GetMapping("/")
    public String doListar(HttpSession session, Model model) {
        List<MovieDTO> popularMovies = movieService.getMoviesSortedByPopularity("");
        if(popularMovies.size() > 6){
            popularMovies = popularMovies.subList(0, 6);
        }

        List<MovieDTO> recentMovies = movieService.getMoviesSortedByReleaseDate("");
        if(recentMovies.size() > 6){
            recentMovies = recentMovies.subList(0, 6);
        }

        model.addAttribute("popularMovies", popularMovies);
        model.addAttribute("recentMovies", recentMovies);

        return "index";
    }
}
