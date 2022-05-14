/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Cours;
import com.mycompany.entities.Exercices;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ghiloufi
 */
public class ExercicesServices {
        ConnectionRequest req;
    static ExercicesServices instance = null;
    
     //util
    boolean resultOK = false;
    List<Exercices> exercices = new ArrayList<>();
    
 
  
    //Constructor
    private ExercicesServices() {
        req = new ConnectionRequest();
    }
     //Singleton
    public static ExercicesServices getInstance() {
        if (instance == null) {
            instance = new ExercicesServices();
        }

        return instance;
    }
     //Add
    public boolean addExercices(Exercices c) {

        //1
        String addURL = Statics.BASE_URL + "/cours/newCours/new/";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
     req.addArgument("hint", c.getHint());
        req.addArgument("type", c.getType());
        req.addArgument("question", c.getQuestion());
        req.addArgument("reponse", c.getReponse());
        req.addArgument("image", c.getImage());
        req.addArgument("idcrs", c.getID_crs()+"");
      

        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
     public boolean updateExercices(Exercices c) {

        //1
        String addURL = Statics.BASE_URL + "/cours/updateCours/"+c.getID_crs();

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
          req.addArgument("hint", c.getHint());
        req.addArgument("type", c.getType());
        req.addArgument("question", c.getQuestion());
        req.addArgument("reponse", c.getReponse());
        req.addArgument("image", c.getImage());
        req.addArgument("idcrs", c.getID_crs()+"");
      

        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
       public boolean deleteExercices(int id) {
    
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/cours/deleteCours/"+id);
            cr.setPost(false);
            
            NetworkManager.getInstance().addToQueueAndWait(cr);
            return true;
      
    }
    
       public List<Exercices> fetchExercices(int id) {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = (Statics.BASE_URL + "/cours/CoursByGuide/"+id);
        
        //2
        
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                exercices = parseExercices(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return exercices;
    }
       public List<Exercices> parseExercices(String jsonText) {
        exercices = new ArrayList<>();
        
      
        JSONParser jp = new JSONParser();
        
        try {
            
            Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
         
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");
        

            for (Map<String, Object> item : list) {
                
                Exercices c = new Exercices();
                 float id = Float.parseFloat(item.get("idex").toString());
              c.setID_ex((int) id);
                c.setHint((String) item.get("hint"));
                c.setQuestion((String) item.get("question"));
                c.setReponse((String) item.get("reponse"));
                c.setImage(item.get("image").toString());
             
                
                exercices.add(c);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return exercices;
    }
}