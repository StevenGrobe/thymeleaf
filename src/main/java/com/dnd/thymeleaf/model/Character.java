package com.dnd.dungeondragon.model;

public class Character {
    private int id;
    private String nom;
    private String job;
    private int hp;

    public Character() {
    }

    public Character(int id, String nom, String job, int hp) {
        this.id = id;
        this.nom = nom;
        this.job = job;
        this.hp = hp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getJob() {
        return job;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", job='" + job + '\'' +
                ", hp=" + hp +
                '}';
    }
}
