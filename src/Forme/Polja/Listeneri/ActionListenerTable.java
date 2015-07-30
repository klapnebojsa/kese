/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Listeneri;

import Class.Apstraktne.AbstractDAO;
import Class.DAO.BrokerDAO;
import Class.KlaseBaze.Margine;
import Forme.Konstante.FunkcijskiTasteri;
import Forme.PopUpovi.PopUp;
import Class.Povezivanje.Brisi;
import Class.Povezivanje.Setuj;
import Forme.FormForme;
import Forme.FormGlavna;
import Forme.FormPrintPreview;
import Forme.Polja.Prikazi.PoljaEnabDisab;
import Forme.Polja.Prikazi.PoljaIzTabeleDefinicija;
import Forme.Polja.Prikazi.PoljaIzTabeleIsprazni;
import Forme.Tabele.MojaTabela;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nebojsa
 */
public class ActionListenerTable {
    FormForme koZove;
    PoljaEnabDisab poljaEnabDisab;
    boolean izbrisano;
    PoljaIzTabeleDefinicija poljaIzTabele;
    MojaTabela mt1;

    public ActionListenerTable(PoljaIzTabeleDefinicija poljaIzTabele, MojaTabela mt1, FormForme koZove) {
        this.poljaIzTabele = poljaIzTabele;
        this.mt1 = mt1;
        this.koZove = koZove;
    }

    public void keyPressed(KeyEvent e, BrokerDAO brokerDAO, int[] kljucevi, java.awt.Frame KoZove, int[] poljaDisabled) throws SQLException, ParseException, Exception {
        FunkcijskiTasteri ft = new FunkcijskiTasteri();
        PopUp popUp = null;
        
        OgranicenjaListeneri oL = new OgranicenjaListeneri(brokerDAO.a.ImeKlase());
        
        //---------------- F1 NOVI -------------------------------------------------------------------- 
        oL.proveriNoviListener();
        if (e.getKeyCode() == ft.getFtNovi() && !oL.getOgranicenja()) {                    // F1-Novi         
            poljaIzTabele.setKojiUpis("Novi");

            poljaEnabDisab = new PoljaEnabDisab(mt1, poljaIzTabele);
            poljaEnabDisab.PoljaEnabled();
            poljaEnabDisab.TableDisabled();
            PoljaIzTabeleIsprazni poljaIzTabeleIsprazni = new PoljaIzTabeleIsprazni(mt1, poljaIzTabele);
            poljaIzTabeleIsprazni.Isprazni();
            try {poljaEnabDisab.SifraAIDisabled(kljucevi);
            } catch (SQLException ex) {Logger.getLogger(MojaTabela.class.getName()).log(Level.SEVERE, null, ex);}
            poljaEnabDisab.PoljeRacunDisabled(poljaDisabled);           
        }

        //---------------- F2 IZMENI --------------------------------------------------------------------
        oL.proveriIzmenaListener();        
        if (e.getKeyCode() == ft.getFtIspravi() && !oL.getOgranicenja()) {                 // F2-Izmeni          
            poljaIzTabele.setKojiUpis("Izmeni");

            poljaEnabDisab = new PoljaEnabDisab(mt1, poljaIzTabele);
            poljaEnabDisab.PoljaEnabled();
            poljaEnabDisab.TableDisabled();
            try {poljaEnabDisab.SifraDisabled(kljucevi);
            } catch (SQLException ex) {Logger.getLogger(MojaTabela.class.getName()).log(Level.SEVERE, null, ex);}

            poljaEnabDisab.PoljeRacunDisabled(poljaDisabled);
        }
        
        //---------------- F5 STAMPA --------------------------------------------------------------------
        oL.proveriStampaTblListener();
        if (e.getKeyCode() == ft.getFtStampaTbl() && !oL.getOgranicenja()) {                 // F5-Stampa
            FormPrintPreview formPrintPreview = new FormPrintPreview(mt1, koZove, "Tabela", "StampaTabele");
            formPrintPreview.Prikazi();            
        }
        
        //---------------- F7 STAMPA PodTabele (npr sada smo na nalogu a ovo stampa stavke naloga)-------
        /*oL.proveriStampaPodTblListener();
        if (e.getKeyCode() == ft.getFtStampaPodTbl() && !oL.getOgranicenja()) {                 // F7-Stampa
            FormPrintPreview formPrintPreview = new FormPrintPreview(mt2, koZove, "Tabela", "StampaPodTabele");
            formPrintPreview.Prikazi();            
        }*/        
        
        
        //---------------- F9 STAMPA POSEBNOG IZVESTAJA ZA JEDNU STAVKU IZ TABELE ------------------------
        /*oL.proveriStampaPosIzvListener();
        if (e.getKeyCode() == ft.getFtStampaIzv() && !oL.getOgranicenja()) {                 // F9-Stampa
            FormPrintPreview formPrintPreview = new FormPrintPreview(mt1, koZove, "Obrazac", "StampaIzvestaja);
            formPrintPreview.Prikazi();            
        }*/
        
        //---------------- F12 MARGINE --------------------------------------------------------------------
        oL.proveriMargineListener();
        if (e.getKeyCode() == ft.getFtMargine() && !oL.getOgranicenja()) {                 // F12-Margine
            FormForme f = null;
            AbstractDAO klasa;
            klasa = new Margine();
            try {
                BrokerDAO brokerDAOPret = koZove.getBrokerDAO();
                f = new FormForme(koZove, "Margine Za Stampu - " + koZove.getTitle(), 1, "Margine");
                f.k = klasa;
                f.setBrokerDAOPret(brokerDAOPret);
                f.main();
            } catch (SQLException | ParseException ex) {Logger.getLogger(FormGlavna.class.getName()).log(Level.SEVERE, null, ex);}
        }
        
        //---------------- Del IZBRISI -------------------------------------------------------------------- 
        oL.proveriBrisiListener();
        if (e.getKeyCode() == ft.getFtBrisi() && !oL.getOgranicenja()) {                     // Del-Brisi
            //int selectedRow =table.getSelectedRow();
            popUp = new PopUp(KoZove, "Da li zelite brisanje?");
            int n = popUp.DaNe();
            if (n == 0) {  // 0-Da.
                String[] podaciZaUpis = null;
                podaciZaUpis = poljaIzTabele.getPodaciZaUpis();
                Setuj setuj = new Setuj(brokerDAO);
                if (setuj.SetujPodatke(podaciZaUpis, poljaIzTabele.getMetaData(), true));
                Brisi brisi = new Brisi(brokerDAO);
                izbrisano = false;
                try { if (brisi.BrisiPostojeci()) izbrisano = true;
                } catch (Exception ex) {Logger.getLogger(MojaTabela.class.getName()).log(Level.SEVERE, null, ex);}
                if (izbrisano) {
                    int u = mt1.getModel().getSelectRow();
                    mt1.getData().remove(u);
                    mt1.getModel().fireTableRowsDeleted(u, u);
                    //Focus
                    int max = mt1.getModel().getRowCount();
                    if (u <= max - 1) {mt1.getModel().selectRow(u, u);
                    } else {mt1.getModel().selectRow(u - 1, u - 1);}
                    mt1.getTable().requestFocus();

                    popUp = new PopUp(KoZove, "Uspesno Brisanje!");
                    popUp.Ok();
                } else {
                    popUp = new PopUp(KoZove, "!!! NEUspesno Brisanje !!!");
                    popUp.Ok();
                }
            }
        }  
    }
}
