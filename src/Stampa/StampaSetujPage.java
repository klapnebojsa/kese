/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa;

import Class.Apstraktne.AbstractDAO;
import Class.DAO.BrokerDAO;
import Class.KlaseBaze.Margine;
import Class.Povezivanje.Procitaj;
import Class.Povezivanje.Setuj;
import Forme.FormForme;
import Forme.FormPrintPreview;
import Forme.Konstante.Mere;
import java.awt.Font;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Nebojsa
 */
public class StampaSetujPage {
    int mLeft=25;
    int mRight=25;
    int mTop=25;
    int mDown=25;
    
    double medjX=0;
    double medjY=0;
    double medjYDw=0;    
    int velFonta=10;
    String font;
    String stampac;
    String formatPapira;
    String orijentacija;
    
    List mojeMargine;
    Double preracun;
    
    public void SetujPageSetup(PageFormat pageFormat, PrinterJob pj, FormPrintPreview formPrintPreview, FormForme koZove) throws Exception{
        
        //Postavljenje Sifre Margina
        Procitaj emp = new Procitaj();
        Setuj set = new Setuj(koZove.getBrokerDAO());               
        String sifraMargine = emp.ProcitajSifruMargina(koZove.getBrokerDAO());
        
        //CitanjeMargina za dobijenu sifru i klasu Margine
        AbstractDAO klasa = new Margine();
        BrokerDAO brokerDAO = new BrokerDAO(klasa);
        set = new Setuj(brokerDAO);                    
        set.SetujSifruMargina(sifraMargine);
        mojeMargine = emp.ProcitajJedanxx(brokerDAO);
        
        //Setovanje podataka iz tabele MARGINE
        for(Object category : mojeMargine) {
            List element = (List)category;   

            medjX=Double.parseDouble(element.get(5).toString());
            medjY=Double.parseDouble(element.get(6).toString());
            medjYDw=Double.parseDouble(element.get(7).toString());            
            velFonta=Integer.parseInt(element.get(8).toString());
            
            font=element.get(9).toString();
            stampac=element.get(10).toString();
            formatPapira=element.get(11).toString();
            orijentacija=element.get(12).toString(); 
            
            Mere mere = new Mere();
            preracun = mere.getMmPageFormat() / 10;  
            Paper paper = pageFormat.getPaper();              
            if ( "Uspravno".equals(getMOrijentacija())) {
                pageFormat.setOrientation(PageFormat.PORTRAIT);
                mLeft = Integer.parseInt(element.get(1).toString());
                mRight = Integer.parseInt(element.get(2).toString());          
                mTop = Integer.parseInt(element.get(3).toString());
                mDown = Integer.parseInt(element.get(4).toString());
                paper.setImageableArea(mLeft*preracun, mTop*preracun, paper.getWidth()-(mRight+mLeft)*preracun, paper.getHeight()-(mDown+mTop)*preracun);
            }else {
                pageFormat.setOrientation(PageFormat.LANDSCAPE);
                mLeft = Integer.parseInt(element.get(3).toString());
                mRight = Integer.parseInt(element.get(4).toString());          
                mTop = Integer.parseInt(element.get(2).toString());
                mDown = Integer.parseInt(element.get(1).toString());
                paper.setImageableArea(mTop*preracun, mRight*preracun, paper.getWidth()-(mTop+mDown)*preracun, paper.getHeight()-(mRight+mLeft)*preracun);
            }
            pageFormat.setPaper(paper);
            formPrintPreview.setPageFormat(pageFormat);            
        }
        pj.setPrintable(null, pj.defaultPage(pageFormat));
    }

    //GET
    public double getMLeft(){
        return mLeft*preracun;
    }
    public double getMRight(){
        return mRight*preracun;
    }
    public double getMTop(){
        return mTop*preracun;
    }
    public double getMDown(){
        return mDown*preracun;
    }
 
    public double getMMedjX(){
        return medjX*preracun;
    }
    public double getMMedjY(){
        return medjY*preracun;
    }
    public double getMMedjYDw(){
        return medjYDw*preracun;
    }
    public double getMVelFonta(){
        return velFonta;
    }
    public Font getMFont(){
        return new Font(font, Font.PLAIN, velFonta);
    }    
    public String getMStampac(){
        return stampac;
    }     
    public String getMFormatPapira(PageFormat pageFormat){
        return formatPapira;
    }               
    public String getMOrijentacija(){
        return orijentacija;
    }   

    //SET IZ FormPrintPreview - kada se promene koordinate
    //Promena nije trajna vec samo za tu odredjenu stampu
    //Ostala polja se ne setuju zato sto se ona menjaju u PRINT FORMI i oni se setuju samo pri ucitavanju iz tabele MARGINE (postoji samo get ali ne i set iz drugr klase)
    public void setMLeft(int mLeft){
        this.mLeft = mLeft;
    }
    public void setMRight(int mRight){
        this.mRight = mRight;
    }
    public void setMTop(int mTop){
        this.mTop = mTop;
    }
    public void setMDown(int mDown){
        this.mDown = mDown;
    }
}
