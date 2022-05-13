/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.gui;

import com.cnone.iheb.entities.Task;
import com.cnone.iheb.entities.Hebergement;
import com.cnone.iheb.entities.Weather;
import com.cnone.khaled.services.HebergementService;
import com.cnone.khaled.services.TaskService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.List;

/**
 *
 * @author khaledguedria
 */
public class meteo extends Form {

    //var
    HebergementService ts = HebergementService.getInstance();
     List<Hebergement> hbr;
     List<Weather> wth;
    private Form current;
    private Resources theme;


    public meteo(Resources res,String pays) {

        //custom
        this.setLayout(BoxLayout.y());
        this.setTitle("Méteo");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeForm(res).showBack();
        });
        

        //widgets
        Label sl = new Label(ts.meteo(pays));
       String t =ts.meteo(pays).substring(ts.meteo(pays).indexOf("temp")+6, 26);
             Label s2 = new Label(t);

            //System.out.println(t);
        //sl.setText(ts.meteo(pays).toString());

        //end
       // this.add(sl);
        this.add(s2);

        
    }

}
