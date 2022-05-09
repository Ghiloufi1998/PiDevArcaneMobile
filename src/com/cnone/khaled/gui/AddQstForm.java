/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnone.khaled.gui;

import com.cnone.khaled.entities.Questions;
import com.cnone.khaled.entities.Sondage;
import com.cnone.khaled.services.QuestionService;
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
 * @author user
 */
public class AddQstForm extends Form{
    //var
    QuestionService qs = QuestionService.getInstance();
    
     public AddQstForm(Questions q, Resources res) {

        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Questions");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeForm().showBack();
        });

        //Widgets
        TextField qst = new TextField("", "qst");
        TextField type = new TextField("", "type");
        
        Button submitBtn = new Button("Ajouter");

        //actions
        submitBtn.addActionListener((evt) -> {
            if (qs.addQuestion(new Questions (qst.getText(),q.getSondage(),type.getText()))) {
                Dialog.show("Success", "Task Inserted successfully", "Got it", null);
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });

        //end
        this.addAll(qst,type, submitBtn);

    }

}
