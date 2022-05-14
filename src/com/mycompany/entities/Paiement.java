/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author bensa
 */
public class Paiement {
    private int ID_PAi;
    private Date Date;
    private int Montant;
    private String Mode_Pay;
    private int ID_fac;
    private Facture fac;
    public Paiement() {
    }

    public Facture getFac() {
        return fac;
    }

    public void setFac(Facture fac) {
        this.fac = fac;
    }

    public Paiement(Facture fac) {
        this.fac = fac;
    }

    public Paiement(Date Date, int Montant, String Mode_Pay, Facture fac) {
        this.Date = Date;
        this.Montant = Montant;
        this.Mode_Pay = Mode_Pay;
        this.fac = fac;
    }

    public Paiement(Date Date, int Montant, String Mode_Pay) {
        this.Date = Date;
        this.Montant = Montant;
        this.Mode_Pay = Mode_Pay;
    }

    public Paiement(int ID_PAi, Date Date, int Montant, String Mode_Pay) {
        this.ID_PAi = ID_PAi;
        this.Date = Date;
        this.Montant = Montant;
        this.Mode_Pay = Mode_Pay;
    }

    public Paiement(int po, java.util.Date x, String prix, String z) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Paiement(int id_pAi, int po, java.util.Date x, String prix, String z) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getID_PAi() {
        return ID_PAi;
    }

    public void setID_PAi(int ID_PAi) {
        this.ID_PAi = ID_PAi;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public int getMontant() {
        return Montant;
    }

    public void setMontant(int Montant) {
        this.Montant = Montant;
    }

    public String getMode_Pay() {
        return Mode_Pay;
    }

    public void setMode_Pay(String Mode_Pay) {
        this.Mode_Pay = Mode_Pay;
    }

    public int getID_fac() {
        return ID_fac;
    }

    public void setID_fac(int ID_fac) {
        this.ID_fac = ID_fac;
    }

    @Override
    public String toString() {
        return "Paiement{" + "ID_PAi=" + ID_PAi + ", Date=" + Date + ", Montant=" + Montant + ", Mode_Pay=" + Mode_Pay + ", ID_fac=" + ID_fac + '}';
    }
    
    
    
    
}
