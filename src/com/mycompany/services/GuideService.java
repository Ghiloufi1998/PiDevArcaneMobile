/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entities.Guides;
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
import com.codename1.io.ConnectionRequest;
import com.codename1.io.MultipartRequest;
import com.codename1.ui.Dialog;
import com.mycompany.entities.Vol;

/**
 *
 * @author Ghiloufi
 */
public class GuideService {
    //var
    ConnectionRequest req;
    static GuideService instance = null;
    
     //util
    boolean resultOK = false;
    List<Guides> guides = new ArrayList<>();
    
    
    //Constructor
    private GuideService() {
        req = new ConnectionRequest();
    }
     //Singleton
    public static GuideService getInstance() {
        if (instance == null) {
            instance = new GuideService();
        }

        return instance;
    }
     //Add
    public boolean addGuide(Guides g) {

        //1
        String addURL = Statics.BASE_URL + "/guide/newGuide/new/";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("titre", g.getTitre());
        req.addArgument("Pays", g.getPays());
        req.addArgument("level", g.getLevel()+ "");
        req.addArgument("image", g.getImage());
        req.addArgument("idvol", g.getId_vol()+"");
      

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
     public boolean updateGuide(Guides g) {

        //1
        String addURL = Statics.BASE_URL + "/guide/updateGuide/"+g.getID_g();

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("titre", g.getTitre());
        req.addArgument("Pays", g.getPays());
        req.addArgument("level", g.getLevel()+ "");
        req.addArgument("image", g.getImage());
        req.addArgument("idvol", g.getId_vol()+"");
      

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
       public boolean deleteGuide(int id) {
    
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/guide/deleteGuide/"+id);
            cr.setPost(false);
            
            NetworkManager.getInstance().addToQueueAndWait(cr);
            return true;
      
    }
    
       public List<Guides> fetchGuides() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/guide/AllGuide";
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                guides = parseGuides(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return guides;
    }
       public List<Guides> parseGuides(String jsonText) {
        guides = new ArrayList<>();
        
      
        JSONParser jp = new JSONParser();
        
        try {
            
            Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
         
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");
        

            for (Map<String, Object> item : list) {
                
                Guides g = new Guides();
                 float id = Float.parseFloat(item.get("idG").toString());
              g.setID_g((int) id);
                g.setTitre((String) item.get("titre"));
                float level = Float.parseFloat(item.get("level").toString());
               g.setLevel((int) level);
                g.setImage(item.get("image").toString());
                g.setPays(item.get("pays").toString());
             //   Object vol = item.get("idVol");
               // System.out.println(vol);
               // g.setVol((Vol)vol);
              //  Vol vol = (Vol)item.get("idVol");
            //   g.setId_vol((int) v);
                guides.add(g);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return guides;
    }
}
