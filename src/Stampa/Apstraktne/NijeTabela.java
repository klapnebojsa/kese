/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Apstraktne;

import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
//Sluzi da poveze lineVector sa jednim poljem u lineVector-u
//Nema nikakvu drugu funkciju niti sadrzi neke podatke u sebi
public abstract class NijeTabela {
    public void FormirajPolja(){} 
    public Vector getLineVectorAll(){Vector vector = new Vector(); return vector;}
    public void setMaxVisina(double maxVisina){}
}
