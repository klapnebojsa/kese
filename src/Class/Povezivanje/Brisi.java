/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.Povezivanje;

import Class.DAO.BrokerDAO;
import Class.SistOperacije.BrisiSO;
import java.sql.ResultSetMetaData;

/**
 *
 * @author Nebojsa
 */
public class Brisi {

    boolean uReduDAO;
    BrokerDAO brokerDAO;

    public Brisi(BrokerDAO brokerDAO) {
        this.brokerDAO = brokerDAO;
    }

    //public boolean BrisiPostojeci(Object[] podaciZaUpis, ResultSetMetaData metaData) throws Exception{
    public boolean BrisiPostojeci() throws Exception {
        BrisiSO brisiSO = new BrisiSO(brokerDAO);
        uReduDAO = (Boolean) brisiSO.Izvrsi();
        return uReduDAO;
    }
}
