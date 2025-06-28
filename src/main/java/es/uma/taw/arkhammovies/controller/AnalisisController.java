package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.GenreDTO;
import es.uma.taw.arkhammovies.dto.KeywordDTO;
import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/analisis")
public class AnalisisController extends BaseController{

    @Autowired MovieService movieService;
    @Autowired MovieCharacterService movieCharacterService;
    @Autowired PersonService personService;
    @Autowired MoviecrewService moviecrewService;
    @Autowired ReviewService reviewService;
    @Autowired UserService userService;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        Map<String, Integer> estadisticas = new HashMap<String, Integer>();

        estadisticas.put("numPeliculas", movieService.findMovieCount());
        estadisticas.put("numPersonajes", movieCharacterService.findCharacterCount());
        estadisticas.put("numPersonas", personService.findPeopleCount());
        estadisticas.put("numActores", personService.findActorCount());
        estadisticas.put("numTrabajosProd", moviecrewService.findCrewCount());
        Double notaMedia = reviewService.findScoreMean();
        estadisticas.put("mediaPresupuesto", movieService.findBudgetMean());
        estadisticas.put("mediaFacturacion", movieService.findRevenueMean());
        estadisticas.put("mediaDuracion", movieService.findRuntimeMean());
        Double likesMedios = movieService.findLikesMean();
        estadisticas.put("numUsuarios", userService.findUserCount());
        estadisticas.put("numEditores", userService.findEditorCount());
        estadisticas.put("numAdministradores", userService.findAdminCount());

        model.addAttribute("estadisticas", estadisticas);
        model.addAttribute("notaMedia", notaMedia);
        model.addAttribute("likesMedios", likesMedios);

        return "analisis";
    }

    @GetMapping("/table")
    public String getAnalisisTable(Model model, @RequestParam(required = false) Integer page) {
        String statName = null;
        List<GenreDTO> genres = null;
        List<KeywordDTO> keywords = null;
        List<UserDTO> users = null;
        Map<String, Integer> movieMapInteger = new LinkedHashMap<>();
        Map<String, Double> movieMapDouble = new LinkedHashMap<>();

        switch (page){
            case 0:
                // Budget
                statName = "Facturación";
                for (MovieDTO movie : movieService.getAllMoviesSortedByBudget()) {
                    movieMapInteger.put(movie.getTitle(), movie.getBudget());
                }
                break;
            case 1:
                // Revenue
                statName = "Recaudación";
                for (MovieDTO movie : movieService.getAllMoviesSortedByRevenue()) {
                    movieMapInteger.put(movie.getTitle(), movie.getRevenue());
                }
                break;
            case 2:
                statName = "Beneficio";
                for (MovieDTO movie : movieService.getAllMoviesSortedByProfit()) {
                    movieMapInteger.put(movie.getTitle(), movie.getRevenue() - movie.getBudget());
                }
                break;
            case 3:
                statName = "Duración";
                for (MovieDTO movie : movieService.getAllMoviesSortedByDuration()) {
                    movieMapInteger.put(movie.getTitle(), movie.getRuntime());
                }
                break;
            case 4:
                statName = "Nota";
                movieMapDouble = movieService.getSortedMovieScores();
                break;
            case 5:
                statName = "Likes";
                movieMapInteger = movieService.getSortedFavouritedMovies();
                break;
            case 6:
                statName = "Popularidad";
                for (MovieDTO movie : movieService.getMoviesSortedByPopularity("")) {
                    movieMapInteger.put(movie.getTitle(), movie.getPopularity());
                }
                break;
            case 7:
                statName = "Frecuencia";
                break;
            case 8:
                statName = "Frecuencia";
                break;
            case 9:
                statName = "Reseñas";
                break;
            case 10:
                statName = "Likes";
                break;
            default:
                break;
        }

        model.addAttribute("statNumber", page);
        model.addAttribute("statName", statName);
        model.addAttribute("movieMapInteger", movieMapInteger);
        model.addAttribute("movieMapDouble", movieMapDouble);
        model.addAttribute("genres", genres);
        model.addAttribute("keywords", keywords);
        model.addAttribute("users", users);

        return "analisisTables";
    }
}
