/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Pripremi;

import Forme.FormForme;
import Stampa.PripremiPolje;
import Stampa.Upiti.KojaTabela;
import Stampa.Apstraktne.OblikStampe;
import Forme.FormPrintPreview;
import Forme.Tabele.MojaTabela;
import Sistem.OsnovneDefinicije.Alignment;
import Stampa.Podaci.Konstante;
import Stampa.Podaci.PoljeZaStampu;
import java.awt.print.PageFormat;
import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
public class PripremiTabelu {
    Vector lineVector;
    MojaTabela mt1;
    FormPrintPreview formPrintPreview;
    PageFormat pageFormat;
    int minVelFonta;
    double medjX;
    
    public PripremiTabelu(FormPrintPreview formPrintPreview, PageFormat pageFormat, Vector lineVector){
        this.mt1 = mt1;
        this.formPrintPreview = formPrintPreview;
        this.pageFormat = pageFormat;
        this.lineVector = lineVector;
    }
    
    public Vector TableLineVectorAll(MojaTabela mt1){
        //lineVector = new Vector();
        Konstante konstante = new Konstante();
        minVelFonta = konstante.getMinVelicinaFonta();
        medjX = formPrintPreview.stampaSetujPage.getMMedjX();
        
        double widthTable=0;
        for (int iC = 0; iC < mt1.getTable().getColumnCount(); iC++) {
            widthTable += mt1.getModel().table.getColumnModel().getColumn(iC).getWidth();
        }
        
        OblikStampe oblikStampe;
        String imeKlase = formPrintPreview.koZove.k.ImeKlase();        
        
        String fontNameOsn = formPrintPreview.stampaSetujPage.getMFont().getFontName();
        int fontSizeOsn = formPrintPreview.stampaSetujPage.getMFont().getSize();
        int tblMaxCol = mt1.getTable().getColumnCount()-1;
        //double maxVisina = 0;
        for (int iR = 0; iR < mt1.getTable().getRowCount(); iR++) {
            Vector cellVector = new Vector(); 
            double maxVisina = 0;           
            for (int iC = 0; iC < mt1.getTable().getColumnCount(); iC++) {
                PoljeZaStampu poljeZaStampu = new PoljeZaStampu();
                //OVDE SETOVATI I OSTALE PARAMETRE MARGINE, VELICINU FONTA ...                  
                Alignment aa = new Alignment();
                String alignment="Left";
                try{ alignment=aa.getCellAlignment(mt1.getTable().getColumnModel().getColumn(iC).getCellRenderer());
                }catch(Exception e){}
                
                KojaTabela kojaTabela = new KojaTabela(imeKlase, fontNameOsn, fontSizeOsn, iR, iC, tblMaxCol, mt1.getModel().getValueAt(iR, iC).toString(), alignment, "");
                oblikStampe = kojaTabela.OdrediTabelu();
                oblikStampe.SetujPodatke();
                
                PripremiPolje pripremiPolje= new PripremiPolje(oblikStampe, minVelFonta, medjX, maxVisina);
                double widthPolja=0.;
                widthPolja = mt1.getModel().table.getColumnModel().getColumn(iC).getWidth() * pageFormat.getImageableWidth() / widthTable;
                poljeZaStampu.setWidthPolja(widthPolja);                
                
                poljeZaStampu = pripremiPolje.Polje(poljeZaStampu);
                
                maxVisina = pripremiPolje.maxVisina;               
                cellVector.addElement(poljeZaStampu);
            }
            lineVector.addElement(cellVector);
        }
        return lineVector;
    }
    
    public Vector TableHeaderLineVector(MojaTabela mt1){
        Konstante konstante = new Konstante();
        minVelFonta = konstante.getMinVelicinaFonta();
        medjX = formPrintPreview.stampaSetujPage.getMMedjX();
        
        double widthTable=0;
        for (int iC = 0; iC < mt1.getTable().getColumnCount(); iC++) {
            widthTable += mt1.getModel().table.getColumnModel().getColumn(iC).getWidth();
        }
        
        OblikStampe oblikStampe;
        //String imeKlase = formPrintPreview.koZove.k.ImeKlase();        
        Vector lineVector = new Vector();
        String fontNameOsn = formPrintPreview.stampaSetujPage.getMFont().getFontName();
        int fontSizeOsn = formPrintPreview.stampaSetujPage.getMFont().getSize();
        int tblMaxCol = mt1.getTable().getColumnCount()-1;
        //double maxVisina = 0;
        //for (int iR = 0; iR < mt1.getTable().getRowCount(); iR++) {
            Vector cellVector = new Vector(); 
            double maxVisina = 0;           
            for (int iC = 0; iC < mt1.getTable().getColumnCount(); iC++) {
                PoljeZaStampu poljeZaStampu = new PoljeZaStampu();
                //OVDE SETOVATI I OSTALE PARAMETRE MARGINE, VELICINU FONTA ...                  
                Alignment aa = new Alignment();
                String alignment="Center";
                /*try{ alignment=aa.getCellAlignment(mt1.getTable().getColumnModel().getColumn(iC).getCellRenderer());
                }catch(Exception e){}*/
                
                KojaTabela kojaTabela = new KojaTabela("TableHeader", fontNameOsn, fontSizeOsn, 0, iC, tblMaxCol, mt1.getModel().getColumnName(iC), alignment, "B");
                oblikStampe = kojaTabela.OdrediTabelu();
                oblikStampe.SetujPodatke();
                
                PripremiPolje pripremiPolje= new PripremiPolje(oblikStampe, minVelFonta, medjX, maxVisina);
                double widthPolja=0.;
                widthPolja = mt1.getModel().table.getColumnModel().getColumn(iC).getWidth() * pageFormat.getImageableWidth() / widthTable;
                poljeZaStampu.setWidthPolja(widthPolja);                
                
                poljeZaStampu = pripremiPolje.Polje(poljeZaStampu);
                
                maxVisina = pripremiPolje.maxVisina;               
                cellVector.addElement(poljeZaStampu);
            }
            lineVector.addElement(cellVector);
        //}        
        return lineVector;        
    }
        
    public Vector TableMedjuZbirVector(MojaTabela mt1, FormForme koZove, String kojaStampa){
        Konstante konstante = new Konstante();
        minVelFonta = konstante.getMinVelicinaFonta();
        medjX = formPrintPreview.stampaSetujPage.getMMedjX();
        
        double widthTable=0;
        for (int iC = 0; iC < mt1.getTable().getColumnCount(); iC++) {
            widthTable += mt1.getModel().table.getColumnModel().getColumn(iC).getWidth();
        }
        boolean trebaMedjuZbir = false;
        OgranicenjaMedjuZbir ogranicenjaStampa= new OgranicenjaMedjuZbir();
        trebaMedjuZbir = ogranicenjaStampa.trebaMedjuZbir(koZove.k.ImeKlase(), kojaStampa);
        lineVector = new Vector();
        
        if (trebaMedjuZbir){
            OblikStampe oblikStampe;       
            String fontNameOsn = formPrintPreview.stampaSetujPage.getMFont().getFontName();
            int fontSizeOsn = formPrintPreview.stampaSetujPage.getMFont().getSize();
            int tblMaxCol = mt1.getTable().getColumnCount()-1;
            Vector cellVector = new Vector(); 
            double maxVisina = 0;           
            for (int iC = 0; iC < mt1.getTable().getColumnCount(); iC++) {
                PoljeZaStampu poljeZaStampu = new PoljeZaStampu();                
                String alignment="Right";

                KojaTabela kojaTabela = new KojaTabela("TableMedjuZbir", fontNameOsn, fontSizeOsn, 0, iC, tblMaxCol, "", alignment, "Bi");
                oblikStampe = kojaTabela.OdrediTabelu();
                oblikStampe.SetujPodatke();

                PripremiPolje pripremiPolje= new PripremiPolje(oblikStampe, minVelFonta, medjX, maxVisina);
                double widthPolja=0.;
                widthPolja = mt1.getModel().table.getColumnModel().getColumn(iC).getWidth() * pageFormat.getImageableWidth() / widthTable;
                poljeZaStampu.setWidthPolja(widthPolja);                

                poljeZaStampu = pripremiPolje.Polje(poljeZaStampu);

                maxVisina = pripremiPolje.maxVisina;               
                cellVector.addElement(poljeZaStampu);
            }
            lineVector.addElement(cellVector);            
        }else{}
      
        return lineVector;        
    }    
    
   
}
