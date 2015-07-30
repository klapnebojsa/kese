/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Listeneri;

import Forme.Konstante.Obavestenja;
import Forme.Polja.Prikazi.InfoLinija;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSetMetaData;
import javax.swing.JComboBox;

/**
 *
 * @author Nebojsa
 */
public class FocusCBx {

    String IspravljenTextF;

    public void FocusLostGained(final JComboBox comboB, final int column, final ResultSetMetaData metaData, final InfoLinija infolinija) {
        comboB.addFocusListener(new FocusListener() {
            //Gained(Got) focus
            @Override
            public void focusGained(FocusEvent e) {
                Obavestenja obavestenja = new Obavestenja();
                infolinija.obavestenje.setText(obavestenja.ObavTxtUnos());
            }

            //Lost focus - formatira polje
            @Override
            public void focusLost(FocusEvent e) {
            }
        });
    }
}
