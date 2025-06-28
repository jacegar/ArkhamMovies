package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
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

        switch (page){
            case 0:
                statName = "Facturación";
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                break;
        }

        model.addAttribute("statName", statName);

        return "analisisTables";
    }
}
