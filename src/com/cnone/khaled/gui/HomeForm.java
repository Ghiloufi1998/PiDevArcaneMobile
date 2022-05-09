/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


/**
 *
 * @author khaledguedria
 */
public class HomeForm extends Form{
  
    //var

    public HomeForm() {
        
        //custom
//        this.setLayout(BoxLayout.yCenter());
//        this.setTitle("Home");
        
        //widgets
        Button addTaskBtn = new Button("Ajouter sondage");
        Button showTaskBtn = new Button("Gérer Sondages");
        
        //actions
        addTaskBtn.addActionListener((evt) -> {
           
            new AddForm().show();
            
        });
        //..
        showTaskBtn.addActionListener((evt) -> {
           
            new ShowForm().show();
            
        });
        
        //end
        this.addAll(addTaskBtn, showTaskBtn);
        
    }

   

    
    
    
}
