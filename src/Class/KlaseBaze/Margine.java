/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.KlaseBaze;

import Class.Apstraktne.AbstractDAO;
import Class.Kljucevi.RasporediKljuceve;
import Class.Provere.Provere;
import Forme.Napuni.ComboBoxovi.NapuniCombo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "margine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Margine.findAll", query = "SELECT m FROM Margine m"),
    @NamedQuery(name = "Margine.findByIdMargine", query = "SELECT m FROM Margine m WHERE m.idMargine = :idMargine"),
    @NamedQuery(name = "Margine.findByLevo", query = "SELECT m FROM Margine m WHERE m.levo = :levo"),
    @NamedQuery(name = "Margine.findByDesno", query = "SELECT m FROM Margine m WHERE m.desno = :desno"),
    @NamedQuery(name = "Margine.findByGore", query = "SELECT m FROM Margine m WHERE m.gore = :gore"),
    @NamedQuery(name = "Margine.findByDole", query = "SELECT m FROM Margine m WHERE m.dole = :dole"),
    @NamedQuery(name = "Margine.findByMedjX", query = "SELECT m FROM Margine m WHERE m.medjX = :medjX"),
    @NamedQuery(name = "Margine.findByMedjY", query = "SELECT m FROM Margine m WHERE m.medjY = :medjY"),
    @NamedQuery(name = "Margine.findByMedjYDw", query = "SELECT m FROM Margine m WHERE m.medjYDw = :medjYDw"),    
    @NamedQuery(name = "Margine.findByVelFonta", query = "SELECT m FROM Margine m WHERE m.velFonta = :velFonta"),
    @NamedQuery(name = "Margine.findByFont", query = "SELECT m FROM Margine m WHERE m.font = :font"),
    @NamedQuery(name = "Margine.findByStampac", query = "SELECT m FROM Margine m WHERE m.stampac = :stampac"),
    @NamedQuery(name = "Margine.findByFormatPapira", query = "SELECT m FROM Margine m WHERE m.formatPapira = :formatPapira"),
    @NamedQuery(name = "Margine.findByOrjentacija", query = "SELECT m FROM Margine m WHERE m.orjentacija = :orjentacija")})
public class Margine extends AbstractDAO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdMargine")
    private String idMargine;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Levo")
    private Integer levo;
    @Column(name = "Desno")
    private Integer desno;
    @Column(name = "Gore")
    private Integer gore;
    @Column(name = "Dole")
    private Integer dole;
    @Column(name = "MedjX")
    private BigDecimal medjX;
    @Column(name = "MedjY")
    private BigDecimal medjY;
    @Column(name = "MedjYDw")
    private BigDecimal medjYDw;
    @Column(name = "VelFonta")
    private Integer velFonta;
    @Column(name = "Font")
    private String font;
    @Column(name = "Stampac")
    private String stampac;
    @Column(name = "FormatPapira")
    private String formatPapira;
    @Column(name = "Orjentacija")
    private String orjentacija;

    public Margine() {
    }

    public Margine(String idMargine) {
        this.idMargine = idMargine;
    }

    public String getIdMargine() {
        return idMargine;
    }

    public void setIdMargine(String idMargine) {
        this.idMargine = idMargine;
    }

    public Integer getLevo() {
        return levo;
    }

    public void setLevo(Integer levo) {
        this.levo = levo;
    }

    public Integer getDesno() {
        return desno;
    }

    public void setDesno(Integer desno) {
        this.desno = desno;
    }

    public Integer getGore() {
        return gore;
    }

    public void setGore(Integer gore) {
        this.gore = gore;
    }

    public Integer getDole() {
        return dole;
    }

    public void setDole(Integer dole) {
        this.dole = dole;
    }

    public BigDecimal getMedjX() {
        return medjX;
    }

    public void setMedjX(BigDecimal medjX) {
        this.medjX = medjX;
    }

    public BigDecimal getMedjY() {
        return medjY;
    }

    public void setMedjY(BigDecimal medjY) {
        this.medjY = medjY;
    }

    public BigDecimal getMedjYDw() {
        return medjYDw;
    }

    public void setMedjYDw(BigDecimal medjYDw) {
        this.medjYDw = medjYDw;
    }
    
    public Integer getVelFonta() {
        return velFonta;
    }

    public void setVelFonta(Integer velFonta) {
        this.velFonta = velFonta;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getStampac() {
        return stampac;
    }

    public void setStampac(String stampac) {
        this.stampac = stampac;
    }

    public String getFormatPapira() {
        return formatPapira;
    }

    public void setFormatPapira(String formatPapira) {
        this.formatPapira = formatPapira;
    }

    public String getOrjentacija() {
        return orjentacija;
    }

    public void setOrjentacija(String orjentacija) {
        this.orjentacija = orjentacija;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMargine != null ? idMargine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Margine)) {
            return false;
        }
        Margine other = (Margine) object;
        if ((this.idMargine == null && other.idMargine != null) || (this.idMargine != null && !this.idMargine.equals(other.idMargine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Class.KlaseBaze.Margine[ idMargine=" + idMargine + " ]";
    }
//-----------------------------Deo za ispravku----------------------------------------------------------------------- 
    public String ZaglavljeTabele() { //Opisi polja tabele koji se vide na formi
        return "Sifra,Margina Levo,Margina Desno,Margina Gore,Margina Dole,Medjuprostor X,Medjuprostor Y,Medjuprostor Y dole,Velicina Fonta,Font,Stampac,Format Papira,Orjentacija";
    }

    public String PoljaBaze() { //Polja Baze koja se vide na formi i upisuju se u bazu
        return "IdMargine,Levo,Desno,Gore,Dole,MedjX,MedjY,MedjYDw,VelFonta,Font,Stampac,FormatPapira,Orjentacija";
    }

    public String PoljaBazeZaUpis() {  //Id AutoIncrement i ne pominje se inace bi bio naveden tamo gde mu je mesto
        //Navode se sva polja koja se upisuju
        return "IdMargine,Levo,Desno,Gore,Dole,MedjX,MedjY,MedjYDw,VelFonta,Font,Stampac,FormatPapira,Orjentacija";
    }

    public String sourClass() {
        return "Class.KlaseBaze.Margine";
    }

    public String GetClassBaze() {
        return KeseProba.class.getName();
    }
    //Formiranje FORME - Da li su polja kod unosa Radio button (Radio), Combo Box(CoBox), Check box(Check) ili Text Field(TextF ili prazno)... 
    public String RadioComboCheck() {
        NapuniCombo napuniCombo = new NapuniCombo();
        String elFontVel = napuniCombo.FontVelicina();
        String elFontObl = napuniCombo.FontOblique();
        String elStampac = napuniCombo.Stampaci();
        
        String elPapirFo = napuniCombo.FormatPapira();        
        String elOrijent = napuniCombo.Orijentacija();
        
        //Ako je TxtField stviti space
        String podaci = "";
        //Oznaka Frejma
        //Sirina u procentima
        //Debljina Linije
        //Tekst frejma
        //Gde se ispisuje Right-U produzetku, DOWN-u novom redu sa istom x koordinatom, NEW ili blank - novi red od pocetka
        
//return ",,,,,,,,Font,Stampac,FormatPapira,Orjentacija";
        
        podaci += " ";                                          //IdMargine    TextField       1
        
        podaci += "Fr@me%20%1%Margine%Right##";        
        podaci += "&& ";                                        //Levo        TextField       2
        podaci += "&& ";                                        //Desno       TextField       2
        podaci += "&& ";                                        //Gore        TextField       2
        podaci += "&& ";                                        //Dole        TextField       2        
        
        podaci += "Fr@me%20%1%Medjuprostor%New##"; 
        podaci += "&& ";                                        //MedjX        TextField       2
        podaci += "&& ";                                        //MedjY        TextField       2
        podaci += "&& ";                                        //MedjYDw      TextField       2        
        podaci += "&&CoBox%%" + elFontVel;                      //VelFonta     ComboBox        3a + 3b
        
        podaci += "Fr@me%40%1%Parametri%Right##";        
        podaci += "&&CoBox%%" + elFontObl;                      //Font         ComboBox        4a + 4b
        podaci += "&&CoBox%%" + elStampac;                      //Stampac      TextField       5
        podaci += "&&CoBox%%" + elPapirFo;                      //FormatPapira ComboBox        6a + 6b
        podaci += "&&CoBox%%" + elOrijent;                      //Orjentacija  TextField       7
        return podaci;
    }
    
    public String ImeKlase() {
        return "Margine";
    }
    public int[] PoljaDisabled() {
        String[] pDis = {"IdMargine"};
        String[] poljaBaze = this.PoljaBaze().split(",");

        RasporediKljuceve rasporediKljuceve = new RasporediKljuceve();
        return rasporediKljuceve.PreracunajRbr(pDis, poljaBaze);
    }

    //Vraca podatke koji se racunaju
    public Object[] DisabledRacunati() {
        Object[] disabledRacunati = new Object[2];

        return disabledRacunati;
    }
    
    public void SetujSifraMargina(String sifra) {
        idMargine = sifra;
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

    //Podaci koji se racunaju(onemogucen unos) i ispisani su na formi i pamte se u bazi - oni se racunaju za svaki keyPress
    @Override
    public boolean IzracPodatkeUTabeli() {
        boolean racun = true;

        return racun;
    }

    @Override
    public int[] Kljuceve() {
        String[] pK = {"IdMargine"};
        String[] poljaBaze = this.PoljaBaze().split(",");

        RasporediKljuceve rasporediKljuceve = new RasporediKljuceve();
        return rasporediKljuceve.PreracunajRbr(pK, poljaBaze);
    }

    @Override
    public String UslovTrazenjaSvih() {
        return "";
    }

    @Override
    public Object[] ProveriVrednosti() {
        String pomoc;
        Object[] poruka = new Object[2];
        poruka[0] = null;
        poruka[1] = null;
        Provere provere = new Provere();
        String[] OpisiPolja = ZaglavljeTabele().split(",");

        pomoc = provere.proveriString(getIdMargine(), OpisiPolja[0]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 0;}}
        pomoc = provere.proveriInteger(getLevo(), OpisiPolja[1]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 1;}}
        pomoc = provere.proveriInteger(getDesno(), OpisiPolja[2]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 2;}}
        pomoc = provere.proveriInteger(getDole(), OpisiPolja[3]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 3;}}
        pomoc = provere.proveriInteger(getGore(), OpisiPolja[4]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 4;}}
        pomoc = provere.proveriBigDecimal(getMedjX(), OpisiPolja[5]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 5;}}
        pomoc = provere.proveriBigDecimal(getMedjY(), OpisiPolja[6]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 6;}}
        pomoc = provere.proveriBigDecimal(getMedjYDw(), OpisiPolja[7]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 7;}}         
        pomoc = provere.proveriInteger(getVelFonta(), OpisiPolja[8]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 8;}}
        pomoc = provere.proveriString(getStampac(), OpisiPolja[9]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 9;}}
        pomoc = provere.proveriString(getFormatPapira(), OpisiPolja[10]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 10;}}
        pomoc = provere.proveriString(getOrjentacija(), OpisiPolja[11]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) { poruka[0] = 11;}}        

        return poruka;
    }

    @Override
    public String UslovTrazenjaSloga() {
        return "IdMargine=" + idMargine + "";
    }

    @Override
    public String IspraviSlog() {
        return    "Levo="          + levo         + " ,  "
                + "Desno="         + desno        + " ,  "
                + "Dole="          + dole         + " ,  "
                + "Gore="          + gore         + " ,  "
                + "MedjX="         + medjX        + " ,  "
                + "MedjY="         + medjY        + " ,  "
                + "MedjYDw="       + medjYDw      + " ,  "                
                + "VelFonta="      + velFonta     + " ,  "
                + "Font='"         + font         + "',  "                
                + "Stampac='"      + stampac      + "',  "
                + "FormatPapira='" + formatPapira + "',  "
                + "Orjentacija='"  + orjentacija  + "'";
    }

    @Override
    public String UpisiSlog() {
        return "'" + idMargine    + "',  " 
                   + desno        + " ,  "
                   + dole         + " ,  "
                   + gore         + " ,  "
                   + medjX        + " ,  "
                   + medjY        + " ,  "
                   + medjYDw      + " ,  "                
                   + velFonta     + " , '"
                   + font         + "', '"                
                   + stampac      + "', '"
                   + formatPapira + "', '"
                   + orjentacija  + "'";
    }    

}
