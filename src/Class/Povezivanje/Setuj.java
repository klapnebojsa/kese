/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.Povezivanje;

import Class.DAO.BrokerDAO;
import java.sql.ResultSetMetaData;

/**
 *
 * @author Nebojsa
 */
public class Setuj {

    boolean uReduDAO;
    BrokerDAO brokerDAO;

    public Setuj(BrokerDAO brokerDAO) {
        this.brokerDAO = brokerDAO;
    }

    public boolean SetujPodatke(String[] podaciZaUpis, ResultSetMetaData metaData, boolean SetAutoIncrement) throws Exception {
        uReduDAO = brokerDAO.a.SetujPodatke(podaciZaUpis, metaData, SetAutoIncrement);
        return uReduDAO;
    }

    public boolean SetujSifru(String[] podaciZaUpis, ResultSetMetaData metaData, boolean SetAutoIncrement) throws Exception {
        uReduDAO = brokerDAO.a.SetujPodatke(podaciZaUpis, metaData, SetAutoIncrement);
        return uReduDAO;
    }
    
    public void SetujSifruMargina(String sifra) throws Exception {
        brokerDAO.SetujSifruMargina(sifra);
    }    
}
