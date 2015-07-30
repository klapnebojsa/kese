/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Konstante;

import Forme.Polja.Listeneri.OgranicenjaListeneri;
import java.awt.event.KeyEvent;

/**
 *
 * @author Nebojsa
 */
public class Obavestenja {

    public String ObavTblUnos(String koZove) {
        
        FunkcijskiTasteri funkcijskiTasteri = new FunkcijskiTasteri();
        String obavestenje = "";
        
        OgranicenjaListeneri oL = new OgranicenjaListeneri(koZove);
        oL.proveriNoviListener();
        if (!oL.getOgranicenja()){obavestenje= obavestenje + "<" + KeyEvent.getKeyText(funkcijskiTasteri.getFtNovi()) + ">-Novi";}
        oL.proveriIzmenaListener();
        if (!oL.getOgranicenja()){obavestenje= obavestenje + "   <" + KeyEvent.getKeyText(funkcijskiTasteri.getFtIspravi()) + ">-Izmena";}        
        oL.proveriStampaTblListener();
        if (!oL.getOgranicenja()){obavestenje= obavestenje + "   <" + KeyEvent.getKeyText(funkcijskiTasteri.getFtStampaTbl()) + ">-Štampa Tabele";}
        oL.proveriStampaPodTblListener();
        if (!oL.getOgranicenja()){obavestenje= obavestenje + "   <" + KeyEvent.getKeyText(funkcijskiTasteri.getFtStampaPodTbl()) + ">-Štampa Stavki dokumenta";}        
        oL.proveriStampaIzvListener();
        if (!oL.getOgranicenja()){obavestenje= obavestenje + "   <" + KeyEvent.getKeyText(funkcijskiTasteri.getFtStampaIzv()) + ">-Štampa Izvestaja";} 
        oL.proveriMargineListener();
        if (!oL.getOgranicenja()){obavestenje= obavestenje + "   <" + KeyEvent.getKeyText(funkcijskiTasteri.getFtMargine()) + ">-Margine";}        
        oL.proveriBrisiListener();
        if (!oL.getOgranicenja()){obavestenje= obavestenje + "   <" + KeyEvent.getKeyText(funkcijskiTasteri.getFtBrisi()) + ">-Brisanje";}
         
        return obavestenje;
        //return "<F1>-Novi, <F2>-Izmena, <F12>-Margine, <Del>-Brisi";
    }

    public String ObavTxtUnos() {
        //return "<Enter>-Upisi, <Esc>-Odustani";
        FunkcijskiTasteri funkcijskiTasteri = new FunkcijskiTasteri();
        String obavestenje = "";
        
        obavestenje= obavestenje + "<" + KeyEvent.getKeyText(funkcijskiTasteri.getFtUpisi()) + ">-Upisi"; 
        obavestenje= obavestenje + "   <" + KeyEvent.getKeyText(funkcijskiTasteri.getFtOdustni()) + ">-Odustani";
        return obavestenje;
    }

    public String ObavPretraga() {
        return "Unesite rec za pretragu";
    }
}
