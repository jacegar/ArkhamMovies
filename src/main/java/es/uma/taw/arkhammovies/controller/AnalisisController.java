package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.*;
import es.uma.taw.arkhammovies.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
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
    @Autowired StatService statService;

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
        List<StatDTO> stats = null;

        switch (page){
            case 0:
                // Budget
                statName = "Facturación (€)";
                stats = statService.getSortedMovieBudgets();
                break;
            case 1:
                // Revenue
                statName = "Recaudación (€)";
                stats = statService.getSortedMovieRevenues();
                break;
            case 2:
                statName = "Beneficio (€)";
                stats = statService.getSortedMovieProfits();
                break;
            case 3:
                statName = "Duración (min)";
                stats = statService.getSortedMovieDurations();
                break;
            case 4:
                statName = "Nota";
                stats = statService.getSortedMovieScores();
                break;
            case 5:
                statName = "Likes";
                stats = statService.getSortedFavouritedMovies();
                break;
            case 6:
                statName = "Popularidad";
                stats = statService.getSortedMoviePopularities();
                break;
            case 7:
                statName = "Frecuencia por película";
                stats = statService.getGenresOrderedByFrequency();
                break;
            case 8:
                statName = "Frecuencia por película";
                stats = statService.getKeywordsOrderedByFrequency();
                break;
            case 9:
                statName = "Reseñas";
                stats = statService.getSortedUserReviews();
                break;
            case 10:
                statName = "Likes";
                stats = statService.getSortedUserLikes();
                break;
            default:
                // No sé
                break;
        }

        model.addAttribute("statNumber", page);
        model.addAttribute("statName", statName);
        model.addAttribute("stats", stats);

        return "analisisTables";
    }
}
