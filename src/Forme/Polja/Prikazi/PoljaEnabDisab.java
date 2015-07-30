/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Prikazi;

import Forme.Konstante.Boje;
import Forme.Tabele.MojaTabela;
import java.awt.Color;
import java.sql.SQLException;

/**
 *
 * @author Nebojsa
 */
public class PoljaEnabDisab {

    MojaTabela mt1;
    PoljaIzTabeleDefinicija poljaIzTabele;

    public PoljaEnabDisab(MojaTabela mt1, PoljaIzTabeleDefinicija poljaIzTabele) {
        this.mt1 = mt1;
        this.poljaIzTabele = poljaIzTabele;
    }

    public void PoljaEnabled() {
        for (int i = 0; i < mt1.getTable().getModel().getColumnCount(); i++) {
            switch (poljaIzTabele.getStaJe(i)) {
                case "CoBox":
                    poljaIzTabele.getCombos()[i].setEnabled(true);
                    poljaIzTabele.getCombos()[i].setEditable(false);
                    break;
                default:
                    poljaIzTabele.getTexts()[i].setEnabled(true);
                    break;
            }
        }
    }

    public void PoljaDisabled() {
        for (int i = 0; i < mt1.getTable().getModel().getColumnCount(); i++) {
            switch (poljaIzTabele.getStaJe(i)) {
                case "CoBox":
                    poljaIzTabele.getCombos()[i].setEnabled(false);
                    poljaIzTabele.getCombos()[i].setEditable(true);
                    break;
                default:
                    poljaIzTabele.getTexts()[i].setEnabled(false);
                    break;
            }
        }
    }

    public void TableDisabled() {
        Boje boje = new Boje();
        Color c = boje.getColorTextDisabled();
        mt1.getTable().setForeground(c);
        mt1.getTable().setEnabled(false);
        mt1.getFilterText().setEnabled(false);
        try {
            //table.setRowSorter(null);              
        } catch (Exception e) {
        }

    }

    public void TableEnabled() {
        Boje boje = new Boje();
        mt1.getTable().setForeground(boje.getColorFontDefTable());
        mt1.getTable().setEnabled(true);
        mt1.getFilterText().setEnabled(true);
    }

    public void SifraDisabled(int[] kljucevi) throws SQLException {
        for (int i = 0; i < mt1.getTable().getModel().getColumnCount(); i++) {
            for (int j = 0; j < kljucevi.length; j++) {
                if (kljucevi[j] == i) {
                    switch (poljaIzTabele.getStaJe(i)) {
                        case "CoBox":
                            poljaIzTabele.getCombos()[i].setEnabled(false);
                            poljaIzTabele.getCombos()[i].setEditable(true);
                            break;
                        default:
                            poljaIzTabele.getTexts()[i].setEnabled(false);
                            break;
                    }
                }
            }
        }
    }

    public void SifraAIDisabled(int[] kljucevi) throws SQLException {
        for (int i = 0; i < mt1.getTable().getModel().getColumnCount(); i++) {
            boolean a = poljaIzTabele.getMetaData().isAutoIncrement(i + 1);
            for (int j = 0; j < kljucevi.length; j++) {
                if (kljucevi[j] == i && a) {
                    switch (poljaIzTabele.getStaJe(i)) {
                        case "CoBox":
                            poljaIzTabele.getCombos()[i].setEnabled(false);
                            poljaIzTabele.getCombos()[i].setEditable(true);
                            break;
                        default:
                            poljaIzTabele.getTexts()[i].setEnabled(false);
                            break;
                    }
                }
            }
        }
    }

    public void PoljeRacunDisabled(int[] poljaDisabled) throws SQLException {
        for (int i = 0; i < mt1.getTable().getModel().getColumnCount(); i++) {
            int p = 0;
            try {
                p = poljaDisabled.length;
            } catch (Exception e2) {
            }
            for (int j = 0; j < p; j++) {
                if (poljaDisabled[j] == i) {
                    switch (poljaIzTabele.getStaJe(i)) {
                        case "CoBox":
                            poljaIzTabele.getCombos()[i].setEnabled(false);
                            poljaIzTabele.getCombos()[i].setEditable(true);
                            break;
                        default:
                            poljaIzTabele.getTexts()[i].setEnabled(false);
                            break;
                    }
                }
            }
        }
    }
}
