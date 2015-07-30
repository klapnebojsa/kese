/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.Apstraktne;

import Class.AutoSeteri.SetKlaseBaze;
import Class.KlaseBaze.KutijaProba;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neso
 */
public abstract class AbstractDAO {

    /**
     *
     * @return
     */
    //Minimum jedan zarez
    public String RadioComboCheck() {
        return null;
    }

    public boolean IzracPodatkeUTabeli() {
        return true;
    }

    public abstract String ZaglavljeTabele();

    public abstract int[] Kljuceve();

    public abstract String PoljaBaze();

    public abstract String PoljaBazeZaUpis();

    public abstract String UslovTrazenjaSvih();

    public abstract String UslovTrazenjaSloga();

    public abstract int[] PoljaDisabled();

    public Object[] DisabledRacunati() {
        return null;
    }
    public String SifraMargina() {
        return null;
    }
    
    public void SetujSifraMargina(String sifra){}    
    
    public abstract boolean IzracPodatkeVanTabele();

    public abstract String ImeKlase();

    public abstract String UpisiSlog();

    public abstract String IspraviSlog();

    public abstract Object[] ProveriVrednosti();

    public abstract String sourClass();

    public abstract String GetClassBaze();

    public boolean SetujPodatke(String[] podaciZaUpis, ResultSetMetaData metaData, boolean SetAutoIncrement) {
        String[] elementi = PoljaBaze().split(",");
        AbstractDAO abstractDAO = this;
        String className = GetClassBaze();

        String sourceClass = sourClass();
        SetKlaseBaze setKlaseBaze = new SetKlaseBaze();
        boolean setujPodatke = false;
        try {
            setujPodatke = setKlaseBaze.setPodatkeKlaseBaze(podaciZaUpis, metaData, sourceClass, className, elementi, abstractDAO, SetAutoIncrement);
        } catch (ParseException | IllegalAccessException | InvocationTargetException | SQLException ex) {
            Logger.getLogger(KutijaProba.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Deo koji poziva racunanje i setovanje polja koja se upisuju u bazu a nema ih u tabeli
        if (!IzracPodatkeVanTabele()) {
            setujPodatke = false;
        }
        //Deo koji poziva racunanje i setovanje polja koja se racunaju i upisuju u bazu a ima ih u tabeli i nema mogucnosti direknog unosa tog polja
        if (!IzracPodatkeUTabeli()) {
            setujPodatke = false;
        }
        return setujPodatke;
    }
}
