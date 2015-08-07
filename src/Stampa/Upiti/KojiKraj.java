/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Upiti;

import Forme.FormPrintPreview;
import Stampa.Apstraktne.NijeTabela;
import Stampa.Obrasci.Kraj.KrajClassic;
import Stampa.Obrasci.Kraj.KrajPrazno;
import java.awt.print.PageFormat;
import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
public class KojiKraj {
    //OblikStampe header;
    NijeTabela kraj;
    Vector lineVectorAll;
    String imeKlase;
    String fontNameOsn;
    int fontSizeOsn;
    String alignment;
    FormPrintPreview formPrintPreview;
    PageFormat pageFormat;
    String oznakaStampe;

    public KojiKraj(FormPrintPreview formPrintPreview, String imeKlase, String fontNameOsn, int fontSizeOsn, PageFormat pageFormat, String oznakaStampe){
        this.imeKlase = imeKlase;
        this.fontNameOsn = fontNameOsn;
        this.fontSizeOsn = fontSizeOsn;
        this.formPrintPreview = formPrintPreview;
        this.pageFormat = pageFormat;
        this.oznakaStampe = oznakaStampe;
    } 
    public Vector OdrediKraj(){
        switch (imeKlase + oznakaStampe){
            case "PartneriTabela":
                kraj = new KrajClassic(formPrintPreview, imeKlase, fontNameOsn, fontSizeOsn, pageFormat);
                break;
            default:
                kraj = new KrajPrazno(formPrintPreview, imeKlase, fontNameOsn, fontSizeOsn, pageFormat);                
                break;
        }
        kraj.FormirajPolja();
        lineVectorAll = kraj.getLineVectorAll();        
        return lineVectorAll;
    }    
}