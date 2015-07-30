/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.SistOperacije;

import Class.Apstraktne.AbstractDAO;
import Class.Apstraktne.Opsta;
import Class.DAO.BrokerDAO;
import java.sql.SQLException;

/**
 *
 * @author Nebojsa
 */
public class VratiTblListuSO extends Opsta {

    public VratiTblListuSO(BrokerDAO brokerDAO) {
        super(brokerDAO);
    }

    protected String formirajRec(BrokerDAO brokerDAO) throws SQLException {
        return (brokerDAO.RecListTabela());
    }

    protected Object izvrsiRec(BrokerDAO brokerDAO) throws SQLException {
        Object result = brokerDAO.izvrsiListu();
        return result;
    }
}
