/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.KlaseBaze;

import Class.Apstraktne.AbstractDAO;
import Forme.Napuni.ComboBoxovi.NapuniCombo;
import Class.Kljucevi.RasporediKljuceve;
import Class.Provere.Provere;
import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "kutija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KutijaProba.findAll", query = "SELECT k FROM Kutija k"),
    @NamedQuery(name = "KutijaProba.findByIdKutije", query = "SELECT k FROM KutijaProba k WHERE k.idKutije = :idKutije"),
    @NamedQuery(name = "KutijaProba.findByOpisKutije", query = "SELECT k FROM KutijaProba k WHERE k.opisKutije = :opisKutije"),
    @NamedQuery(name = "KutijaProba.findByDuzPrazno", query = "SELECT k FROM KutijaProba k WHERE k.duzPrazno = :duzPrazno"),
    @NamedQuery(name = "KutijaProba.findBySirPrazno", query = "SELECT k FROM KutijaProba k WHERE k.sirPrazno = :sirPrazno"),
    @NamedQuery(name = "KutijaProba.findByVisKutije", query = "SELECT k FROM KutijaProba k WHERE k.visKutije = :visKutije"),
    @NamedQuery(name = "KutijaProba.findByPreklop", query = "SELECT k FROM KutijaProba k WHERE k.preklop = :preklop"),
    @NamedQuery(name = "KutijaProba.findByBrojKesa", query = "SELECT k FROM KutijaProba k WHERE k.brojKesa = :brojKesa")})
public class KutijaProba extends AbstractDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdKutije")
    private BigInteger idKutije;
    @Column(name = "OpisKutije")
    private String opisKutije;
    @Column(name = "DuzPrazno")
    private BigInteger duzPrazno;
    @Column(name = "SirPrazno")
    private BigInteger sirPrazno;
    @Column(name = "VisKutije")
    private BigInteger visKutije;
    @Column(name = "Preklop")
    private BigInteger preklop;
    @Column(name = "BrojKesa")
    private BigInteger brojKesa;

    public KutijaProba() {
    }

    public KutijaProba(BigInteger idKutije) {
        this.idKutije = idKutije;
    }

    public BigInteger getIdKutije() {
        return idKutije;
    }

    public void setIdKutije(BigInteger idKutije) {
        this.idKutije = idKutije;
    }

    public String getOpisKutije() {
        return opisKutije;
    }

    public void setOpisKutije(String opisKutije) {
        this.opisKutije = opisKutije;
    }

    public BigInteger getDuzPrazno() {
        return duzPrazno;
    }

    public void setDuzPrazno(BigInteger duzPrazno) {
        this.duzPrazno = duzPrazno;
    }

    public BigInteger getSirPrazno() {
        return sirPrazno;
    }

    public void setSirPrazno(BigInteger sirPrazno) {
        this.sirPrazno = sirPrazno;
    }

    public BigInteger getVisKutije() {
        return visKutije;
    }

    public void setVisKutije(BigInteger visKutije) {
        this.visKutije = visKutije;
    }

    public BigInteger getPreklop() {
        return preklop;
    }

    public void setPreklop(BigInteger preklop) {
        this.preklop = preklop;
    }

    public BigInteger getBrojKesa() {
        return brojKesa;
    }

    public void setBrojKesa(BigInteger brojKesa) {
        this.brojKesa = brojKesa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKutije != null ? idKutije.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KutijaProba)) {
            return false;
        }
        KutijaProba other = (KutijaProba) object;
        if ((this.idKutije == null && other.idKutije != null) || (this.idKutije != null && !this.idKutije.equals(other.idKutije))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Class.KlaseBaze.KutijaProba[ idKutije=" + idKutije + " ]";
    }

    /**
     *
     * @return
     */
    //-----------------------------Deo za ispravku-----------------------------------------------------------------------
    public String ZaglavljeTabele() {   //Opisi polja u zaglavlju tabela i Label-sima pre textField-a
        return "IdKutije,Opis,Prazno po Duzini(mm),Prazno po Sirini(mm),Visina Kutije(mm),Preklop(mm),Br.Kesa u Kutiji,"
                + "IdKutije,Opis,Prazno po Duzini(mm),Prazno po Sirini(mm),Visina Kutije(mm),Preklop(mm),Br.Kesa u Kutiji,"
                + "IdKutije,Opis,Prazno po Duzini(mm),Prazno po Sirini(mm),Visina Kutije(mm),Preklop(mm),Br.Kesa u Kutiji,"
                + "IdKutije,Opis,Prazno po Duzini(mm),Prazno po Sirini(mm),Visina Kutije(mm),Preklop(mm),Br.Kesa u Kutiji";
    }

    public String PoljaBaze() {   //Polja Baze koja se vide na formi i upisuju se u bazu
        return "IdKutije,OpisKutije,DuzPrazno,SirPrazno,VisKutije,Preklop,BrojKesa,"
                + "IdKutije,OpisKutije,DuzPrazno,SirPrazno,VisKutije,Preklop,BrojKesa,"
                + "IdKutije,OpisKutije,DuzPrazno,SirPrazno,VisKutije,Preklop,BrojKesa,"
                + "IdKutije,OpisKutije,DuzPrazno,SirPrazno,VisKutije,Preklop,BrojKesa";
    }

    public String PoljaBazeZaUpis() {   //Id AutoIncrement i ne pominje se
        return "OpisKutije,DuzPrazno,SirPrazno,VisKutije,Preklop,BrojKesa";
    }

    public String sourClass() {
        return "Class.KlaseBaze.KutijaProba";
    }

    public String GetClassBaze() {
        return KutijaProba.class.getName();
    }

    public String UslovTrazenjaSvih() {
        return "";
    }

    public String UslovTrazenjaSloga() {
        return "IdKutije=" + idKutije;
    }

    public String IspraviSlog() {
        return "OpisKutije='" + opisKutije + "' ,  "
                + "DuzPrazno=" + duzPrazno + "  ,  "
                + "SirPrazno=" + sirPrazno + "  ,  "
                + "VisKutije=" + visKutije + "  ,  "
                + "Preklop=" + preklop + "  ,  "
                + "BrojKesa=" + brojKesa;
    }

    public String UpisiSlog() {
        return "    '" //id AutoIncrement - ako nije auto inkrement i id-e uvrstiti u upis
                + opisKutije + "' ,  "
                + duzPrazno + "  ,  "
                + sirPrazno + "  ,  "
                + visKutije + "  ,  "
                + preklop + "  ,  "
                + brojKesa;
    }

    //Formiranje FORME - Da li su polja kod unosa Radio button (Radio), Combo Box(CoBox), Check box(Check) ili Text Field(TextF ili prazno)... 
    public String RadioComboCheck() {
        NapuniCombo napuniCombo = new NapuniCombo();
        String elPrazno_ = napuniCombo.KutijaPrazno();
        String elPreklop = napuniCombo.KutijaPreklop();
        //Ako je TxtField stviti space
        String podaci = "";
        //Oznaka Frejma
        //Sirina u procentima
        //Debljina Linije
        //Tekst frejma
        //Gde se ispisuje Right-U produzetku, DOWN-u novom redu sa istom x koordinatom, NEW ili blank - novi red od pocetka

        podaci += " ";                                          //IdKutije    TextField       1
        
        podaci += "Fr@me%034%1%Frame 0%Right##";        
        podaci += "&& ";                                        //Opis        TextField       2
        
        podaci += "Fr@me%66%1%Frame 1%Down##";         
        podaci += "&&CoBox%%" + elPrazno_;                      //DuzPrazno   ComboBox        3a + 3b
        podaci += "&&CoBox%%" + elPrazno_; podaci += "//Right"; //SirPrazno   ComboBox        4a + 4b
        podaci += "&& ";                   podaci += "//Right"; //VisKutije   TextField       5
        podaci += "&&CoBox%%" + elPreklop; podaci += "//Right"; //Preklop     ComboBox        6a + 6b
        podaci += "&& ";                                        //BrojKesa    TextField       7
        
        podaci += "Fr@me%040%1%Frame 2%New##";
        podaci += "&& ";                                        //IdKutije    TextField       1
        podaci += "&& ";                                        //Opis        TextField       2
        podaci += "&&CoBox%%" + elPrazno_; podaci += "//Right"; //DuzPrazno   ComboBox        3a + 3b
        podaci += "&&CoBox%%" + elPrazno_;                      //SirPrazno   ComboBox        4a + 4b
        podaci += "&& ";                   podaci += "//Right"; //VisKutije   TextField       5
        podaci += "&&CoBox%%" + elPreklop;                      //Preklop     ComboBox        6a + 6b
        podaci += "&& ";                                        //BrojKesa    TextField       7 
        
        podaci += "Fr@me%070%1%Frame 3%Right##";
        podaci += "&& ";                                        //IdKutije    TextField       1
        podaci += "&& ";                                        //Opis        TextField       2
        podaci += "&&CoBox%%" + elPrazno_; podaci += "//Right"; //DuzPrazno   ComboBox        3a + 3b
        podaci += "&&CoBox%%" + elPrazno_; podaci += "//Right"; //SirPrazno   ComboBox        4a + 4b
        podaci += "&& ";                   podaci += "//Down";  //VisKutije   TextField       5
        podaci += "&&CoBox%%" + elPreklop; podaci += "//Right"; //Preklop     ComboBox        6a + 6b
        podaci += "&& ";                   podaci += "//New";   //BrojKesa    TextField       7
        podaci += "&& ";                   podaci += "//Right"; //IdKutije    TextField       1
        podaci += "&& ";                                        //Opis        TextField       2
        podaci += "&&CoBox%%" + elPrazno_;                      //DuzPrazno   ComboBox        3a + 3b
        podaci += "&&CoBox%%" + elPrazno_;                      //SirPrazno   ComboBox        4a + 4b
         
        podaci += "Fr@me%25%1%Frame 4% ##";        
        podaci += "&& ";                                        //VisKutije   TextField       5
        podaci += "&&CoBox%%" + elPreklop;                      //Preklop     ComboBox        6a + 6b
        podaci += "Fr@me%30%1%Frame 5%Right##";        
        podaci += "&& ";                                        //BrojKesa    TextField       7        
        

        return podaci;
        //                             1  2   3a  %%     3b                  4a        4b                5    6a        6b                7
        //podaci = (java.lang.String) " && &&CoBox%%5@@10@@15@@20@@25@@30&&CoBox%%5@@10@@15@@20@@25@@30&& &&CoBox%%5@@10@@15@@20@@25@@30&& "
    }

    public String ImeKlase() {
        return "KutijaProba";
    }
    
    public String SifraMargina() {
        idKutije = BigInteger.valueOf(12345679);
        return idKutije.toString();
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

    public int[] Kljuceve() {
        String[] pK = {"IdKutije"};
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

        pomoc = provere.proveriString(getOpisKutije(), OpisiPolja[1]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc; if (poruka[0] == null) {poruka[0] = 1;}}
        
        pomoc = provere.proveriBigInteger(getVisKutije(), OpisiPolja[4]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc;if (poruka[0] == null) {poruka[0] = 4;}}
        
        pomoc = provere.proveriBigInteger(getBrojKesa(), OpisiPolja[6]);
        if (pomoc != null) {poruka[1] = (poruka[1] == null) ? pomoc : poruka[1] + pomoc;if (poruka[0] == null) {poruka[0] = 6;}}

        return poruka;
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
}
