/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Pripremi;

import Forme.FormPrintPreview;
import Stampa.Upiti.KojiNaslov;
import Stampa.Upiti.KojiUvod;
import java.awt.print.PageFormat;
import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
public class PripremiUvod {
    Vector lineVectorAll;
    FormPrintPreview formPrintPreview;
    PageFormat pageFormat;
    int minVelFonta;
    String oznakaStampe;
    //double medjX;
    
    public PripremiUvod(FormPrintPreview formPrintPreview, PageFormat pageFormat, Vector lineVectorAll, String oznakaStampe){
        this.formPrintPreview = formPrintPreview;
        this.pageFormat = pageFormat;
        this.lineVectorAll = lineVectorAll;
        this.oznakaStampe = oznakaStampe;
    }  
    public Vector UvodLineVectorAll(){
        String imeKlase = formPrintPreview.koZove.k.ImeKlase();        
        
        String fontNameOsn = formPrintPreview.stampaSetujPage.getMFont().getFontName();
        int fontSizeOsn = formPrintPreview.stampaSetujPage.getMFont().getSize();
        
        //Odredjivanje koji obrazac Headera je u pitanju i punjenje podataka u lineVectorAll
        KojiUvod kojiUvod = new KojiUvod(formPrintPreview, imeKlase, fontNameOsn, fontSizeOsn, pageFormat, oznakaStampe);

        lineVectorAll = kojiUvod.OdrediUvod();
        return lineVectorAll;
    }
      
}