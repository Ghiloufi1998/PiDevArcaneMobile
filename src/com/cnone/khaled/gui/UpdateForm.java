/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.gui;

import com.cnone.iheb.entities.Hebergement;
import com.cnone.iheb.entities.Task;
import com.cnone.khaled.services.HebergementService;
import com.cnone.khaled.services.TaskService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author khaledguedria
 */
public class UpdateForm extends Form {

    //var
    HebergementService th = HebergementService.getInstance();

    public UpdateForm(Resources res,int id,Hebergement h) {

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
        
      EncodedImage enc = EncodedImage.createFromImage(Image.createImage(500, 500), true);
        
        String url = "file:///C:/Users/Acer/exp/AboveBeyondSymfony-module_iheb/public/assets/qr-code/"+h.getDescription()+".png";
        //C:\Users\Acer\exp\AboveBeyondSymfony-module_iheb\public\assets\qr-code
        System.out.println(h.getImage());
        ImageViewer img1 = new ImageViewer(URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(500, 500), true), url.substring(url.lastIndexOf("/")+1, url.length()), url,URLImage.RESIZE_SCALE_TO_FILL)); 
      // ImageViewer v=new ImageViewer(URLImage.createToStorage(enc, url, url, adapter));
       
       
       
        Button submitBtn = new Button("Modifier");

        //actions
        submitBtn.addActionListener((evt) -> {
            if (th.updateHebergement(new Hebergement(id, desc.getText(), type.getText(), Integer.parseInt(dis.getText()), ad.getText(), img.getText()))) {
                Dialog.show("Avec succes", "Hébergement modifié", "ok", null);
            } else {
                Dialog.show("Echec", "Erreur", "ok", null);
            }
        });

        //end
        this.addAll(desc, type , dis , ad ,img, img1, submitBtn); 

    }

}
