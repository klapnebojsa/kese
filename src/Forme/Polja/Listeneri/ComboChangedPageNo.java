/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Listeneri;

import Stampa.PreviewMenuBar;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
/**
 *
 * @author Nebojsa
 */
public class ComboChangedPageNo implements ItemListener {
    PreviewMenuBar previewMenuBar; 
    String vred;
    
    public ComboChangedPageNo(PreviewMenuBar previewMenuBar) {
        this.previewMenuBar = previewMenuBar;
    }

    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            try {
                try{vred = event.getItem().toString();   
                }catch(Exception e){}
                if (previewMenuBar.formPrintPreview.pagesPripremi != null) {
                    previewMenuBar.formPrintPreview.pagesPripremi.ovaStrana(Integer.parseInt(vred));
                    previewMenuBar.formPrintPreview.showTitle(previewMenuBar.formPrintPreview.pagesPripremi);
                }                
            }catch(Exception e1){}
       }
       
    }
}