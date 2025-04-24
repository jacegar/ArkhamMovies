package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dao.MovieRepository;
import es.uma.taw.arkhammovies.entity.Movie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//Controlador de recomendación
@Controller
public class RecommendationController extends BaseController{
    @Autowired protected MovieRepository movieRepository;


    @GetMapping("/")
    public String doListar(HttpSession session, Model model) {
        if(isAuthenticated(session)){
            //Añadir recomendacion personalizada
        }else {
            List<Movie> recommendedMovies = movieRepository.getMoviesSortedByPopularity();
            model.addAttribute("recommendedMovies", recommendedMovies);
        }
        return "index";
    }
}
