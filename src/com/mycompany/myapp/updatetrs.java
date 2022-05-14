/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.capture.Capture;
import com.mycompany.entities.Hebergement;
import com.mycompany.entities.Task;
import com.mycompany.entities.Transport;
import com.mycompany.services.HebergementService;
import com.mycompany.services.TaskService;
import com.mycompany.services.TransportService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author khaledguedria
 */
public class updatetrs extends Form {
String path;
String photo;
    //var
    TransportService th = TransportService.getInstance().getInstance();

    public updatetrs(Resources res,int id,Transport t, Hebergement h) {

        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Modifier Transport");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeFormIheb(res).showBack();
        });

        //Widgets
       
        
        TextField desc = new TextField("", "Description");
        TextField type = new TextField("", "Type");
        TextField dis = new TextField("", "Disponibilité");
        
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
       // TextField statusTF = new TextField("", "Task's status [0 - 1]");
        
      EncodedImage enc = EncodedImage.createFromImage(Image.createImage(500, 500), true);
        
        String url = "file:///C:/Users/Acer/exp/AboveBeyondSymfony-module_iheb/public/assets/qr-code/"+t.getDescription()+".png";
        //C:\Users\Acer\exp\AboveBeyondSymfony-module_iheb\public\assets\qr-code
       // System.out.println(h.getImage());
        ImageViewer img1 = new ImageViewer(URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(500, 500), true), url.substring(url.lastIndexOf("/")+1, url.length()), url,URLImage.RESIZE_SCALE_TO_FILL)); 
      // ImageViewer v=new ImageViewer(URLImage.createToStorage(enc, url, url, adapter));
       
       
       
        Button submitBtn = new Button("Modifier");

        //actions
        submitBtn.addActionListener((evt) -> {
            if (th.updatetrs(new Transport(id, type.getText(), desc.getText(), Integer.parseInt(dis.getText()), 0, this.photo,h.getHebergement_id()))) {
                Dialog.show("Avec succes", "Transport modifié", "ok", null);
            } else {
                Dialog.show("Echec", "Erreur", "ok", null);
            }
        });

        //end
        this.addAll(desc, type , dis ,btCapture, img1, submitBtn); 

    }

}
