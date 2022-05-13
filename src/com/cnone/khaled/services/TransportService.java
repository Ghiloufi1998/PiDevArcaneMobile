/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.services;

import com.cnone.iheb.entities.Task;
import com.cnone.iheb.entities.Hebergement;
import com.cnone.iheb.entities.Transport;
import com.cnone.iheb.entities.Voyageorganise;
import com.cnone.iheb.entities.Weather;
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
public class TransportService {

    //var
    ConnectionRequest req;
    static TransportService instance = null;
    

    //util
    boolean resultOK = false;
    
            List<Transport> trs = new ArrayList<>();


    List<Object> x;

    //Constructor
    private TransportService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static TransportService getInstance() {
        if (instance == null) {
            instance = new TransportService();
        }

        return instance;
    }

    //ACTIONS
    //Add
    public boolean addtrs(Transport h, Hebergement t) {

        //1
        String addURL = Statics.BASE_URL2 + "/newJsont/new/";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
         req.addArgument("desc", h.getDescription());
        req.addArgument("type", h.getType()+ "");
        req.addArgument("dis", h.getDisponibilité() + "");
        req.addArgument("im", h.getImage() + "");
        req.addArgument("hid", t.getHebergement_id()+"");
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
    
     public boolean updatetrs(Transport h) {

        //1
        String addURL = Statics.BASE_URL2 + "/jst/"+h.getTransport_id();

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        
               // h.setAdresse((String) item.get("adresse"));
                
        req.addArgument("desc", h.getDescription());
        req.addArgument("type", h.getType()+ "");
        req.addArgument("dis", h.getDisponibilité() + "");
        req.addArgument("im", h.getImage() + "");
        req.addArgument("hid",h.getHebergement_id() +"");
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
     
     
     public boolean deletefts(int id) {
       //try {
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL2+"/dtr/"+id);
            cr.setPost(false);
           
			
			
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Supprimer","Transport Supprimé " , "OK",null);
                

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
    public List<Transport> fetchtrs(Integer id) {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL2 + "/findth/"+id;
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                trs = parsetrs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return trs;
    }

    //Parse
    public List<Transport> parsetrs(String jsonText) {

        //var
        trs = new ArrayList<>();
        
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
                
                Transport h = new Transport();
               
                h.setTransport_id((int) (double)item.get("transportId"));
               // h.setAdresse((String) item.get("adresse"));
                
                h.setDisponibilité((int) (double)item.get("Disponibilite"));
                h.setDescription((String) item.get("description"));
                h.setImage( (String) item.get("image"));
                h.setType( (String) item.get("type") );
              //  h.setHebergement_id((int) (double) item.get("hid"));
               // h.setPrix((int) (double) item.get("pr"));
                System.out.println(h);
                trs.add(h);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return trs;
    }

}