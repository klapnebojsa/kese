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
public class NoviSO extends Opsta {

    public NoviSO(BrokerDAO brokerDAO) {
        super(brokerDAO);
    }

    protected String formirajRec(BrokerDAO brokerDAO) throws SQLException {
        return (brokerDAO.RecNovi());
    }

    protected Object izvrsiRec(BrokerDAO brokerDAO) throws SQLException {
        Object result = brokerDAO.izvrsiNovi();
        return result;
    }
}
