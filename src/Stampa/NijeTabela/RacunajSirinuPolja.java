/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.NijeTabela;

import java.awt.print.PageFormat;
import java.sql.Array;

/**
 *
 * @author Nebojsa
 */
public class RacunajSirinuPolja {
    PageFormat pageFormat;
    
    public RacunajSirinuPolja(PageFormat pageFormat){
        this.pageFormat = pageFormat;
    }     
    public double[] SirinaPolja(String odnosSirina){

        String[] result = odnosSirina.split(",");
        double [] sirine = new double[result.length];            
        double ukupno = 0;
        double hh = pageFormat.getImageableWidth();
        for (int i = 0; i < result.length; i++){ukupno+=Double.parseDouble(result[i]);}
        for (int i = 0; i < result.length; i++){sirine[i] = Double.parseDouble(result[i])*hh/ukupno; }        
      
        return sirine;
    }
    
}
