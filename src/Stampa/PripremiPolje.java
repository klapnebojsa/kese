/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa;

import Stampa.Apstraktne.OblikStampe;
import Stampa.Podaci.FontMetric;
import Stampa.Podaci.PoljeZaStampu;
import java.awt.Font;

/**
 *
 * @author Nebojsa
 */
public class PripremiPolje {
    PoljeZaStampu poljeZaStampu;
    OblikStampe oblikStampe;
    int minVelFonta;
    double medjX;
    public double maxVisina;
    
    public PripremiPolje(OblikStampe oblikStampe, int minVelFonta, double medjX, double maxVisina){
        this.oblikStampe = oblikStampe;
        this.minVelFonta = minVelFonta;
        this.medjX = medjX;
        this.maxVisina = maxVisina;
    }
    
    public PoljeZaStampu Polje(PoljeZaStampu poljeZaStampu){
        poljeZaStampu.setAlignment(oblikStampe.getAlignment());                
        String vrednost = oblikStampe.getVrednost();

        poljeZaStampu.setVrednost(vrednost);
               
        poljeZaStampu.setLeftLine(oblikStampe.getLeftLine());
        poljeZaStampu.setRightLine(oblikStampe.getRightLine());
        poljeZaStampu.setTopLine(oblikStampe.getTopLine());                
        poljeZaStampu.setDownLine(oblikStampe.getDownLine());
               
        poljeZaStampu.setLeftLineWeight(oblikStampe.getLeftLineWeight());
        poljeZaStampu.setRightLineWeight(oblikStampe.getRightLineWeight());
        poljeZaStampu.setTopLineWeight(oblikStampe.getTopLineWeight());
        poljeZaStampu.setDownLineWeight(oblikStampe.getDownLineWeight());                
                
        poljeZaStampu.setSmanji(oblikStampe.getSmanji());                        
        poljeZaStampu.setOdseci(oblikStampe.getOdseci());
                
        poljeZaStampu.setFontName(oblikStampe.getFontName());
        poljeZaStampu.setBold(oblikStampe.getBold());
        poljeZaStampu.setItalic(oblikStampe.getItalic());
        poljeZaStampu.setUnderLine(oblikStampe.getUnderLine());
        
        poljeZaStampu.setCijeJe(oblikStampe.getCijeJe());
        
        int fontSize = oblikStampe.getFontSize();
        poljeZaStampu.setFontSize(fontSize);
                

        double widthTexta=0.;
        
        /*double widthPolja=0.;
        widthPolja = mt1.getModel().table.getColumnModel().getColumn(iC).getWidth() * pageFormat.getImageableWidth() / widthTable;
        poljeZaStampu.setWidthPolja(widthPolja);*/
                
        String fontName = poljeZaStampu.getFontName();
        int fontStyle =poljeZaStampu.getBoldItal();
                
        Font font = new Font(fontName, fontStyle, fontSize);
        font = poljeZaStampu.setUnder(font);              
        FontMetric fontMetric = new FontMetric(font);
        widthTexta = fontMetric.getTextWidth(vrednost);
        poljeZaStampu.setWidthTexta(widthTexta);
          
        /*if (poljeZaStampu.getCijeJe() == "MedjuZbir"){
            int gfd = 0;
            gfd ++;
        }*/
        
        //Smanjivanje fonta i odsecanje viska ako je potrebno
        //Bitno da je pre metrike da smanjenje fonta udje u preracun visine reda
        if (poljeZaStampu.getSmanji()){
            for (int i=fontSize-1; i>=minVelFonta; i--){
                if(widthTexta + 2 * medjX > poljeZaStampu.getWidthPolja()){
                    fontSize = i;
                    fontMetric = new FontMetric(new Font(fontName, fontStyle, fontSize));
                    widthTexta = fontMetric.getTextWidth(vrednost);
                    poljeZaStampu.setWidthTexta(widthTexta);
                }else{break;}
            }
        }
        poljeZaStampu.setFontSize(fontSize);
        //Odsecanje teksta ako prelazi granice polja i ako se to zeli
        if (poljeZaStampu.getOdseci()){
            int ukZnakova = vrednost.length();
            for (int i=1; i<ukZnakova; i++){                    
                if(widthTexta + 2 * medjX > poljeZaStampu.getWidthPolja()){
                    fontMetric = new FontMetric(new Font(fontName, fontStyle, fontSize));
                    String novaVrednost = vrednost.substring(0, ukZnakova-i-1) + "...";
                    widthTexta = fontMetric.getTextWidth(novaVrednost);
                            
                    poljeZaStampu.setWidthTexta(widthTexta);
                    poljeZaStampu.setVrednost(novaVrednost);  
                }else{break;}
           }                    
        }                
                
        //Velicina Fonta za cell i maximalna velicina fonta za tu liniju
        poljeZaStampu.setVisinaFonta(fontMetric.getVisinaFonta());  
        maxVisina = (fontMetric.getVisinaFonta() > maxVisina) ? fontMetric.getVisinaFonta() : maxVisina;
        poljeZaStampu.setMaxVisinaFonta(maxVisina); 

        return poljeZaStampu;
    }
}
