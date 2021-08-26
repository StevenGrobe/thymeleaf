package com.dnd.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import com.dnd.thymeleaf.dao.CharacterDaoImpl;
import com.dnd.thymeleaf.form.CharacterForm;
import com.dnd.thymeleaf.model.Character;
import com.dnd.thymeleaf.dao.CharacterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private CharacterDao characterDao;

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
        model.addAttribute("characters", characterDao.findAll());
        return "characterList";
    }

    @RequestMapping(value = { "/addCharacter" }, method = RequestMethod.GET)
    public String showAddCharacterPage(Model model) {

        CharacterForm characterForm = new CharacterForm();
        model.addAttribute("characterForm", characterForm);

        return "addCharacter";
    }

    @RequestMapping(value = { "/addCharacter" }, method = RequestMethod.POST)
    public String saveCharacter(Model model, //
                             @ModelAttribute("characterForm") CharacterForm characterForm) {

        int Id = characterForm.getId();
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
    public Character afficherUnPersonnage(Model model, @PathVariable int id) {
        model.addAttribute("character", characterDao.findById(id));
        return "characterDetails";
    }
}
