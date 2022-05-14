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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Paiement;
import com.mycompany.entities.Réservation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author bensa
 */
public class PaiementService {

    //var
    ConnectionRequest req;
    static PaiementService instance = null;

    //util
    boolean resultOK = false;
    List<Paiement> Paiement = new ArrayList<>();

    //Constructor
    private PaiementService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static PaiementService getInstance() {
        if (instance == null) {
            instance = new PaiementService();
        }

        return instance;
    }

    //ACTIONS
    //Add
    public boolean addPaiement(Paiement r) {

        //1
        String addURL = "http://127.0.0.1:8000/paiment/getPaiementjson/new";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
         req.addArgument("Date",r.getDate().toString());
         req.addArgument("Montant",String.valueOf(r.getMontant()));
         req.addArgument("Etat",r.getMode_Pay()); 
        
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
    
     public boolean updatePaiement(Paiement r) {

        //1
        String addURL = "http://127.0.0.1:8000/paiment/AllPaiement/Update/"+r.getID_PAi();

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
         req.addArgument("Date",r.getDate().toString());
         req.addArgument("Montant",String.valueOf(r.getMontant()));
         req.addArgument("Etat",r.getMode_Pay()); 
        
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
     
     
     public boolean deletePaiement(int id) {
       //try {
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl("http://127.0.0.1:8000/paiment/AllPaiement/Del/"+id);
            cr.setPost(false);
           
			
			
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Supprimer","Paiement Supprimé " , "OK",null);

                  //  envoyerMail("wassim.benafia@esprit.tn", "New Reclamtion ", p.getType()+" "+p.getDescription());
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
            return true;
        /*  }
        catch (ParseException e1) {
         e1.printStackTrace();
         }*/
     }
    
     public ArrayList<Paiement> parsePaiement(String jsonText) throws ParseException{
 
DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        try {
            
            Paiement=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Paiement f = new Paiement();
                f.setID_PAi((int)(double) obj.get("paiId"));
                f.setDate((Date) simpleDateFormat.parse(obj.get("date").toString())  );
                f.setMontant(Integer.parseInt((String)obj.get("montant")));
                f.setMode_Pay((String)obj.get("modePay"));
                  
           
                if (obj.get("paiId")==null)
              f.setID_PAi(1);
                else
                    f.setID_PAi((int)(double)obj.get("paiId"));
                Paiement.add(f);
            }
            
            
        } catch (IOException ex) {
            
        }
        return (ArrayList<Paiement>) Paiement;
    }
      public ArrayList<Paiement> getAllPaiement(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = "http://127.0.0.1:8000/paiment/getPaiementjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Paiement = parsePaiement(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return (ArrayList<Paiement>) Paiement;
    }
    

   

    //Parse
//    public List<Facture> parseFacture(String jsonText) throws ParseException {
//
//        //var
//        Facture = new ArrayList<>();
//        
//        //DO
//        //1
//        JSONParser jp = new JSONParser();
//        
//        try {
//            
//            //2
//            Map<String, Object> ListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//        
//            //3
//            List<Map<String, Object>> list = (List<Map<String, Object>>) ListJSON.get("root");
//        
//            //4
//            for (Map<String, Object> item : list) {
//                
//                
//           //     System.out.println("hedhaaaaaaaaaaa" +item.get("hebergementId")); 
//             //   System.out.println("trl  " +(int)15.2);
//                
//                 f.setEtat((String) item.get("etat"));
//                
//                
//                System.out.println(f);
//                Facture.add(f);
//            }
//        
//        } catch (IOException ex) {
//        }
//        
//        
//        
//        //End
//        return Facture;
//    }

}