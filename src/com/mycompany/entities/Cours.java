/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Ghiloufi
 */
public class Cours {
   private int ID_crs;
    private String Type;
    private String Titre;
    private String Contenu;
    private String image;
    private int ID_g;
    private Guides guide;

    public Cours(int ID_crs, String Type, String Titre, String Contenu, String image, int ID_g, Guides guide) {
        this.ID_crs = ID_crs;
        this.Type = Type;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.image = image;
        this.ID_g = ID_g;
        this.guide = guide;
    }

    public Cours(String Type, String Titre, String Contenu, String image, int ID_g, Guides guide) {
        this.Type = Type;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.image = image;
        this.ID_g = ID_g;
        this.guide = guide;
    }

    public Cours(String Type, String Titre, String Contenu, String image, int ID_g) {
        this.Type = Type;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.image = image;
        this.ID_g = ID_g;
    }

    public Cours(String Type, String Titre, String Contenu, String image) {
        this.Type = Type;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.image = image;
    }

    public Cours() {
    }
    

    public int getID_crs() {
        return ID_crs;
    }

    public String getType() {
        return Type;
    }

    public String getTitre() {
        return Titre;
    }

    public String getContenu() {
        return Contenu;
    }

    public String getImage() {
        return image;
    }

    public int getID_g() {
        return ID_g;
    }

    public Guides getGuide() {
        return guide;
    }

    public void setID_crs(int ID_crs) {
        this.ID_crs = ID_crs;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setID_g(int ID_g) {
        this.ID_g = ID_g;
    }

    public void setGuide(Guides guide) {
        this.guide = guide;
    }

    @Override
    public String toString() {
        return "Cours{" + "ID_crs=" + ID_crs + ", Type=" + Type + ", Titre=" + Titre + ", Contenu=" + Contenu + ", image=" + image + ", ID_g=" + ID_g + ", guide=" + guide + '}';
    }

    
    
    
}
