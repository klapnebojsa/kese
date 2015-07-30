/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.KlaseBaze;

import Class.Apstraktne.AbstractDAO;
import Class.AutoSeteri.SetKlaseBaze;
import Class.Kljucevi.RasporediKljuceve;
import Class.Provere.Provere;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.sql.ResultSetMetaData;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nebojsa
 */
@Entity
@Table(name = "partneri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partneri.findAll", query = "SELECT p FROM Partneri p"),
    @NamedQuery(name = "Partneri.findByIdPartner", query = "SELECT p FROM Partneri p WHERE p.idPartner = :idPartner"),
    @NamedQuery(name = "Partneri.findByNazivPartnera", query = "SELECT p FROM Partneri p WHERE p.nazivPartnera = :nazivPartnera"),
    @NamedQuery(name = "Partneri.findByAdresa", query = "SELECT p FROM Partneri p WHERE p.adresa = :adresa"),
    @NamedQuery(name = "Partneri.findByMesto", query = "SELECT p FROM Partneri p WHERE p.mesto = :mesto"),
    @NamedQuery(name = "Partneri.findByTel", query = "SELECT p FROM Partneri p WHERE p.tel = :tel"),
    @NamedQuery(name = "Partneri.findByMb", query = "SELECT p FROM Partneri p WHERE p.mb = :mb"),
    @NamedQuery(name = "Partneri.findByPib", query = "SELECT p FROM Partneri p WHERE p.pib = :pib"),
    @NamedQuery(name = "Partneri.findByPdv", query = "SELECT p FROM Partneri p WHERE p.pdv = :pdv"),
    @NamedQuery(name = "Partneri.findByDelatnost", query = "SELECT p FROM Partneri p WHERE p.delatnost = :delatnost")})
public class Partneri extends AbstractDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPartner")
    private BigInteger idPartner;
    @Column(name = "NazivPartnera")
    private String nazivPartnera;
    @Column(name = "Adresa")
    private String adresa;
    @Column(name = "Mesto")
    private String mesto;
    @Column(name = "Tel")
    private String tel;
    @Column(name = "MB")
    private String mb;
    @Column(name = "PIB")
    private String pib;
    @Column(name = "PDV")
    private String pdv;
    @Column(name = "Delatnost")
    private String delatnost;

    public Partneri() {
    }

    public Partneri(BigInteger idPartner) {
        this.idPartner = idPartner;
    }

    public BigInteger getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(BigInteger idPartner) {
        this.idPartner = idPartner;
    }

    public String getNazivPartnera() {
        return nazivPartnera;
    }

    public void setNazivPartnera(String nazivPartnera) {
        this.nazivPartnera = nazivPartnera;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMB() {
        return mb;
    }

    public void setMB(String mb) {
        this.mb = mb;
    }

    public String getPIB() {
        return pib;
    }

    public void setPIB(String pib) {
        this.pib = pib;
    }

    public String getPDV() {
        return pdv;
    }

    public void setPDV(String pdv) {
        this.pdv = pdv;
    }

    public String getDelatnost() {
        return delatnost;
    }

    public void setDelatnost(String delatnost) {
        this.delatnost = delatnost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartner != null ? idPartner.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partneri)) {
            return false;
        }
        Partneri other = (Partneri) object;
        if ((this.idPartner == null && other.idPartner != null) || (this.idPartner != null && !this.idPartner.equals(other.idPartner))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Class.KlaseBaze.Partneri[ idPartner=" + idPartner + " ]";
    }

    //-----------------------------Deo za ispravku-----------------------------------------------------------------------    
    public String ZaglavljeTabele() {
        return "Sifra Partner,Naziv Partnera,Adresa,Mesto,Tel,MB,PIB,PDV,Delatnost";
    }

    public String PoljaBaze() {  //Polja Baze koja se vide na formi i upisuju se u bazu
        return "IdPartner,NazivPartnera,Adresa,Mesto,Tel,MB,PIB,PDV,Delatnost";
    }

    public String PoljaBazeZaUpis() {   //Id AutoIncrement i ne pominje se
        return "NazivPartnera,Adresa,Mesto,Tel,MB,PIB,PDV,Delatnost";
    }

    public String sourClass() {
        return "Class.KlaseBaze.Partneri";
    }

    public String GetClassBaze() {
        return Partneri.class.getName();
    }

    //Sve su text boxovi   
    //Da li su polja kod unosa Radio button (Radio), Combo Box(CoBox), Check box(Check) ili Text Field(TextF ili prazno)... 
    /*public String RadioComboCheck(){ 
     //Ako je TxtField stviti space
     String podaci = "";
     podaci += " "  ;                     //IdKutije    TextField       1
     podaci += "&& ";                   //Opis        TextField       2
     podaci += "&& "; //DuzPrazno   ComboBox        3a + 3b
     podaci += "&& "; //SirPrazno   ComboBox        4a + 4b
     podaci += "&& ";                   //VisKutije   TextField       5
     podaci += "&&" ;        //Preklop     ComboBox        6a + 6b
     podaci += "&& ";                   //BrojKesa    TextField       7
     return podaci;
     //                             1  2   3a  %%     3b                  4a        4b                5    6a        6b                7
     //podaci = (java.lang.String) " && &&CoBox%%5@@10@@15@@20@@25@@30&&CoBox%%5@@10@@15@@20@@25@@30&& &&CoBox%%5@@10@@15@@20@@25@@30&& "
     }  */
    public String ImeKlase() {
        return "Partneri";
    }
    public String SifraMargina() {
        idPartner = BigInteger.valueOf(12345677);
        return idPartner.toString();
    }
    public int[] PoljaDisabled() {
        String[] pDis = {""};
        String[] poljaBaze = this.PoljaBaze().split(",");

        RasporediKljuceve rasporediKljuceve = new RasporediKljuceve();
        return rasporediKljuceve.PreracunajRbr(pDis, poljaBaze);
    }
    /*public Object[] DisabledRacunati(){       
     return null; 
     }*/

    @Override
    public int[] Kljuceve() {
        String[] pK = {"IdPartner"};
        String[] poljaBaze = this.PoljaBaze().split(",");

        RasporediKljuceve rasporediKljuceve = new RasporediKljuceve();
        return rasporediKljuceve.PreracunajRbr(pK, poljaBaze);
    }

    public Object[] ProveriVrednosti() {
        String pomoc;
        Object[] poruka = new Object[2];
        poruka[0] = null;
        poruka[1] = null;
        Provere provere = new Provere();
        String[] OpisiPolja = ZaglavljeTabele().split(",");

        pomoc = provere.proveriString(getNazivPartnera(), OpisiPolja[1]);
        if (pomoc != null) {
            poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc;
            if (poruka[0] == null) {
                poruka[0] = 1;
            }
        }

        return poruka;
    }

    public String UslovTrazenjaSvih() {
        return "";
    }

    public String UslovTrazenjaSloga() {
        return "IdPartner=" + idPartner + "";
    }

    public String IspraviSlog() {
        return "NazivPartnera='" + nazivPartnera + "' ,  "
                + "Adresa='" + adresa + "' ,  "
                + "Mesto='" + mesto + "' ,  "
                + "Tel='" + tel + "' ,  "
                + "MB='" + mb + "' ,  "
                + "PIB='" + pib + "' ,  "
                + "PDV='" + pdv + "' ,  "
                + "Delatnost='" + delatnost + "'";
    }

    public String UpisiSlog() {
        return "    '" //id AutoIncrement
                + nazivPartnera + "' , '"
                + adresa + "' , '"
                + mesto + "' , '"
                + tel + "' , '"
                + mb + "' , '"
                + pib + "' , '"
                + pdv + "' , '"
                + delatnost + "'";
    }

    //Podaci koji se racunaju i pamte u bazi ali se ne ispisuju na Formi
    public boolean IzracPodatkeVanTabele() {
        boolean racun = false;
        try {

            racun = true;
        } catch (Exception e) {
        }
        return racun;
    }

    //Podaci koji se racunaju(onemogucen unos) i ispisani su na formi i pamte se u bazi
    public boolean IzracPodatkeUTabeli() {
        boolean racun = false;
        try {

            racun = true;
        } catch (Exception e) {
        }
        return racun;
    }

    //---------------------------DOVDE Deo za ispravku----------------------------------    
    /*@Override
    public boolean SetujPodatke(String[] podaciZaUpis, ResultSetMetaData metaData, boolean SetAutoIncrement) {
        boolean setujPodatke = false;
        String[] elementi = PoljaBaze().split(",");
        AbstractDAO abstractDAO = this;
        String className = GetClassBaze();

        String sourceClass = sourClass();
        SetKlaseBaze setKlaseBaze = new SetKlaseBaze();
        try {
            setujPodatke = setKlaseBaze.setPodatkeKlaseBaze(podaciZaUpis, metaData, sourceClass, className, elementi, abstractDAO, SetAutoIncrement);
        } catch (Exception e) {
            setujPodatke = false;
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
    }*/

}
