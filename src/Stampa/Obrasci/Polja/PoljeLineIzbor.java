/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Obrasci.Polja;

import Stampa.Apstraktne.OblikStampe;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Nebojsa
 */

//boldItalic = b,B-Bold I,i-italic U,u-UnderLine // d,D-LineDown T,t-LineTop l,L-LineLeft r,R-LineRight  // o,O-Odseci S,s-Smanji 
public class PoljeLineIzbor  extends OblikStampe{
    String boldItalic;
    public PoljeLineIzbor(String fontName, int fontSize, String vrednost, String alignment, String boldItalic, String cijeJe){
        super(fontName, fontSize, vrednost, alignment, boldItalic, cijeJe);
        this.boldItalic = boldItalic;
    } 
    public void SetujPodatke(){
        setLeftLine(false);  setLeftLineWeight(0);
        setDownLine(false);  setDownLineWeight(0);
        setRightLine(false); setRightLineWeight(0);
        setTopLine(false);   setTopLineWeight(0);

        setSmanji(false);
        setOdseci(false);
            
        String[] wL = boldItalic.replaceAll("\\D+","").split(""); 
        // List hh = Arrays.asList(str.trim().split(""));
        
        if (boldItalic.contains("s") || boldItalic.contains("S")){setSmanji(true);  }
        if (boldItalic.contains("o") || boldItalic.contains("O")){setOdseci(true);  }
        
        if (boldItalic.contains("l") || boldItalic.contains("L")){setLeftLine(true);  try{setLeftLineWeight(Integer.parseInt(wL[1]));}catch(Exception e){};}    
        if (boldItalic.contains("d") || boldItalic.contains("D")){setDownLine(true);  try{setDownLineWeight(Integer.parseInt(wL[2]));}catch(Exception e){};}
        if (boldItalic.contains("r") || boldItalic.contains("R")){setRightLine(true); try{setRightLineWeight(Integer.parseInt(wL[3]));}catch(Exception e){};}        
        if (boldItalic.contains("t") || boldItalic.contains("T")){setTopLine(true);   try{setTopLineWeight(Integer.parseInt(wL[4]));}catch(Exception e){};}
    }
}
