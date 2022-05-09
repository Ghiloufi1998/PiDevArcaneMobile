/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.services;

import com.cnone.khaled.entities.Task;
import com.cnone.khaled.entities.Sondage;
import com.cnone.khaled.utilities.Statics;
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
 * @author khaledguedria
 */
public class SondageService {

    //var
    ConnectionRequest req;
    static SondageService instance = null;

    //util
    boolean resultOK = false;
    List<Sondage> ss = new ArrayList<>();

    //Constructor
    private SondageService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static SondageService getInstance() {
        if (instance == null) {
            instance = new SondageService();
        }

        return instance;
    }
    

    //ACTIONS
    //Add
    public boolean addSondage(Sondage t) {

        //1
        String addURL = Statics.BASE_URL + "/newJsonSond/new";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("cat", t.getCatégorie());
        req.addArgument("sjt", t.getSujet() + "");

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

    //FETCH
    public List<Sondage> fetchSondage() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/AllSondage/";
        
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
    public List<Sondage> parseTasks(String jsonText) {

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
                
                Sondage s = new Sondage();
                System.out.println(item.get("sondage_id"));
                s.setSondage_id((int)(double) item.get("sondageId"));
                s.setCatégorie((String) item.get("categorie"));
                s.setSujet((String) item.get("sujet"));
                
                ss.add(s);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return ss;
    }

    
    
     //Delete 
    public boolean deleteSondage(int id ) {
        String DeleteURL = Statics.BASE_URL +"/deletejsonsond/"+id;
        
        req.setUrl(DeleteURL);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200 ;  
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return   resultOK;
    }
    
    
      //Update 
    public boolean updateSondage(Sondage sondage) {
        String url = Statics.BASE_URL +"/updatejsons/"+sondage.getSondage_id()+"?sjt="+sondage.getSujet()+"&cat="+sondage.getCatégorie();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
        
    }
    
}
