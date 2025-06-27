package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.*;
import es.uma.taw.arkhammovies.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Autor: Juan Acevedo García (35%)

//Controlador para tareas comunes que no encajan en ningun rol, ej ver datos de una pelicula o buscar una pelicula concreta en la pagina
@Controller
@RequestMapping("/movies")
public class CommonController extends BaseController{

    @Autowired protected MovieService movieService;
    @Autowired protected ReviewService reviewService;
    @Autowired protected GenreService genreService;
    @Autowired protected KeywordService keywordService;
    @Autowired protected MovieCharacterService characterService;
    @Autowired protected PersonService personService;
    @Autowired protected MoviecrewService crewService;

    //Usado en las pestañas de ver más para ver todas las peliculas según cierto criterio
    //0 -> peliculas mas populares, 1 -> recomendadas para usuario, 2 -> mas recientes, 4 -> mejor valoración media
   @GetMapping("/list")
    public String getExtendedList(HttpSession session, Model model, @RequestParam(value = "criteria", defaultValue = "0") Integer criteria) {
        List<MovieDTO> completeList = null;
        List<MovieCharacterDTO> characterDTOList = null;
        List<PersonDTO> personDTOList = null;
        UserDTO user = (UserDTO) session.getAttribute("user");

        switch (criteria){
            case 0:
                completeList = movieService.getMoviesSortedByPopularity("");
                break;
            case 1:
                if(user == null || user.getMoviesLiked() == null || user.getMoviesLiked().isEmpty()){
                    completeList = movieService.getAllMovies();
                    Collections.shuffle(completeList);
                }else{
                    completeList = movieService.getRecommendedMovies(user, "");
                }
                break;
                case 2:
                    completeList = movieService.getMoviesSortedByReleaseDate("");
                    break;
                case 3:
                    characterDTOList = characterService.getCharactersByName("");
                    break;
                case 4:
                    completeList = movieService.getMoviesSortedByAverageScore("");
                    break;
                case 5:
                    personDTOList = personService.getActorsByName("");
                    break;
                case 6:
                    personDTOList = personService.getCrewmembersByName("");
                    break;
                case 7:
                    characterDTOList = characterService.getLikedCharactersFromUser(user.getId(), "");
                    break;
                default:
                    completeList = movieService.getMoviesSortedByPopularity("");
                    characterDTOList = characterService.getCharactersByName("");
                    personDTOList = personService.getPeopleByName("");
                    break;
        }

        model.addAttribute("movieList", completeList);
        model.addAttribute("characterList", characterDTOList);
        model.addAttribute("personList", personDTOList);
        model.addAttribute("criteria", criteria);

        return "searchList";
    }


    @GetMapping("/movie")
    public String getMovie(HttpSession session, Model model, @RequestParam(value = "id") Integer id) {
        MovieDTO movie = movieService.findMovie(id);
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<MovieCharacterDTO> characters = characterService.getCharactersFromMovie(movie.getId());
        List<ReviewDTO> reviews = this.reviewService.findByMovieId(id);
        List<GenreDTO> genres = this.genreService.getGenresByMovie(movie.getId());
        List<KeywordDTO> keywords = this.keywordService.findKeywordsByMovieId(movie.getId());

        List<MoviecrewDTO> crew = crewService.getMoviecrewByMovie(movie.getId());
        Map<Integer, PersonDTO> crewPeople = new HashMap<>();
        for(PersonDTO person : personService.getPeopleByWorkedMovie(movie.getId())){
            crewPeople.put(person.getId(), person);
        }

        boolean isLiked = false;
        boolean isSaved = false;

        if(user != null){
            isLiked = user.getMoviesLiked() != null && user.getMoviesLiked().contains(movie.getId());
            isSaved = user.getMoviesSaved() != null && user.getMoviesSaved().contains(movie.getId());
        }

        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviews);
        model.addAttribute("isLiked", isLiked);
        model.addAttribute("isSaved", isSaved);
        model.addAttribute("genres", genres);
        model.addAttribute("keywords", keywords);
        model.addAttribute("characters", characters);
        model.addAttribute("crew", crew);
        model.addAttribute("crewPeople", crewPeople);

        return "movie";
    }

    @PostMapping("/searchbyTitle")
    public String postMovie(HttpSession session, Model model, @RequestParam(value = "title") String title, @RequestParam(value = "criteria", defaultValue = "999") Integer criteria) {
        List<MovieDTO> movies = null;
        List<MovieCharacterDTO> characters = null;
        List<PersonDTO> people = null;
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
            case 4:
                movies = movieService.getMoviesSortedByAverageScore(title);
                break;
            case 5:
                people = personService.getActorsByNameOrSurname(title);
                break;
            case 6:
                people = personService.getCrewmembersByNameByNameOrSurname(title);
                break;
            case 7:
                if(user != null) {
                    characters = characterService.getLikedCharactersFromUser(user.getId(), title);
                }
                break;
            default:
                characters = characterService.getCharactersByName(title);
                movies = movieService.getMoviesByTitle(title);
                people = personService.getPeopleByNameOrSurname(title);
                break;
        }

        model.addAttribute("personList", people);
        model.addAttribute("characterList", characters);
        model.addAttribute("movieList", movies);
        model.addAttribute("criteria", criteria);
        model.addAttribute("title", title);

        return "searchList";
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
