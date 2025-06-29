package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.*;
import es.uma.taw.arkhammovies.service.MovieCharacterService;
import es.uma.taw.arkhammovies.service.MovieService;
import es.uma.taw.arkhammovies.service.MoviecrewService;
import es.uma.taw.arkhammovies.service.PersonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ================================================================================
// Eduardo Ariza: 60%
// Juan Acevedo: 25%
// Enrique Ibáñez: 15%
// ================================================================================

@Controller
@RequestMapping("/people")
public class PeopleController extends BaseController{
    @Autowired
    protected PersonService personService;
    @Autowired
    private MovieCharacterService movieCharacterService;
    @Autowired
    private MoviecrewService moviecrewService;
    @Autowired
    private MovieService movieService;

    @GetMapping("/inicio")
    public String getPeoplePage(HttpSession session, Model model) {
        List<PersonDTO> actors = null;
        List<PersonDTO> crewmembers = null;

        actors = personService.getAllActors();
        if(actors.size() > 6){
            actors = actors.subList(0, 6);
        }

        crewmembers = personService.getAllCrewMembers();
        if(crewmembers.size() > 6){
            crewmembers = crewmembers.subList(0, 6);
        }

        model.addAttribute("actors", actors);
        model.addAttribute("crewmembers", crewmembers);

        return "peoplePage";
    }

    @GetMapping("/person")
    public String getPerson(HttpSession session, Model model, @RequestParam(value = "id") Integer id,
                            @RequestHeader(value = "referer", required = false) String referer) {
        PersonDTO person = personService.findPerson(id);
        List<MovieCharacterDTO> characters = movieCharacterService.getCharactersFromPerson(person.getId());
        List<MoviecrewDTO> jobs = moviecrewService.getMoviecrewsByPerson(person.getId());

        List<MovieDTO> moviesWorked = movieService.getMoviesWherePersonIsCrew(person.getId());
        Map<Integer, MovieDTO> movieMap = new HashMap<Integer, MovieDTO>();
        for (MovieDTO movie : moviesWorked) {
            movieMap.put(movie.getId(), movie);
        }

        model.addAttribute("person", person);
        model.addAttribute("characters", characters);
        model.addAttribute("jobs", jobs);
        model.addAttribute("moviesWorked", movieMap);
        model.addAttribute("referer", referer);

        return "person";
    }

    @GetMapping("/new")
    public String doCreate(Model model, HttpSession session,
                           @RequestHeader(value = "referer", required = false) String referer) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        PersonDTO personDTO = new PersonDTO();
        boolean esEditar = false;

        model.addAttribute("person", personDTO);
        model.addAttribute("esEditar", esEditar);
        model.addAttribute("referer", referer);

        return "saveperson";
    }

    @PostMapping("/save")
    public String doSave(@ModelAttribute("person") PersonDTO person,
                         Model model,
                         @RequestParam("esEditar") boolean esEditar,
                         HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if (person.getName().isEmpty() || person.getSurname1().isEmpty() || person.getGender()==null || person.getAge()==null) {
            model.addAttribute("error",
                    "Por favor, rellene todos los campos obligatorios");
            model.addAttribute("esEditar", esEditar);

            return "saveperson";
        }

        if (person.getPhotoUrl().isEmpty()) {
            person.setPhotoUrl("https://donaldthompson.com/wp-content/uploads/2024/10/placeholder-image-vertical.png");
        }

        this.personService.savePerson(person);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String doEdit(@RequestParam("id") Integer id, Model model, HttpSession session,
                         @RequestHeader(value = "referer", required = false) String referer) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        PersonDTO personDTO = this.personService.findPerson(id);
        boolean esEditar = true;

        model.addAttribute("person", personDTO);
        model.addAttribute("esEditar", esEditar);
        model.addAttribute("referer", referer);

        return "saveperson";
    }

    @PostMapping("/delete")
    public String doDelete(@RequestParam("id") Integer id, HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        this.personService.deletePersonById(id);

        return "redirect:/";
    }

    @PostMapping("/atras")
    public String doAtras(@RequestParam(value = "prevUrl", required = false) String prevUrl) {
        if (prevUrl != null && prevUrl.contains("?") && !prevUrl.contains("new") && !prevUrl.contains("edit")) {
            return "redirect:" + prevUrl;
        } else {
            return "redirect:/people/inicio";
        }
    }
}
