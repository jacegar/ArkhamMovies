package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.KeywordDTO;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.service.KeywordService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/keywords")
public class KeywordController extends BaseController {

    @Autowired
    private KeywordService keywordService;

    @GetMapping("/inicio")
    public String doKeywordsPage(HttpSession session, Model model) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        List<KeywordDTO> keywords = this.keywordService.findAllKeywords();

        model.addAttribute("keywords", keywords);

        return "keywordsPage";
    }

    @GetMapping("/new")
    public String doNewKeyword(Model model, HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        KeywordDTO keyword = new KeywordDTO();
        boolean esEditar = false;

        model.addAttribute("keyword", keyword);
        model.addAttribute("esEditar", esEditar);

        return "savekeyword";
    }

    @PostMapping("/save")
    public String doSaveKeyword(@ModelAttribute("keyword") KeywordDTO keyword,
                                Model model,
                                @RequestParam("esEditar") boolean esEditar,
                                HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if (keyword.getName().isEmpty()) {
            model.addAttribute("error",
                    "Por favor, rellene todos los campos obligatorios");
            model.addAttribute("esEditar", esEditar);

            return "savekeyword";
        } else if (this.keywordService.findKeywordByName(keyword.getName()) != null) {
            model.addAttribute("error",
                    "La palabra clave ya existe");
            model.addAttribute("esEditar", esEditar);

            return "savekeyword";
        }

        this.keywordService.saveKeyword(keyword);

        return "redirect:/keywords/inicio";
    }

    @GetMapping("/{name}")
    public String doEditKeyword(@PathVariable("name") String name, Model model, HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        KeywordDTO keyword = this.keywordService.findKeywordByName(name);
        boolean esEditar = true;

        model.addAttribute("keyword", keyword);
        model.addAttribute("esEditar", esEditar);

        return "savekeyword";
    }

    @PostMapping("/delete")
    public String doDeleteKeyword(@RequestParam("id") Integer id, HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        this.keywordService.deleteKeywordById(id);

        return "redirect:/keywords/inicio";
    }
}
