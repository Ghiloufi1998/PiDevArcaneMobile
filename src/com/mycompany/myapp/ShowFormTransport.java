/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.mycompany.entities.Hebergement;
import com.mycompany.entities.Task;
import com.mycompany.entities.Transport;
import com.mycompany.services.HebergementService;
import com.mycompany.services.TaskService;
import com.mycompany.services.TransportService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
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
public class ShowFormTransport extends Form {

    //var
   ShowFormTransport instance;
    //private List<promotion> listprom;
     private List<Transport> lt;
     EncodedImage enc;
    private Form form ;
//    private Toolbar tb;
    private Container evenements;
    Form current;
        TransportService th = TransportService.getInstance();


    public ShowFormTransport(Resources res, Hebergement o) {

        instance=this;
        String url;
//        super(BoxLayout.y());
       Toolbar tb = getToolbar();
//		tb.setUIID("Toolbar");
      tb.addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e-> showOtherForm(res));
//        SpanLabel sp = new SpanLabel();

        
        List<Transport> list = TransportService.getInstance().fetchtrs(o.getHebergement_id());

       
        for (Transport h : list){
           
        setLayout(new FlowLayout(CENTER));
        Container cnt1 = new Container(BoxLayout.y());   
        Container cnt2 = new Container(BoxLayout.x());
        //ontainer cnt = new Container();
//            System.out.println("alllooo------"+article.getNom());
        Label SLdesc = new Label(h.getDescription());
         Label SLadd = new Label("Description : "+h.getDescription());
         Label SLType = new Label("Type : "+h.getType());
//        SpanLabel SLdesc = new SpanLabel(p.getDescription());
       // Label SLrem = new Label(h.getDisponibilité());
        Button Modifier = new Button("Modifier ");
        Button Supprimer = new Button("Supprimer ");
        EncodedImage enc = EncodedImage.createFromImage(Image.createImage(500, 500), true);
     
        
             url =h.getImage(); 
             
        
       ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/")+1, url.length()), url,URLImage.RESIZE_SCALE_TO_FILL));

  
        Modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                       new updatetrs(res,h.getTransport_id(),h,o).show();
            }
        });
        
        Supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                       if (th.deletefts(h.getTransport_id())) {
                Dialog.show("Avec succes", "Transport supprimé", "ok", null);
            } else {
                Dialog.show("Echec", "Erreur!", "ok", null);
            };
            new ShowFormTransport(res,o).showBack();
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
                TextField searchField;
                searchField = new TextField("", "rechercher...");
                //searchField.getHintLabel().setUIID("Title");
                //searchField.setUIID("Title");
                //tb.setGlobalToolbar(true);
                tb.setTitleComponent(searchField);
                //if field content changed
                searchField.addDataChangeListener((i1, i2) -> {
                String t = searchField.getText();
                if(t.length() < 1) {
                for(Component cmp : getContentPane()) {
                cmp.setHidden(false);
                cmp.setVisible(true);
                }
                } else {
                t = t.toLowerCase();
                for(Component cmp: getContentPane()) {
                //tekhou el val ta3 el champ : champ li 3malt 3lih el recherche type span label (emplacement : container->container->spanlabel )
                String val = ((Label) ((Container)((Container) cmp).getComponentAt(0)).getComponentAt(0)).getText();
                System.out.println( val );
                boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
                cmp.setHidden(!show);
                cmp.setVisible(show);
                }
                }
                getContentPane().animateLayout(250);
                });
        
        
        
        
        }
        
        
    }
    
    protected void showOtherForm(Resources res) {
        new HomeFormIheb(res).show();
    }
    
   

}
