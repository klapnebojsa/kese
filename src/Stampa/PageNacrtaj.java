/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa;

import Stampa.Podaci.FontMetric;
import Stampa.Podaci.PoljeZaStampu;
import Stampa.RezervisanaPolja.RezervisanaPolja;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;


/**
 *
 * @author Nebojsa
 */
public class PageNacrtaj {
    double visinaFonta;
    Graphics2D g2D;
    double medjY;
    double medjYDw;    
    double medjX;
    PagesPripremi pagesPripremi;
    double p;
    String naslov;
    Vector pageVector;
    public PageNacrtaj(PagesPripremi pagesPripremi, double p){
        this.p = p;        
        this.pagesPripremi = pagesPripremi;
        medjY = pagesPripremi.formPrintPreview.stampaSetujPage.getMMedjY();
        medjYDw = pagesPripremi.formPrintPreview.stampaSetujPage.getMMedjYDw();        
        medjX = pagesPripremi.formPrintPreview.stampaSetujPage.getMMedjX();
        naslov = pagesPripremi.formPrintPreview.koZove.getOpisForme();
    }
    public PageNacrtaj(){
    }
    public void Prikazi(Vector pageVector, Graphics g) throws Exception{
        //FontMetrics fm = g.getFontMetrics();
        this.pageVector = pageVector;
        visinaFonta=0;
            
        Dimension pocetakPage = pagesPripremi.getPocetakPage();

        int sirinaPage = (int)pagesPripremi.getUkupnoSize().width;         
        int visinaPage = (int)pagesPripremi.getUkupnoSize().height; 

        java.awt.geom.Rectangle2D r = new java.awt.geom.Rectangle2D.Float (pocetakPage.width, pocetakPage.height, sirinaPage, visinaPage); 
        
        Vector page = (Vector) this.pageVector.elementAt(pagesPripremi.getTrenutnatPage());

        g2D = (Graphics2D) g;        
        g2D.setPaint(Color.white);
        g2D.fill(r);        
        g2D.setPaint(Color.black);

        double mLeft = 0;
        double trenutnoY = pagesPripremi.formPrintPreview.stampaSetujPage.getMTop()*p + pocetakPage.height;        
        if (!pagesPripremi.getJesteStampa()){
            mLeft = pagesPripremi.formPrintPreview.stampaSetujPage.getMLeft();       
        }
        int xRStaro = 0;
        for (int iR = 0; iR < page.size(); iR++) {
            Vector <PoljeZaStampu> lineVector = (Vector)page.get(iR);         
            double pocX = 0;            
            for (int iC=0; iC<lineVector.size(); iC++){
                Font font = new Font(lineVector.elementAt(iC).getFontName(), lineVector.elementAt(iC).getBoldItal(), (int)(lineVector.elementAt(iC).getFontSize() * p));
                font = lineVector.elementAt(iC).setUnder(font);
                
                g2D.setFont(font);
                FontMetrics fm = g2D.getFontMetrics();
                visinaFonta = lineVector.elementAt(lineVector.size()-1).getMaxVisinaFonta() * p;
            
                String cell = (String) lineVector.elementAt(iC).getVrednost();
                
                //Text
                int ukVisina = (int)(medjY*p + visinaFonta);
                int textwidth = (int)(lineVector.elementAt(iC).getWidthTextaa()*p + 0.5);
                //  &&%%-Rezervisano za sluzbene reci - Strana od do, Datum, Firma, Adresa ...
                // Prepravljamo text i sirinu texta
                String[] vrednosti = cell.split("&&%%");
                if (vrednosti.length > 1){
                    RezervisanaPolja rezervisanaPolja = new RezervisanaPolja(pagesPripremi.getTrenutnatPage()+1, this.pageVector.size(), naslov);
                    cell = rezervisanaPolja.Prepravi(vrednosti);

                    PoljeZaStampu poljeZaStampu = new PoljeZaStampu();
                    font = poljeZaStampu.setUnder(font);              
                    FontMetric fontMetric = new FontMetric(font);
                    textwidth = (int)fontMetric.getTextWidth(cell);            
                }                    

                double widthPolja = lineVector.elementAt(iC).getWidthPolja();

                double pX = (mLeft + pocX) * p;
                double pY = trenutnoY;
                
                int xLf=0;
                if(iC==0){xLf = (int)(pX + pocetakPage.width + 0.5);}else {xLf = xRStaro;}
                double dodatak = 0.5; if (iC==lineVector.size()-1)dodatak=0;
                int xRg = xLf  + (int)(widthPolja * p + dodatak);
                xRStaro = xRg;

                int yUp = (int)(pY);
                int yDw = (int)(pY + (medjYDw*p + medjY*p + visinaFonta));

                int yText = (int)(pY - fm.getMaxDescent() + ukVisina);

                switch (lineVector.elementAt(iC).getAlignment()){          
                    case "Left":
                        g2D.drawString(cell, (int)(pX + pocetakPage.width + medjX*p), yText);
                        break;
                    case "Right":
                        double x = xRg - textwidth - medjX*p;
                        g2D.drawString(cell, (int)x, yText);
                        break;
                    case "Center":
                        double x1 = xLf + (xRg - xLf  - textwidth)/2;
                        g2D.drawString(cell, (int)x1, yText);
                        break;                        
                }
                pocX += widthPolja;
                
                if (lineVector.elementAt(iC).getCijeJe()=="MedjuZbir"){}
                    
                //Linije tabele
                if (lineVector.elementAt(iC).getDownLine())  {g2D.setStroke(new BasicStroke(lineVector.elementAt(iC).getDownLineWeight()));  g2D.drawLine(xLf, yDw, xRg, yDw);}
                if (lineVector.elementAt(iC).getTopLine())   {g2D.setStroke(new BasicStroke(lineVector.elementAt(iC).getTopLineWeight()));   g2D.drawLine(xLf, yUp, xRg, yUp);} 
                if (lineVector.elementAt(iC).getLeftLine())  {g2D.setStroke(new BasicStroke(lineVector.elementAt(iC).getLeftLineWeight()));  g2D.drawLine(xLf, yUp, xLf, yDw);}
                if (lineVector.elementAt(iC).getRightLine()) {g2D.setStroke(new BasicStroke(lineVector.elementAt(iC).getRightLineWeight())); g2D.drawLine(xRg, yUp, xRg, yDw);}  
            }
            trenutnoY += visinaFonta + medjY*p + medjYDw*p;
        }
    }
}
