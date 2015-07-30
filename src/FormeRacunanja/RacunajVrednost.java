/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormeRacunanja;

import Class.DAO.BrokerDAO;
import Class.Povezivanje.Procitaj;
import Class.Povezivanje.Setuj;
import Forme.Ispis.Formatiraj;
import Forme.Polja.Prikazi.PoljaIzTabeleDefinicija;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nebojsa
 */
public class RacunajVrednost {

    boolean ispravanoSetovanje = false;

    public String[] DisabledPolja(PoljaIzTabeleDefinicija poljaIzTabele, BrokerDAO brokerDAO) throws Exception {
        Setuj setuj = new Setuj(brokerDAO);
        String[] podaciZaUpis = null;
        try {
            podaciZaUpis = poljaIzTabele.getPodaciZaUpis();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(PoljaIzTabeleDefinicija.class.getName()).log(Level.SEVERE, null, ex);
        }

        setIspravanoSetovanje(setuj.SetujPodatke(podaciZaUpis, poljaIzTabele.getMetaData(), true));

        //Racunanje vrednosti koje su disabled u textFieldu ili ComboBoxu
        Procitaj procitaj = new Procitaj();
        int[] poljaDisabled = procitaj.ProcitajPoljaDisabled(brokerDAO);
        boolean izracunato = procitaj.IzracPodatkeUTabeli(brokerDAO);
        Object[] poljaDisVrednosti = procitaj.ProcitajPoljaDisabledRacunati(brokerDAO);
        int p = 0;
        try {
            p = poljaDisabled.length;
        } catch (Exception e1) {
        }
        for (int i = 0; i < p; i++) {
            Formatiraj dpt = new Formatiraj();
            dpt.setTipVar(poljaIzTabele.getMetaData().getColumnTypeName(poljaDisabled[i] + 1));
            dpt.setDuzinaVar(poljaIzTabele.getMetaData().getPrecision(poljaDisabled[i] + 1));
            dpt.setDecVar(poljaIzTabele.getMetaData().getScale(poljaDisabled[i] + 1));
            dpt.setVrednostVar(poljaDisVrednosti[i]);
            switch (poljaIzTabele.getStaJe(i)) {
                case "CoBox":
                    dpt.setKojaVrsta("ComboBox");
                    break;
                default:
                    dpt.setKojaVrsta("TextBox");
                    break;
            }
            dpt.setKljuc(false);
            try{
                String IspravljenTextF = dpt.formatirajVar().toString();
                poljaIzTabele.getTexts()[poljaDisabled[i]].setText(IspravljenTextF);                
            }catch(Exception e){}
        }
        return podaciZaUpis;
    }

    public void setIspravanoSetovanje(boolean ispravanoSetovanje) {
        this.ispravanoSetovanje = ispravanoSetovanje;
    }

    public boolean getIspravanoSetovanje() {
        return ispravanoSetovanje;
    }
}
