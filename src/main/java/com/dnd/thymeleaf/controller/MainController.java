package com.dnd.thymeleaf.controller;

import com.dnd.thymeleaf.form.CharacterForm;
import com.dnd.thymeleaf.model.Character;
import com.dnd.thymeleaf.dao.CharacterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Controller
public class MainController {

    @Autowired
    private CharacterDao characterDao;

    @Autowired
    private RestTemplate restTemplate;

    // Injectez (inject) via application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping(value = { "/characterList" }, method = RequestMethod.GET)
    public String characterList(Model model) {
        String url = "http://localhost:8081/Personnages";
        List<Character> c = restTemplate.getForObject(url,List.class);
        model.addAttribute("Character", c);
        return "characterList";
    }

    @RequestMapping(value = { "/addCharacter" }, method = RequestMethod.GET)
    public String showAddCharacterPage(Model model) {

        CharacterForm characterForm = new CharacterForm();
        model.addAttribute("characterForm", characterForm);

        return "addCharacter";
    }

    @RequestMapping(value = { "/addCharacter" }, method = RequestMethod.POST)
    public String saveCharacter(Model model, @ModelAttribute("characterForm") CharacterForm characterForm) {
        String url = "http://localhost:8081/Personnages/";
        CharacterForm cform = restTemplate.postForObject(url, characterForm, CharacterForm.class);
        String name = characterForm.getNom();
        String job = characterForm.getJob();
        int hp = characterForm.getHp();

        int max = characterDao.findAll().stream()
                .map(personnage -> personnage.getId())
                .max(Integer::compare)
                .orElse(0);


        // int max = characterDao.findAll().stream()
        //         .map(personnage -> personnage.getId())
        //         .max(Integer::compare)
        //         .orElse(0);


        int Id = max+1;
        String Nom = characterForm.getNom();
        String Job = characterForm.getJob();
        int Hp = characterForm.getHp();

        if (Nom != null && Nom.length() > 0) {
            Character newCharacter = new Character(Id, Nom, Job, Hp);
            characterDao.save(newCharacter);

            return "redirect:/characterList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addCharacter";
    }

    @RequestMapping(value = {"/characterDetails/{id}"}, method = RequestMethod.GET)
    public String afficherUnPersonnage(Model model, @PathVariable int id) {
        String url = "http://localhost:8081/Personnages/";
        Character c = restTemplate.getForObject(url + id,Character.class);

        model.addAttribute("Character", c);
        System.out.println(c);
        return "characterDetails";
    }
}