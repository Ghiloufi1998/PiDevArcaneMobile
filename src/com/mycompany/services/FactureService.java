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
import com.mycompany.entities.Facture;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;




/**
 *
 * @author bensa
 */
public class FactureService {

    //var
    ConnectionRequest req;
    static FactureService instance = null;
    String s="";

    //util
    boolean resultOK = false;
    List<Facture> Facture = new ArrayList<>();

    //Constructor
    private FactureService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static FactureService getInstance() {
        if (instance == null) {
            instance = new FactureService();
        }

        return instance;
    }

    //ACTIONS
    //Add
    public boolean addTask(Facture f) {

        //1
        String addURL = "http://127.0.0.1:8000/facture/AllFacture/new";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
         req.addArgument("Date",f.getDate_ech().toString());
        req.addArgument("Montant",String.valueOf(f.getMontant()) );
        req.addArgument("Etat", f.getEtat());
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
    
     public boolean updateFacture(Facture f) {

        //1
        String addURL = "http://127.0.0.1:8000/facture/AllFacture/Update/"+f.getID_fac();

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("dateEch",f.getDate_ech().toString());
        req.addArgument("montantTtc",String.valueOf(f.getMontant()));
        req.addArgument("etat", f.getEtat());
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
     
     
    public boolean deleteFacture(int id) {
       //try {
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl("http://127.0.0.1:8000/facture/AllFacture/Del/"+id);
            cr.setPost(false);
           
			
			
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Supprimer","Hebergement Supprimé " , "OK",null);

                  //  envoyerMail("wassim.benafia@esprit.tn", "New Reclamtion ", p.getType()+" "+p.getDescription());
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
            return true;
        
    }
     public ArrayList<Facture> parseFacture(String jsonText) throws ParseException{
 
DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        try {
            
            Facture=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Facture f = new Facture();
                 f.setID_fac( (int)(double)obj.get("idFac"));
              f.setDate_ech( (Date) simpleDateFormat.parse(obj.get("dateEch").toString())  );
                f.setMontant((int)(double)obj.get("montantTtc"));
//                float id = Float.parseFloat(obj.get("id").toString());
               f.setEtat((String)obj.get("etat"));
                if (obj.get("idFac")==null)
              f.setID_fac(1);
                else
                    f.setID_fac((int)(double)obj.get("idFac"));
                Facture.add(f);
            }
            
            
        } catch (IOException ex) {
            
        }
        return (ArrayList<Facture>) Facture;
    }
      public ArrayList<Facture> getAllTasks(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = "http://127.0.0.1:8000/facture/AllFacturesjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Facture = parseFacture(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return (ArrayList<Facture>) Facture;
    }
      
    
   

   

}