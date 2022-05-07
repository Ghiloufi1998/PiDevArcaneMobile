/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnome.arcane.gui;

import com.cnome.arcane.entities.Guides;
import com.cnome.arcane.services.GuideService;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Ghiloufi
 */
public class ModifGuideForm extends Form{
    
 //var
    GuideService gs = GuideService.getInstance();

    public ModifGuideForm(Resources res,int id) {

       this.setLayout(BoxLayout.y());
        this.setTitle("Modifier Guides");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ListeGuideFront(res).showBack();
        });


        //Widgets
        TextField titre = new TextField("", "Titre");
        TextField Pays = new TextField("", "Pays");
        TextField Level = new TextField("", "Level");
        TextField image = new TextField("", "image");
        TextField idvol = new TextField("", "Numéro Vol");
        
       // TextField statusTF = new TextField("", "Task's status [0 - 1]");
        
        
        Button submitBtn = new Button("Modifier");

        //actions
        submitBtn.addActionListener((evt) -> {
            if (gs.updateGuide(new Guides(id,titre.getText(),Pays.getText(),Integer.parseInt(Level.getText()),image.getText(),Integer.parseInt(idvol.getText() ))      )){
               Dialog.show("Succès", "Guide Modifié ", "Bien", null);
            } else {
                Dialog.show("Erreur", "Erreur lors de modification", "Bien", null);
            }
        });

        //end
        this.addAll(titre, Pays , Level , image,idvol, submitBtn);

    }
    
}
