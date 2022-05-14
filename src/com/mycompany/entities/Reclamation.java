/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Abirn
 */
public class Reclamation {

    private int id;
    private String sujet;
    private String description;
    private String date;

    private int user_id;

    public Reclamation() {
    }

    public Reclamation(String sujet, String description, String date, int user_id) {
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.user_id = user_id;
    }

    public Reclamation(int id, String sujet, String description, String date, int user_id) {
        this.id = id;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", sujet=" + sujet + ", description=" + description + ", date=" + date + ", user_id=" + user_id + '}';
    }

}
