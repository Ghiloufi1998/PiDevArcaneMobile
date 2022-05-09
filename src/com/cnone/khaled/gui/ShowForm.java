/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.gui;

import com.cnone.khaled.entities.Sondage;
import com.cnone.khaled.entities.Task;
import com.cnone.khaled.services.SondageService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khaledguedria
 */
public class ShowForm extends Form {

    //var
    
   // SondageService ts = SondageService.getInstance();
      List<Sondage> Sondages = SondageService.getInstance().fetchSondage();

  
      // List<Sondage> Sondages = new ArrayList<>();
     public Container addItem(Sondage s,Image img){
         int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        
     //container
            Container cell = new Container(BoxLayout.x());
             Label nameCat = new Label(s.getCatégorie());
              Label nameSondg = new Label(s.getSujet());
              
              
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
                if(SondageService.getInstance().deleteSondage(s.getSondage_id())) {
                    new ShowForm().show();
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
            new UpdateForm(s).show();
        });
        
              Container credentialsCtn = new Container(BoxLayout.x());
              credentialsCtn.addAll(nameCat,nameSondg);
              cell.addAll(credentialsCtn,lSupprimer,lModifier);
              
      
    
               
              return cell;
              
        }
           

    public ShowForm() {
      
       
       

        //custom
        this.setLayout(BoxLayout.y());
        this.setTitle("All Tasks");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeForm().showBack();
        });
        
       
      
        //widgets
        SpanLabel sl = new SpanLabel();
   
                for (Sondage s : Sondages) {
                     String urlImage ="back-logo.jpeg";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
                   URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);     
                this.add(addItem(s,urlim)) ;}
        //end
//         
//           sl.setText(ts.fetchSondage().toString());
//          this.add(sl);

 
        }
        

    }

//}
