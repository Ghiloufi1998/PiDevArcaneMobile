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
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Guides;
import com.mycompany.services.GuideService;
import java.util.List;

/**
 * The aceeuil form
 *
 * @author Abirn
 */
public class GuideForm extends BaseForm {
    GuideService ts = GuideService.getInstance();
       GuideForm instance;

    public GuideForm(Resources res) {
        
          super("Newsfeed", BoxLayout.y());
            instance=this;
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
         Button addguide = new Button("\nAjouter Nouveau Guide\n ");
         addguide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  new GuideAdd(res).show();
            }
        });
        add(addguide);
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, Guide, Reservation, Sondage),
            //    new Label("hhhhhhhhhhhh"),
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
        
Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("", "Title"),
                                new Label("", "SubTitle")
                        )
                )
        );
        tb.setTitleComponent(titleCmp);
            List<Guides> list = GuideService.getInstance().fetchGuides();

       
            
      
       //   Container cnt5 = new Container(BoxLayout.x());
//         cnt5.add(addguide);
      //   add(cnt5);
                Button Retour = new Button("\nRetour ");

 Retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 new NewsfeedForm(res).showBack();
            }
        });
add(Retour);
        for (Guides gu : list){
           setLayout(new FlowLayout(CENTER));
        Container cnt1 = new Container(BoxLayout.y());   
        Container cnt2 = new Container(BoxLayout.x());
        
        SpanLabel SLnom = new SpanLabel(gu.getTitre());
       // Label SLlevel = new Label("Level : "+gu.getLevel());

     // Label SLrem = new Label(gu.getPays());
        Button SLvoir = new Button("Voir Guide");
        Button Modifier = new Button("Modifier ");

        EncodedImage enc = EncodedImage.createFromImage(Image.createImage(500, 500), true);
        String url = gu.getImage();
      ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/")+1, url.length()), url,URLImage.RESIZE_SCALE_TO_FILL));
       
        SLvoir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  new ViewGuideForm(gu, instance,res).show();
            }
        });
         Modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      new GuideModif(res, gu).show();
            }
        });

       
        cnt2.add(SLnom);
       
        
      // cnt2.add(SLlevel);
      // cnt2.add(SLrem);
       
        cnt1.add(cnt2);
       cnt1.add(img);
        cnt1.add(SLvoir);
        cnt1.add(Modifier);

        add(cnt1);
                //search tbadel 3onwen tool bar
        //prepare field
        TextField searchField;
        searchField = new TextField("", "Rechercher...");
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
}
