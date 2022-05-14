/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


/**
 *
 * @author khaledguedria
 */
public class HomeFormAsma extends Form{
  
    //var

    public HomeFormAsma(Resources res) {
        
        //custom
//        this.setLayout(BoxLayout.yCenter());
//        this.setTitle("Home");
        
        //widgets
        Button addTaskBtn = new Button("Ajouter sondage");
        Button showTaskBtn = new Button("Gérer Sondages");
                Button Retour = new Button("\nRetour ");

 Retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 new NewsfeedForm(res).showBack();
            }
        });
        add(Retour);
        
        //actions
        addTaskBtn.addActionListener((evt) -> {
           
            new AddFormasma(res).show();
            
        });
        //..
        showTaskBtn.addActionListener((evt) -> {
           
            new ShowFormasma(res).show();
            
        });
        
        //end
        this.addAll(addTaskBtn, showTaskBtn);
        
    }

   

    
    
    
}
