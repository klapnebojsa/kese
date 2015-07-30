/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa;

import Forme.FormPrintPreview;
import Forme.Ispis.Formatiraj;
import Forme.Polja.Listeneri.FocusTxt;
import Stampa.Podaci.FontMetric;
import Stampa.Podaci.Konstante;
import Stampa.Podaci.PoljeZaStampu;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nebojsa
 */
public class RedefinisiMedjuZbir {
    Vector <PoljeZaStampu> redefVect;
    
    public PoljeZaStampu redefinisi(FormPrintPreview formPrintPreview, Vector <PoljeZaStampu> lineMZ, Vector <PoljeZaStampu> line, 
                                    double[] fff, int i, int fontSize, boolean kojaPoljaMedjuZbirVector, double medjX){
        redefVect = new <PoljeZaStampu> Vector();
        try {fff[i] += Double.parseDouble(line.get(i).getVrednost().replace(",", ""));}catch(Exception e){}   
        String tipVar = null;
        int decVar = 0;
        int duzinaVar = 0;
        
        String text = "";
        if (kojaPoljaMedjuZbirVector){
            try {
                tipVar = formPrintPreview.koZove.metaData.getColumnTypeName(i + 1);
                decVar = formPrintPreview.koZove.metaData.getScale(i + 1);
                duzinaVar = formPrintPreview.koZove.metaData.getPrecision(i + 1);
            } catch (SQLException ex) {Logger.getLogger(FocusTxt.class.getName()).log(Level.SEVERE, null, ex);}

            Formatiraj dpt = new Formatiraj();
            dpt.setVrednostVar(fff[i]);
            dpt.setTipVar(tipVar);
            dpt.setDuzinaVar(duzinaVar);
            dpt.setDecVar(decVar);
            //dpt.setKojaVrsta("TextBox");
            dpt.setKljuc(false);
            text = dpt.formatirajVar().toString();
        }
        PoljeZaStampu poljeZaStampu = new PoljeZaStampu();
        
        Font font = new Font(lineMZ.get(i).getFontName(), lineMZ.get(i).getBoldItal(), fontSize);
        font = lineMZ.get(i).setUnder(font);              
        FontMetric fontMetric = new FontMetric(font);
        double widthTexta = fontMetric.getTextWidth(text);
        poljeZaStampu.setWidthTexta(widthTexta);

        poljeZaStampu.setAlignment(lineMZ.get(i).getAlignment());

        poljeZaStampu.setFontSize(lineMZ.get(i).getFontSize());        
        poljeZaStampu.setVisinaFonta(lineMZ.get(i).getVisinaFonta());        
        poljeZaStampu.setMaxVisinaFonta(lineMZ.get(i).getMaxVisinaFonta());
        poljeZaStampu.setWidthPolja(lineMZ.get(i).getWidthPolja());
        
        poljeZaStampu.setLeftLine(lineMZ.get(i).getLeftLine());
        poljeZaStampu.setDownLine(lineMZ.get(i).getDownLine());        
        poljeZaStampu.setRightLine(lineMZ.get(i).getRightLine());        
        poljeZaStampu.setTopLine(lineMZ.get(i).getTopLine());
        
        poljeZaStampu.setLeftLineWeight(lineMZ.get(i).getLeftLineWeight());
        poljeZaStampu.setDownLineWeight(lineMZ.get(i).getDownLineWeight());        
        poljeZaStampu.setRightLineWeight(lineMZ.get(i).getRightLineWeight());        
        poljeZaStampu.setTopLineWeight(lineMZ.get(i).getTopLineWeight());
        
        poljeZaStampu.setSmanji(lineMZ.get(i).getSmanji());
        poljeZaStampu.setOdseci(lineMZ.get(i).getOdseci());
        
        poljeZaStampu.setBold(lineMZ.get(i).getBold());        
        poljeZaStampu.setItalic(lineMZ.get(i).getItalic());
        poljeZaStampu.setUnderLine(lineMZ.get(i).getUnderLine());        
        poljeZaStampu.setFontName(lineMZ.get(i).getFontName());
        
        poljeZaStampu.setCijeJe(lineMZ.get(i).getCijeJe());
        
        poljeZaStampu.setVrednost(text);            
        Konstante konstante = new Konstante();
        int minVelFonta = konstante.getMinVelicinaFonta();
        if (poljeZaStampu.getSmanji()){
            for (int j=fontSize-1; j>=minVelFonta; j--){
                if(widthTexta + 2 * medjX > poljeZaStampu.getWidthPolja()){
                    fontSize = j;
                    fontMetric = new FontMetric(new Font(lineMZ.get(i).getFontName(), lineMZ.get(i).getBoldItal(), fontSize));
                    widthTexta = fontMetric.getTextWidth(text);
                    poljeZaStampu.setWidthTexta(widthTexta);
                }else{break;}
            }
        }
        poljeZaStampu.setFontSize(fontSize);
        //Odsecanje teksta ako prelazi granice polja i ako se to zeli
        if (poljeZaStampu.getOdseci()){
            int ukZnakova = text.length();
            for (int j=1; j<ukZnakova; j++){                    
                if(widthTexta + 2 * medjX > poljeZaStampu.getWidthPolja()){
                    fontMetric = new FontMetric(new Font(lineMZ.get(i).getFontName(), lineMZ.get(i).getBoldItal(), fontSize));
                    String novaVrednost = text.substring(0, ukZnakova-j-1) + "...";
                    widthTexta = fontMetric.getTextWidth(novaVrednost);
                            
                    poljeZaStampu.setWidthTexta(widthTexta);
                    poljeZaStampu.setVrednost(novaVrednost);  
                }else{break;}
           }                    
        }         
        
        
        
        
        
        return poljeZaStampu;
    }
    
}
