/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Konstante;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Nebojsa
 */
public class Formati {

    public String getDateFormatTable() {
        return "yyyy/MM/dd";
    }

    public String getDateFormatText() {
        return "dd.MM.yyyy";
    }

    public String getMaskedFormat() throws ParseException {
        return "##.##.####";
    }

    public String getDateFormatBaza() {
        return "yyyy-MM-dd";
    }

    /*public String getDateFormatTable(){
     return "dd/MM/yyyy";
     } 
     public String getDateFormatText(){
     return "yyyy/MM/dd";
     }    
     public String getMaskedFormat(){
     return "####/##/##";
     } */
}
