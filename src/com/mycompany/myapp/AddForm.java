/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.mycompany.entities.Hebergement;
import com.mycompany.entities.Task;
import com.mycompany.services.HebergementService;
import com.mycompany.services.TaskService;
import com.codename1.capture.Capture;
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
import java.io.IOException;

/**
 *
 * @author khaledguedria
 */
public class AddForm extends Form {

    //var
    HebergementService th = HebergementService.getInstance();
    String path;
  
//String path2;
String photo;

    public AddForm(Resources res) {

        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Ajouter Hébergement");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeFormIheb(res).showBack();
        });

        //Widgets
        TextField desc = new TextField("", "Description");
        TextField type = new TextField("", "Type");
        TextField dis = new TextField("", "Disponibilité");
        TextField ad  = new TextField("", "Adresse ");
       /// TextField img  = new TextField("", "Image ");
      Button btCapture = new Button("Choisir Image");
        Label image = new Label();
       btCapture.addActionListener(e->{
          path = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
       // path2 = path.substring(48,path.length());
            System.out.println(path);
             int pod = path.indexOf("/", 4);
                System.out.println(pod);

                String news = path.substring(pod + 43, path.length());
                System.out.println(news);
                this.photo = news;
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
        
        Button submitBtn = new Button("Envoyer");

        //actions
        submitBtn.addActionListener((evt) -> {
            if (th.addHebergement(new Hebergement(desc.getText(),type.getText(),Integer.parseInt(dis.getText()),ad.getText(), this.photo  )      )) {
                Dialog.show("Avec succés", "Hébergement ajouté", "ok", null);
            } else {
                Dialog.show("Echec", "Erreur!!", "ok", null);
            }
        });

        //end
        this.addAll(desc, type , dis , ad ,image, submitBtn, btCapture);
        
    }

}
