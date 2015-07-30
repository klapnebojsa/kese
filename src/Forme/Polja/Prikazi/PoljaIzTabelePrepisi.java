/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Prikazi;

import Class.DAO.BrokerDAO;
import Forme.Konstante.Formati;
import Forme.Polja.Listeneri.FocusTxt;
import Forme.Ispis.Formatiraj;
import Forme.Tabele.MojaTabela;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nebojsa
 */
public class PoljaIzTabelePrepisi {

    public List UListu(MojaTabela mt1, PoljaIzTabeleDefinicija poljaIzTabele, BrokerDAO brokerDAO, String KoJe) throws SQLException {
        //int viewRow = table.getSelectedRow();
        List uListu = new ArrayList();
        String tipVar = null;
        int poslednjiAIKljuc = 0;
        for (int i = 0; i < mt1.getTable().getModel().getColumnCount(); i++) {
            tipVar = poljaIzTabele.getMetaData().getColumnTypeName(i + 1);
            if (KoJe == "Novi") {
                try {
                    if (poljaIzTabele.getMetaData().isAutoIncrement(i + 1)) {
                        poslednjiAIKljuc = brokerDAO.opstaOperacija.poslednjiAIKljuc;
                        poljaIzTabele.getTexts()[i].setText(Integer.toString(poslednjiAIKljuc));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FocusTxt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            Object vrednostUTabeli = null; // = table.getValueAt(viewRow, i).toString();

            //Ovde videti da li je text ili combo pa pozvati sta treba
            /*String[] staJe = {""};
            try {
                staJe = poljaIzTabele.getRadioComboCheck()[i].split("%%");
            } catch (Exception e) {
                staJe[0] = "";
            }*/
            //Setovanje vrednosti u ComboBox poljima ili TextField Poljima
            switch (poljaIzTabele.getStaJe(i)) {
                case "CoBox":
                    vrednostUTabeli = poljaIzTabele.getCombos()[i].getSelectedItem().toString();
                    break;
                default:
                    vrednostUTabeli = poljaIzTabele.getTexts()[i].getText();
                    break;
            }
            switch (tipVar) {
                //Datum - rotiranje datuma iz (format iz textbox u tabelu) gggg/mm/dd u dd/mm/gggg
                //ili obrnuto u zavisnosti kako je definisano za tekst box i tabelu
                case "DATE":
                    try {
                        Formati constM = new Formati();
                        Date myDate = new Date(vrednostUTabeli.toString());
                        vrednostUTabeli = new SimpleDateFormat(constM.getDateFormatTable()).format(myDate);
                    } catch (Exception e) {
                    }
                    break;
                //U Svim ostalim slucajevima ispisujemo ono sto je u tabeli
                default:

                    break;
            }
            /*try {
             vrednostUTabeli= new Double(vrednostUTabeli.toString());  
             }catch(Exception e){}*/

            Formatiraj dpt = new Formatiraj();
            dpt.setTipVar(poljaIzTabele.getMetaData().getColumnTypeName(i + 1));
            dpt.setDuzinaVar(poljaIzTabele.getMetaData().getPrecision(i + 1));
            dpt.setDecVar(poljaIzTabele.getMetaData().getScale(i + 1));
            dpt.setVrednostVar(vrednostUTabeli);
            dpt.setKojaVrsta("Tabela");
            dpt.setKljuc(false);

            vrednostUTabeli = dpt.formatirajVar();
            uListu.add(vrednostUTabeli);
        }
        //lista.add(uListu);        
        return uListu;
    }
}
