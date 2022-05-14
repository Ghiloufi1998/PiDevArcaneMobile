/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;
import com.mycompany.entities.Facture;
import com.mycompany.services.FactureService;
import com.mycompany.services.PaiementService;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import java.util.Date;

import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Paiement;
/**
 *
 * @author bensa
 */
public class AddPaiement extends Form {
    //var
    PaiementService ts = PaiementService.getInstance();

    public AddPaiement(Resources res) {

        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Facture");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeFormamine(res).showBack();
        });

        //Widgets
        Picker DateTF = new Picker();DateTF.setUIID("TextFieldBlack");
        TextField MontantTF = new TextField("", "Montant");MontantTF.setUIID("TextFieldBlack");
        TextField EtatTF = new TextField("", "Mode de paiement");EtatTF.setUIID("TextFieldBlack");
        
        Button submitBtn = new Button("Submit");

        //actions
        submitBtn.addActionListener((evt) -> {
              if ((DateTF.getText().length()==0)||(MontantTF.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Paiement t = new Paiement((Date)DateTF.getValue(), Integer.parseInt(MontantTF.getText()),EtatTF.getText() );
                        if( ts.getInstance().addPaiement(t))
                        {
                           Dialog.show("Success","Paiement effectuée",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
               
            
        });

        //end
        this.addAll(DateTF,MontantTF,EtatTF , submitBtn);

    } 
}
