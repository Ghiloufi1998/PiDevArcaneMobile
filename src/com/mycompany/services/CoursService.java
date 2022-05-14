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
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ghiloufi
 */
public class CoursService {
      ConnectionRequest req;
    static CoursService instance = null;
    
     //util
    boolean resultOK = false;
    List<Cours> cours = new ArrayList<>();
    
 
  
    //Constructor
    private CoursService() {
        req = new ConnectionRequest();
    }
     //Singleton
    public static CoursService getInstance() {
        if (instance == null) {
            instance = new CoursService();
        }

        return instance;
    }
     //Add
    public boolean addCours(Cours c) {

        //1
        String addURL = Statics.BASE_URL + "/cours/newCours/new/";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
   req.addArgument("titre", c.getTitre());
        req.addArgument("type", c.getType());
        req.addArgument("contenu", c.getContenu());
        req.addArgument("image", c.getImage());
        req.addArgument("idg", c.getID_g()+"");
      

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
     public boolean updateCours(Cours c) {

        //1
        String addURL = Statics.BASE_URL + "/cours/updateCours/"+c.getID_crs();

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
          req.addArgument("titre", c.getTitre());
        req.addArgument("type", c.getType());
        req.addArgument("contenu", c.getContenu());
        req.addArgument("image", c.getImage());
        req.addArgument("idg", c.getID_g()+"");
      

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
       public boolean deleteCours(int id) {
    
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/cours/deleteCours/"+id);
            cr.setPost(false);
            
            NetworkManager.getInstance().addToQueueAndWait(cr);
            return true;
      
    }
    
       public List<Cours> fetchCours(int id) {
        
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
                cours = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cours;
    }
       public List<Cours> parseCours(String jsonText) {
        cours = new ArrayList<>();
        
      
        JSONParser jp = new JSONParser();
        
        try {
            
            Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
         
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");
        

            for (Map<String, Object> item : list) {
                
                Cours c = new Cours();
                 float id = Float.parseFloat(item.get("idCrs").toString());
              c.setID_crs((int) id);
                c.setTitre((String) item.get("titre"));
           //     float level = Float.parseFloat(item.get("level").toString());
             //  c.setsetLevel((int) level);
                c.setImage(item.get("image").toString());
                c.setContenu(item.get("contenu").toString());
                cours.add(c);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return cours;
    }
}