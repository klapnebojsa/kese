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
public class ComboChangePagePercent implements ItemListener {
    JMenuItem menuItemNovi;
    JMenuItem menuItemIzmeni;
    PreviewMenuBar previewMenuBar; 

    public ComboChangePagePercent(PreviewMenuBar previewMenuBar) {
        this.previewMenuBar = previewMenuBar;
    }

    Object vrednost = null;
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            vrednost = event.getItem();
            try {
                previewMenuBar.tekuceUvecanje.setText(vrednost.toString());
                try{
                    vrednost = vrednost.toString().replace(" %", "");
                    previewMenuBar.formPrintPreview.pagesPripremi.p = Double.parseDouble(vrednost.toString()) / 100;                
                }catch(Exception e){
                    RezolucijaEkrana re = new RezolucijaEkrana();
                    Dimension fullScr = re.FullScreen();

                    Dimension ukupnoOsnovno = previewMenuBar.formPrintPreview.pagesPripremi.getUkupnoOsnovno();
                    switch (vrednost.toString()){
                        case "page Width":
                            previewMenuBar.formPrintPreview.pagesPripremi.p = (double)fullScr.width / ukupnoOsnovno.width;
                            break;
                        case "page Height":
                            previewMenuBar.formPrintPreview.pagesPripremi.p = (double)fullScr.height / ukupnoOsnovno.height;                            
                            break;
                    }
                    try{previewMenuBar.formPrintPreview.stampaMenuBar.tekuceUvecanje.setText(String.valueOf((int)(previewMenuBar.formPrintPreview.pagesPripremi.p*100)) + " %");
                    }catch(Exception e1){}
                }
                previewMenuBar.formPrintPreview.pagesPripremi.revalidate();
                previewMenuBar.formPrintPreview.pagesPripremi.repaint();   
            }catch(Exception e1){}
       }
    }
}
