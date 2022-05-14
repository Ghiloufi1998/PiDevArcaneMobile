/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Cours;
import com.mycompany.entities.Guides;
import com.mycompany.services.CoursService;
import com.mycompany.services.GuideService;
import java.util.List;

/**
 * The aceeuil form
 *
 * @author Abirn
 */
public class ViewCoursForm extends BaseForm {
    CoursService ts = CoursService.getInstance();

    public ViewCoursForm(Cours cu,Form previous,Resources res) {
          super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        Toolbar tbb = new Toolbar();
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Above And Beyond Guide");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
//        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("blackbar.png"), spacer1, "", "",  "Voyagez autrement");
//        addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
//                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
     //   Container x =  new Container(BoxLayout.x());
        ButtonGroup barGroup = new ButtonGroup();
        
        RadioButton Guide = RadioButton.createToggle("Guide", barGroup);
        Guide.setUIID("SelectBar");
        RadioButton Reservation = RadioButton.createToggle("Réservation", barGroup);
        Reservation.setUIID("SelectBar");
        RadioButton Sondage = RadioButton.createToggle("Sondage", barGroup);
        Sondage.setUIID("SelectBar");
        
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, Guide, Reservation, Sondage),
                FlowLayout.encloseBottom(arrow)
        ));
        
        Guide.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(Guide, arrow);
        });
        bindButtonSelection(Guide, arrow);
        bindButtonSelection(Reservation, arrow);
        bindButtonSelection(Sondage, arrow);
       // bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        
        
        
        
        Form hi = new Form();
          //Article article = new entite.Article();
        EncodedImage enc = EncodedImage.createFromImage(Image.createImage(1900, 1900), true);
     String url = cu.getImage();
    ImageViewer imgg = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/")+1, url.length()), url,URLImage.RESIZE_FAIL));
        
        
        setTitle(cu.getTitre()+"         "+cu.getType());
        setLayout(new FlowLayout(CENTER));
        Container c= new Container(BoxLayout.y());
        c.add(imgg);
        System.out.println("nchalah-------"+cu.getTitre());
        //c.add(new Label(article.getPrix()+"DT"));
        Container cx= new Container(BoxLayout.x());
        cx.add(new Label("Titre guide\n    :"+cu.getTitre()));
        cx.add(new Label("     Type de Cours :"+cu.getType()));
        c.add(cx);
        Container cntdes= new Container(BoxLayout.x());
        
       
      //  ShareButton sb = new ShareButton();
        c.add(cntdes);
      //  Slider s = new Slider();
    //    s.setEditable(true);
     //   s.setMinValue(0);
     //   s.setMaxValue(5);
    //    s.setIncrements(1);
      // s.setProgress(5);
      
      /////////rating///////////
   
//    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
//            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
//    Style style = new Style(0xffff33, 0, fnt, (byte)0);
//    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, style).toImage();
//    style.setOpacity(100);
//    style.setFgColor(0);
//    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, style).toImage();
//    initStarRankStyle(s.getSliderEmptySelectedStyle(), emptyStar);
//    initStarRankStyle(s.getSliderEmptyUnselectedStyle(), emptyStar);
//    initStarRankStyle(s.getSliderFullSelectedStyle(), fullStar);
//    initStarRankStyle(s.getSliderFullUnselectedStyle(), fullStar);
//    s.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
//     Button btnE = new Button("Noter");
//    // Label l1 = new Label ("Vous pouvez noter cette formation");
//      Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
//      
//     Label l = new Label ("Note");
//       l.getUnselectedStyle().setFont(mediumBoldSystemFont);
//     s.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                
//                    l.setText(Integer.toString(s.getProgress(evt)));
//              
//            }
//
//             
//        });
         Button Supprimer = new Button("\nSupprimer ");
//         Button AddCours = new Button("\nAjouter Cours ");
//         Button showcrsguide = new Button("\nAfficher les cours de guide ");
         Button Retour = new Button("\nRetour ");
//      btnE.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//         
//        Dialog.show("success", "Notation est Bien ajoutée", new Command("OK"));
//               
//            } 
//      } );
 Retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 new GuideForm(res).showBack();;
            }
        });
// showcrsguide.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                 new CoursForm(res,cu).showBack();
//            }
//        });
//  AddCours.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                 new CoursAdd(res, cu).show();
//            }
//        });
     Supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                       if (ts.deleteCours(cu.getID_crs())) {
                Dialog.show("Success", "Guide Supprimé Avec Succès", "Bie", null);
                 new GuideForm(res).showBack();
            } else {
                Dialog.show("Erreur", "Erreur Suppression! Veuuller Ressayer ", "OK", null);
            };
           
            }
        });
     Container cn = new Container(BoxLayout.x());
     Container cn2 = new Container(BoxLayout.y());
     setLayout(new FlowLayout(CENTER));
       //      cn.add(s);
       //      cn.add(l);
            
          //  cn.add(btnE);
            
//            cn2.add(AddCours);
//            cn2.add(showcrsguide);
            cn2.add(Supprimer);
            cn2.add(Retour);
            c.add(cn2);
             c.add(cn);

            c.add(hi);
           

         
          
   
        
        this.add(c);
            
        
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
       // FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
      //  likes.setIcon(heartImage);
       // likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        //FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
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
