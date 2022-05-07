/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnome.arcane.entities;

/**
 *
 * @author Acer
 */
public class Vol {
  private int Vol_id ;   
  private String Destination ;   
  private String Départ ;   
  private int prix;
  private String Image ;   

    public Vol() {
    }

    public Vol(String Image, String Départ, int prix ) {
        this.Départ = Départ;
        this.prix = prix;
        this.Image = Image;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

 

    public Vol(int Vol_id, String Destination, String Départ, String Image) {
        this.Vol_id = Vol_id;
        this.Destination = Destination;
        this.Départ = Départ;
        this.Image = Image;
    }

    public Vol(String Destination, String Départ, String Image) {
        this.Destination = Destination;
        this.Départ = Départ;
        this.Image = Image;
    }

    public int getVol_id() {
        return Vol_id;
    }

    public String getDestination() {
        return Destination;
    }

    public String getDépart() {
        return Départ;
    }

    public String getImage() {
        return Image;
    }

    public void setVol_id(int Vol_id) {
        this.Vol_id = Vol_id;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public void setDépart(String Départ) {
        this.Départ = Départ;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "Vol{" + "Vol_id=" + Vol_id + ", Destination=" + Destination + ", D\u00e9part=" + Départ + ", Image=" + Image + '}';
    }
    
  
  
  
  
}
