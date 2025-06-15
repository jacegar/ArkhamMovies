package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.MovieCharacterDTO;
import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.dto.ReviewDTO;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.entity.Genre;
import es.uma.taw.arkhammovies.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

//Controlador para tareas comunes que no encajan en ningun rol, ej ver datos de una pelicula o buscar una pelicula concreta en la pagina
@Controller
@RequestMapping("/movies")
public class CommonController extends BaseController{

    @Autowired protected MovieService movieService;
    @Autowired protected ReviewService reviewService;
    @Autowired protected GenreService genreService;
    @Autowired protected MovieCharacterService characterService;

    //Usado en la pestaña de ver más
    //0 -> peliculas mas populares, 1 -> recomendadas para usuario, 2 -> mas recientes
    @GetMapping("/list")
    public String getExtendedList(HttpSession session, Model model, @RequestParam(value = "criteria", defaultValue = "0") Integer criteria) {
        List<MovieDTO> completeList = null;
        List<MovieCharacterDTO> characters = null;
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (criteria == 0){
            completeList = movieService.getMoviesSortedByPopularity("");
        }
        else if(criteria == 1){
            if(user == null || user.getMoviesLiked() == null || user.getMoviesLiked().isEmpty()){
                completeList = movieService.getAllMovies();
                Collections.shuffle(completeList);
            }else{
                completeList = movieService.getRecommendedMovies(user, "");
            }
        }else if(criteria == 2){
            completeList = movieService.getMoviesSortedByReleaseDate("");
        }else{ // criteria == 3
            characters = characterService.getCharactersByName("");
        }

        model.addAttribute("movieList", completeList);
        model.addAttribute("characterList", characters);
        model.addAttribute("criteria", criteria);

        return "movieList";
    }


    @GetMapping("/movie")
    public String getMovie(HttpSession session, Model model, @RequestParam(value = "id") Integer id) {
        MovieDTO movie = movieService.findMovie(id);
        UserDTO user = (UserDTO) session.getAttribute("user");
        boolean isLiked = false;
        boolean isSaved = false;

        if(user != null){
            isLiked = user.getMoviesLiked() != null && user.getMoviesLiked().contains(movie.getId());
            isSaved = user.getMoviesSaved() != null && user.getMoviesSaved().contains(movie.getId());
        }

        model.addAttribute("movie", movie);
        List<ReviewDTO> reviews = this.reviewService.findByMovieId(id);
        model.addAttribute("reviews", reviews);
        model.addAttribute("isLiked", isLiked);
        model.addAttribute("isSaved", isSaved);
        model.addAttribute("genres", genreService.getAllGenres());


        return "movie";
    }

    @GetMapping("/character")
    public String getCharacter(HttpSession session, Model model, @RequestParam(value = "id") Integer id) {
        MovieCharacterDTO character = characterService.findCharacter(id);

        model.addAttribute("character", character);

        return "character";
    }

    @PostMapping("/moviesbyTitle")
    public String postMovie(HttpSession session, Model model, @RequestParam(value = "title") String title, @RequestParam(value = "criteria", defaultValue = "999") Integer criteria) {
        List<MovieDTO> movies = null;
        List<MovieCharacterDTO> characters = null;
        UserDTO user = (UserDTO) session.getAttribute("user");

        switch (criteria) {
            case 0:
                movies = movieService.getMoviesSortedByPopularity(title);
                break;
            case 1:
                if(user == null || user.getMoviesLiked() == null || user.getMoviesLiked().isEmpty()){
                    movies = movieService.getMoviesByTitle(title);
                    Collections.shuffle(movies);
                }else{
                    movies = movieService.getRecommendedMovies(user, title);
                }
                break;
            case 2:
                movies = movieService.getMoviesSortedByReleaseDate(title);
                break;
            case 3:
                characters = characterService.getCharactersByName(title);
                break;
            default:
                characters = characterService.getCharactersByName(title);
                movies = movieService.getMoviesByTitle(title);
                break;
        }

        model.addAttribute("characterList", characters);
        model.addAttribute("movieList", movies);
        model.addAttribute("criteria", criteria);
        model.addAttribute("title", title);

        return "movieList";
    }

    @GetMapping("/flipLike")
    public String flipLike(HttpSession session, Model model, @ModelAttribute("movieId") Integer movieId, @RequestParam Boolean tipo) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        MovieDTO movie = movieService.findMovie(movieId);

        user = movieService.flipLike(movie.getId(), user.getId());
        session.setAttribute("user", user); //Recargamos el usuario con los cambios en los likes

        if (tipo) {
            return "redirect:/movies/movie?id=" + movieId;
        }
        else{
            return "redirect:/user/" + user.getNickname();
        }
    }

    @GetMapping("/flipSave")
    public String flipSave(HttpSession session, Model model, @ModelAttribute("movieId") Integer movieId,@RequestParam Boolean tipo) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        MovieDTO movie = movieService.findMovie(movieId);

        user = movieService.flipSave(movie.getId(), user.getId());
        session.setAttribute("user", user); //Recargamos el usuario con los cambios en los guardados

        if (tipo) {
            return "redirect:/movies/movie?id=" + movieId;
        }
        else{
            return "redirect:/user/" + user.getNickname();
        }

    }

    @PostMapping("/addReview")
    public String doAddReview(HttpSession session, RedirectAttributes redirectAttributes,
                              @RequestParam("movieId") Integer movieId,
                              @RequestParam(required = false, name = "score") Integer score,
                              @RequestParam("reviewText") String review) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) {
            return "redirect:/user/login";
        }
        else if (score != null && !review.isEmpty()){
            MovieDTO movie = movieService.findMovie(movieId);
            reviewService.addReview(movie.getId(), user.getId(), score, review);
        } else { // Atributos para redirección
            redirectAttributes.addFlashAttribute("error", "Por favor, rellene todos los campos");
        }

        return "redirect:/movies/movie?id=" + movieId;
    }

    @GetMapping("/removeReview")
    public String doRemoveReview(@RequestParam("movieId") Integer movieId, @RequestParam("userId") Integer userId) {
        this.reviewService.removeById(movieId, userId);
        return "redirect:/movies/movie?id=" + movieId;
    }

}
