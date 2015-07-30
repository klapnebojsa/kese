/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Obrasci.Polja;

import Stampa.Apstraktne.OblikStampe;

/**
 *
 * @author Nebojsa
 */
public class PoljeLineAll extends OblikStampe{
    public PoljeLineAll(String fontName, int fontSize, String vrednost, String alignment, String boldItalic, String cijeJe){
        super(fontName, fontSize, vrednost, alignment, boldItalic, cijeJe);
    } 
    public void SetujPodatke(){
        setLeftLine(true);  setLeftLineWeight(1);
        setDownLine(true);  setDownLineWeight(1);
        setRightLine(true); setRightLineWeight(1);
        setTopLine(true);   setTopLineWeight(1);

        setSmanji(true);
        setOdseci(true);       
    }
}
