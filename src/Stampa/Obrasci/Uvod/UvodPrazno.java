/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Obrasci.Uvod;

import Forme.FormPrintPreview;
import Stampa.Apstraktne.NijeTabela;
import Stampa.Apstraktne.OblikStampe;
import Stampa.NijeTabela.RacunajSirinuPolja;
import java.awt.print.PageFormat;
import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
public class UvodPrazno extends NijeTabela{
    OblikStampe oblikStampe;
    String fontName;
    int fontSize;
    String imeKlase;
    double medjX;
    Vector lineVectorAll;
    double maxVisina;
    FormPrintPreview formPrintPreview;
    RacunajSirinuPolja racunajSirinuPolja;
    PageFormat pageFormat;
    double[] sirine;
    String[] alignment;
        
    public UvodPrazno(FormPrintPreview formPrintPreview, String imeKlase, String fontName, int fontSize, PageFormat pageFormat){
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.imeKlase = imeKlase;
        medjX = formPrintPreview.stampaSetujPage.getMMedjX();
        this.formPrintPreview = formPrintPreview;
        this.pageFormat = pageFormat;
    }  

    public void FormirajPolja(){
        lineVectorAll = new Vector(); 
    }
    public void setMaxVisina(double maxVisina){
        this.maxVisina = maxVisina;
    }
    
    public Vector getLineVectorAll(){
        return lineVectorAll;
    }
}