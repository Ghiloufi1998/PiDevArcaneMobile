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

    public HomeForm(Resources res) {
        
        //custom
        this.setLayout(BoxLayout.yCenter());
        this.setTitle("Home");
        
        //widgets
        Button addTaskBtn = new Button("Ajouter Hebergement");
        Button showTaskBtn = new Button("Afficher tous les Hébergements");
        
        //actions
        addTaskBtn.addActionListener((evt) -> {
           
            new AddForm(res).show();
            
        });
        //..
        showTaskBtn.addActionListener((evt) -> {
           
            new ShowFormHeb(res).show();
            
        });
        
        //end
        this.addAll(addTaskBtn, showTaskBtn);
        
    }
    
    
    
}
