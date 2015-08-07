/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Upiti;

import Forme.FormPrintPreview;
import Stampa.Apstraktne.NijeTabela;
import Stampa.Obrasci.Naslov.NaslovClassic;
import java.awt.print.PageFormat;
import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
public class KojiNaslov {
    //OblikStampe header;
    NijeTabela naslov;
    Vector lineVectorAll;
    String imeKlase;
    String fontNameOsn;
    int fontSizeOsn;
    String alignment;
    FormPrintPreview formPrintPreview;
    PageFormat pageFormat;
    String oznakaStampe;

    public KojiNaslov(FormPrintPreview formPrintPreview, String imeKlase, String fontNameOsn, int fontSizeOsn, PageFormat pageFormat, String oznakaStampe){
        this.imeKlase = imeKlase;
        this.fontNameOsn = fontNameOsn;
        this.fontSizeOsn = fontSizeOsn;
        this.formPrintPreview = formPrintPreview;
        this.pageFormat = pageFormat;
        this.oznakaStampe = oznakaStampe;
    } 
    public Vector OdrediNaslov(){
        //FunkcijskiTasteri ft = new FunkcijskiTasteri();
        switch (imeKlase + oznakaStampe){
            /*case "PartneriTabela":
                header = new HeaderLine(formPrintPreview, imeKlase, fontNameOsn, fontSizeOsn, pageFormat);
                break;*/
            default:
                naslov = new NaslovClassic(formPrintPreview, imeKlase, fontNameOsn, fontSizeOsn, pageFormat);
                break;
        }
        naslov.FormirajPolja();
        lineVectorAll = naslov.getLineVectorAll();        
        return lineVectorAll;
    }    
}
