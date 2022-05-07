/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.gui;

import com.cnone.iheb.entities.Hebergement;
import com.cnone.iheb.entities.Task;
import com.cnone.khaled.services.HebergementService;
import com.cnone.khaled.services.TaskService;
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
public class UpdateForm extends Form {

    //var
    HebergementService th = HebergementService.getInstance();

    public UpdateForm(Resources res,int id) {

        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Modifier Hebergement");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeForm(res).showBack();
        });

        //Widgets
        TextField desc = new TextField("", "Description");
        TextField type = new TextField("", "Type");
        TextField dis = new TextField("", "Disponibilité");
        TextField ad  = new TextField("", "Adresse ");
        TextField img  = new TextField("", "Image ");
       // TextField statusTF = new TextField("", "Task's status [0 - 1]");
        
        
        Button submitBtn = new Button("Modifier");

        //actions
        submitBtn.addActionListener((evt) -> {
            if (th.updateHebergement(new Hebergement(id, desc.getText(), type.getText(), Integer.parseInt(dis.getText()), ad.getText(), img.getText()))) {
                Dialog.show("Success", "Task Updated successfully", "Got it", null);
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });

        //end
        this.addAll(desc, type , dis , ad ,img, submitBtn);

    }

}
