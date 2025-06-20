package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.MovieCharacterDTO;
import es.uma.taw.arkhammovies.dto.PersonDTO;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.service.MovieCharacterService;
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
@RequestMapping("/people")
public class PeopleController extends BaseController{
    @Autowired
    protected PersonService personService;
    @Autowired
    private MovieCharacterService movieCharacterService;

    @GetMapping("/inicio")
    public String getPeoplePage(HttpSession session, Model model) {
        List<PersonDTO> people = null;

        people = personService.getPeopleByName("");
        if(people.size() > 12){
            people = people.subList(0, 12);
        }

        model.addAttribute("people", people);

        return "peoplePage";
    }

    @GetMapping("/person")
    public String getPerson(HttpSession session, Model model, @RequestParam(value = "id") Integer id) {
        PersonDTO person = personService.findPerson(id);
        List<MovieCharacterDTO> characters = movieCharacterService.getCharactersFromPerson(person.getId());

        model.addAttribute("person", person);
        model.addAttribute("characters", characters);

        return "person";
    }

    @GetMapping("/new")
    public String doCreate(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() != 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        PersonDTO personDTO = new PersonDTO();
        boolean esEditar = false;

        model.addAttribute("person", personDTO);
        model.addAttribute("esEditar", esEditar);

        return "saveperson";
    }

    @PostMapping("/save")
    public String doSave(@ModelAttribute("person") PersonDTO person,
                         Model model,
                         @RequestParam("esEditar") boolean esEditar) {

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
    public String doEdit(@RequestParam("id") Integer id, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() != 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        PersonDTO personDTO = this.personService.findPerson(id);
        boolean esEditar = true;

        model.addAttribute("person", personDTO);
        model.addAttribute("esEditar", esEditar);

        return "saveperson";
    }

    @PostMapping("/delete")
    public String doDelete(@RequestParam("id") Integer id) {
        this.personService.deletePersonById(id);

        return "redirect:/";
    }
}
