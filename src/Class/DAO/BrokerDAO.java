/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.DAO;

import Class.Apstraktne.AbstractDAO;
import Baza.ParametriBaze.OpstaOperacija;
import java.io.Serializable;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author neso
 */
public class BrokerDAO implements Serializable {

    public AbstractDAO a;
    public OpstaOperacija opstaOperacija;
    ResultSetMetaData rsmd;
    DatabaseMetaData dBmeta;

    public BrokerDAO() {
    }

    public BrokerDAO(AbstractDAO interf) {
        a = interf;
        opstaOperacija = new OpstaOperacija(a);
    }

/////////// OTVARANJE BAZE /////////////////////
    public void otvoriBazu() throws ClassNotFoundException, SQLException {
        opstaOperacija.OtvoriBazu();
    }
/////////// ZAPOCINJANJE COMMIT ILI ROLLBACK ///

    public void zapocniComm() throws SQLException {
        opstaOperacija.ZapocniComm();
    }

///////////FORMIANJE RECI //////////////////////   
    //Rec za Upis novog sloga    
    public String RecNovi() throws SQLException {
        return (opstaOperacija.FormUpisiJedan());
    }

    //Rec za Upis ispravljenog sloga    
    public String RecIspravi() throws SQLException {
        return (opstaOperacija.FormIspraviJedan());
    }

    //Rec za brisanje sloga    
    public String RecBrisi() throws SQLException {
        return (opstaOperacija.FormBrisi());
    }

    //Rec za Formiranje Liste svih      
    public String RecListSve() throws SQLException {
        return (opstaOperacija.FormListSve());
    }
    
    //Rec za Formiranje Liste Jednog     
    public String RecListJedan() throws SQLException {
        return (opstaOperacija.FormSelectJedan());
    }    

    //Rec za Formiranje Liste svih      
    public String RecListTabela() throws SQLException {
        return (opstaOperacija.FormListTabela());
    }
    
    //Setovanje sifre za pozivanje margina    
    public void SetujSifruMargina(String sifra) throws SQLException {
        opstaOperacija.SetujSifruMargina(sifra);
    }
    
    //Citanje sifre za pozivanje margina    
    public String ProcitajSifruMargina() throws SQLException {
        return opstaOperacija.ProcitajSifruMargina();
    }  

/////////// IZVRSAVANJE SQL UPITA //////////////
    //Upis novog sloga    
    public Object izvrsiNovi() throws SQLException {
        Object result = opstaOperacija.IzvrsiNovi();
        return result;
    }

    //Brisanje postojece zavisne stavke
    public Object izvrsiNoviZav() throws SQLException {
        Object result = opstaOperacija.IzvrsiNovi() && opstaOperacija.IzvrsiNoviNadr();
        return result;
    }

    //Ispravka postojeceg sloga    
    public Object izvrsiIspravi() throws SQLException {
        Object result = opstaOperacija.IzvrsiNovi();
        return result;
    }

    //Ispravka postojece zavisne stavke
    public Object izvrsiIspraviZav() throws SQLException {
        Object result = opstaOperacija.IzvrsiNovi() && opstaOperacija.IzvrsiNoviNadr();
        return result;
    }

    //Brisanje postojece stavke
    public Object izvrsiBrisi() throws SQLException {
        Object result = opstaOperacija.IzvrsiNovi();
        return result;
    }

    //Brisanje postojece zavisne stavke
    public Object izvrsiBrisiZav() throws SQLException {
        Object result = opstaOperacija.IzvrsiNovi() && opstaOperacija.IzvrsiNoviNadr();
        return result;
    }

    //Vrati MetaData - podatke
    public ResultSetMetaData VratiMeta() throws SQLException {
        //opstaOperacija.uf = opstaOperacija;
        ResultSetMetaData rsmd = opstaOperacija.VratiMeta();
        return rsmd;
    }

////////// COMMIT - POTVRDA KOMANDE //////////////
    public void izvrsiComm() throws SQLException {
        opstaOperacija.IzvrsiComm();
    }
////////// ROLLBACK - ODUSTAJANJE OD KOMANDE /////

    public void izvrsiRoll() throws SQLException {
        opstaOperacija.IzvrsiRoll();
    }
////////// ZATVARANJE BAZE ////////////////////////

    public void zatvoriBazu() throws SQLException {
        opstaOperacija.ZatvoriSve();
    }

    public Object[] ProveriVrednosti() {
        return opstaOperacija.ProveriVrednosti();
    }

    public String ZaglavljeTabele() {
        return opstaOperacija.ZaglavljeTabele();
    }

    public int[] Kljuceve() {
        return opstaOperacija.Kljuceve();
    }

    public int[] PoljaDisabled() {
        return opstaOperacija.PoljaDisabled();
    }

    public Object[] DisabledRacunati() {
        return opstaOperacija.DisabledRacunati();
    }

    public boolean IzracPodatkeUTabeli() {
        return opstaOperacija.IzracPodatkeUTabeli();
    }

    public String RadioComboCheck() {
        return opstaOperacija.RadioComboCheck();
    }

    //////////OVO PO MOGUCSTVU IZBECI I URADITI NA GORNJI NACIN
    //Formiranje Liste svih    
    public Object izvrsiListu() throws SQLException {
        opstaOperacija.IzvrsiRS();
        Object result = opstaOperacija.NapraviListu();
        return result;
    }

    //Kontrola da li postoji trazeni slog
    public boolean citajDAO() throws ClassNotFoundException, SQLException {
        //OpstaOperacija opstaOperacija = new OpstaOperacija(a);
        Boolean Uslov = false;
        try {
            opstaOperacija.OtvoriBazu();
            opstaOperacija.FormSelectJedan();
            Uslov = opstaOperacija.IzvrsiImaLi();
            opstaOperacija.ZatvoriSve();
        } catch (Exception e) {
            Uslov = false;
        }
        return Uslov;
    }

    //Lista odre]enih koji se sabiraju
    public List citajDeoDAO() {
        //OpstaOperacija opstaOperacija = new OpstaOperacija(a);
        List results = new LinkedList();
        try {
            opstaOperacija.OtvoriBazu();
            opstaOperacija.FormListDeo();
            opstaOperacija.PozoviComm();
            results = opstaOperacija.NapraviListu();
            opstaOperacija.ZatvoriSve();
        } catch (Exception e) {
            results = null;
        }
        return results;
    }

    //Citanje samo jednog polja iz tabele   
    public String citajCellDAO(String Polje) {
        //OpstaOperacija opstaOperacija = new OpstaOperacija(a);
        String results = null;
        try {
            opstaOperacija.OtvoriBazu();
            opstaOperacija.FormCell(Polje);
            opstaOperacija.PozoviComm();
            results = opstaOperacija.NapraviString();
            opstaOperacija.ZatvoriSve();
        } catch (Exception e) {
            results = null;
        }
        return results;
    }

    //Citanje vi[e odre]enih polja iz tabele   
    public List citajViseCellDAO(String Polje) {
        //OpstaOperacija opstaOperacija = new OpstaOperacija(a);
        List results = null;
        try {
            opstaOperacija.OtvoriBazu();
            opstaOperacija.FormViseCell(Polje);
            opstaOperacija.PozoviComm();
            results = opstaOperacija.NapraviListu();
            opstaOperacija.ZatvoriSve();
        } catch (Exception e) {
            results = null;
        }
        return results;
    }

    //Citanje vi[e odre]enih polja iz tabele sa Uslovom  
    public List citajViseCellDAOUslov(String Polje, String Uslov) {
        List results = null;
        try {
            opstaOperacija.OtvoriBazu();
            opstaOperacija.FormViseCellUslov(Polje, Uslov);
            opstaOperacija.PozoviComm();
            results = opstaOperacija.NapraviListu();
            opstaOperacija.ZatvoriSve();
        } catch (Exception e) {
            results = null;
        }
        return results;
    }

    public int MaxSifraDAO(String PoCemu) throws SQLException {
        //OpstaOperacija opstaOperacija = new OpstaOperacija(a);
        int results = 0;
        try {
            opstaOperacija.OtvoriBazu();
            opstaOperacija.DajNajveci(PoCemu);
            opstaOperacija.PozoviComm();
            results = opstaOperacija.NapraviInt();
            opstaOperacija.ZatvoriSve();
        } catch (Exception e) {
        }
        return results;
    }

    public List citajZbirDAO() throws SQLException {
        //OpstaOperacija opstaOperacija = new OpstaOperacija(a);
        List results = new LinkedList();
        try {
            opstaOperacija.OtvoriBazu();
            opstaOperacija.FormListZbir();
            opstaOperacija.PozoviComm();
            results = opstaOperacija.NapraviListu();
            opstaOperacija.ZatvoriSve();
        } catch (Exception e) {
            results = null;
        }
        return results;
    }

}
