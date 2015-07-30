/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.Apstraktne;

import Class.DAO.BrokerDAO;
import java.sql.SQLException;

/**
 *
 * @author neso
 */
public abstract class Opsta {

    String sql;
    AbstractDAO k;
    BrokerDAO brokerDAO;

    public Opsta(BrokerDAO brokerDAO_) {
        //k=interf;
        //brokerDAO = new BrokerDAO(k);
        brokerDAO = brokerDAO_;
    }

    public Object Izvrsi() throws Exception {
        Object StaJeTrazeno = null;
        try {
            otvoriBazu();
            zapocniComm();
            formirajRec(brokerDAO);
            StaJeTrazeno = izvrsiRec(brokerDAO);
            try {
                izvrsiComm();
            } catch (ClassNotFoundException | SQLException e) {
                izvrsiRoll();
                StaJeTrazeno = null;
            }
            VratiMeta();
            zatvoriBazu();
        } catch (Exception e) { }
        return StaJeTrazeno;
    }

    private void otvoriBazu() throws ClassNotFoundException, SQLException {
        brokerDAO.otvoriBazu();
    }

    private void zapocniComm() throws ClassNotFoundException, SQLException {
        brokerDAO.zapocniComm();
    }

    // U svakoj podklasi se definisu uslovi formiranja reci za sql upit i koji se sql upit izvrsava
    protected abstract String formirajRec(BrokerDAO brokerDAO) throws Exception;

    protected abstract Object izvrsiRec(BrokerDAO brokerDAO) throws Exception;

    private void izvrsiComm() throws ClassNotFoundException, SQLException {
        brokerDAO.izvrsiComm();
    }

    private void izvrsiRoll() throws ClassNotFoundException, SQLException {
        brokerDAO.izvrsiRoll();
    }

    private void zatvoriBazu() throws ClassNotFoundException, SQLException {
        brokerDAO.zatvoriBazu();
    }

    private void VratiMeta() throws ClassNotFoundException, SQLException {
        brokerDAO.VratiMeta();
    }
}
