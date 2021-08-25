package com.dnd.dungeondragon.dao;
import com.dnd.dungeondragon.model.Character;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CharacterDaoImpl implements CharacterDao {
    public static List<Character> characters = new ArrayList<>();
    static {
        characters.add(new Character(0, new String("Enryn Love"), "warrior", 20));
        characters.add(new Character(1, new String("Rarder Aber"), "mage", 10));
        characters.add(new Character(2, new String("Helia Willey"), "warrior", 17));
        characters.add(new Character(3, new String("Anen Hancey"), "mage", 7));
        characters.add(new Character(4, new String("Ryany Bourne"), "warrior", 12));
    }

    @Override
    public List<Character> findAll() {
        return characters;
    }

    @Override
    public Character findById(int id) {
        Character c = characters.stream()
                .filter(character -> character.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No data !"));
        return c;
    }

    @Override
    public Character save(Character character) {
        characters.add(character);
        return character;
    }

    @Override
    public Character update(Character character) {
        findById(character.getId()).setNom(character.getNom());
        findById(character.getId()).setJob(character.getJob());
        findById(character.getId()).setHp(character.getHp());
        return null;
    }

    @Override
    public Character deleteById(int id) {
        characters.remove(id);
        return null;
    }
}
