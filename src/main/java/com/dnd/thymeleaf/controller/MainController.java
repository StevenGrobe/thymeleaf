package com.dnd.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import com.dnd.thymeleaf.form.CharacterForm;
import com.dnd.thymeleaf.model.Character;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private static List<Character> characters = new ArrayList<Character>();

    static {
        characters.add(new Character(0, new String("Enryn Love"), "warrior", 20));
        characters.add(new Character(1, new String("Rarder Aber"), "mage", 10));
        characters.add(new Character(2, new String("Helia Willey"), "warrior", 17));
        characters.add(new Character(3, new String("Anen Hancey"), "mage", 7));
        characters.add(new Character(4, new String("Ryany Bourne"), "warrior", 12));
    }

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

        model.addAttribute("characters", characters);

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
            characters.add(newCharacter);

            return "redirect:/characterList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addCharacter";
    }

}
