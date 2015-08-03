/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Pripremi;

import Forme.FormPrintPreview;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nebojsa
 */
public class PripremiSve {
    public void Priprema(FormPrintPreview form){
        form.pripremiHeader = new PripremiHeader(form, form.pageFormat, new Vector(), form.oznakaStampe);        //HEADER
        form.headerLineVectorAll = form.pripremiHeader.HeaderLineVectorAll(); 
        form.pripremiNaslov = new PripremiNaslov(form, form.pageFormat, new Vector(), form.oznakaStampe);        //NASLOV
        form.naslovLineVectorAll = form.pripremiNaslov.NaslovLineVectorAll(); 
        form.pripremiUvod = new PripremiUvod(form, form.pageFormat, new Vector(), form.oznakaStampe);            //UVOD  
        form.uvodLineVectorAll = form.pripremiUvod.UvodLineVectorAll();
        form.pripremiZakljucak = new PripremiZakljucak(form, form.pageFormat, new Vector(), form.oznakaStampe);  //ZAKLUCAK
        form.zakljucakLineVectorAll = form.pripremiZakljucak.ZakljucakLineVectorAll();        
        form.pripremiKraj = new PripremiKraj(form, form.pageFormat, new Vector(), form.oznakaStampe);            //KRAJ
        form.krajLineVectorAll = form.pripremiKraj.KrajLineVectorAll();        
        form.pripremiFooter = new PripremiFooter(form, form.pageFormat, new Vector(), form.oznakaStampe);        //FOOTER
        form.footerLineVectorAll = form.pripremiFooter.FooterLineVectorAll();

        //Priprema podataka za stampu Tabele(lineVector-a) sa TableHeader-om  ili neceg drugog      
        form.tableLineVectorAll = new Vector();
        switch (form.oznakaStampe){
            case "Tabela":
                form.pripremiTabelu = new PripremiTabelu(form, form.pageFormat, form.tableLineVectorAll);
                form.tableLineVectorAll = form.pripremiTabelu.TableLineVectorAll(form.mt1);
                
                form.tableHeaderLineVector = form.pripremiTabelu.TableHeaderLineVector(form.mt1);
                form.tableMedjuZbirVector = form.pripremiTabelu.TableMedjuZbirVector(form.mt1, form.koZove, form.kojaStampa);
                OgranicenjaMedjuZbir ogranicenjaStampa= new OgranicenjaMedjuZbir();
            try {form.kojaPoljaMedjuZbirVector = ogranicenjaStampa.KolonaMedjuZbir(form.koZove, form.kojaStampa);
            } catch (SQLException ex) {Logger.getLogger(FormPrintPreview.class.getName()).log(Level.SEVERE, null, ex);}
                
                break;
            default:
                form.tableLineVectorAll = new Vector();
                form.tableHeaderLineVector = new Vector();
                form.tableMedjuZbirVector = new Vector();
                form.kojaPoljaMedjuZbirVector = new Vector();
        }         
    }  
}
