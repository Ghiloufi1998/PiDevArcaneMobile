/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnome.arcane.entities;

/**
 *
 * @author Ghiloufi
 */
public class Exercices {
    private int ID_ex;
    private String Type;
    private String Question ;
    private String Reponse ;
    private String Hint;
    private String image;
    private Cours cours;
    private int ID_crs;

    public Exercices() {
    }

    
    
    public Exercices(int ID_ex, String Type, String Question, String Reponse, String Hint, String image, Cours cours, int ID_crs) {
        this.ID_ex = ID_ex;
        this.Type = Type;
        this.Question = Question;
        this.Reponse = Reponse;
        this.Hint = Hint;
        this.image = image;
        this.cours = cours;
        this.ID_crs = ID_crs;
    }

    public Exercices(String Type, String Question, String Reponse, String Hint, String image, Cours cours, int ID_crs) {
        this.Type = Type;
        this.Question = Question;
        this.Reponse = Reponse;
        this.Hint = Hint;
        this.image = image;
        this.cours = cours;
        this.ID_crs = ID_crs;
    }

    public Exercices(String Type, String Question, String Reponse, String Hint, String image) {
        this.Type = Type;
        this.Question = Question;
        this.Reponse = Reponse;
        this.Hint = Hint;
        this.image = image;
    }

    public Exercices(String Type, String Question, String Reponse, String Hint, String image, int ID_crs) {
        this.Type = Type;
        this.Question = Question;
        this.Reponse = Reponse;
        this.Hint = Hint;
        this.image = image;
        this.ID_crs = ID_crs;
    }
    
    

    public int getID_ex() {
        return ID_ex;
    }

    public String getType() {
        return Type;
    }

    public String getQuestion() {
        return Question;
    }

    public String getReponse() {
        return Reponse;
    }

    public String getHint() {
        return Hint;
    }

    public String getImage() {
        return image;
    }

    public Cours getCours() {
        return cours;
    }

    public int getID_crs() {
        return ID_crs;
    }

    public void setID_ex(int ID_ex) {
        this.ID_ex = ID_ex;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public void setReponse(String Reponse) {
        this.Reponse = Reponse;
    }

    public void setHint(String Hint) {
        this.Hint = Hint;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public void setID_crs(int ID_crs) {
        this.ID_crs = ID_crs;
    }
    
    
    
}
