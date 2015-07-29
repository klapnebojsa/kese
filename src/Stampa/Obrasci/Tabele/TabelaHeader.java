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
public class TabelaHeader extends OblikStampe{
    int red;
    int col;
    int maxCol;
    
    public TabelaHeader(String fontName, int fontSize, int red, int col, int maxCol, String vrednost, String alignment, String boldItalic){
        super(fontName, fontSize, vrednost, alignment, boldItalic, "Table");
        this.maxCol = maxCol;
        this.red=red;
        this.col=col;
    }  

    public void SetujPodatke(){
        setLeftLine(true);  setLeftLineWeight(2);
        setDownLine(true);  setDownLineWeight(2);
        setRightLine(true); setRightLineWeight(2);
        setTopLine(true);   setTopLineWeight(2);

        setSmanji(true);
        setOdseci(true);

        //!!!!!!!!!!!!!!NE BRISATI !!!!!!!!!!!!!!!!!!!!!!
        //Sve ispravke se mogu vrsiti bez probema u zavisnosti od svake klase
        //red i kolona ako treba neke specificno setovati npr:       
        //if (col == 3){setUnderLine(true); setItalic(true); setBold(true); setFontSize(getFontSize() + 2); setAlignment("Right"); setVrednost("Pera"); }        
    }
}
