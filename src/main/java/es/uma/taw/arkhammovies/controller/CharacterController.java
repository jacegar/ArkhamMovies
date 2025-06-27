package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.MovieCharacterDTO;
import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.dto.PersonDTO;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.service.MovieCharacterService;
import es.uma.taw.arkhammovies.service.MovieService;
import es.uma.taw.arkhammovies.service.PersonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/characters")
public class CharacterController extends BaseController{
    @Autowired
    protected MovieCharacterService characterService;
    @Autowired
    protected MovieService movieService;
    @Autowired
    protected PersonService personService;

    @GetMapping("/inicio")
    public String getCharactersPage(HttpSession session, Model model) {
        List<MovieCharacterDTO> characters = null;
        List<MovieCharacterDTO> likedCharacters = null;
        UserDTO user = (UserDTO) session.getAttribute("user");

        characters = characterService.getCharactersByName("");
        if(characters.size() > 6){
            characters = characters.subList(0, 6);
        }

        if(user != null) {
            likedCharacters = characterService.getLikedCharactersFromUser(user.getId(), "");
            if(likedCharacters.size() > 6){
                characters = characters.subList(0, 6);
            }
        }

        model.addAttribute("characters", characters);
        model.addAttribute("likedCharacters", likedCharacters);

        return "charactersPage";
    }

    @GetMapping("/character")
    public String getCharacter(HttpSession session, Model model, @RequestParam(value = "id") Integer id) {
        MovieCharacterDTO character = characterService.findCharacter(id);
        MovieDTO movie = movieService.findMovie(character.getMovie());
        //character.getPerson() puede devolver nulo, faltaria tener en cuenta este caso
        PersonDTO person = personService.findPerson(character.getPerson());

        model.addAttribute("character", character);
        model.addAttribute("movie", movie);
        model.addAttribute("person", person);

        return "character";
    }

    @GetMapping("/new")
    public String doCreate(Model model, HttpSession session,
                           @RequestParam (required = false) Integer movieId,
                           @RequestParam (required = false) Integer personId,
                           @RequestParam(value = "ret", required = false) Integer ret) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        MovieCharacterDTO movieCharacterDTO = new MovieCharacterDTO();

        // Si se añade desde una película, esta ya estará seleccionada a la hora de crear el personaje
        if(movieId != null){
            movieCharacterDTO.setMovie(movieId);
        }

        // Si se añade desde una persona, este ya estará seleccionado a la hora de crear el personaje
        if(personId != null){
            movieCharacterDTO.setPerson(personId);
        }

        boolean esEditar = false;

        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("people", personService.getPeopleByName(""));
        model.addAttribute("character", movieCharacterDTO);
        model.addAttribute("esEditar", esEditar);
        model.addAttribute("personId", personId);
        model.addAttribute("movieId", movieId);
        model.addAttribute("ret", ret);

        return "savecharacter";
    }

    @PostMapping("/save")
    public String doSave(@ModelAttribute("character") MovieCharacterDTO character,
                         Model model,
                         @RequestParam("esEditar") boolean esEditar,
                         HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if (character.getName().isEmpty()) {
            model.addAttribute("movies", movieService.getAllMovies());
            model.addAttribute("people", personService.getPeopleByName(""));
            model.addAttribute("error",
                    "Por favor, rellene todos los campos obligatorios");
            model.addAttribute("esEditar", esEditar);

            return "savecharacter";
        }

        if (character.getPhotoUrl().isEmpty()) {
            character.setPhotoUrl("https://donaldthompson.com/wp-content/uploads/2024/10/placeholder-image-vertical.png");
        }

        this.characterService.saveCharacter(character);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String doEdit(@RequestParam("id") Integer id, Model model, HttpSession session,
                         @RequestParam(value = "ret", required = false) Integer ret,
                         @RequestParam (required = false) Integer movieId,
                         @RequestParam (required = false) Integer personId) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        MovieCharacterDTO characterDTO = this.characterService.findCharacter(id);
        boolean esEditar = true;

        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("people", personService.getPeopleByName(""));
        model.addAttribute("character", characterDTO);
        model.addAttribute("esEditar", esEditar);
        model.addAttribute("ret", ret);
        model.addAttribute("movieId", movieId);
        model.addAttribute("personId", personId);

        return "savecharacter";
    }

    @PostMapping("/delete")
    public String doDelete(@RequestParam("id") Integer id, HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        this.characterService.deleteCharacterById(id);

        return "redirect:/";
    }

    @PostMapping("/atras")
    public String doAtras(@RequestParam(value = "characterId", required = false) Integer characterId,
                          @RequestParam(value = "personId", required = false) Integer personId,
                          @RequestParam(value = "movieId", required = false) Integer movieId,
                          @RequestParam(value = "ret", required = false) Integer ret) {
        if (personId != null && ret != null && ret == 0) {
            return "redirect:/people/person?id=" + personId;
        } else if (characterId != null && ret != null && ret == 1) {
            return "redirect:/characters/character?id=" + characterId;
        } else if (movieId != null && ret != null && ret == 2) {
            return "redirect:/movies/movie?id=" + movieId;
        } else {
            return "redirect:/characters/inicio";
        }
    }
}
