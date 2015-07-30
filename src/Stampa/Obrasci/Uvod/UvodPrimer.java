/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Obrasci.Uvod;

import Forme.FormPrintPreview;
import Stampa.Apstraktne.NijeTabela;
import Stampa.Apstraktne.OblikStampe;
import Stampa.NijeTabela.DefinisiPolje;
import Stampa.NijeTabela.RacunajSirinuPolja;
import java.awt.print.PageFormat;
import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
public class UvodPrimer extends NijeTabela{
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
        
    public UvodPrimer(FormPrintPreview formPrintPreview, String imeKlase, String fontName, int fontSize, PageFormat pageFormat){
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.imeKlase = imeKlase;
        medjX = formPrintPreview.stampaSetujPage.getMMedjX();
        this.formPrintPreview = formPrintPreview;
        this.pageFormat = pageFormat;
    }  

    public void FormirajPolja(){
        lineVectorAll = new Vector();
        Vector lineVector;
        OblikStampe oblikStampe;

        DefinisiPolje definisiPolje = new DefinisiPolje(this);
        racunajSirinuPolja = new RacunajSirinuPolja(pageFormat);

        //Linija 1
        lineVector = new Vector();
        sirine = racunajSirinuPolja.SirinaPolja("100");
        alignment = ("Center").split(",");        
        maxVisina = 0;  
            lineVector.addElement(definisiPolje.DefPoljeLineNo(fontName, fontSize-2, "", alignment[0], maxVisina, sirine[0], "", "Uvod"));  //Polje 1       
        lineVectorAll.addElement(lineVector);
        
        //Linija 2 Prazno
        lineVector = new Vector();        
        sirine = racunajSirinuPolja.SirinaPolja("100");
        alignment = ("Right").split(",");
        maxVisina = 0;
            lineVector.addElement(definisiPolje.DefPoljeLineNo(fontName, fontSize, "Na svakoj strani iznosi su u dinarima sa parama 0.00", alignment[0], maxVisina, sirine[0], "B", "Uvod"));                    //Polje 1 
        lineVectorAll.addElement(lineVector);         
    }
    public void setMaxVisina(double maxVisina){
        this.maxVisina = maxVisina;
    }
    
    public Vector getLineVectorAll(){
        return lineVectorAll;
    }
}