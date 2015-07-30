/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.SistOperacije;

import Class.DAO.BrokerDAO;
import Class.Apstraktne.Opsta;
import java.sql.SQLException;

/**
 *
 * @author neso
 */
public class IspraviSO extends Opsta {

    public IspraviSO(BrokerDAO brokerDAO) {
        super(brokerDAO);
    }

    protected String formirajRec(BrokerDAO brokerDAO) throws SQLException {
        return (brokerDAO.RecIspravi());
    }

    protected Object izvrsiRec(BrokerDAO brokerDAO) throws SQLException {
        Object result = brokerDAO.izvrsiIspravi();
        return result;
    }
}
