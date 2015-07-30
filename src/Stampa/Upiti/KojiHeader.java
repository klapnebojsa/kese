/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Upiti;

import Forme.FormPrintPreview;
import Forme.Konstante.FunkcijskiTasteri;
import Stampa.Apstraktne.NijeTabela;
import Stampa.Apstraktne.OblikStampe;
import Stampa.Obrasci.Header.HeaderClassic;
import Stampa.Obrasci.Header.HeaderLine;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import java.awt.print.PageFormat;
import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
public class KojiHeader {
    //OblikStampe header;
    NijeTabela header;
    Vector lineVectorAll;
    String imeKlase;
    String fontNameOsn;
    int fontSizeOsn;
    String alignment;
    FormPrintPreview formPrintPreview;
    PageFormat pageFormat;
    String oznakaStampe;

    public KojiHeader(FormPrintPreview formPrintPreview, String imeKlase, String fontNameOsn, int fontSizeOsn, PageFormat pageFormat, String oznakaStampe){
        this.imeKlase = imeKlase;
        this.fontNameOsn = fontNameOsn;
        this.fontSizeOsn = fontSizeOsn;
        this.formPrintPreview = formPrintPreview;
        this.pageFormat = pageFormat;
        this.oznakaStampe = oznakaStampe;
    } 
    public Vector OdrediHeader(){
        //FunkcijskiTasteri ft = new FunkcijskiTasteri();
        switch (imeKlase + oznakaStampe){
            /*case "PartneriTabela":
                header = new HeaderLine(formPrintPreview, imeKlase, fontNameOsn, fontSizeOsn, pageFormat);
                break;*/
            default:
                header = new HeaderClassic(formPrintPreview, imeKlase, fontNameOsn, fontSizeOsn, pageFormat);
                break;
        }
        header.FormirajPolja();
        lineVectorAll = header.getLineVectorAll();        
        return lineVectorAll;
    }    
}
