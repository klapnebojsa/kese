/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme;

import Class.Apstraktne.AbstractDAO;
import Class.DAO.BrokerDAO;
import Forme.Konstante.Boje;
import Forme.Polja.Listeneri.ClickTbl;
import Forme.Polja.Listeneri.FocusCBx;
import Forme.Polja.Listeneri.FocusPretraga;
import Forme.Polja.Listeneri.FocusTable;
import Class.Povezivanje.Procitaj;
import Class.Povezivanje.Setuj;
import Sistem.OsnovneDefinicije.OsnovnaRezolucija;
import Sistem.OsnovneDefinicije.RezolucijaEkrana;
import Forme.Tabele.MojaTabela;
import Forme.Polja.Listeneri.FocusTxt;
import Forme.Polja.Prikazi.InfoLinija;
import Forme.Polja.Prikazi.PoljaIzTabeleDefinicija;
import Forme.PopUpovi.PopUp;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Nebojsa
 */
public class FormForme extends JFrame {

    List myDataList = null;
    String zaglTabele = null;
    String opisPolja = null;
    public ResultSetMetaData metaData = null;
    int[] kljucevi = null;
    int[] poljaDisabled = null;
    JFrame KoZove;
    JPanel contentPane;
    public MojaTabela mt1;
    Point sP;
    Point lB[];
    JLabel[] labels;
    JFormattedTextField[] texts;
    JComboBox[] combos;
    InfoLinija infolinija;
    JTextField tex;
    public AbstractDAO k;
    BrokerDAO brokerDAO;
    BrokerDAO brokerDAOPreth;
    PoljaIzTabeleDefinicija poljaIzTabele;
    double deoWidth;
    String kojaForma;
    String opisForme;
    String sifraMargine;
    public JScrollPane scrPane;
    
    private Map<Integer, Integer> mSpecialColumnAlignmentMap = new Hashtable<Integer, Integer>();

    public FormForme(JFrame KoZoveDonet, String OpisForme, double deoWidth, String kojaForma) throws SQLException {
        KoZove = KoZoveDonet;
        //KoZove.setVisible(false);
        KoZove.setEnabled(false);
        this.opisForme = OpisForme;
        
        setTitle(opisForme);
        setDeoWidth(deoWidth);
        setKojaForma(kojaForma);
    }

    public void main() throws SQLException, ParseException, AWTException {
        napuniFormu();
    }

    @SuppressWarnings("empty-statement")
    private void napuniFormu() throws SQLException, ParseException, AWTException {
        //Podaci za tabelu iz baze
        brokerDAO = new BrokerDAO(k);
        try {
            Procitaj emp = new Procitaj();
            switch (getKojaForma()){
                //Citamo samo jedan u tabelu ako su pozivaju margine (ili nesto u tom obliku)
                //bez prikaza tabele i otvorenim poljima za ispravku ili unosom novog                
                case "Margine":
                    Setuj set = new Setuj(brokerDAOPreth);               
                    sifraMargine = emp.ProcitajSifruMargina(brokerDAOPreth);
                    
                    set = new Setuj(brokerDAO);                    
                    set.SetujSifruMargina(sifraMargine);
                    myDataList = emp.ProcitajJedanxx(brokerDAO);
                    break;

                    //Citamo sve u tabelu  
                    //Default verzija sa tabelom i svim opcijama
                default:
                    
                    myDataList = emp.ProcitajTabelaxx(brokerDAO);
                    break;
            }
            metaData = brokerDAO.opstaOperacija.rsmd;
            zaglTabele = emp.ProcitajZaglTabele(brokerDAO);
            kljucevi = emp.ProcitajKljuceve(brokerDAO);
            poljaDisabled = emp.ProcitajPoljaDisabled(brokerDAO);
            opisPolja = emp.ProcitajRadioComboCheck(brokerDAO);
            //radioComboCheck = r.split("&&");
        } catch (Exception e) {
        }
        if(metaData==null){
            PopUp popUp = new PopUp(KoZove, "   !!! NEMA KONEKCOJE SA BAZOM !!!  \n \n PROVERITE DA LI JE SERVER STARTOVAN");
            popUp.Ok();
            this.setVisible(false);
            KoZove.setEnabled(true);
            return;
        }
        //rezolucija
        RezolucijaEkrana re = new RezolucijaEkrana();
        Dimension fullScr = re.FullScreen();
        OsnovnaRezolucija or = new OsnovnaRezolucija();

        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

        //Ne treba za formiranje forme po koordinatama
        //this.setLayout(new FlowLayout());
        
        //Formiranje tabele - punjenje podacima            
        mt1 = new MojaTabela(myDataList, zaglTabele, metaData, kljucevi, brokerDAO, poljaDisabled, this);
        mt1.NapuniTabelu();
        //mt1.table.setAutoCreateRowSorter(true);
        mt1.KoZove = KoZove;
        //mt1.table.setShowGrid(true);        
      
        //Dimenzije tabele. Ne uticu na vidljivost tabele na formi. Sluzi samo da u MojaTabela (model)
        //upisemo koje su dimenzije tabele tj csrPane-a koji se menja i utice na velicinu tabele na formi
        Double sirina = fullScr.width * getDeoWidth();
        mt1.setWidthTbl(sirina.intValue());
        mt1.setHeightTbl(getMojHeight() * fullScr.height / or.getHeight());
        
        //PANEL
        contentPane = new JPanel();
        contentPane.setLayout(null);

        JLabel lab = new JLabel();
        Boje boje = new Boje();
        Color c;

        //Dodavanje polja za pretragu definisanih u Moja Tabela 
        /*lab = mt1.l1; 
         lab.setSize(200,25);
         lab.setLocation(((fullScr.width - mt1.getWidthTbl()) / 2) - lab.getWidth(),0);
         contentPane.add(lab);
         tex = new JTextField();
         tex = mt1.filterText;
         c = boje.getColorTextFilter();        
         tex.setBackground(c);
         tex.setSize(mt1.getWidthTbl(),25);        
         tex.setLocation((fullScr.width - mt1.getWidthTbl()) / 2, 0);
         contentPane.add(tex);*/
        // DOVDE polja za pretragu
        
        //TABELA postavljanje na formu u scrPane
        scrPane = new JScrollPane(mt1.getTable());
        scrPane.setSize(mt1.getWidthTbl(), mt1.getHeightTbl());
        scrPane.setLocation((fullScr.width - scrPane.getWidth()) / 2, lab.getHeight());
         
        c = boje.getColorPaneBackground();
        scrPane.getViewport().setBackground(c);

        contentPane.add(scrPane);
        sP = scrPane.getLocation();
        //Dovde Tabela      

        //Polja za obavestenje
        infolinija = new InfoLinija();
        contentPane.add(infolinija.DefInfoLiniju());

        
        //-------------KREIRANJE Polja iz tabele u tekst boksovima ili combo boxovima spremnim za unos sa opisima u label-u --------------
        poljaIzTabele = new PoljaIzTabeleDefinicija(this, contentPane, metaData, mt1, sP, opisPolja, brokerDAO);
        poljaIzTabele.setujPoljaIzTabele();
        poljaIzTabele.prikaziPoljaIzTabele();
        texts = poljaIzTabele.getTexts();
        combos = poljaIzTabele.getCombos();
        mt1.setPoljaIzTabele(poljaIzTabele);

        this.setContentPane(contentPane);

       
        
        //Listner-i ------------------------------------------------------------------------------------------ 
        // X-Za zatvaranje forme
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                //KoZove.setVisible(true);
                KoZove.setEnabled(true);
            }
        });
        
          
        // Polje za Pretragu tabele
        /*tex.getDocument().addDocumentListener(
         new DocumentListener() {
         public void changedUpdate(DocumentEvent e) {
         mt1.newFilter();
         }
         public void insertUpdate(DocumentEvent e) {
         mt1.newFilter();
         }
         public void removeUpdate(DocumentEvent e) {
         mt1.newFilter();
         }
         }
         );*/

        //MouseClick na tabelu ili up-down + Double click Listener
        ClickTbl click = new ClickTbl();
        click.TableClick(mt1, poljaIzTabele, kljucevi, poljaDisabled);
  

 
        //focusLost i focusGained Table
        FocusTable focusTbl = new FocusTable();
        focusTbl.FocusLostGained(mt1, infolinija, brokerDAO.a.ImeKlase());

        //focusLost i focusGained Polja za pretragu
        FocusPretraga focusPretraga = new FocusPretraga();
        focusPretraga.FocusLostGained(tex, infolinija);

        //focusLost i focusGained na polja iz tabele TextField, ComboBox, ...
        String[] staJe = {""};
        for (int i = 0; i < mt1.getTable().getColumnCount(); i++) {
            switch (poljaIzTabele.getStaJe(i)) {
                case "CoBox":
                    FocusCBx focusCbx = new FocusCBx();
                    focusCbx.FocusLostGained(combos[i], i, metaData, infolinija);
                    break;
                default:
                    FocusTxt focusTxt = new FocusTxt();
                    focusTxt.FocusLostGained(texts[i], i, metaData, infolinija);
                    focusTxt.getIspravljenTextF();
                    break;
            }
        }
        // Dovde Listeneri ------------------------------------------------------------------------------------------------------------
     
        //Kontrola koji je oblik forme potreban
        this.setVisible(true);
        
        //Biramo oblik forme koja nam je potrebna
        switch (getKojaForma()){
            //ako su pozivaju margine (ili nesto u tom obliku)
            //bez prikaza tabele i otvorenim poljima za ispravku ili unosom novog  
            case "Margine":
                FormaKoja formaKoja = new FormaKoja(mt1, this, brokerDAO);                
                formaKoja.Margine(sifraMargine, poljaIzTabele);
                break;

                //Default verzija sa tabelom i svim opcijama
            default:                
                break;
        } 

    }

    //Koliko je siroka tabela u odnosu na sirinu forme
    private void setDeoWidth(double deoWidth){
        this.deoWidth = deoWidth;
    }
    private double getDeoWidth() {
        return deoWidth;
    }
    
    //Da li je forma sa tabelom - standardno
    //ili je bez tabele npr. za margine (odmah otvorena za upis polja)
    private void setKojaForma(String kojaForma){
        this.kojaForma = kojaForma;
    }
    private String getKojaForma() {
        return kojaForma;
    }
    
    public String getOpisForme() {
        return opisForme;
    }    
    //Visina tabele u kojoj su podaci
    private int getMojHeight() {
        int visina = 200;
        switch (kojaForma){
            case "Margine": visina = 0;      
        }
        return visina;
    }
    //Prethodni broker sa prethodnom klasom koji je zvao ovu klasu
    public void setBrokerDAOPret(BrokerDAO brokerDAO){
        brokerDAOPreth = brokerDAO;
    }
    
    public BrokerDAO getBrokerDAO(){
        return brokerDAO;
    }
}
