/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.RezervisanaPolja;

import Class.Apstraktne.AbstractDAO;
import Class.DAO.BrokerDAO;
import Class.KlaseBaze.KutijaProba;
import Class.KlaseBaze.Vlasnik;
import Class.Povezivanje.Procitaj;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nebojsa
 */
public class RezervisanaPolja {
    int trenutnaStrana;
    int ukupnoStrana;
    AbstractDAO k;
    BrokerDAO brokerDAO;
    Procitaj emp;
    List myDataList;
    String naslov;
    
    public RezervisanaPolja( int trenutnaStrana, int ukupnoStrana, String naslov){
        this.trenutnaStrana = trenutnaStrana;
        this.ukupnoStrana = ukupnoStrana;
        this.naslov = naslov;
    }
    public String Prepravi(String[] vrednosti) throws Exception{
        String cell = "";
        for (int i=0; i<vrednosti.length; i++){
            switch (vrednosti[i]){
                case "PageOf":
                    cell += trenutnaStrana;
                    break;
                case "OfPages":
                    cell += ukupnoStrana;
                    break;
                case "DateTime":
                    cell += new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                    break;
                case "Date":
                    cell += new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                    break;
                case "FormName":
                    cell += naslov;
                    break;                    
                case "FirNaz":
                    k = new Vlasnik();
                    brokerDAO = new BrokerDAO(k);
                    emp = new Procitaj();
                    myDataList = emp.ProcitajTabelaxx(brokerDAO);
                    for(Object category : myDataList) {
                        List element = (List)category;
                        cell += element.get(1).toString();                       
                    }
                    break;
                case "FirMes":
                    k = new Vlasnik();
                    brokerDAO = new BrokerDAO(k);
                    emp = new Procitaj();
                    myDataList = emp.ProcitajTabelaxx(brokerDAO);
                    for(Object category : myDataList) {
                        List element = (List)category;
                        cell += element.get(3).toString();                        
                    }
                    break;                    
                default:
                    cell += vrednosti[i];
                    break;
            }              
        } 
        return cell;
    }
}
