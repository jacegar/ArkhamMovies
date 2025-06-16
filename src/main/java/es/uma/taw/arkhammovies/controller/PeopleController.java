package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.PersonDTO;
import es.uma.taw.arkhammovies.service.PersonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController extends BaseController{
    @Autowired
    protected PersonService personService;

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

        model.addAttribute("person", person);

        return "person";
    }
}
