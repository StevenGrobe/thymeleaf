package com.dnd.thymeleaf.form;

public class CharacterForm {
    private int id;
    private String nom;
    private String job;
    private int hp;

    public CharacterForm() {
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
}
