/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.KlaseBaze;

import Class.Apstraktne.AbstractDAO;
import Class.Kljucevi.RasporediKljuceve;
import Class.Provere.Provere;
import java.io.Serializable;
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
@Table(name = "vlasnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vlasnik.findAll", query = "SELECT v FROM Vlasnik v"),
    @NamedQuery(name = "Vlasnik.findByIdVlasnika", query = "SELECT v FROM Vlasnik v WHERE v.idVlasnika = :idVlasnika"),
    @NamedQuery(name = "Vlasnik.findByNazivVlasnika", query = "SELECT v FROM Vlasnik v WHERE v.nazivVlasnika = :nazivVlasnika"),
    @NamedQuery(name = "Vlasnik.findByAdresa", query = "SELECT v FROM Vlasnik v WHERE v.adresa = :adresa"),
    @NamedQuery(name = "Vlasnik.findByMesto", query = "SELECT v FROM Vlasnik v WHERE v.mesto = :mesto"),
    @NamedQuery(name = "Vlasnik.findByTel", query = "SELECT v FROM Vlasnik v WHERE v.tel = :tel"),
    @NamedQuery(name = "Vlasnik.findByMb", query = "SELECT v FROM Vlasnik v WHERE v.mb = :mb"),
    @NamedQuery(name = "Vlasnik.findByPib", query = "SELECT v FROM Vlasnik v WHERE v.pib = :pib"),
    @NamedQuery(name = "Vlasnik.findByPdv", query = "SELECT v FROM Vlasnik v WHERE v.pdv = :pdv"),
    @NamedQuery(name = "Vlasnik.findByDelatnost", query = "SELECT v FROM Vlasnik v WHERE v.delatnost = :delatnost")})
public class Vlasnik extends AbstractDAO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdVlasnika")
    private BigInteger idVlasnika;
    @Column(name = "NazivVlasnika")
    private String nazivVlasnika;
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

    public Vlasnik() {
    }

    public Vlasnik(BigInteger idVlasnika) {
        this.idVlasnika = idVlasnika;
    }

    public BigInteger getIdVlasnika() {
        return idVlasnika;
    }

    public void setIdVlasnika(BigInteger idVlasnika) {
        this.idVlasnika = idVlasnika;
    }

    public String getNazivVlasnika() {
        return nazivVlasnika;
    }

    public void setNazivVlasnika(String nazivVlasnika) {
        this.nazivVlasnika = nazivVlasnika;
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
        hash += (idVlasnika != null ? idVlasnika.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vlasnik)) {
            return false;
        }
        Vlasnik other = (Vlasnik) object;
        if ((this.idVlasnika == null && other.idVlasnika != null) || (this.idVlasnika != null && !this.idVlasnika.equals(other.idVlasnika))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Class.KlaseBaze.Vlasnik[ idVlasnika=" + idVlasnika + " ]";
    }
    //-----------------------------Deo za ispravku-----------------------------------------------------------------------    
    public String ZaglavljeTabele() {
        return "Sifra Vlasnika,Naziv Vlasnika,Adresa,Mesto,Tel,MB,PIB,PDV,Delatnost";
    }

    public String PoljaBaze() {  //Polja Baze koja se vide na formi i upisuju se u bazu
        return "IdVlasnika,NazivVlasnika,Adresa,Mesto,Tel,MB,PIB,PDV,Delatnost";
    }

    public String PoljaBazeZaUpis() {  
        return "IdVlasnika,NazivVlasnika,Adresa,Mesto,Tel,MB,PIB,PDV,Delatnost";
    }

    public String sourClass() {
        return "Class.KlaseBaze.Vlasnik";
    }

    public String GetClassBaze() {
        return Vlasnik.class.getName();
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
        return "Vlasnik";
    }
    public String SifraMargina() {
        idVlasnika = BigInteger.valueOf(1);
        return idVlasnika.toString();
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
        String[] pK = {"IdVlasnika"};
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

        pomoc = provere.proveriString(getNazivVlasnika(), OpisiPolja[1]);
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
        return "IdVlasnika=" + idVlasnika + "";
    }

    public String IspraviSlog() {
        return "NazivVlasnika='" + nazivVlasnika + "' ,  "
                + "Adresa='" + adresa + "' ,  "
                + "Mesto='" + mesto + "' ,  "
                + "Tel='" + tel + "' ,  "
                + "MB='" + mb + "' ,  "
                + "PIB='" + pib + "' ,  "
                + "PDV='" + pdv + "' ,  "
                + "Delatnost='" + delatnost + "'";
    }

    public String UpisiSlog() {
        return    idVlasnika + " , '"
                + nazivVlasnika + "' , '"
                + adresa + "' , '"
                + mesto + "' , '"
                + tel + "' , '"
                + mb + "' , '"
                + pib + "' , '"
                + pdv + "' , '"
                + delatnost + "'";
    }

    @Override
    public boolean IzracPodatkeVanTabele() {
        return true;
    }

  
}
