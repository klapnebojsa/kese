/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Prikazi;

import Class.DAO.BrokerDAO;
import Forme.Konstante.Boje;
import Forme.Konstante.Formati;
import Forme.Polja.Listeneri.ActionListenerPoljaTbl;
import Forme.Polja.Ogranicenja.OgrJTextMaxDuzina;
import Sistem.OsnovneDefinicije.Alignment;
import Sistem.OsnovneDefinicije.RezolucijaEkrana;
import Forme.Tabele.MojaTabela;
import FormeRacunanja.RacunajVrednost;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Nebojsa
 */
public class PoljaIzTabeleDefinicija implements KeyListener, ActionListener {

    JPanel contentPane;
    java.awt.Frame KoZove;
    ResultSetMetaData metaData;
    String[] radioComboCheck;

    MojaTabela mt1;
    Point sP;
    Point sPf;
    //Point sPNew;
    Point sPPoc;
    int MaxDown;
    Point lB[];
    JLabel[] labels;
    JButton[] buttons;
    JPanel frameForma;
    public BrokerDAO brokerDAO;
    JFormattedTextField[] texts;
    JComboBox[] combos;
    String kojiUpis;  // Sta je upis: Novi upis ili Izmena postojeceg 

    int selectedRow;
    int selectedCell;
    int widthLab;

    String staJe[];
    String vrednostStaJe[];
    String gdeJe[];
    String frame[];
    String opisPolja;
    List<Integer> rbrFrame = new ArrayList<Integer>();

    int[] tekuciLbWidth;
    int[] SirinaTxt;
    JPanel fRframe; 
    boolean boolFrame; 
    boolean imaFrame;
    int frX;
    int redPerFrame;
    double sirinaFrame;
    String[] podaciOFrame;
    int maxHeigtPret;
    
    public PoljaIzTabeleDefinicija(java.awt.Frame KoZove, JPanel contentPane, ResultSetMetaData metaData, MojaTabela mt1,
            Point sP, String opisPolja, BrokerDAO brokerDAO) {
        this.KoZove = KoZove;
        this.contentPane = contentPane;
        this.metaData = metaData;
        this.mt1 = mt1;
        this.sP = sP;
        this.opisPolja = opisPolja;
        this.brokerDAO = brokerDAO;
        frX = sP.x;
    }

    // Prikazi na formi TextBox, Label, ComboBox ... -------------------------------------------------------------------------------
    //Labels ispred text boxa ili combo boxa
    //Setovanje Frame-a ComboBox-ova radio ...
    public void setujPoljaIzTabele() throws ParseException, SQLException {
        gdeJe = new String[mt1.getTable().getColumnCount()];        
        staJe = new String[mt1.getTable().getColumnCount()];
        vrednostStaJe = new String[mt1.getTable().getColumnCount()];
        frame = new String[mt1.getTable().getColumnCount()];        
        String[] frameX = new String[mt1.getTable().getColumnCount()];
        try {radioComboCheck = opisPolja.split("&&");
        } catch (Exception e1) {}

        for (int i = 0; i < mt1.getTable().getColumnCount(); i++) {
            try {
                if (radioComboCheck[i] != radioComboCheck[i].replace("##", "")) {
                    frameX[i] = radioComboCheck[i].substring(radioComboCheck[i].indexOf("Fr@me"), radioComboCheck[i].indexOf("##"));
                    //boolean bol = (i != 0) ? setFrame(i+1,frameX[i]): setFrame(i,frameX[i]);
                    setFrame(i+1,frameX[i]);
                    setRbrFrame(i);
                    radioComboCheck[i]=radioComboCheck[i].replace(frameX[i]+"##", "");
                } else {setFrame(i+1, null);}
            } catch (Exception e) {}
        }
        
        for (int i = 0; i < mt1.getTable().getColumnCount(); i++) {
            try{
                String[] gdeJeX=radioComboCheck[i].split("//");                        
                setGdeJe(i,gdeJeX[1]);
                radioComboCheck[i]=gdeJeX[0];
            }catch(Exception e2){}            
            
            String[] staJe = new String[]{"", ""};
            try {
                staJe = radioComboCheck[i].split("%%");
                if (staJe.length < 2) {staJe = new String[]{"", ""};}
            } catch (Exception e) {staJe = new String[]{"", ""};}
            setStaJe(i, staJe[0]);
            setVrednostStaJe(i, staJe[1]);
        }
    }

    //Punjenje polja vrednostima
    public void prikaziPoljaIzTabele() throws ParseException, SQLException {
        int DodajMarginu = 0;

        lB = new Point[mt1.getTable().getColumnCount()];
        labels = new JLabel[mt1.getTable().getColumnCount()];
        texts = new JFormattedTextField[mt1.getTable().getColumnCount()];
        combos = new JComboBox[mt1.getTable().getColumnCount()];
                    
        labels = kreirajLabels();
        sP = new Point(sP.x + DodajMarginu, sP.y + mt1.getHeightTbl() + 10);
        sPPoc = new Point(sP.x, sP.y);
        
        //Preracun Sirina Label-a     
        PreracunajSirinuLabel(); 
        //Pozicioniranje Polja na Formi
        PozicionirajPolja();

        //Ako nekada zatrebaju buttons
        //prikaziButtonOsnovno();         
    }
    private void PreracunajSirinuLabel(){
        //Preracun Sirina Label-a 
        tekuciLbWidth = new int[mt1.getTable().getColumnCount()];
        SirinaTxt = new int[mt1.getTable().getColumnCount()];        
        boolean [] newRow = new boolean[mt1.getTable().getColumnCount()+1];
        newRow[0]=false;
        SirinaTxt[0] = mt1.getTable().getColumnModel().getColumn(0).getPreferredWidth();
        tekuciLbWidth[0] = getLabWidth();
        labels[0].setSize(getLabWidth(), getLabHeight());
        
        for (int i = 0; i < mt1.getTable().getColumnCount() - 1; i++) {
            SirinaTxt[i+1] = mt1.getTable().getColumnModel().getColumn(i+1).getPreferredWidth();
            try{            
                switch(getGdeJe(i)){
                    case "Right": newRow[i+1]=false; break;   //Pomeranje Desno   
                    case "Down": newRow[i+1]=false; break;    //Pomeranje dole tako da je x koordinata ista                
                    default: newRow[i+1]=true; break;         //Pomeranje prema dole u novi red     
                }
            //Pomeranje prema dole u novi red ako je null                
            }catch(Exception e){newRow[i+1]=true;}
            
            if(newRow[i+1]) { tekuciLbWidth[i+1]=getLabWidth();
            }else{ tekuciLbWidth[i+1]=SirinaLabel(labels[i+1].getText() + 20);}
            labels[i+1].setSize(tekuciLbWidth[i+1], getLabHeight());            
        }         
    }
    
    
    private void SetujFrame(int i){
        //Dovrsavanje parametara Frejma
        int fRheightPret=0;
        imaFrame=true;
        fRheightPret = (redPerFrame-1)*getLabHeight();                
        if(boolFrame){
            fRheightPret = redPerFrame*getLabHeight()+5;
            maxHeigtPret = (maxHeigtPret>fRheightPret) ? maxHeigtPret: fRheightPret;
            //redefinisanje visine PRETHODNOG FRAME-a kada vidimo koliko ima redova koja idu u taj frame            
            fRframe.setSize((int)sirinaFrame, fRheightPret);            
        }

        //Pozicioniranje pocetne tacke novog Frame-a
        try{
            switch(podaciOFrame[4]){          
                case "Right": //Pomeranje Desno
                    sPf.setLocation(sPf.x + (int)sirinaFrame, sPf.y); break; 
                case "Down": //Pomeranje dole tako da je x koordinata ista
                    sPf.setLocation(sPf.x , sPf.y  + maxHeigtPret); maxHeigtPret =0; break;                  
                default: //Pomeranje prema dole u novi red 
                    sPf.setLocation(frX, sPf.y  + maxHeigtPret); maxHeigtPret=0; break;
            }              
        }catch(Exception e){}         

        //Otvaranje novog Frame-a
        podaciOFrame = frame[i].split("%");
        double proc = Double.parseDouble(podaciOFrame[1])/100;
        sirinaFrame = mt1.getWidthTbl() * proc;        
        fRframe = new JPanel();
   
        fRframe.setLayout(null);    
        fRframe.setBorder(new TitledBorder(new EtchedBorder(), podaciOFrame[3]));
        //Visina se redefinise na kraju (u redovima ispred). Zato je 0.
        //fRframe.setBounds(frX, sPf.y + fRheightPret, (int)sirinaFrame,0);
        fRframe.setBounds(sPf.x, sPf.y, (int)sirinaFrame,0); 
        
        fRframe.setVisible(true);
        contentPane.add(fRframe);        

        sPf.setLocation(fRframe.getX(),fRframe.getY());
        
        sP.setLocation(0, getLabHeight());     
        sPPoc.setLocation(0, getLabHeight());       
        boolFrame=true; 
        redPerFrame=1;        
    }    
    
    
    private void PozicionirajPolja() throws ParseException, SQLException{
        boolFrame = false;
        imaFrame = false;
        sPf = new Point(sPPoc.x,sPPoc.y);        
        int maxWidth = 0;
        int krug = 0; 
        RezolucijaEkrana re = new RezolucijaEkrana();
        Dimension fullScr = re.FullScreen();
        redPerFrame = 1;
        maxHeigtPret =0;
        
        int maxDown = (int) (fullScr.height - mt1.getHeightTbl()-10) ;
     
        for (int i = 0; i < mt1.getTable().getColumnCount(); i++) {
            //Setovanje Frame-a ako postoji
            if(getFrame(i) !=null){SetujFrame(i);}
            
            maxWidth = (maxWidth>SirinaTxt[i]) ?  maxWidth :  SirinaTxt[i];        
            labels[i].setLocation(sP.x , sP.y);
            labels[i].setHorizontalAlignment(SwingConstants.RIGHT);
            
            if (boolFrame){fRframe.add(labels[i]);     
            }else{contentPane.add(labels[i]);}
            
            lB[i] = labels[i].getLocation();
            switch (getStaJe(i)) {
                case "CoBox": prikaziCoBox(i);break;
                default:      prikaziTxt(i)  ;break;
            }
            //Pomeranje sledeceg polja
            PomeriSledecePolje(i, maxWidth, krug);
            
            //Redefinisi pocetnu tacku Frame-a ako nije jos bilo frame-ova
            try{
                if (!imaFrame) sPf.setLocation(sPPoc.x,sP.y);
            }catch(Exception e4){}
            
            //Redefinisanje sirine i visine za zadnji frame ako postoji         
            if(boolFrame && i==mt1.getTable().getColumnCount()-1){
                fRframe.setSize((int)sirinaFrame, redPerFrame*getLabHeight()+5);               
            }
            //Kraj Strane - Prelazak u novu kolonu - OVO TREBA Testirati ako zatreba
            if (maxDown < sP.y){
                krug ++;                
                sP.setLocation(sPPoc.x + krug*(maxWidth + labels[i].getWidth()), sPPoc.y);
                maxWidth=0;
            }
        }         
    }    

    private void PomeriSledecePolje(int i, int maxWidth, int krug){
            try{
                switch(getGdeJe(i)){              
                    case "Right": //Pomeranje Desno
                        sP.setLocation(sP.x + SirinaTxt[i] + tekuciLbWidth[i], sP.y); break; 
                    case "Down": //Pomeranje dole tako da je x koordinata ista
                        sP.setLocation(sP.x , sP.y + getLabHeight()); redPerFrame++; break;                  
                    default: //Pomeranje prema dole u novi red 
                        sP.setLocation(sPPoc.x + krug*(maxWidth + labels[i].getWidth()), sP.y + getLabHeight()); redPerFrame++; break;
                }
            //Pomeranje prema dole u novi red ako je getGdeJe(i) =null                
            }catch(Exception e){
                sP.setLocation(sPPoc.x + krug*(maxWidth + labels[i].getWidth()), sP.y + getLabHeight()); redPerFrame++;
            }        
    }
    private int SirinaLabel(String StaWidth){
        int sirina;
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        Font font = new Font(labels[0].getName(), Font.PLAIN, 12);
        sirina = (int) font.getStringBounds(StaWidth, frc).getWidth();
        return sirina;    
    }
    private JLabel[] kreirajLabels() {
        int maxLenghtLab = 10;
        for (int i = 0; i < mt1.getTable().getColumnCount(); i++) {
            labels[i] = new JLabel(mt1.getTable().getColumnName(i) + " :");
            maxLenghtLab = (maxLenghtLab > mt1.getTable().getColumnName(i).length()) ? maxLenghtLab : mt1.getTable().getColumnName(i).length();
        }
        int sirina;
        String text = "A";
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        Font font = new Font(labels[0].getName(), Font.PLAIN, 12);
        sirina = maxLenghtLab * (int) (font.getStringBounds(text, frc).getWidth());
        setLabWidth(sirina);
        return labels;
    }

    //Text box posle Labela 
    private void prikaziTxt(final int cell) throws ParseException, SQLException {
        texts[cell] = new JFormattedTextField();
        texts[cell].setSize(mt1.getTable().getColumnModel().getColumn(cell).getPreferredWidth(), getLabHeight());
        texts[cell].setLocation(lB[cell].x + labels[cell].getWidth(), lB[cell].y);
        texts[cell].setDocument(new OgrJTextMaxDuzina(mt1.getMaxLength(cell)));

        texts[cell].setEnabled(false);
        Boje boje = new Boje();
        //Boja teksta u TeksField-u kada je disabled        
        Color c = boje.getColorTextDisabled();
        texts[cell].setDisabledTextColor(c);
        //Boja pozadine u TeksField-u kada je disabled
        c = boje.getColorBackgroundDisabled();
        texts[cell].setBackground(c);
        //Osnovna boja teksta u TextFIeld-u
        c = boje.getColorFontDefText();
        texts[cell].setForeground(c);

        //Ako je text Box Datum onda maskirano polje
        String tipVar = metaData.getColumnTypeName(cell + 1);
        if (tipVar == "DATE") {
            Formati constM = new Formati();
            MaskFormatter dateMask = new MaskFormatter(constM.getMaskedFormat());
            dateMask.install(texts[cell]);
        };
        //Action Listener na text box 
        texts[cell].addKeyListener(this);

        //Setovanje alignmenta text fielda na osnovno
        Alignment aa = new Alignment();
        try{
            aa.setTxtAlignment(texts[cell], mt1.getTable().getColumnModel().getColumn(cell).getCellRenderer());
        }catch(Exception e){}
        //contentPane.add(texts[cell]);
        if (boolFrame){fRframe.add(texts[cell]);     
        }else{contentPane.add(texts[cell]);}        
    }

    //Combo box posle Labela 
    private void prikaziCoBox(final int cell) throws ParseException, SQLException {
        combos[cell] = new JComboBox();
        combos[cell].setSize(mt1.getTable().getColumnModel().getColumn(cell).getPreferredWidth(), getLabHeight());
        combos[cell].setLocation(lB[cell].x + labels[cell].getWidth(), lB[cell].y);

        combos[cell].setEnabled(false);
        combos[cell].setEditable(true);
        Boje boje = new Boje();
        Color c = boje.getColorTextDisabled();
        ((JTextField) combos[cell].getEditor().getEditorComponent()).setDisabledTextColor(c);
        String[] kojiNapuniCombo = {"", ""};
        try {
            String[] elementi = getVrednostStaJe(cell).split("@@");
            for (int i = 0; i < elementi.length; i++) {
                combos[cell].addItem(elementi[i]);
            }
        } catch (Exception e) {
        }
        
        //Action Listener na text box 
        combos[cell].addKeyListener(this);
        //contentPane.add(combos[cell]);
        if (boolFrame){fRframe.add(combos[cell]);     
        }else{contentPane.add(combos[cell]);}        
    }
    // Dovde Prikazi na formi TextBox, Label, ComboBox , Button... ---------------------------------------------------------------------- 

    //Ako se izvrsi neka akcija (ActionListener) na poljima text, combobox, radiobutton, checkbox ...
    @Override
    public void keyPressed(KeyEvent e) {
        ActionListenerPoljaTbl actionListenerPoljaTbl = new ActionListenerPoljaTbl(this, mt1);
        try {
            actionListenerPoljaTbl.keyPressed(e, brokerDAO, KoZove, getKojiUpis());
        } catch (SQLException ex) {
            Logger.getLogger(PoljaIzTabeleDefinicija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PoljaIzTabeleDefinicija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Ako se ne zeli racunanje pri svakom unosu podataka izbrisati ovo ispod
        RacunajVrednost racunajVrednosti = new RacunajVrednost();
        try {
            String[] podaciZaUpis = racunajVrednosti.DisabledPolja(this, brokerDAO);
        } catch (Exception ex) {
            Logger.getLogger(PoljaIzTabeleDefinicija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public String[] getPodaciZaUpis() throws SQLException, ParseException {
        String[] podaciZaUpis = new String[mt1.getTable().getColumnCount()];
        for (int i = 0; i < mt1.getTable().getColumnCount(); i++) {
            switch (getStaJe(i)) {
                case "CoBox":
                    podaciZaUpis[i] = combos[i].getSelectedItem().toString();
                    break;
                default:
                    String tipVar = metaData.getColumnTypeName(i + 1);
                    if (tipVar == "DATE") {
                        Formati constM = new Formati();
                        String dd = texts[i].getText();

                        SimpleDateFormat dateTextField = new SimpleDateFormat(constM.getDateFormatText());
                        try {
                            dateTextField.setLenient(false);
                            Date myDate = dateTextField.parse(dd);
                            podaciZaUpis[i] = new SimpleDateFormat(constM.getDateFormatBaza()).format(myDate);
                        } catch (Exception e1) {
                            podaciZaUpis[i] = null;
                        }
                    } else {
                        podaciZaUpis[i] = texts[i].getText();
                    }
                    ;
                    break;
            }
        }
        return podaciZaUpis;
    }

    //Dimenzije labela koji stoji ispred txt boxa ili combo boxa
    private void setLabWidth(int widthLab) {
        this.widthLab = widthLab;
    }

    private int getLabWidth() {
        return widthLab;
    }

    private int getLabHeight() {
        return 25;
    }

    public void setKojiUpis(String kojiUpis) {
        this.kojiUpis = kojiUpis;
    }

    public String getKojiUpis() {
        return kojiUpis;
    }

    public ResultSetMetaData getMetaData() {
        return metaData;
    }
    public void setTexts(String texts, int i) {
        this.texts[i].setValue(texts);
    }
    public JFormattedTextField[] getTexts() {
        return texts;
    }

    public JComboBox[] getCombos() {
        return combos;
    }

    public String getStaJe(int i) {
        return staJe[i];
    }
    private void setStaJe(int i, String staJe) {
        this.staJe[i] = staJe;
    }
    //Vrednosti ako je Combo Box
    public String getVrednostStaJe(int i) {
        return vrednostStaJe[i];
    }
    private void setVrednostStaJe(int i, String vrednostStaJe) {
        this.vrednostStaJe[i] = vrednostStaJe;
    }
    //U istom redu kao i prethodno polje(Right) ili u novom redu sa istim pocetkom kao i prethodno polje u prethodnom redu (Down)
    //ili u novom redu (New ili blanko)
    public String getGdeJe(int i) {
        return gdeJe[i];
    }
    private void setGdeJe(int i, String gdeJe) {
        this.gdeJe[i] = gdeJe;
    }
    
    
    public String getFrame(int i) {
        return frame[i];
    }
    private boolean setFrame(int i, String frame) {
        this.frame[i] = frame;
        return true;
    }
    
    public List<Integer> getRbrFrame() {
        return rbrFrame;
    }
    private void setRbrFrame(int i) {
        this.rbrFrame.add(i);
    }    
    //Prikaz button Izmeni, Novi, Brisi, Kraj
    /*public void prikaziButtonOsnovno() throws ParseException, SQLException{
     String IspButton[] = getButtonNameOsnovno();
     buttons=new JButton[IspButton.length];         
     for (int i=0;i<IspButton.length;i++){
     buttons[i]=new JButton(IspButton[i]);      
     buttons[i].setSize(getButtWidth(), getButtHeight());
     buttons[i].setLocation(lB[i].x + (i + 1) * labels[i].getWidth(), MaxDown + 2 * labels[i].getHeight());
     contentPane.add(buttons[i]);
     switch(buttons[i].getText()){
     case "Novi":
     buttons[i].setEnabled(true);
     break;
     default:
     buttons[i].setEnabled(false); 
     break;
     }
     }    
     }
     private int getButtWidth(){
     return 80;
     }
     private int getButtHeight(){
     return 30;
     }
     private String[] getButtonNameOsnovno(){
     String[] butt = {"Izmeni", "Novi", "Brisi"};
     return butt;
     }
     private String[] getButtonNameUpisi(){
     String[] butt = {"Upisi", "Odustani"};
     return butt;
     }*/
}
/*class sPPret {
    private double x;
    private double y;

    public sPPret(double x, double y){
        this.x=x;
        this.y=y;
    }
}*/
