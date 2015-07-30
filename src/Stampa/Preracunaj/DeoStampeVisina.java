/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Preracunaj;

import Stampa.Podaci.PoljeZaStampu;
import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
public class DeoStampeVisina {
    public double Preracunaj(Vector vector, double dodatak){
        double visina = 0;
        for (int iR = 0; iR < vector.size(); iR++) {
            Vector <PoljeZaStampu> line = (Vector)vector.get(iR);
            visina += line.elementAt(line.size()-1).getMaxVisinaFonta() + dodatak;
        }
        return visina;        
    }
    
}
