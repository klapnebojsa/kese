/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Podaci;


import java.awt.Font;
/**
 *
 * @author Nebojsa
 */
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
  
 
public class FontMetric extends Frame {
    double visinaFonta;
    Font font; 

    public FontMetric(Font font) {
        this.font = font;
        String text = "ŽĐŽĐjjzzyy{}";
        AffineTransform affineTransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affineTransform,true,true);     
        //int textwidth = (int)(font.getStringBounds(text, frc).getWidth());

        int textheight = (int)(font.getStringBounds(text, frc).getHeight());
        setVisinaFonta(textheight);        
    }
    
    public double getTextWidth(String text){
        double textWidth=0.;
        AffineTransform affineTransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affineTransform,true,true);     
        textWidth = font.getStringBounds(text, frc).getWidth();        
        return textWidth;
    }
    
    public void setVisinaFonta(double visinaFonta){
        this.visinaFonta = visinaFonta;
    }
    public double getVisinaFonta(){
        return visinaFonta;
    }
}
