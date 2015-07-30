/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.KlaseBaze;

import Class.Apstraktne.AbstractDAO;
import Class.AutoSeteri.SetKlaseBaze;
import Class.Kljucevi.RasporediKljuceve;
import Forme.Konstante.Formati;
import Class.Provere.Provere;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nebojsa
 */
@Entity
@Table(name = "keseproba")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KeseProba.findAll", query = "SELECT k FROM KeseProba k"),
    @NamedQuery(name = "KeseProba.findByIdKese", query = "SELECT k FROM KeseProba k WHERE k.idKese = :idKese"),
    @NamedQuery(name = "KeseProba.findByOpisKese", query = "SELECT k FROM KeseProba k WHERE k.opisKese = :opisKese"),
    @NamedQuery(name = "KeseProba.findBySKese", query = "SELECT k FROM KeseProba k WHERE k.sKese = :sKese"),
    @NamedQuery(name = "KeseProba.findByFKese", query = "SELECT k FROM KeseProba k WHERE k.fKese = :fKese"),
    @NamedQuery(name = "KeseProba.findByVKese", query = "SELECT k FROM KeseProba k WHERE k.vKese = :vKese"),
    @NamedQuery(name = "KeseProba.findByProm", query = "SELECT k FROM KeseProba k WHERE k.prom = :prom"),
    @NamedQuery(name = "KeseProba.findByProm2", query = "SELECT k FROM KeseProba k WHERE k.prom2 = :prom2"),
    @NamedQuery(name = "KeseProba.findByDatum", query = "SELECT k FROM KeseProba k WHERE k.datum = :datum")})
public class KeseProba extends AbstractDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdKese")
    private BigInteger idKese;
    @Column(name = "OpisKese")
    private String opisKese;
    @Column(name = "SKese")
    private BigInteger sKese;
    @Column(name = "FKese")
    private BigInteger fKese;
    @Column(name = "VKese")
    private BigInteger vKese;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prom")
    private BigDecimal prom;
    @Column(name = "prom2")
    private BigDecimal prom2;
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    private Date datum;

    private BigDecimal racun1;
    private BigDecimal racun2;
    private String racun3;
    private BigDecimal racun4;

    Formati constM = new Formati();
    DateFormat df = new SimpleDateFormat(constM.getDateFormatBaza());
    int[] poljaDisabled;

    public KeseProba() {
    }

    public KeseProba(BigInteger idKese) {
        this.idKese = idKese;
    }

    public BigInteger getIdKese() {
        return idKese;
    }

    public void setIdKese(BigInteger idKese) {
        this.idKese = idKese;
    }

    public String getOpisKese() {
        return opisKese;
    }

    public void setOpisKese(String opisKese) {
        this.opisKese = opisKese;
    }

    public BigInteger getSKese() {
        return sKese;
    }

    public void setSKese(BigInteger sKese) {
        this.sKese = sKese;
    }

    public BigInteger getFKese() {
        return fKese;
    }

    public void setFKese(BigInteger fKese) {
        this.fKese = fKese;
    }

    public BigInteger getVKese() {
        return vKese;
    }

    public void setVKese(BigInteger vKese) {
        this.vKese = vKese;
    }

    public BigDecimal getprom() {
        return prom;
    }

    public void setprom(BigDecimal prom) {
        this.prom = prom;
    }

    public BigDecimal getprom2() {
        return prom2;
    }

    public void setprom2(BigDecimal prom2) {
        this.prom2 = prom2;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public BigDecimal getRacun1() {
        return racun1;
    }

    public void setRacun1(BigDecimal racun1) {
        this.racun1 = racun1;
    }

    public BigDecimal getRacun2() {
        return racun2;
    }

    public void setRacun2(BigDecimal racun2) {
        this.racun2 = racun2;
    }

    public String getRacun3() {
        return racun3;
    }

    public void setRacun3(String racun3) {
        this.racun3 = racun3;
    }

    public BigDecimal getRacun4() {
        return racun4;
    }

    public void setRacun4(BigDecimal racun4) {
        this.racun4 = racun4;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKese != null ? idKese.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KeseProba)) {
            return false;
        }
        KeseProba other = (KeseProba) object;
        if ((this.idKese == null && other.idKese != null) || (this.idKese != null && !this.idKese.equals(other.idKese))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Class.KlaseBaze.KeseProba[ idKese=" + idKese + " ]";
    }

    /**
     *
     * @return
     */
    //-----------------------------Deo za ispravku----------------------------------------------------------------------- 
    public String ZaglavljeTabele() { //Opisi polja tabele koji se bide na formi
        return "Sifra,Racun4,Opis,Sirina,F....,Visina,Decimal,Double,Date,Racun1";
    }

    public String PoljaBaze() { //Polja Baze koja se vide na formi i upisuju se u bazu
        return "IdKese,Racun4,OpisKese,SKese,FKese,VKese,prom,prom2,Datum,Racun1";
    }

    public String PoljaBazeZaUpis() {  //Id AutoIncrement i ne pominje se inace bi bio naveden tamo gde mu je mesto
        //Navode se sva polja koja se upisuju
        return "OpisKese,SKese,FKese,VKese,prom,prom2,Datum,Racun1,Racun2,Racun3,Racun4";
    }

    public String sourClass() {
        return "Class.KlaseBaze.KeseProba";
    }

    public String GetClassBaze() {
        return KeseProba.class.getName();
    }

    /*public String RadioComboCheck(){ 
     return ", , , , , , ,";
     } */
    public String ImeKlase() {
        return "KeseProba";
    }
    
    public String SifraMargina() {
        idKese = BigInteger.valueOf(12345678);
        return idKese.toString();
    }
    
    public int[] PoljaDisabled() {
        String[] pDis = {"Racun1", "Racun4"};
        String[] poljaBaze = this.PoljaBaze().split(",");

        RasporediKljuceve rasporediKljuceve = new RasporediKljuceve();
        poljaDisabled = rasporediKljuceve.PreracunajRbr(pDis, poljaBaze);
        return poljaDisabled;
    }

    //Vraca podatke koji se racunaju
    public Object[] DisabledRacunati() {
        Object[] disabledRacunati = new Object[2];

        try {disabledRacunati[0] = racun1;
        } catch (Exception e) {disabledRacunati[0] = null;}

        try {disabledRacunati[1] = racun4;} catch (Exception e) {
            disabledRacunati[1] = null;}

        return disabledRacunati;
    }

    //Podaci koji se racunaju i pamte u bazi ali se ne ispisuju na Formi
    public boolean IzracPodatkeVanTabele() {
        boolean racun = false;
        try {
            MathContext mc = new MathContext(30);
            double x = prom.doubleValue() * prom2.doubleValue();
            setRacun2(new BigDecimal(x).round(mc));

            setRacun3("Ovo je upis bez tabele");

            racun = true;
        } catch (Exception e) {
        }
        return racun;
    }

    //Podaci koji se racunaju(onemogucen unos) i ispisani su na formi i pamte se u bazi - oni se racunaju za svaki keyPress
    public boolean IzracPodatkeUTabeli() {
        boolean racun = true;
        double x;
        MathContext mc30 = new MathContext(30);

        try {
            x = prom.doubleValue() * prom2.doubleValue() / 133;
            setRacun1(new BigDecimal(x).round(mc30));
        } catch (Exception e1) {racun = false;}

        try {
            x = prom.doubleValue() * prom2.doubleValue() / 100;
            setRacun4(new BigDecimal(x).round(mc30));
        } catch (Exception e2) {racun = false;}

        return racun;
    }

    @Override
    public int[] Kljuceve() {
        String[] pK = {"IdKese"};
        String[] poljaBaze = this.PoljaBaze().split(",");

        RasporediKljuceve rasporediKljuceve = new RasporediKljuceve();
        return rasporediKljuceve.PreracunajRbr(pK, poljaBaze);
    }

    public String UslovTrazenjaSvih() {
        return "";
    }

    public Object[] ProveriVrednosti() {
        String pomoc;
        Object[] poruka = new Object[2];
        poruka[0] = null;
        poruka[1] = null;
        Provere provere = new Provere();
        String[] OpisiPolja = ZaglavljeTabele().split(",");

        pomoc = provere.proveriString(getOpisKese(), OpisiPolja[1]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 1;}}
        pomoc = provere.proveriBigInteger(getSKese(), OpisiPolja[2]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 2;}}
        pomoc = provere.proveriBigInteger(getFKese(), OpisiPolja[3]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 3;}}
        pomoc = provere.proveriBigInteger(getVKese(), OpisiPolja[4]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 4;}}
        pomoc = provere.proveriBigDecimal(getprom(), OpisiPolja[5]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 5;}}
        pomoc = provere.proveriBigDecimal(getprom2(), OpisiPolja[6]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 6;}}
        pomoc = provere.proveriDatumTxt(getDatum(), OpisiPolja[7]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 7;}}

        return poruka;
    }

    public String UslovTrazenjaSloga() {
        return "IdKese=" + idKese + "";
    }

    public String IspraviSlog() {
        return "OpisKese='" + opisKese + "',  "
                + "SKese=" + sKese + " ,  "
                + "FKese=" + fKese + " ,  "
                + "VKese=" + vKese + " ,  "
                + "prom=" + prom + " ,  "
                + "prom2=" + prom2 + " ,  "
                + "Datum='" + df.format(datum) + "',  "
                + "Racun1=" + racun1 + " ,  "
                + "Racun2=" + racun2 + " ,  "
                + "Racun3='" + racun3 + "',  "
                + "Racun4=" + racun4 + "";
    }

    public String UpisiSlog() {
        return "   '" //id AutoIncrement pa ga zato nema ovde
                + opisKese + "',  "
                + sKese + " ,  "
                + fKese + " ,  "
                + vKese + " ,  "
                + prom + " ,  "
                + prom2 + " , '"
                + df.format(datum) + "',  "
                + racun1 + " ,  "
                + racun2 + " , '"
                + racun3 + "',  "
                + racun4 + "";
    }

    //---------------------------DOVDE Deo za ispravku----------------------------------   
    //Setovanje podataka u klasi
    /*@Override
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
            Logger.getLogger(KeseProba.class.getName()).log(Level.SEVERE, null, ex);
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
