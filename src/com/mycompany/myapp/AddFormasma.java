/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.mycompany.entities.Sondage;
import com.mycompany.services.SondageService;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.List;


/**
 *
 * @author khaledguedria
 */
public class AddFormasma extends Form {
           AddFormasma instance;
    //var
    SondageService ts = SondageService.getInstance();
        List<Sondage> list = SondageService.getInstance().fetchSondage();

    public AddFormasma(Resources res) {

        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Task");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeFormAsma(res).showBack();
        });

        //Widgets
        TextField Catg = new TextField("", "Catégorie");
        TextField Sujet = new TextField("", "Sujet");
        Button submitBtn = new Button("Submit");

        //actions
        submitBtn.addActionListener((evt) -> {
            Sondage s=new Sondage(Sujet.getText(), Catg.getText());
            if (ts.addSondage(s)    ) {
                Dialog.show("Success", "Task Inserted successfully", "Got it", null);
                System.out.println(s);
                new AddQstForm(s,instance,res).show();
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });

        //end
        this.addAll(Catg,Sujet, submitBtn);

    }

//    AddFormasma(Resources res) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    

}
