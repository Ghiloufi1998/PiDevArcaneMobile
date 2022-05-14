/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.mycompany.entities.Task;
import com.mycompany.entities.Hebergement;
import com.mycompany.services.HebergementService;
import com.mycompany.services.TaskService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author khaledguedria
 */
public class ShowHbrForm extends Form {

    //var
        Form current;
        private Map<String, Object> createListEntry(Hebergement h) {
        Map<String, Object> entry = new HashMap<>();
                entry.put("Line1", h.getHebergement_id());

        entry.put("Line2", h.getAdresse());
        entry.put("Line3", h.getDescription());
        entry.put("Line4", h.getDisponibilité());
      
        entry.put("Line5", h.getType());
         entry.put("Line7", " klsny                                       ");

        return entry;
    }


    public ShowHbrForm(Resources res) {

   current=this; //Back

        System.out.println(HebergementService.getInstance().fetchHbrs().toString());
        ArrayList<Hebergement> data = (ArrayList<Hebergement>) HebergementService.getInstance().fetchHbrs();
        ArrayList<Map<String, Object>> dataToAdd = new ArrayList<>();

        for (Hebergement h : data) {
            dataToAdd.add(createListEntry(h));
        }
       
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(dataToAdd);

        MultiList ml = new MultiList(model);
         ml.addActionListener(new ActionListener() {

       
               @Override
            public void actionPerformed(ActionEvent evt) {
                String res=evt.getActualComponent().toString();
                String idv=res.substring(res.indexOf("selected index = ")+17);
                idv=idv.substring(0,idv.indexOf(','));
                System.out.println(Integer.parseInt(idv));
                //new VoyageUpdateGUI(current,data.get(Integer.parseInt(idv))).show();
            }
        });
 addAll(ml);       
    }
   
}
