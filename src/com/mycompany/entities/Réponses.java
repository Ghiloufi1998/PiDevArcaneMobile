/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author user
 */
public class Réponses {
        private int réponses_id; 
        private int Question_id;
        private Questions Questions;
        private String réponse;

    public Réponses(String réponse,int réponses_id) {
        this.réponses_id = réponses_id;
        this.réponse = réponse;
    }

    public Réponses() {
    }

    public Réponses(int réponses_id,Questions Questions, String réponse) {
        this.réponses_id = réponses_id;
        this.Questions = Questions;
        this.réponse = réponse;
    }

    public Réponses(Questions Questions,String réponse) {
        this.Questions = Questions;
        this.réponse = réponse;
    }

    public int getRéponses_id() {
        return réponses_id;
    }

    public Réponses(Questions Questions) {
        this.Questions = Questions;
    }

   

    public int getQuestion_id() {
        return Question_id;
    }

    public void setQuestions(Questions Questions) {
        this.Questions = Questions;
    }

    public Questions getQuestions() {
        return Questions;
    }

    public String getRéponse() {
        return réponse;
    }

    public void setRéponses_id(int réponses_id) {
        this.réponses_id = réponses_id;
    }

    public void setQuestion_id(int Question_id) {
        this.Question_id = Question_id;
    }

    public void setRéponse(String réponse) {
        this.réponse = réponse;
    }

    @Override
    public String toString() {
        return "Réponses{" + "réponses_id=" + réponses_id + ", Question_id=" + Question_id + ", Questions=" + Questions.getQuestion() + ", réponse=" + réponse + '}';
    }

   

        
    
}
