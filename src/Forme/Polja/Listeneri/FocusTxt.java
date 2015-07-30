/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Listeneri;

import Forme.Konstante.Obavestenja;
import Forme.Polja.Prikazi.InfoLinija;
import Forme.Ispis.Formatiraj;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Nebojsa
 */
public class FocusTxt {

    String IspravljenTextF;

    public void FocusLostGained(final JTextField textF, final int column, final ResultSetMetaData metaData, final InfoLinija infolinija) {
        textF.addFocusListener(new FocusListener() {
            //Gained(Got) focus - zaplavi polje
            @Override
            public void focusGained(FocusEvent e) {
                textF.selectAll();
                Obavestenja obavestenja = new Obavestenja();
                infolinija.obavestenje.setText(obavestenja.ObavTxtUnos());
            }

            //Lost focus - formatira polje
            @Override
            public void focusLost(FocusEvent e) {
                String tipVar = null;
                int decVar = 0;
                int duzinaVar = 0;
                try {
                    tipVar = metaData.getColumnTypeName(column + 1);
                    decVar = metaData.getScale(column + 1);
                    duzinaVar = metaData.getPrecision(column + 1);
                } catch (SQLException ex) {
                    Logger.getLogger(FocusTxt.class.getName()).log(Level.SEVERE, null, ex);
                }
                Formatiraj dpt = new Formatiraj();
                dpt.setTipVar(tipVar);
                dpt.setDuzinaVar(duzinaVar);
                dpt.setDecVar(decVar);
                dpt.setKojaVrsta("TextBox");
                dpt.setKljuc(false);

                //Int, bigInt
                try {
                    long l = Long.parseLong(textF.getText());
                    dpt.setVrednostVar(l);
                } catch (Exception ee) {
                    //Double + Big Decimal
                    try {
                        double d = Double.parseDouble(textF.getText().replaceAll(",", ""));
                        dpt.setVrednostVar(d);
                    //Big Decimal
                    } catch (Exception ee1) {
                        /*try {
                            BigDecimal b = new BigDecimal(textF.getText().replaceAll(",", ""));
                            dpt.setVrednostVar(b);
                        } catch (Exception ee2) {}*/
                    }
                }
                //Sve otalo osim broja i datuma
                try {
                    IspravljenTextF = dpt.formatirajVar().toString();
                    String noviText = getIspravljenTextF();
                    if (noviText !="") textF.setText(getIspravljenTextF());
                } catch (Exception e2) {
                }
            }
        });
    }

    public String getIspravljenTextF() {
        return IspravljenTextF;
    }
}
