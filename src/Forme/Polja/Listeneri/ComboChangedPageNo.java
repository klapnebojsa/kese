/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Listeneri;

import Sistem.OsnovneDefinicije.RezolucijaEkrana;
import Stampa.PreviewMenuBar;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JMenuItem;

/**
 *
 * @author Nebojsa
 */
public class ComboChangedPageNo implements ItemListener {
    PreviewMenuBar previewMenuBar; 

    public ComboChangedPageNo(PreviewMenuBar previewMenuBar) {
        this.previewMenuBar = previewMenuBar;
    }

    Object vrednost;
    String vred;
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            vrednost = event.getItem();
            try {
                try{vred = vrednost.toString();              
                }catch(Exception e){}
                if (previewMenuBar.formPrintPreview.pagesPripremi != null) {
                    previewMenuBar.formPrintPreview.pagesPripremi.ovaStrana(Integer.parseInt(vred));
                    previewMenuBar.formPrintPreview.showTitle(previewMenuBar.formPrintPreview.pagesPripremi);
                }                
            }catch(Exception e1){}
       }
       
    }
}