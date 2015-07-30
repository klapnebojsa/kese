/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.Povezivanje;

import Class.DAO.BrokerDAO;
import Class.SistOperacije.IspraviSO;
import Class.SistOperacije.NoviSO;
import java.sql.ResultSetMetaData;

/**
 *
 * @author Nebojsa
 */
public class Upisi {

    boolean uReduDAO;
    BrokerDAO brokerDAO;

    public Upisi(BrokerDAO brokerDAO) {
        this.brokerDAO = brokerDAO;
    }

    public boolean UpisiNovi(Object[] podaciZaUpis, ResultSetMetaData metaData) throws Exception {
        NoviSO noviSO = new NoviSO(brokerDAO);
        uReduDAO = (Boolean) noviSO.Izvrsi();
        return uReduDAO;
    }

    public boolean UpisiIzmenu(String[] podaciZaUpis, ResultSetMetaData metaData) throws Exception {
        IspraviSO ispraviSO = new IspraviSO(brokerDAO);
        uReduDAO = (Boolean) ispraviSO.Izvrsi();
        return uReduDAO;
    }
}
