/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baza.ParametriBaze;

import Class.DAO.BrokerDAO;
import Class.Apstraktne.AbstractDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author neso
 */
public class OpstaOperacija implements Serializable {

    private AbstractDAO a;
    public ResultSet rs;
    public OpstaOperacija uf;

    public ResultSetMetaData rsmd;
    public DatabaseMetaData dBmeta;

    public int poslednjiAIKljuc;

    String sql;
    String sqln;
    Parametri par = new Parametri();
    String JDBC_DRIVER = par.getJDBC_DRIVER();
    String DB_URL = par.getDB_URL();
    String USER = par.getUSER();
    String PASS = par.getPASS();

    Connection conn = null;
    Statement stmt = null;

    //Pola za zavisne klase
    String PoljaZbirP;
    String[] PoljaZP;
    String VredZbirP;
    String[] VredZP;
    String PoljaZbirN;
    String[] PoljaZN;
    int brCol;
    private AbstractDAO p;
    private AbstractDAO n;

    public OpstaOperacija() {
    }

    public OpstaOperacija(AbstractDAO a) {
        this.rs = null;
        this.a = a;
        this.sql = null;
        this.sqln = null;
    }

    public String ZaglavljeTabele() {
        return a.ZaglavljeTabele();
    }

    public int[] Kljuceve() {
        return a.Kljuceve();
    }

    public int[] PoljaDisabled() {
        return a.PoljaDisabled();
    }

    public Object[] DisabledRacunati() {
        return a.DisabledRacunati();
    }

    public boolean IzracPodatkeUTabeli() {
        return a.IzracPodatkeUTabeli();
    }

    public String RadioComboCheck() {
        return a.RadioComboCheck();
    }

    public Object[] ProveriVrednosti() {
        return a.ProveriVrednosti();
    }

    public void SetujSifruMargina(String sifra) {
        a.SetujSifraMargina(sifra);
    }
    
    public String ProcitajSifruMargina() {
        return a.SifraMargina();
    }    
    
    
    public void OtvoriBazu() throws ClassNotFoundException, SQLException {
        //try{
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        //}catch(SQLException | ClassNotFoundException se){}//greske JDBC  //greske Class.forName    
    }

    //Komanda za Trazenje da li postoji trazeni slog
    public String FormSelectJedan() {
        String UslovTrazenjaSloga = a.UslovTrazenjaSloga();
        String ImeKlase = a.ImeKlase();
        String StaList = a.PoljaBaze();        

        //sql = "SELECT * FROM " + ImeKlase + " where " + UslovTrazenjaSloga;
        sql = "SELECT " + StaList + " FROM " + ImeKlase + " where " + UslovTrazenjaSloga;     
        return sql;
    }

    //Komanda za Ispravku postojeceg sloga
    public String FormIspraviJedan() {
        String UslovTrazenjaSloga = a.UslovTrazenjaSloga();
        String ImeKlase = a.ImeKlase();
        String IspraviSlog = a.IspraviSlog();
        sql = "UPDATE " + ImeKlase + " SET " + IspraviSlog + " WHERE " + UslovTrazenjaSloga;
        return sql;
    }

    //Komanda za Upisivanje novog sloga
    public String FormUpisiJedan() {
        String PoljaBazeZaUpis = a.PoljaBazeZaUpis();
        String ImeKlase = a.ImeKlase();
        String UpisiSlog = a.UpisiSlog();
        sql = "INSERT INTO " + ImeKlase + " (" + PoljaBazeZaUpis + ")" + " VALUES (" + UpisiSlog + ")";
        return sql;
    }

    //Komanda za Brisanje slog
    public String FormBrisi() {
        String UslovTrazenjaSloga = a.UslovTrazenjaSloga();
        String ImeKlase = a.ImeKlase();
        sql = "DELETE FROM " + ImeKlase + " where " + UslovTrazenjaSloga;
        return sql;
    }

    //Komanda za Listanje Svih
    public String FormListSve() {
        String UslovTrazenjaSvih = a.UslovTrazenjaSvih();
        String ImeKlase = a.ImeKlase();
        sql = "SELECT * FROM " + ImeKlase;
        if (UslovTrazenjaSvih != "") {
            sql = "SELECT * FROM " + ImeKlase + " where " + UslovTrazenjaSvih;
        }
        return sql;
    }

    //Komanda za Listanje Svih
    public String FormListTabela() {
        String UslovTrazenjaSvih = a.UslovTrazenjaSvih();
        String ImeKlase = a.ImeKlase();
        String StaList = a.PoljaBaze();
        sql = "SELECT " + StaList + " FROM " + ImeKlase;
        if (UslovTrazenjaSvih != "") {
            sql = "SELECT " + StaList + " FROM " + ImeKlase + " where " + UslovTrazenjaSvih;
        }
        return sql;
    }

    // Zapocni commit
    public void ZapocniComm() throws SQLException {
        conn.setAutoCommit(false);
    }

    //OK izvrsi commit 
    public void IzvrsiComm() throws SQLException {
        conn.commit();
    }

    //Nije OK rollback
    public void IzvrsiRoll() throws SQLException {
        conn.rollback();
    }

    //Spremi komandu
    public void PozoviComm() throws SQLException {
        rs = stmt.executeQuery(sql);
    }

    //Izvrsi komandu i vrati Record Set
    public void IzvrsiRS() throws SQLException {
        rs = stmt.executeQuery(sql);
    }

    //Vrati MetaData - podatke
    public ResultSetMetaData VratiMeta() throws SQLException {
        rsmd = rs.getMetaData();
        return rsmd;
    }

    //Izvrsi komandu i vrati da li je upis/brisanje Izvrseno
    public boolean IzvrsiNovi() throws SQLException {
        boolean Uslov = false;
        int rows = 0;
        try {
            rows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            //Generisani Kluc
            ResultSet aI = stmt.getGeneratedKeys();
            aI.next();
            poslednjiAIKljuc = aI.getInt(1);
        } catch (Exception e) {
        }
        if (rows > 0) {
            Uslov = true;
        }
        return Uslov;
    }

    //Izvrsi komandu nadredjenog i vrati da li je upis/brisanje Izvrseno
    public boolean IzvrsiNoviNadr() throws SQLException {
        boolean Uslov = false;
        int rows = stmt.executeUpdate(sqln);
        if (rows > 0) {
            Uslov = true;
        }
        return Uslov;
    }

    //Izvrsi komandu i vrati da li je upis/brisanje Izvrseno
    public boolean IzvrsiBool() throws SQLException {
        boolean Uslov = false;
        int rows = stmt.executeUpdate(sql);
        if (rows > 0) {
            Uslov = true;
        }
        return Uslov;
    }

    //Izvrsi komandu i vrati da li ima slogova u Result Setu
    public boolean IzvrsiImaLi() throws SQLException {
        boolean Uslov = false;
        rs = stmt.executeQuery(sql);
        boolean ImaJos = rs.next();
        if (ImaJos) {
            Uslov = true;
        }
        return Uslov;
    }

    //Result Set Prebaci u List
    public List NapraviListu() throws SQLException {
        List results = new LinkedList();
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        while (rs.next()) {
            List singleRow = new ArrayList();
            for (int i = 1; i <= columnCount; i++) {
                //String xx =rs.getObject(i).toString();
                singleRow.add(rs.getObject(i));
            }
            results.add(singleRow);
        }
        return results;
    }

    //Result Set Prebaci u String (Samo za jedno polje)
    public String NapraviString() throws SQLException {
        Object r = null;
        String results = null;
        while (rs.next()) {
            r = rs.getObject(1);
        }
        results = r.toString();
        return results;
    }

    //Result Set Prebaci u Int (Samo za jedno polje)
    public int NapraviInt() throws SQLException {
        int results = 0;
        while (rs.next()) {
            results = rs.getInt(5);
        } //(a.getStavku());}
        return results;
    }

    //Zatvori RecordSet, stmt, connection 
    public void ZatvoriSve() {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException s) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException s) {
            }//end finally
        }//end try         
    }

    //Komanda za Listanje polja za zbir
    public String FormListZbir() {
        String UslovTrazenjaSvih = null;
        UslovTrazenjaSvih = a.UslovTrazenjaSvih();
        String ImeKlase = a.ImeKlase();
        String PoljaZbir = ""; //a.PoljaZbir();
        sql = "SELECT " + PoljaZbir + " FROM " + ImeKlase;
        if (UslovTrazenjaSvih != "") {
            sql = "SELECT " + PoljaZbir + " FROM " + ImeKlase + " where " + UslovTrazenjaSvih;
        }
        return sql;
    }

    //Vraca najvecu vrednost polja tj. dotadasnju najvecu vrednost (maksimalnu (int) sifru)
    public String DajNajveci(String PoCemu) {
        String UslovTrazenjaSvih = null;
        UslovTrazenjaSvih = a.UslovTrazenjaSvih();
        String ImeKlase = a.ImeKlase();
        sql = "SELECT * FROM " + ImeKlase + " order by " + PoCemu;
        if (!"".equals(UslovTrazenjaSvih)) {
            sql = "SELECT * FROM " + ImeKlase + " where " + UslovTrazenjaSvih + " order by " + PoCemu;
        }
        return sql;
    }

    //Komanda za Listanje polja koja se sabiraju
    public String FormListDeo() {
        String UslovTrazenjaSloga = a.UslovTrazenjaSloga();
        String ImeKlase = a.ImeKlase();
        String PoljaZbir = ""; //a.PoljaZbir(); 
        sql = "SELECT " + PoljaZbir + " FROM " + ImeKlase + " where " + UslovTrazenjaSloga;
        return sql;
    }

    //Komanda za podatke jednog polja iz klase
    public String FormCell(String Polje) {
        String UslovTrazenjaSloga = a.UslovTrazenjaSloga();
        String ImeKlase = a.ImeKlase();
        sql = "SELECT " + Polje + " FROM " + ImeKlase + " where " + UslovTrazenjaSloga;
        return sql;
    }

    //Komanda za podatke vise polja iz klase
    public String FormViseCell(String Polje) {
        String UslovTrazenjaSvih = a.UslovTrazenjaSvih();
        String ImeKlase = a.ImeKlase();
        sql = "SELECT " + Polje + " FROM " + ImeKlase + " where " + UslovTrazenjaSvih;
        return sql;
    }

    //Komanda za podatke viise polja iz klase sa donetim uslovom trayenja
    public String FormViseCellUslov(String Polje, String UslovTrazenja) {
        String ImeKlase = a.ImeKlase();
        sql = "SELECT " + Polje + " FROM " + ImeKlase + " where " + UslovTrazenja;
        return sql;
    }

    public Double[] saberiPodredjeni() throws SQLException {
        brCol = 0;
        BrokerDAO brokerDAO = new BrokerDAO(p);
        List Sabirci = new LinkedList();
        Sabirci = brokerDAO.citajZbirDAO();
        /*VredZbirP = p.VredPoljaZbir();
         VredZP = VredZbirP.split(",");*/
        PoljaZbirN = ""; //n.PoljaZbir();
        PoljaZN = PoljaZbirN.split(",");
        brCol = PoljaZN.length - 1;

        Double[] zbirKol = new Double[PoljaZN.length];
        Arrays.fill(zbirKol, 0.00);
        try {
            for (Object category : Sabirci) {
                List element = (List) category;
                for (int i = 0; i <= brCol; i++) {
                    zbirKol[i] += Double.parseDouble(element.get(i).toString());
                }
            }
        } catch (Exception e) {
        }
        return zbirKol;
    }

}
