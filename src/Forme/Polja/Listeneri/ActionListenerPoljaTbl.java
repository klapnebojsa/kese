/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Listeneri;

import Class.DAO.BrokerDAO;
import Forme.Konstante.FunkcijskiTasteri;
import Forme.PopUpovi.PopUp;
import Class.Povezivanje.Upisi;
import Forme.Polja.Prikazi.PoljaEnabDisab;
import Forme.Polja.Prikazi.PoljaIzTabeleDefinicija;
import Forme.Polja.Prikazi.PoljaIzTabeleNapuni;
import Forme.Polja.Prikazi.PoljaIzTabelePrepisi;
import Forme.Tabele.MojaTabela;
import FormeRacunanja.RacunajVrednost;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nebojsa
 */
public class ActionListenerPoljaTbl {

    PoljaIzTabeleDefinicija poljaIzTabele;
    MojaTabela mt1;

    public ActionListenerPoljaTbl(PoljaIzTabeleDefinicija poljaIzTabele, MojaTabela mt1) {
        this.poljaIzTabele = poljaIzTabele;
        this.mt1 = mt1;
    }

    public void keyPressed(KeyEvent e, BrokerDAO brokerDAO, java.awt.Frame KoZove, String kojiUpis) throws SQLException, Exception {

        PopUp popUp = null;
        int n;
        boolean ispravanUpis = true;
        
        FunkcijskiTasteri ft = new FunkcijskiTasteri();
        Upisi upisi = new Upisi(brokerDAO);

        //----------------------------ENTER - Upisi---------------------------------------------------------------------------------
        if (e.getKeyCode() == ft.getFtUpisi()) {                      // Enter - Upisi
            popUp = new PopUp(KoZove, "Da li zelite da upisete podatke?");
            n = popUp.DaNe();
            if (n == 0) {  // 0-Da
                RacunajVrednost racunajVrednosti = new RacunajVrednost();

                String[] podaciZaUpis = racunajVrednosti.DisabledPolja(poljaIzTabele, brokerDAO);

                //Provere da li su vrednosti polja validne
                Object[] TextPoruke = brokerDAO.ProveriVrednosti();
                if (TextPoruke[0] != null) {
                    popUp = new PopUp(KoZove, TextPoruke[1].toString());
                    popUp.Ok();
                    poljaIzTabele.getTexts()[(int) TextPoruke[0]].requestFocus();
                    return;
                }
                switch (kojiUpis) {
                    case "Izmeni":
                        try {
                            if (racunajVrednosti.getIspravanoSetovanje()) {
                                if (!upisi.UpisiIzmenu(podaciZaUpis, poljaIzTabele.getMetaData())) ispravanUpis = false;
                            } else {ispravanUpis = false;}
                        } catch (Exception ex) {
                            ispravanUpis = false;
                            Logger.getLogger(PoljaIzTabeleDefinicija.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "Novi":
                        try {
                            if (racunajVrednosti.getIspravanoSetovanje()) {
                                if (!upisi.UpisiNovi(podaciZaUpis, poljaIzTabele.getMetaData())) ispravanUpis = false;
                            } else {ispravanUpis = false;}
                        } catch (Exception ex) {
                            ispravanUpis = false;
                            Logger.getLogger(PoljaIzTabeleDefinicija.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    default:
                        ispravanUpis = false;
                        break;
                }
                if (ispravanUpis) {
                    popUp = new PopUp(KoZove, "Uspesan Upis!");
                    popUp.Ok();
                    PoljaEnabDisab poljaEnabDisab = new PoljaEnabDisab(mt1, poljaIzTabele);
                    poljaEnabDisab.PoljaDisabled();
                    poljaEnabDisab.TableEnabled();
                    PoljaIzTabelePrepisi poljaIzTabelePrepisi = new PoljaIzTabelePrepisi();

                    switch (kojiUpis) {
                        case "Izmeni":
                            List listaIzmeni = null;
                            listaIzmeni = poljaIzTabelePrepisi.UListu(mt1, poljaIzTabele, brokerDAO, "Izmeni");
                            ListIterator<Object> i = mt1.getData().listIterator();
                            int j = 0;
                            while (i.hasNext()) {
                                Object x = i.next();
                                x = listaIzmeni;
                                if (j == mt1.getModel().getSelectRow()) {
                                    i.set(listaIzmeni);
                                    //Focus
                                    mt1.getModel().selectRow(j, j);
                                    mt1.getTable().requestFocus();
                                }
                                j++;
                            }
                            break;
                        case "Novi":
                            List listaNovi = null;
                            listaNovi = poljaIzTabelePrepisi.UListu(mt1, poljaIzTabele, brokerDAO, "Novi");
                            mt1.getData().add(listaNovi);
                            mt1.getModel().fireTableRowsInserted(mt1.getTable().getRowCount(), mt1.getTable().getRowCount());
                            break;
                        default:
                            ispravanUpis = false;
                            break;
                    }

                } else {
                    popUp = new PopUp(KoZove, "!!! NE Uspesan Upis !!!");
                    popUp.Ok();
                }
            }
        }

        //--------------------------ESC - Odustani------------------------------------------------------------------------------------------        
        if (e.getKeyCode() == ft.getFtOdustni()) {                    // Esc - Odustani
            popUp = new PopUp(KoZove, "Da li zelite da odustanete?");
            n = popUp.DaNe();
            if (n == 0) {  // 0-Da         
                PoljaEnabDisab poljaEnabDisab = new PoljaEnabDisab(mt1, poljaIzTabele);
                poljaEnabDisab.PoljaDisabled();
                poljaEnabDisab.TableEnabled();
                //Ispisivanje polja (tekstField, combos, radioButton ...) iz tabele
                PoljaIzTabeleNapuni poljaIzTabeleNapuni = new PoljaIzTabeleNapuni(mt1, poljaIzTabele);
                poljaIzTabeleNapuni.Napuni();
            }
        }
      

    }
}
