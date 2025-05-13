package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dao.MovieRepository;
import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.dto.ReviewDTO;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.entity.Movie;
import es.uma.taw.arkhammovies.entity.Review;
import es.uma.taw.arkhammovies.service.MovieService;
import es.uma.taw.arkhammovies.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controlador para tareas comunes que no encajan en ningun rol, ej ver datos de una pelicula o buscar una pelicula concreta en la pagina
@Controller
@RequestMapping("/movies")
public class CommonController extends BaseController{

    @Autowired protected MovieService movieService;
    @Autowired protected ReviewService reviewService;

    //Usado en la pestaña de ver más
    //0 -> peliculas mas populares, 1 -> recomendadas para usuario, 2 -> mas recientes
    @GetMapping("/list")
    public String getExtendedList(HttpSession session, Model model, @RequestParam(value = "criteria", defaultValue = "0") Integer criteria) {
        List<MovieDTO> completeList;

        //todo añadir lista de usuario
        if(criteria == 2){
            completeList = movieService.getMoviesSortedByReleaseDate("");
        }else{
            completeList = movieService.getMoviesSortedByPopularity("");
        }

        model.addAttribute("movieList", completeList);
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

        return "movie";
    }

    @PostMapping("/moviesbyTitle")
    public String postMovie(HttpSession session, Model model, @RequestParam(value = "title") String title, @RequestParam(value = "criteria", defaultValue = "3") Integer criteria) {
        List<MovieDTO> movies;
        switch (criteria) {
            case 0:
                movies = movieService.getMoviesSortedByPopularity(title);
                break;
            case 2:
                movies = movieService.getMoviesSortedByReleaseDate(title);
                break;
            case 3:
                movies = movieService.getMoviesByTitle(title);
                break;
            default:
                movies = movieService.getMoviesSortedByPopularity(title);
        }

        model.addAttribute("movieList", movies);
        model.addAttribute("criteria", criteria);
        model.addAttribute("title", title);

        return "movieList";
    }

    @GetMapping("/flipLike")
    public String flipLike(HttpSession session, Model model, @ModelAttribute("movieId") Integer movieId) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        MovieDTO movie = movieService.findMovie(movieId);

        user = movieService.flipLike(movie.getId(), user.getId());
        session.setAttribute("user", user); //Recargamos el usuario con los cambios en los likes

        return "redirect:/movies/movie?id=" + movieId;
    }

    @GetMapping("/flipSave")
    public String flipSave(HttpSession session, Model model, @ModelAttribute("movieId") Integer movieId) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        MovieDTO movie = movieService.findMovie(movieId);

        user = movieService.flipSave(movie.getId(), user.getId());
        session.setAttribute("user", user); //Recargamos el usuario con los cambios en los likes

        return "redirect:/movies/movie?id=" + movieId;
    }

    @PostMapping("/addReview")
    public String doAddReview(HttpSession session, Model model,
                              @RequestParam("movieId") Integer movieId,
                              @RequestParam("score") Integer score,
                              @RequestParam("reviewText") String review) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) {
            return "redirect:/user/login";
        }

        MovieDTO movie = movieService.findMovie(movieId);

        user = movieService.flipSave(movie.getId(), user.getId());
        session.setAttribute("user", user);

        reviewService.addReview(movie.getId(), user.getId(), score, review);
        return "redirect:/movies/movie?id=" + movieId;

    }

}
