/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Upiti;

import Forme.FormPrintPreview;
import Stampa.Apstraktne.NijeTabela;
import Stampa.Obrasci.Footer.FooterClassic;
import Stampa.Obrasci.Footer.FooterLine;
import Stampa.Obrasci.Header.HeaderClassic;
import java.awt.print.PageFormat;
import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
public class KojiFooter {
    //OblikStampe header;
    NijeTabela footer;
    Vector lineVectorAll;
    String imeKlase;
    String fontNameOsn;
    int fontSizeOsn;
    String alignment;
    FormPrintPreview formPrintPreview;
    PageFormat pageFormat;
    String oznakaStampe;

    public KojiFooter(FormPrintPreview formPrintPreview, String imeKlase, String fontNameOsn, int fontSizeOsn, PageFormat pageFormat, String oznakaStampe){
        this.imeKlase = imeKlase;
        this.fontNameOsn = fontNameOsn;
        this.fontSizeOsn = fontSizeOsn;
        this.formPrintPreview = formPrintPreview;
        this.pageFormat = pageFormat;
        this.oznakaStampe = oznakaStampe;
    } 
    public Vector OdrediFooter(){
        switch (imeKlase + oznakaStampe){
            /*case "PartneriTabela":
                footer = new FooterLine(formPrintPreview, imeKlase, fontNameOsn, fontSizeOsn, pageFormat);
                break;*/
            default:
                footer = new FooterClassic(formPrintPreview, imeKlase, fontNameOsn, fontSizeOsn, pageFormat);
                break;
        }
        footer.FormirajPolja();
        lineVectorAll = footer.getLineVectorAll();        
        return lineVectorAll;
    }    
}