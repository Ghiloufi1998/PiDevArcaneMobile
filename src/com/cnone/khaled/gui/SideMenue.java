/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnone.khaled.gui;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;


/**
 *
 * @author user
 */
public class SideMenue {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ghiloufi
 */
public abstract class SideMenu extends Form {
    public SideMenu(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenu(String title) {
        super(title);
    }

    public SideMenu() {
    }

    public SideMenu(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu(Resources res) {
            getToolbar().addCommandToSideMenu("gérer sondage", null, e -> new HomeForm().show());
          //  getToolbar().addCommandToSideMenu("Cours", null, e -> new ListeGuideFront(res).show());
           // getToolbar().addCommandToSideMenu("Exercices", null, e -> new ListeGuideFront(res).show());
   

        }
        

    protected abstract void showOtherForm(Resources res);
}
}
