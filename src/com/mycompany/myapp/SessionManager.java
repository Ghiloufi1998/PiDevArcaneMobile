/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.Preferences;
import java.util.Date;

/**
 *
 * @author Abirn
 */
public class SessionManager {
    
    
    public static Preferences pref ; //  Memoire pour enregister data 
    
    
    
    private static int id ; 
    private static String name ; 
    private static String fname ;
    private static String email; 
    private static String passowrd ;
    private static String image;
    private static String num;
    private static String birthday;
    private static String gender;
    

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// Id user connecté
    }

    public static void setId(int id) {
        pref.set("id",id);//Enregistrer id user connecté et donner un identifiant "id";
    }

    public static String getUserName() {
        return pref.get("name",name);
    }

    public static void setUserName(String name) {
         pref.set("name",name);
    }

       public static String getUserFname() {
        return pref.get("fname",fname);
    }

    public static void setUserFname(String fname) {
         pref.set("fname",fname);
    }

    
    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }

    public static String getImage() {
        return pref.get("image",image);
    }

    public static void setImage(String image) {
         pref.set("image",image);
    }
    
    
    
    
    public static String getGender() {
        return pref.get("gender",gender);
    }

    public static void setGender(String gender) {
         pref.set("gender",gender);
    }
     public static String getBirthday() {
        return pref.get("birthday",birthday);
    }
    public static void setBirthday(String birthday) {
        pref.set("birthday",birthday);
    }
     public static String getNum(){
        return pref.get("num",num);
    }
    public static void setNum(String num) {
        pref.set("num",num);
    }
    
}
