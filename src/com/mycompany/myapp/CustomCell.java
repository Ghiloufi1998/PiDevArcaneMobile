///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.myapp;
//
//import com.cnone.iheb.entities.Hebergement;
//import com.codename1.components.ImageViewer;
//import com.codename1.ui.Container;
//import com.codename1.ui.Label;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.util.Resources;
//
///**
// *
// * @author khaledguedria
// */
//public class CustomCell extends Container{
//
//    
//    //constructor
//    public CustomCell(Resources theme, Hebergement h) {
//        
//        //this
//        this.setLayout(BoxLayout.x());
//        
//        
//        //conponents
//        Label descLB = new Label(h.getDescription());
//        
//        Label adssLB = new Label(h.getAdresse());
//        Label typeLB = new Label(h.getType());
//       
//        Label dispLB = new Label( Integer.toString(h.getDisponibilité())  );
//       // emailLB.getAllStyles().setFgColor(0x808080);
//        
//        Container credentialsCtn = new Container(BoxLayout.y());
//        credentialsCtn.addAll(descLB, adssLB,typeLB,dispLB );
//        
////        ImageViewer imgViewer = new ImageViewer(theme.getImage(h.getImage()));
//        
////        this.addAll(imgViewer, credentialsCtn);
//          this.addAll(credentialsCtn);
//        
//        
//    }
//        
//    
//    
//}
