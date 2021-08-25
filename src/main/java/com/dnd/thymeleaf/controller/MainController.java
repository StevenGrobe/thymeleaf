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
        characters.add(new Character("Bill", "Gates"));
        characters.add(new Character("Steve", "Jobs"));
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

        String firstName = characterForm.getFirstName();
        String lastName = characterForm.getLastName();

        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            Character newCharacter = new Character(firstName, lastName);
            characters.add(newCharacter);

            return "redirect:/characterList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addCharacter";
    }

}
