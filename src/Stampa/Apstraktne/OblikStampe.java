/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Apstraktne;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
public abstract class OblikStampe {
    String vrednost;
    String alignment; 
    String boldItalic;

    int fontSize;
    
    boolean leftLine;
    boolean downLine;
    boolean rightLine;
    boolean topLine;
    
    int leftLineWeight;
    int rightLineWeight;
    int topLineWeight;
    int downLineWeight;
    
    boolean smanji;
    boolean odseci;
    
    boolean bold;
    boolean italic;
    boolean underLine;
    String fontName;
    
    String cijeJe;
    
    public OblikStampe(String fontName, int fontSize, String vrednost, String alignment, String boldItalic, String cijeJe){
        this.fontName=fontName;
        this.fontSize=fontSize;
        this.alignment = alignment;
        this.vrednost = vrednost;
        this.boldItalic = boldItalic;
        this.cijeJe = cijeJe;
        
        if (boldItalic.contains("b") || boldItalic.contains("B"))bold = true;
        if (boldItalic.contains("i") || boldItalic.contains("I"))italic = true;        
        if (boldItalic.contains("u") || boldItalic.contains("U"))underLine = true;       
    }    
    public abstract void SetujPodatke();
    
    public void    setVrednost(String vrednost){this.vrednost=vrednost;}    
    public String  getVrednost() {return vrednost;}

    public void    setAlignment(String alignment){this.alignment=alignment;}
    public String  getAlignment() {return alignment;}    
    public void    setFontSize(int fontSize){this.fontSize=fontSize;}    
    public int     getFontSize() {return fontSize;}
    
    public void    setLeftLine(boolean leftLine) {this.leftLine=leftLine;}    
    public boolean getLeftLine() {return leftLine;}
    public void    setDownLine(boolean downLine) {this.downLine=downLine;}    
    public boolean getDownLine() {return downLine;}    
    public void    setRightLine(boolean rightLine) {this.rightLine=rightLine;}    
    public boolean getRightLine() {return rightLine;}    
    public void    setTopLine(boolean topLine) {this.topLine=topLine;}    
    public boolean getTopLine() {return topLine;} 
    
    public void    setLeftLineWeight(int leftLineWeight) {this.leftLineWeight=leftLineWeight;}    
    public int     getLeftLineWeight() {return leftLineWeight;}   
    public void    setRightLineWeight(int rightLineWeight) {this.rightLineWeight=rightLineWeight;}    
    public int     getRightLineWeight() {return rightLineWeight;}   
    public void    setTopLineWeight(int topLineWeight) {this.topLineWeight=topLineWeight;}    
    public int     getTopLineWeight() {return topLineWeight;}    
    public void    setDownLineWeight(int downLineWeight) {this.downLineWeight=downLineWeight;}    
    public int     getDownLineWeight() {return downLineWeight;} 
    
    public void    setSmanji(boolean smanji) {this.smanji=smanji;}
    public boolean getSmanji() {return smanji;}  
    public void    setOdseci(boolean odseci) {this.odseci=odseci;}    
    public boolean getOdseci() {return odseci;}   
    public void    setBold(boolean bold) {this.bold=bold;}    
    public boolean getBold() {return bold;}       
    public void    setItalic(boolean italic) {this.italic=italic;}    
    public boolean getItalic() {return italic;}    
    public void    setUnderLine(boolean underLine) {this.underLine=underLine;}    
    public boolean getUnderLine() {return underLine;}
   
    public void    setFontName(String fontName) {this.fontName=fontName;}    
    public String  getFontName()   {return fontName;}
    
    public int getBoldItal(){
        boolean menjano =false;
        int boldItal = Font.PLAIN;
        if (bold){boldItal = Font.BOLD; menjano =true;}
        if (italic){
            if (menjano){boldItal += Font.ITALIC;
            }else{boldItal = Font.ITALIC;}
        }      
        return boldItal;
    }
    public Font setUnder(Font font){
        if (underLine){
            Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
            fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            font = font.deriveFont(fontAttributes);
        }
        return font;        
    }
    
    public void    setCijeJe(String fontName) {this.cijeJe=cijeJe;}    
    public String  getCijeJe()   {return cijeJe;}    
}
