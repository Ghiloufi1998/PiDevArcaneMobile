/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entities.Questions;
import com.mycompany.entities.Réponses;
import com.mycompany.entities.Sondage;
import com.mycompany.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceReponse {
       //var
    ConnectionRequest req;
    static ServiceReponse instance = null;

    //util
    boolean resultOK = false;
    List<Réponses> ss = new ArrayList<>();

    //Constructor
    private ServiceReponse() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static ServiceReponse getInstance() {
        if (instance == null) {
            instance = new ServiceReponse();
        }

        return instance;
    }
    
    
      public List<Réponses> fetchReponse(int id) {
        
        req = new ConnectionRequest();
         
        //1
        String fetchURL = Statics.BASE_URL + "/reponses/AllReponse/"+id;
       
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ss = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ss;
    }

    //Parse
    public List<Réponses> parseTasks(String jsonText) {

        //var
        ss = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {
                
                Réponses q = new Réponses();
                //+System.out.println(item.get("sondage_id"));
                q.setRéponses_id((int)(double) item.get("reponsesId"));
                q.setRéponse((String) item.get("reponse"));
               // q.setType((String) item.get("type"));
               //  q.setSondage_id((int)(double) item.get("sondageId"));
                
                ss.add(q);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return ss;
    }

    
    
    
}
