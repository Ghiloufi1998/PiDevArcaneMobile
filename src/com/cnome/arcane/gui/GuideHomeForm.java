/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnome.arcane.gui;

import com.cnome.arcane.services.GuideService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Ghiloufi
 */
public class GuideHomeForm extends Form{
    
    
      //var
    GuideService ts = GuideService.getInstance();

    public GuideHomeForm() {

        //custom
        this.setLayout(BoxLayout.y());
        this.setTitle("All Guides");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new GuideHomeForm().showBack();
        });

        //widgets
        SpanLabel sl = new SpanLabel();
        sl.setText(ts.fetchGuides().toString());

        //end
        this.add(sl);

    }
    
    
}
