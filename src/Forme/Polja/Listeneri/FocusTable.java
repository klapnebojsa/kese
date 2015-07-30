/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Listeneri;

import Class.DAO.BrokerDAO;
import Forme.Konstante.Obavestenja;
import Forme.Polja.Prikazi.InfoLinija;
import Forme.Tabele.MojaTabela;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTable;

/**
 *
 * @author Nebojsa
 */
public class FocusTable {
    String koZove;

    public void FocusLostGained(MojaTabela mt1, final InfoLinija infolinija, final String koZove) {
        this.koZove = koZove;
        mt1.getTable().addFocusListener(new FocusListener() {
            //Gained(Got) focus - zaplavi polje
            @Override
            public void focusGained(FocusEvent e) {
                Obavestenja obavestenja = new Obavestenja();
                infolinija.obavestenje.setText(obavestenja.ObavTblUnos(koZove));
            }

            //Lost focus
            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }
}
