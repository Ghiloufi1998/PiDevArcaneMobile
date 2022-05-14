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

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.util.Resources;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author user
 */
public class AddQstForm extends Form{
    //var
    QuestionService tq = QuestionService.getInstance();
    
     public AddQstForm( Sondage q, Form previous,Resources res) {

        //CUSTOM
         System.out.println("hedha howa " +q);
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Questions");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeFormAsma(res).showBack();
        });

        //Widgets
//        ComboBox<Map<String, Object>> combo = new ComboBox<> (
//          createListEntry("A Game of Thrones", "1996"),
//          createListEntry("A Clash Of Kings", "1998"),
//          createListEntry("A Storm Of Swords", "2000"),
//          createListEntry("A Feast For Crows", "2005"),
//          createListEntry("A Dance With Dragons", "2011"),
//          createListEntry("The Winds of Winter", "2016 (please, please, please)"),
//          createListEntry("A Dream of Spring", "Ugh"));
//  
//  combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
// 

        
        TextField qst = new TextField("", "entrer Objet!!");
        qst.setUIID("TextFieldBlack");
        addStringValue("question",qst);
        
        TextField type = new TextField("", "entrer description!!");
        type.setUIID("TextFieldBlack");
        addStringValue("type",type);
        
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        
//        TextField qst = new TextField("", "qst");
//        TextField type = new TextField("", "type");
//        
//        Button submitBtn = new Button("Ajouter");

        //actions
        btnAjouter.addActionListener((evt) -> {
            
              Questions qs=new Questions(q.getSondage_id(),qst.getText(), type.getText());
            if (tq.addQuestion(qs)    ) {
                Dialog.show("Success", "Task Inserted successfully", "Got it", null);
                System.out.println(qs);
             //   new AddQstForm(s,instance,res).show();
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
                    
                    System.out.println("data  question == "+qs);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                   // QuestionService.getInstance().addQuestion(qs);
                    
            
            
            
        });
//            if (qs.addQuestion(new Questions (qst.getText(),q,type.getText()))) {
//                Dialog.show("Success", "Task Inserted successfully", "Got it", null);
//                System.out.println(q);
//                System.out.println(qst.getText()+""+type.getText());
//            } else {
//                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
//            }
//        });

        //end
      //  this.addAll(qst,type, submitBtn);

    }
  private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
      //  add(createLineSeparator(0xeeeeee));
    }

}
