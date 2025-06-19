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

        characters = characterService.getCharactersByName("");
        if(characters.size() > 12){
            characters = characters.subList(0, 12);
        }

        model.addAttribute("characters", characters);

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
                           @RequestParam (required = false) Integer personId) {
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() != 0) {
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

        return "savecharacter";
    }

    @PostMapping("/save")
    public String doSave(@ModelAttribute("character") MovieCharacterDTO character,
                         Model model,
                         @RequestParam("esEditar") boolean esEditar) {

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
    public String doEdit(@RequestParam("id") Integer id, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() != 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        MovieCharacterDTO characterDTO = this.characterService.findCharacter(id);
        boolean esEditar = true;

        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("people", personService.getPeopleByName(""));
        model.addAttribute("character", characterDTO);
        model.addAttribute("esEditar", esEditar);

        return "savecharacter";
    }

    @PostMapping("/delete")
    public String doDelete(@RequestParam("id") Integer id) {
        this.characterService.deleteCharacterById(id);

        return "redirect:/";
    }
}
