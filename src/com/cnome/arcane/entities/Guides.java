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
public class Guides {
    private int ID_g;
    private String titre;
    private String Pays;
    private int Level; 
    private String image;
    private Vol vol; 
    private int id_vol;

    public Guides() {
    }
    

    
    public Guides(int ID_g, String titre, String Pays, int Level, String image, Vol vol, int id_vol) {
        this.ID_g = ID_g;
        this.titre = titre;
        this.Pays = Pays;
        this.Level = Level;
        this.image = image;
        this.vol = vol;
        this.id_vol = id_vol;
    }

    public Guides(String titre, String Pays, int Level, String image, Vol vol, int id_vol) {
        this.titre = titre;
        this.Pays = Pays;
        this.Level = Level;
        this.image = image;
        this.vol = vol;
        this.id_vol = id_vol;
    }

    public Guides(Vol vol) {
        this.vol = vol;
    }

    public Guides(String titre, String Pays, int Level, String image, int id_vol) {
        this.titre = titre;
        this.Pays = Pays;
        this.Level = Level;
        this.image = image;
        this.id_vol = id_vol;
    }

    public Guides(String titre, String Pays, int Level, String image) {
        this.titre = titre;
        this.Pays = Pays;
        this.Level = Level;
        this.image = image;
    }

    public int getID_g() {
        return ID_g;
    }

    public String getTitre() {
        return titre;
    }

    public String getPays() {
        return Pays;
    }

    public int getLevel() {
        return Level;
    }

    public String getImage() {
        return image;
    }

    public Vol getVol() {
        return vol;
    }

    public int getId_vol() {
        return id_vol;
    }

    public void setID_g(int ID_g) {
        this.ID_g = ID_g;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setPays(String Pays) {
        this.Pays = Pays;
    }

    public void setLevel(int Level) {
        this.Level = Level;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    @Override
    public String toString() {
        return "Guides{" + "ID_g=" + ID_g + ", titre=" + titre + ", Pays=" + Pays + ", Level=" + Level + ", image=" + image + ", vol=" + vol + ", id_vol=" + id_vol + '}';
    }





}
