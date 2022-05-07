/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.services;

import com.cnone.iheb.entities.Task;
import com.cnone.iheb.entities.Hebergement;
import com.cnone.khaled.utilities.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author khaledguedria
 */
public class HebergementService {

    //var
    ConnectionRequest req;
    static HebergementService instance = null;

    //util
    boolean resultOK = false;
    List<Hebergement> Hbrs = new ArrayList<>();

    //Constructor
    private HebergementService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static HebergementService getInstance() {
        if (instance == null) {
            instance = new HebergementService();
        }

        return instance;
    }

    //ACTIONS
    //Add
    public boolean addHebergement(Hebergement h) {

        //1
        String addURL = Statics.BASE_URL + "/newJsonhbr/new/";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("desc", h.getDescription());
        req.addArgument("type", h.getType()+ "");
        req.addArgument("dis", h.getDisponibilité() + "");
        req.addArgument("ad", h.getAdresse() + "");
        req.addArgument("im", h.getImage() + "");
       //  req.addArgument("prix", h.getP + "");

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
    
     public boolean updateHebergement(Hebergement h) {

        //1
        String addURL = Statics.BASE_URL + "/updatejsonhbr/"+h.getHebergement_id();

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("desc", h.getDescription());
        req.addArgument("type", h.getType()+ "");
        req.addArgument("dis", h.getDisponibilité() + "");
        req.addArgument("ad", h.getAdresse() + "");
        req.addArgument("im", h.getImage() + "");
       //  req.addArgument("prix", h.getP + "");

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
     
     
     public boolean deleteHebergement(int id) {
       //try {
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/deletejsonhbr/"+id);
            cr.setPost(false);
           
			
			
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Supprimer","Hebergement Supprimé " , "OK",null);

                  //  envoyerMail("wassim.benafia@esprit.tn", "New Reclamtion ", p.getType()+" "+p.getDescription());
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
            return true;
        /*   }
        catch (ParseException e1) {
         e1.printStackTrace();
         }*/
    }
    
    

    //FETCH
    public List<Hebergement> fetchHbrs() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/Allhbr";
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Hbrs = parsehbr(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Hbrs;
    }

    //Parse
    public List<Hebergement> parsehbr(String jsonText) {

        //var
        Hbrs = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> ListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) ListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {
                
                Hebergement h = new Hebergement();
                System.out.println("hedhaaaaaaaaaaa" +item.get("hebergementId")); 
                System.out.println("trl  " +(int)15.2);
                h.setHebergement_id((int) (double)item.get("hebergementId"));
                h.setAdresse((String) item.get("adresse"));
                
                h.setDisponibilité((int) (double)item.get("Disponibilite"));
                h.setDescription((String) item.get("description"));
                h.setImage( (String) item.get("image"));
                h.setType( (String) item.get("type") );
                
                
                System.out.println(h);
                Hbrs.add(h);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return Hbrs;
    }

}
