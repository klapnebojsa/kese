/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.Povezivanje;

import Class.DAO.BrokerDAO;
import Class.SistOperacije.VratiJedanSO;
import Class.SistOperacije.VratiListuSO;
import Class.SistOperacije.VratiTblListuSO;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.PersistenceContext;

/**
 *
 * @author neso
 */
public class Procitaj {

    //private BrokerDAO brokerDAO;
    /**
     *
     * @param KoZove
     * @param vlasnikStr
     * @return
     * @throws java.sql.SQLException
     */
    //BrokerDAO brokerDAO;    
    @PersistenceContext
    @SuppressWarnings({"unchecked", "fallthrough"})
    public List ProcitajSvexx(BrokerDAO brokerDAO) throws SQLException, Exception {
        List results = new LinkedList();
        results = null;

        VratiListuSO vratiListu = new VratiListuSO(brokerDAO);
        Object rez = vratiListu.Izvrsi();
        results = (List) rez;

        return results;
    }

    public List ProcitajTabelaxx(BrokerDAO brokerDAO) throws SQLException, Exception {

        List results = new LinkedList();
        results = null;

        VratiTblListuSO vratiListu = new VratiTblListuSO(brokerDAO);
        Object rez = vratiListu.Izvrsi();
        results = (List) rez;

        return results;
    }
    public List ProcitajJedanxx(BrokerDAO brokerDAO) throws SQLException, Exception {

        List results = new LinkedList();
        results = null;

        VratiJedanSO vratiListu = new VratiJedanSO(brokerDAO);
        Object rez = vratiListu.Izvrsi();
        results = (List) rez;

        return results;
    }
    
    public String ProcitajZaglTabele(BrokerDAO brokerDAO) throws SQLException, Exception {
        //BrokerDAO brokerDAO = new BrokerDAO(aa);
        return (brokerDAO.ZaglavljeTabele());
    }

    public int[] ProcitajKljuceve(BrokerDAO brokerDAO) throws SQLException, Exception {
        //BrokerDAO brokerDAO = new BrokerDAO(aa);
        return (brokerDAO.Kljuceve());
    }

    public int[] ProcitajPoljaDisabled(BrokerDAO brokerDAO) throws SQLException, Exception {
        //BrokerDAO brokerDAO = new BrokerDAO(aa);
        return (brokerDAO.PoljaDisabled());
    }

    public Object[] ProcitajPoljaDisabledRacunati(BrokerDAO brokerDAO) throws SQLException, Exception {
        //BrokerDAO brokerDAO = new BrokerDAO(aa);
        return (brokerDAO.DisabledRacunati());
    }

    public boolean IzracPodatkeUTabeli(BrokerDAO brokerDAO) throws SQLException, Exception {
        //BrokerDAO brokerDAO = new BrokerDAO(aa);
        return (brokerDAO.IzracPodatkeUTabeli());
    }

    public String ProcitajRadioComboCheck(BrokerDAO brokerDAO) throws SQLException, Exception {
        //BrokerDAO brokerDAO = new BrokerDAO(aa);
        return (brokerDAO.RadioComboCheck());
    }

    //Vrati MetaData - podatke
    public ResultSetMetaData ProcitajMeta(BrokerDAO brokerDAO) throws SQLException {
        //BrokerDAO brokerDAO = new BrokerDAO(aa);        
        ResultSetMetaData rsmd = brokerDAO.VratiMeta();
        return rsmd;
    }
    public String ProcitajSifruMargina(BrokerDAO brokerDAO) throws Exception {
        return brokerDAO.ProcitajSifruMargina();
    }     
}
