/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Podaci;

import java.awt.event.MouseWheelEvent;

/**
 *
 * @author Nebojsa
 */
public class Konstante {
    public double MouseStep(MouseWheelEvent e, double p){
        double delta = 0.1f * e.getPreciseWheelRotation();
        p += delta;
        //max i min preview
        p=(p<0.8)? 0.8:p;
        p=(p>10)? 10:p;
        return p;  
    }
    
    public int getMinVelicinaFonta(){
        return 6;  
    }
}
