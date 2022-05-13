/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.gui;

import com.cnone.iheb.entities.Task;
import com.cnone.iheb.entities.Hebergement;
import com.cnone.khaled.services.HebergementService;
import com.cnone.khaled.services.TaskService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.List;

/**
 *
 * @author khaledguedria
 */
public class ShowHForm extends Form {

    //var
    HebergementService ts = HebergementService.getInstance();
     List<Hebergement> hbr;
    private Form current;
    private Resources theme;


    public ShowHForm(Resources res) {

        //custom
        this.setLayout(BoxLayout.y());
        this.setTitle("Liste Hebergements");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeForm(res).showBack();
        });

        //widgets
        SpanLabel sl = new SpanLabel();
        sl.setText(ts.fetchHbrs().toString());

        //end
        this.add(sl);

        
        List<Hebergement> hs= ts.fetchHbrs();
        if(current != null){
            current.show();
            return;
        }
        Form f = new Form("Contacts", BoxLayout.y());
        for (Hebergement h : hs) {
           CustomCell mCell = new CustomCell(theme, h);
           f.addAll(mCell, new Label(" ", "Separator"));
           
           //on Cell Click 
           mCell.addPointerPressedListener((evt) -> {
               
               //Form 2
               Form f2 = new Form(h.getDescription(), BoxLayout.xCenter());
               
               f2.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (ev) -> {
                   f.showBack();
               });
               
               Container ctn = new Container(BoxLayout.y());
               ctn.addAll(new ImageViewer(theme.getImage(h.getImage())), new Label(h.getType()), new Label(h.getAdresse()), new Label(h.getType()));
               ctn.addAll(new Label(h.getType()), new Label(h.getAdresse()), new Label(h.getType()));
               f2.add(ctn);
               f2.show();
               
           });
           }
        
        f.show();


    }

}
