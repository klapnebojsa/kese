/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Napuni.ComboBoxovi;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.print.PageFormat;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

/**
 *
 * @author Nebojsa
 */
public class NapuniCombo {

    public String KutijaPrazno() {
        String a = "5@@10@@15@@20@@25@@30";
        return a;
    }

    public String KutijaPreklop() {
        String a = "10@@15@@20@@25@@30@@35@@40@@45@@50";
        return a;
    }
    
    public String FontVelicina() {
        String a = "5@@6@@7@@8@@9@@10@@11@@12@@13@@14@@15@@16";
        return a;
    }
    
    public String FontOblique(){
        String fontovi = "";
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = e.getAllFonts();
        for (Font f : fonts) {
          fontovi += f.getFontName() + "@@";
        }   
        return fontovi;
    }
    
    public String Stampaci(){
        String printeri = "";
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printer : printServices) {
          printeri += printer.getName() + "@@";
        }   
        return printeri;
    }
        
    public String FormatPapira() {
        String a = "A4(210x297)@@A3(297x420)";
        return a;
    }
    public String Orijentacija() {
        String a = "Uspravno@@Polozeno";
        return a;
    }
    
    public String procPreviewWidth() {
        String a = "@@80 %@@100 %@@150 %@@200 %@@250 %@@300 %@@350 %@@400 %@@450 %@@500 %@@page Width@@page Height";
        return a;
    }
    
}
