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
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.util.List;

/**
 *
 * @author khaledguedria
 */
public class ShowFormHeb extends Form {

    //var
   ShowFormHeb instance;
    //private List<promotion> listprom;
     private List<Hebergement> lh;
     EncodedImage enc;
    private Form form ;
//    private Toolbar tb;
    private Container evenements;
    Form current;
        HebergementService th = HebergementService.getInstance();


    public ShowFormHeb(Resources res) {

        instance=this;
//        super(BoxLayout.y());
       Toolbar tb = getToolbar();
//		tb.setUIID("Toolbar");
      tb.addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e-> showOtherForm(res));
//        SpanLabel sp = new SpanLabel();
        List<Hebergement> list = HebergementService.getInstance().fetchHbrs();

       
        for (Hebergement h : list){
            
        setLayout(new FlowLayout(CENTER));
        Container cnt1 = new Container(BoxLayout.y());   
        Container cnt2 = new Container(BoxLayout.x());
        //ontainer cnt = new Container();
//            System.out.println("alllooo------"+article.getNom());
        Label SLdesc = new Label(h.getDescription());
         Label SLadd = new Label("Adresse : "+h.getAdresse());
         Label SLType = new Label("Type : "+h.getType());
//        SpanLabel SLdesc = new SpanLabel(p.getDescription());
       // Label SLrem = new Label(h.getDisponibilité());
        Button Modifier = new Button("Modifier ");
        Button Supprimer = new Button("Supprimer ");
        EncodedImage enc = EncodedImage.createFromImage(Image.createImage(500, 500), true);
        String url = "file:///C:/Users/Acer/exp/AboveBeyondSymfony-module_iheb/public/uploads/"+h.getImage();
      ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/")+1, url.length()), url,URLImage.RESIZE_SCALE_TO_FILL));
        Modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                       new UpdateForm(res,h.getHebergement_id()).show();
            }
        });
        
        Supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                       if (th.deleteHebergement(h.getHebergement_id())) {
                Dialog.show("Success", "Task Deleted successfully", "Got it", null);
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            };
            new HomeForm(res).showBack();
            }
        });
        
        

        cnt2.add(SLdesc);
        cnt2.add(SLadd);
        cnt2.add(SLType);
       
        cnt1.add(cnt2);
       cnt1.add(img);
        cnt1.add(Modifier);
        cnt1.add(Supprimer);
        
        

        
        add(cnt1);
        }
    }
    
    protected void showOtherForm(Resources res) {
        new HomeForm(res).show();
    }
    
   

}
