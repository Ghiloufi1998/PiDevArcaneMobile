/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnome.arcane.gui;

import com.cnome.arcane.entities.Guides;
import com.cnome.arcane.services.GuideService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RGBImage;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.OutputStream;
/**
 *
 * @author Ghiloufi
 */
public class VoirGuideFront extends Form {
      GuideService gs = GuideService.getInstance();
 public VoirGuideFront(Guides guide,Form previous,Resources res) {
                Form hi = new Form();

        //Article article = new entite.Article();
        EncodedImage enc = EncodedImage.createFromImage(Image.createImage(800, 800), true);
     String url = guide.getImage();
    ImageViewer imgg = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/")+1, url.length()), url,URLImage.RESIZE_FAIL));
        
        
        setTitle(guide.getTitre()+"     "+guide.getPays());
        setLayout(new FlowLayout(CENTER));
        Container c= new Container(BoxLayout.y());
      c.add(imgg);
        System.out.println("nchalah-------"+guide.getTitre());
        //c.add(new Label(article.getPrix()+"DT"));
        Container cx= new Container(BoxLayout.x());
        cx.add(new Label(guide.getTitre()));
        cx.add(new Label("     LEVEL:"+guide.getLevel()));
        c.add(cx);
        Container cdes= new Container(BoxLayout.x());
        cdes.add(new Label("Description:"));
        c.add(cdes);
        Container cntdes= new Container(BoxLayout.x());
        
        cntdes.add(new SpanLabel(guide.getTitre()));
        ShareButton sb = new ShareButton();
        c.add(cntdes);

        
        Slider s = new Slider();
        s.setEditable(true);
        s.setMinValue(0);
        s.setMaxValue(5);
        s.setIncrements(1);
      // s.setProgress(5);
      
      /////////rating///////////
   
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style style = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, style).toImage();
    style.setOpacity(100);
    style.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, style).toImage();
    initStarRankStyle(s.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(s.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(s.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(s.getSliderFullUnselectedStyle(), fullStar);
    s.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
     Button btnE = new Button("Noter");
    // Label l1 = new Label ("Vous pouvez noter cette formation");
      Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
      
     Label l = new Label ("Note");
       l.getUnselectedStyle().setFont(mediumBoldSystemFont);
     s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                    l.setText(Integer.toString(s.getProgress(evt)));
              
            }

             
        });
          
     Button Supprimer = new Button("\nSupprimer ");
      btnE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
         
        Dialog.show("success", "Notation est Bien ajoutée", new Command("OK"));
               
            } 
      } );
     Supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                       if (gs.deleteGuide(guide.getID_g())) {
                Dialog.show("Success", "Task Deleted successfully", "Got it", null);
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            };
            new ListeGuideFront(res).showBack();
            }
        });
     Container cn = new Container(BoxLayout.x());
     cn.add(s);
     cn.add(l);
    cn.add(btnE);
    cn.add(Supprimer);
c.add(cn);

        c.add(hi);

        
   
        
        this.add(c);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                previous.showBack();
            }
        });
    }
     public void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
    
}
      
}