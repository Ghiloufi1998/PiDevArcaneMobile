/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.gui;

import com.cnone.khaled.entities.Sondage;
import com.cnone.khaled.entities.Task;
import com.cnone.khaled.services.SondageService;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author khaledguedria
 */
public class AddForm extends Form {

    //var
    SondageService ts = SondageService.getInstance();

    public AddForm() {

        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Task");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeForm().showBack();
        });

        //Widgets
        TextField Catg = new TextField("", "Catégorie");
        TextField Sujet = new TextField("", "Sujet");
        Button submitBtn = new Button("Submit");

        //actions
        submitBtn.addActionListener((evt) -> {
            if (ts.addSondage(new Sondage(Sujet.getText(), Catg.getText()) )    ) {
                Dialog.show("Success", "Task Inserted successfully", "Got it", null);
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });

        //end
        this.addAll(Catg,Sujet, submitBtn);

    }

//    AddForm(Resources res) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    

}
