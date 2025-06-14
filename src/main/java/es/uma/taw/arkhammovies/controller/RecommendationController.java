package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.MovieCharacterDTO;
import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.service.MovieCharacterService;
import es.uma.taw.arkhammovies.service.MovieService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

//Controlador de recomendación
@Controller
public class RecommendationController extends BaseController{
    @Autowired protected MovieService movieService;
    @Autowired protected MovieCharacterService characterService;

    @GetMapping("/")
    public String doListar(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");

        List<MovieDTO> popularMovies = movieService.getMoviesSortedByPopularity("");
        if(popularMovies.size() > 6){
            popularMovies = popularMovies.subList(0, 6);
        }

        List<MovieDTO> recommendedMovies;
        //Si el usuario no ha iniciado sesion o no ha dado likes, recomendamos peliculas aleatoriamente
        if(user == null || user.getMoviesLiked() == null || user.getMoviesLiked().isEmpty()){
            recommendedMovies = movieService.getAllMovies();
            Collections.shuffle(recommendedMovies);
        }else{
            recommendedMovies = movieService.getRecommendedMovies(user, "");
        }
        if(recommendedMovies.size() > 6){
            recommendedMovies = recommendedMovies.subList(0, 6);
        }

        List<MovieDTO> recentMovies = movieService.getMoviesSortedByReleaseDate("");
        if(recentMovies.size() > 6){
            recentMovies = recentMovies.subList(0, 6);
        }

        List<MovieCharacterDTO> characters = characterService.getCharactersByName("");

        model.addAttribute("popularMovies", popularMovies);
        model.addAttribute("recommendedMovies", recommendedMovies);
        model.addAttribute("recentMovies", recentMovies);
        model.addAttribute("characters", characters);

        return "index";
    }
}
