/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.services;

import com.cnone.iheb.entities.Task;
import com.cnone.iheb.entities.Hebergement;
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
public class HebergementService {

    //var
    ConnectionRequest req;
    static HebergementService instance = null;
    String ooo="";
    String p="";

    //util
    boolean resultOK = false;
    List<Hebergement> Hbrs = new ArrayList<>();
        List<Weather> wth = new ArrayList<>();
            List<Voyageorganise> voy = new ArrayList<>();


    List<Object> x;

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
     
      public void QR(Hebergement H) {
       //try {
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/qrjson"+H.getHebergement_id());
            cr.setPost(false);
           
			
			
//            cr.addResponseListener(e -> {
//             
//
//                
//
//                  //  envoyerMail("wassim.benafia@esprit.tn", "New Reclamtion ", p.getType()+" "+p.getDescription());
//            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
            //return true;
        /*   }
        catch (ParseException e1) {
         e1.printStackTrace();
         }*/
    }
      
        //meteo
      
      public String meteo(String pays) {
        
        req = new ConnectionRequest();
        
        
        //1
        String fetchURL = Statics.BASE_URL1 + "/weathermobile/"+pays;
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("exemple");
                
                       
                p =new String(req.getResponseData());
                //System.out.println(req.getResponseData());
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }

       public List<Weather> parseweather(String jsonText) {

        //var
        wth = new ArrayList<>();
        
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
                
             Weather t = new Weather();
                t.setWeather((String)item.get("weather"));
                t.setSpeed((String)item.get("speed"));
                t.setTemp((String)item.get("temp"));
                t.setFl((String)item.get("fl"));
                t.setDesc((String)item.get("desc"));
                System.out.println(t);
                
                wth.add(t);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return wth;
    }

//}
//    //Parse
//    public List<Weather> parseweather(String jsonText) {
//
//        //var
//        wth = new ArrayList<>();
//        String thw="";
//        
//        //DO
//     
//        JSONParser jp = new JSONParser();
//        
//        try {
//            
//            //2
//            Map<String, Object> ListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//            //3
//            List<Map<String, Object>> list = (List<Map<String, Object>>) ListJSON.get("root");
////            System.out.println("hedha");
////            System.out.println(list.toString());
//
//            //4
//            for (Map<String, Object> item : list) {
//                                            Weather t = new Weather();
//
////                
////                Hebergement h = new Hebergement();
////                System.out.println("hedhaaaaaaaaaaa" +item.get("hebergementId")); 
////                System.out.println("trl  " +(int)15.2);
////                h.setHebergement_id((int) (double)item.get("hebergementId"));
////                h.setAdresse((String) item.get("adresse"));
////                
////                h.setDisponibilité((int) (double)item.get("Disponibilite"));
////                h.setDescription((String) item.get("description"));
////                h.setImage( (String) item.get("image"));
////                h.setType( (String) item.get("type") );
//
//               t.setWeather((String)item.get("weather"));
//                t.setSpeed((String)item.get("speed"));
//                t.setTemp((String)item.get("temp"));
//                t.setFl((String)item.get("fl"));
//                t.setDesc((String)item.get("desc"));
//                System.out.println(t);
//                
//                wth.add(t);
//
//
//                //String w=item.get("weather");
////                String sp=(String)item.get("speed");
////                String t=(String)item.get("temp");
////                String fl=(String)item.get("fl");
////                String d=(String)item.get("desc");
//                //System.out.println(t+"°C / Ressenti "+fl+"°C");
////                String ww=t+"°C / Ressenti "+fl+"°C";
////              //  {{speed ~ 'Km/h -' ~ deg  ~ '°'}}
////                String www=sp+" Km/h";
////                 thw=ww+" "+ www;
//
//                //System.out.println(w);
//                //System.out.println(h);
//               
//            }
//        
//        } catch (IOException ex) {
//        }
//        
//        
//        
//        //End
//        return wth ;
//    }

      
      
      
      //finmeteo
    
//weather
    public String fetchwth() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL1 + "/weathermobile/madrid";
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                p = new String(req.getResponseData());
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }

    //Parse
    public List<Weather> parsewth(String jsonText) {

        //var
        wth = new ArrayList<>();
        
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
                Weather t = new Weather();
               t.setWeather((String)item.get("weather"));
                t.setSpeed((String)item.get("speed"));
                t.setTemp((String)item.get("temp"));
                t.setFl((String)item.get("fl"));
                t.setDesc((String)item.get("desc"));
                System.out.println(t);
                
                wth.add(t);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return wth;
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

    
//    public List<Voyageorganise> fetchvoy() {
//        
//        req = new ConnectionRequest();
//        
//        //1
//        String fetchURL = Statics.BASE_URL1 + "/get";
//        
//        //2
//        req.setUrl(fetchURL);
//        
//        //3
//        req.setPost(false);
//        
//        //4
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                tasks = parseTasks(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return tasks;
//    }
//
//    //Parse
//    public List<Task> parseTasks(String jsonText) {
//
//        //var
//        tasks = new ArrayList<>();
//        
//        //DO
//        //1
//        JSONParser jp = new JSONParser();
//        
//        try {
//            
//            //2
//            Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//        
//            //3
//            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");
//        
//            //4
//            for (Map<String, Object> item : list) {
//                
//                Task t = new Task();
//                t.setId((double) item.get("id"));
//                t.setName((String) item.get("name"));
//                t.setStatus((double) item.get("status"));
//                
//                tasks.add(t);
//            }
//        
//        } catch (IOException ex) {
//        }
//        
//        
//        
//        //End
//        return tasks;
//    }
//
//}
}
