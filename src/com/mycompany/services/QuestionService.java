/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

//import com.cnone.khaled.entities.Questions;
import com.mycompany.entities.Questions;
import com.mycompany.entities.Sondage;
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

/**
 *
 * @author user
 */
public class QuestionService {
    
       //var
    ConnectionRequest req;
    static QuestionService instance = null;

    //util
    boolean resultOK = false;
    List<Questions> ss = new ArrayList<>();

    //Constructor
    private QuestionService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static QuestionService getInstance() {
        if (instance == null) {
            instance = new QuestionService();
        }

        return instance;
    }
    

    //ACTIONS
    //Add
    public boolean addQuestion(Questions q) {

        //1
        String addURL = Statics.BASE_URL + "/questions/newJsonQuest/new/";

        req.setUrl(addURL);
        
          req.setPost(false);

        //4
        req.addArgument("qst", q.getQuestion() + "");
        req.addArgument("type", q.getType() + "");
        req.addArgument("sdjId", q.getSondage_id() + "");

        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

//    
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
          return   resultOK;
        
    }

    //FETCH
    public List<Questions> fetchQuestion(int id) {
        
        req = new ConnectionRequest();
         
        //1
        String fetchURL = Statics.BASE_URL + "/questions/ShowQ/"+id;
       
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
    public List<Questions> parseTasks(String jsonText) {

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
                
                Questions q = new Questions();
                //System.out.println(item.get("sondage_id"));
               q.setQuestion_id((int)(double) item.get("questionId"));
                q.setQuestion((String) item.get("question"));
                q.setType((String) item.get("type"));
               //  q.setSondage_id((int)(double) item.get("sondageId"));
                
                ss.add(q);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return ss;
    }

    
    
     //Delete 
    public boolean deleteQuestion(int QuestionId ) {
        String DeleteURL = Statics.BASE_URL + "/questions/deletejsonqst/"+QuestionId;
        
        req.setUrl(DeleteURL);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return   resultOK;
    }
    
    
     // Update 
   public boolean updateQuestion(Questions quest) {
        String url = Statics.BASE_URL +"/questions/updatejsonsqst/"+quest.getQuestion_id()+"?qst="+quest.getQuestion()+"&type="+quest.getType();
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
