package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.MovieCharacterDTO;
import es.uma.taw.arkhammovies.service.MovieCharacterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/characters")
public class CharacterController extends BaseController{
    @Autowired
    protected MovieCharacterService characterService;

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

        model.addAttribute("character", character);

        return "character";
    }
}
