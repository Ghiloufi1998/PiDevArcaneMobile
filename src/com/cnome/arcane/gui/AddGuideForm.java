/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnome.arcane.gui;

import com.cnome.arcane.entities.Guides;
import com.cnome.arcane.services.GuideService;
import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;


/**
 *
 * @author Ghiloufi
 */
public class AddGuideForm extends Form{
    
 //var
    GuideService gs = GuideService.getInstance();
String path;
    public AddGuideForm(Resources Res) {

       this.setLayout(BoxLayout.y());
        this.setTitle("Ajouter Guides");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ListeGuideFront(Res).showBack();
        });


        //Widgets
        TextField titre = new TextField("", "Titre");
        TextField Pays = new TextField("", "Pays");
        TextField Level = new TextField("", "Level");
       // TextField image = new TextField("", "image");
        Button btCapture = new Button("Choisir Image");
        Label image = new Label();
        
        btCapture.addActionListener(e->{
        path = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
          
          if(path != null ){
              try {
                  Image img = Image.createImage(path);
                  image.setIcon(img);
                  this.revalidate();
              } catch (IOException ex) {
                  ex.getStackTrace();
              }
              
          }
        });
        
        TextField idvol = new TextField("", "Numéro Vol");
        
       // TextField statusTF = new TextField("", "Task's status [0 - 1]");
        
        
        Button submitBtn = new Button("Ajouter");

        //actions
        submitBtn.addActionListener((evt) -> {
            if (gs.addGuide(new Guides(titre.getText(),Pays.getText(),Integer.parseInt(Level.getText()),path,Integer.parseInt(idvol.getText() ))      )){
                Dialog.show("Succès", "Guide Ajouté ", "Bien", null);
            } else {
                Dialog.show("Erreur", "Erreur lors d'ajout", "Bien", null);
            }
        });

        //end
        this.addAll(titre, Pays , Level ,btCapture, image,idvol, submitBtn);

    }
    
}
