/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Prikazi;

import Forme.Tabele.MojaTabela;

/**
 *
 * @author Nebojsa
 */
public class PoljaIzTabeleIsprazni {

    MojaTabela mt1;
    PoljaIzTabeleDefinicija poljaIzTabele;

    public PoljaIzTabeleIsprazni(MojaTabela mt1, PoljaIzTabeleDefinicija poljaIzTabele) {
        this.mt1 = mt1;
        this.poljaIzTabele = poljaIzTabele;
    }

    public void Isprazni() {
        for (int i = 0; i < mt1.getTable().getModel().getColumnCount(); i++) {
            //Setovanje vrednosti u ComboBox poljima ili TextField Poljima
            switch (poljaIzTabele.getStaJe(i)) {
                case "CoBox":
                    poljaIzTabele.getCombos()[i].setSelectedIndex(0);
                    break;
                default:
                    poljaIzTabele.getTexts()[i].setText("");
                    break;
            }
        }      
    }
}
