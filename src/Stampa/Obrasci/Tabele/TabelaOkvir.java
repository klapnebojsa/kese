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
//Za svaki Cell se setuje jedan OblikStampe
public class TabelaOkvir extends OblikStampe{
    int red;
    int col;
    int maxCol;
    
    public TabelaOkvir(String fontName, int fontSize, int red, int col, int maxCol, String vrednost, String alignment, String boldItalic){
        super(fontName, fontSize, vrednost, alignment, boldItalic, "Table");
        this.maxCol = maxCol;
        this.red=red;
        this.col=col;
    }  

    public void SetujPodatke(){
        setLeftLine(true);  setLeftLineWeight(1);
        setDownLine(true);  setDownLineWeight(1);
        setRightLine(true); setRightLineWeight(1);
        setTopLine(true);   setTopLineWeight(1);

        setSmanji(true);
        setOdseci(true);
        //!!!!!!!!!!!!!!NE BRISATI !!!!!!!!!!!!!!!!!!!!!!
        //Sve ispravke se mogu vrsiti bez probema u zavisnosti od svake klase
        //red i kolona ako treba neke specificno setovati npr:
        if (col==0){setLeftLineWeight(3);}
        if (col==maxCol){setRightLineWeight(3);}
        if (col == 3 && (red == 0 | red == 4)){setUnderLine(true); setItalic(true); setBold(true); setFontSize(getFontSize() + 6); setAlignment("Right"); setVrednost("Pera"); }       
    }    
}
