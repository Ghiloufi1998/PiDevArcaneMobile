/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnome.arcane.gui;
import com.cnome.arcane.entities.Guides;
import com.cnome.arcane.services.GuideService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;

import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author Ghiloufi
 */
public class ListeGuideFront extends SideMenu {

       ListeGuideFront instance;
    //private List<promotion> listprom;
     private List<Guides> listeguide;
     EncodedImage enc;
    private Form form ;
//    private Toolbar tb;
    private Container evenements;
    Form current;
    
    public ListeGuideFront(Resources res) {
        instance=this;
//        super(BoxLayout.y());
       Toolbar tb = getToolbar();
//		tb.setUIID("Toolbar");
        tb.addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e-> showOtherForm(res));
//        SpanLabel sp = new SpanLabel();
        List<Guides> list = GuideService.getInstance().fetchGuides();

       
        for (Guides g : list){
            
        setLayout(new FlowLayout(CENTER));
        Container cnt1 = new Container(BoxLayout.y());   
        Container cnt2 = new Container(BoxLayout.x());
        //ontainer cnt = new Container();
//            System.out.println("alllooo------"+article.getNom());
        Label SLnom = new Label(g.getTitre());
         Label SLlevel = new Label("Level : "+g.getLevel());
//        SpanLabel SLdesc = new SpanLabel(p.getDescription());
        Label SLrem = new Label(g.getPays());
        Button SLvoir = new Button("Voir Guide");
        EncodedImage enc = EncodedImage.createFromImage(Image.createImage(500, 500), true);
        String url = g.getImage();
      ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/")+1, url.length()), url,URLImage.RESIZE_SCALE_TO_FILL));
        SLvoir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                       new VoirGuideFront(g, instance).show();
            }
        });

        cnt2.add(SLnom);
        cnt2.add(SLlevel);
        cnt2.add(SLrem);
       
        cnt1.add(cnt2);
       cnt1.add(img);
        cnt1.add(SLvoir);
        
        

        
        add(cnt1);
        //search tbadel 3onwen tool bar
//prepare field
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

     @Override
    protected void showOtherForm(Resources res) {
       // new Home(res).show();
    }


    
}