/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.mycompany.entities.Questions;
import com.mycompany.entities.Sondage;
import com.mycompany.services.SondageService;
import com.mycompany.services.QuestionService;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


/**
 *
 * @author user
 */
public class UpdateQst extends Form {
       QuestionService ts = QuestionService.getInstance(); 
      UpdateQst instance;
    public UpdateQst(Questions s,Resources res) {
        
   
         //CUSTOM
       this.setLayout(BoxLayout.y());
        this.setTitle("Modifier Questions");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeFormAsma(res).showBack();
        });
        
        TextField qst = new TextField(s.getQuestion());
        TextField type= new TextField(s.getType());
         Button submitBtn = new Button("Modifier");
 
      
          //actions
        submitBtn.addActionListener((evt) -> {
            if (ts.updateQuestion(new Questions(qst.getText(), type.getText(),s.getQuestion_id()) )    ) {
                Dialog.show("Success", "Sondage updated successfully", "Got it", null);
                 new ShowFormasma(res).show();
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });

        //end
       this.addAll(qst,type, submitBtn);

        
        
        
       
        
    }
}
