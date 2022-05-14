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
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Réponses;
import com.mycompany.services.ServiceReponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ShowRep extends Form{
    ShowRep instance;
    
    
    
    public Container addItem(Réponses s,Image img,Resources res ){
         int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        
     //container
            Container cell = new Container(BoxLayout.x());
             Label nameRep = new Label(s.getRéponse());
              
              // Label idSondg = new Label(Integer.toString(s.getQuestion_id()));
              
              
               //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce sondage ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
                if(QuestionService.getInstance().deleteQuestion(s.getQuestion_id())) {
                    new ShowFormasma(res).show();
                }
        });
        
          //Update icon 
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
            //System.out.println("hello update");
          //  new UpdateQst(s,res).show();
        });
        
              Container credentialsCtn = new Container(BoxLayout.x());
              credentialsCtn.addAll(nameRep);
              cell.addAll(credentialsCtn,lSupprimer,lModifier);
              
      
    
               
              return cell;
              
        }
           
      // List<Sondage> Sondages = new ArrayList<>();
   

    public ShowRep(int q, Form previous,Resources res) {
         List<Réponses> Réponses= ServiceReponse.getInstance().fetchReponse(q);
      
        instance=this;
       

        //custom
        this.setLayout(BoxLayout.y());
        this.setTitle("All Tasks");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeFormAsma(res).showBack();
        });
        
       
      
        //widgets
        SpanLabel sl = new SpanLabel();
   
                for (Réponses s : Réponses) {
                     String urlImage ="back-logo.jpeg"; 
                      
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
                   URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);    
                  System.out.println("reppppppppppppp"+ s);
                this.add(addItem(s,urlim,res)) ;
                        
                       
            }
            
        
//         
//           sl.setText(ts.fetchSondage().toString());
//          this.add(sl);

 
        }
        
  
}
