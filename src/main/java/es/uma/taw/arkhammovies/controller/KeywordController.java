package es.uma.taw.arkhammovies.controller;

import es.uma.taw.arkhammovies.dto.KeywordDTO;
import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.service.KeywordService;
import es.uma.taw.arkhammovies.service.MovieService;
import es.uma.taw.arkhammovies.service.MoviekeywordService;
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

    @Autowired
    private MovieService movieService;

    @Autowired
    private MoviekeywordService moviekeywordService;

    @GetMapping("/inicio")
    public String doKeywordsPage(HttpSession session, Model model) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        return doShowKeywords(user, model, null);
    }

    @PostMapping("/search")
    public String doSearchKeyword(HttpSession session,
                                  Model model,
                                  @RequestParam("busqueda") String busqueda) {

        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        return doShowKeywords(user, model, busqueda);
    }

    protected String doShowKeywords(UserDTO user, Model model, String busqueda) {
        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        List<KeywordDTO> keywords;

        if (busqueda == null || busqueda.isEmpty()) {
            keywords = this.keywordService.findAllKeywords();
        } else {
            keywords = this.keywordService.findKeywordsBySearch(busqueda);
        }

        model.addAttribute("keywords", keywords);
        model.addAttribute("busqueda", busqueda);

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

    @GetMapping("/add_to_movie")
    public String doAddToMovie(@RequestParam("movieId") Integer id, Model model, HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        MovieDTO movie = this.movieService.findMovie(id);
        List<KeywordDTO> keywords = this.keywordService.findAllKeywords();

        model.addAttribute("movie", movie);
        model.addAttribute("keywords", keywords);

        return "addKeywords";
    }

    @PostMapping("/save_movie")
    public String doSaveMovie(@ModelAttribute("movie") MovieDTO movie,
                              Model model,
                              HttpSession session) {

        if (!isAuthenticated(session)) return "redirect:/user/login";

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user.getRole() >= 2) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        this.moviekeywordService.deleteMovieKeywordsByMovieId(movie.getId());
        this.moviekeywordService.saveMovieKeywords(movie.getId(), movie.getKeywords());

        return "redirect:/";
    }
}
