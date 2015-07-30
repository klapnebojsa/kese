/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Prikazi;

import Forme.Konstante.Formati;
import Forme.Polja.Listeneri.FocusTxt;
import Forme.Tabele.MojaTabela;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Nebojsa
 */
public class PoljaIzTabeleNapuni {

    JTable table;
    JTextField[] text;
    JComboBox[] combo;
    ResultSetMetaData metaData;
    PoljaIzTabeleDefinicija poljaIzTabele;

    public PoljaIzTabeleNapuni(MojaTabela mt1, PoljaIzTabeleDefinicija poljaIzTabele) {
        this.table = mt1.getTable();
        this.text = poljaIzTabele.getTexts();
        this.combo = poljaIzTabele.getCombos();
        this.metaData = poljaIzTabele.getMetaData();
        this.poljaIzTabele = poljaIzTabele;
    }

    public void Napuni() throws SQLException {
        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
            NapuniJedan(i);
        }
    }

    public void NapuniJedan(int i) throws SQLException {
        int viewRow = table.getSelectedRow();
        String tipVar = null;
        tipVar = metaData.getColumnTypeName(i + 1);
        String vrednostUTabeli=null;
        try {vrednostUTabeli = table.getValueAt(viewRow, i).toString();
        }catch(Exception e){}
        switch (tipVar) {
            //Datum - rotiranje datuma iz (format iz tabele u textbox) dd/mm/gggg u gggg/mm/dd 
            //ili obrnuto u zavisnosti kako je definisano za tekst box i tabelu
            case "DATE":
                try {
                    Formati constM = new Formati();
                    Date myDate = new Date(vrednostUTabeli);
                    vrednostUTabeli = new SimpleDateFormat(constM.getDateFormatText()).format(myDate);
                } catch (Exception e) {
                }
                break;
            //U Svim ostalim slucajevima ispisujemo ono sto je u tabeli
            default:

                break;
        }

        //Setovanje vrednosti u ComboBox poljima ili TextField Poljima
        switch (poljaIzTabele.getStaJe(i)) {
            case "CoBox":
                combo[i].setSelectedItem(vrednostUTabeli);
                break;
            default:
                text[i].setText(vrednostUTabeli);
                break;
        }
    }

}
