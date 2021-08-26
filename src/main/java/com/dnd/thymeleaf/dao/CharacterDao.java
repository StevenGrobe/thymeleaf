package com.dnd.thymeleaf.dao;
import com.dnd.thymeleaf.model.Character;
import java.util.List;

public interface CharacterDao {
    public List<Character>findAll();
    public Character findById(int id);
    public Character save(Character character);
    // public Character update(Character character);
    // public Character deleteById(int id);
}
