/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Obrasci.Tabele;

import Stampa.Apstraktne.OblikStampe;

/**
 *
 * @author Nebojsa
 */
public class TableMedjuZbir  extends OblikStampe{
    int red;
    int col;
    int maxCol;
    
    public TableMedjuZbir(String fontName, int fontSize, int red, int col, int maxCol, String vrednost, String alignment, String boldItalic){
        super(fontName, fontSize, vrednost, alignment, boldItalic, "MedjuZbir");
        this.maxCol = maxCol;
        this.red=red;
        this.col=col;
    }  

    public void SetujPodatke(){
        setLeftLine(false);   setLeftLineWeight(0);
        setDownLine(true);   setDownLineWeight(2);
        setRightLine(false); setRightLineWeight(0);
        setTopLine(true);    setTopLineWeight(2);

        setSmanji(true);
        setOdseci(true); 
        if (col==0){setLeftLine(true);setLeftLineWeight(2);}
        if (col==maxCol){setRightLine(true);setRightLineWeight(2);}       
    }
}
