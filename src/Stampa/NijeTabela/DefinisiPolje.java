/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.NijeTabela;

import Stampa.Apstraktne.NijeTabela;
import Stampa.Apstraktne.OblikStampe;
import Stampa.Obrasci.Polja.PoljeLineAll;
import Stampa.Obrasci.Polja.PoljeLineDown;
import Stampa.Obrasci.Polja.PoljeLineIzbor;
import Stampa.Obrasci.Polja.PoljeLineNo;
import Stampa.Obrasci.Polja.PoljeLineTop;
import Stampa.Podaci.Konstante;
import Stampa.Podaci.PoljeZaStampu;
import Stampa.PripremiPolje;

/**
 *
 * @author Nebojsa
 */
public class DefinisiPolje {
    PoljeZaStampu poljeZaStampu;
    OblikStampe oblikStampe;
    double medjX;
    double maxVisina;
    NijeTabela nijeTabela;
    int minVelFonta;
    String boldItal;
            
    public DefinisiPolje(NijeTabela  nijeTabela){
        this.nijeTabela = nijeTabela;
        this.maxVisina = maxVisina;
        Konstante konstante = new Konstante();
        minVelFonta = konstante.getMinVelicinaFonta();
                
    }
    private void DefPolje(double sirina){
        oblikStampe.SetujPodatke();
        PripremiPolje pripremiPolje= new PripremiPolje(oblikStampe, minVelFonta, medjX, this.maxVisina); 

        poljeZaStampu = new PoljeZaStampu();
        poljeZaStampu.setWidthPolja(sirina);
        
        poljeZaStampu = pripremiPolje.Polje(poljeZaStampu);
        this.maxVisina = pripremiPolje.maxVisina;
        nijeTabela.setMaxVisina(this.maxVisina);        
    }
    
    public PoljeZaStampu DefPoljeLineAll(String fontName, int fontSize, String vrednost, String alignment, double maxVisina, double sirina, String boldItal, String cijeJe){
        oblikStampe = new PoljeLineAll(fontName, fontSize, vrednost, alignment, boldItal, cijeJe);
        DefPolje(sirina); return poljeZaStampu;
    }

    
    public PoljeZaStampu DefPoljeLineNo(String fontName, int fontSize, String vrednost, String alignment, double maxVisina, double sirina, String boldItal, String cijeJe){
        oblikStampe = new PoljeLineNo(fontName, fontSize, vrednost, alignment, boldItal, cijeJe);
        DefPolje(sirina); return poljeZaStampu;
    }
    
    public PoljeZaStampu DefPoljeLineDown(String fontName, int fontSize, String vrednost, String alignment, double maxVisina, double sirina, String boldItal, String cijeJe){
        oblikStampe = new PoljeLineDown(fontName, fontSize, vrednost, alignment, boldItal, cijeJe);
        DefPolje(sirina);  return poljeZaStampu;
    }
    
    public PoljeZaStampu DefPoljeLineTop(String fontName, int fontSize, String vrednost, String alignment, double maxVisina, double sirina, String boldItal, String cijeJe){
        oblikStampe = new PoljeLineTop(fontName, fontSize, vrednost, alignment, boldItal, cijeJe);
        DefPolje(sirina);  return poljeZaStampu;
    }
    public PoljeZaStampu DefPoljeLineIzbor(String fontName, int fontSize, String vrednost, String alignment, double maxVisina, double sirina, String boldItal, String cijeJe){
        oblikStampe = new PoljeLineIzbor(fontName, fontSize, vrednost, alignment, boldItal, cijeJe);
        DefPolje(sirina);  return poljeZaStampu;
    }
}
