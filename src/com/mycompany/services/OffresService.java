/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entities.Offres;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Vol;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author bensa
 */
public class OffresService {
     //var
    ArrayList<Vol> Of=null ;
    ConnectionRequest req;
    static OffresService instance = null;

    //util
    boolean resultOK = false;
    List<Offres> Offres = new ArrayList<>();
    List<Vol> vo= new ArrayList<>();

    //Constructor
    private OffresService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static OffresService getInstance() {
        if (instance == null) {
            instance = new OffresService();
        }

        return instance;
    }

    //ACTIONS
    //Add
    public boolean addOffres(Offres f) {

        //1
        String addURL = "http://127.0.0.1:8000/facture/AllFacture/new";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
         req.addArgument("Description",f.getDescription());
        req.addArgument("nbPointReq",String.valueOf(f.getNb_points_req() ));
        req.addArgument("destination", f.getDestination());
            req.addArgument("pourcentageRed",String.valueOf(f.getPourcentage_red()));
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
    
     public boolean updateOffres(Offres f) {

        //1
        String addURL = "http://127.0.0.1:8000/offres/AllOffres/Update/"+f.getID();

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("Description",f.getDescription());
        req.addArgument("nbPointReq",String.valueOf(f.getNb_points_req() ));
        req.addArgument("destination", f.getDestination());
        req.addArgument("pourcentageRed",String.valueOf(f.getPourcentage_red()));
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
     
     
    public boolean deleteOffres(int id) {
       //try {
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl("http://127.0.0.1:8000/offres/AllOffres/Del/"+id);
            cr.setPost(false);
           
			
			
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Supprimer","Offres Supprimé " , "OK",null);

                  //  envoyerMail("wassim.benafia@esprit.tn", "New Reclamtion ", p.getType()+" "+p.getDescription());
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
            return true;
        
    }
     public ArrayList<Offres> parseOffres(String jsonText) throws ParseException, IOException{
 


        try {
            
            Offres=new ArrayList<>();
            JSONParser jp = new JSONParser();
             Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
             List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");
            for(Map<String,Object> obj : list){
                Offres f = new Offres();
                 f.setID((int)(double)obj.get("idOff"));
              f.setDescription((String)obj.get("description") );
                f.setNb_points_req((int)(double)obj.get("nbPointReq"));
               
                f.setDestination((String)obj.get("destination"));
                f.setPourcentage_red((int)(double)obj.get("pourcentageRed"));
//                float id = Float.parseFloat(obj.get("id").toString());
          
                if (obj.get("idOff")==null)
              f.setID(1);
                else
                    f.setID((int)(double)obj.get("idOff"));
                Offres.add(f);
            }
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return (ArrayList<Offres>) Offres;
    }
      public ArrayList<Offres> getAllOffres(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = "http://127.0.0.1:8000/offres/AllOffresjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    try {
                        Offres  = parseOffres(new String(req.getResponseData()));
                    } catch (IOException ex) {
                         System.out.println(ex.getMessage());
                    }
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return (ArrayList<Offres>) Offres;
    }
     public ArrayList<Vol> parseDestination(String jsonText) throws ParseException, IOException{
 
DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        try {
            
            Offres=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Vol v = new Vol();
                 v.setX((double)obj.get("x"));
              v.setY((double)obj.get("y") );
             
//                float id = Float.parseFloat(obj.get("id").toString());
          
             
                vo.add(v);
            }
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return (ArrayList<Vol>) vo;
    }
 public ArrayList<Vol> getAllDestionation(){
     
        //String url = Statics.BASE_URL+"/tasks/";
        String url = "http://127.0.0.1:8000/vol/destination";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    try {
                   Of = parseDestination(new String(req.getResponseData()));
                    } catch (IOException ex) {
                         System.out.println(ex.getMessage());
                    }
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return (ArrayList<Vol>) Of;
    }
   


}

