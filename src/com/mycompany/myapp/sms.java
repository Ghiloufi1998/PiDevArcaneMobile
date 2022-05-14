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
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;

/**
 *
 * @author khaledguedria
 */
public class sms extends Form {

    //var
    HebergementService ts = HebergementService.getInstance();

    public sms(Resources res) {

        //custom
        this.setLayout(BoxLayout.y());
        this.setTitle("All Tasks");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeFormIheb(res).showBack();
        });

        //widgets
        SpanLabel sl = new SpanLabel();
        sl.setText(ts.fetchHbrs().toString());

        //end
        this.add(sl);

    }
    
   

}
