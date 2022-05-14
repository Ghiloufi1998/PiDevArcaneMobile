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
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;


/**
 *
 * @author user
 */
public class OneSondage extends Form{
    OneSondage instance;
    public OneSondage(Sondage s, Form previous, Resources res){
       
        Sondage son = new Sondage();
    
        setTitle(son.getCatégorie());
        setLayout(new FlowLayout(CENTER));
        Container c= new Container(BoxLayout.y());
      
        c.add(new Label("Sujet="+(s.getSujet())+""));
        c.add(new Label("Catégorie="+(s.getCatégorie())+""));
        c.add(new Label("ID="+(s.getSondage_id())+""));
        
        
          Button test = new Button("Ajouter Questions");
          Button test2 = new Button("Voir Questions");
                 
             
                        
                          this.add(test);
                              test.addActionListener(new ActionListener() {
 
                         @Override
                         public void actionPerformed(ActionEvent evt) {
                            new AddQstForm (s, instance,res).show();             
                         }
                         
                           });
                          this.add(test2);
                              test2.addActionListener(new ActionListener() {
 
                         @Override
                         public void actionPerformed(ActionEvent evt) {
                            new ShowQst (s.getSondage_id(),instance,res).show();             
                         }
        });

      
//        c.add(p.getArticles().toString());
        
        this.add(c);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,new ActionListener() {
            

            @Override
            public void actionPerformed(ActionEvent evt) {
                previous.showBack();            }
        });
    }
    
    
    
}
    
