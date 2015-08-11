/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa;

import Forme.FormPrintPreview;
import Forme.Konstante.Mere;
import Forme.Tabele.MojaTabela;
import Sistem.OsnovneDefinicije.RezolucijaEkrana;
import Stampa.Podaci.FontMetric;
import Stampa.Podaci.Konstante;
import Stampa.Podaci.PoljeZaStampu;
import Stampa.Preracunaj.DeoStampeVisina;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author Nebojsa
 */
public class PagesPripremi extends JComponent implements Printable, Serializable{ 
    public double p = 1;
    private int trenutniRbrStrane;
    public Vector pageVector;   
    private Vector tableLineVectorAll;
    private Vector headerLineVectorAll;
    private Vector naslovLineVectorAll;
    private Vector uvodLineVectorAll;
    private Vector zakljucakLineVectorAll;    
    private Vector krajLineVectorAll;
    private Vector footerLineVectorAll;
    private Vector tableHeaderLineVector;
    private Vector tableMedjuZbirVector;
    private Vector kojaPoljaMedjuZbirVector;
    
    String fName;
    int fStyle;
    private Font font;
    private int fontSize;
    private Dimension preferredSize;
    private Dimension prefSize;
    
    private Dimension ukupnoOsnovno;
    private Dimension ukupnoSize;
    
    private Dimension pocetakPage;
    private double sirinaPage;
    private double visinaPage;
    
    PageFormat pageFormat;
    FormPrintPreview formPrintPreview;
    boolean jesteStampa;
    MojaTabela mt1;
    private double visinaNovo;
    
    PageNacrtaj pageNacrtaj;
    Double preracun;
    int brKopija;

    public PagesPripremi(Vector headerLineVectorAll, Vector naslovLineVectorAll, Vector uvodLineVectorAll, 
                         Vector zakljucakLineVectorAll, Vector krajLineVectorAll, Vector footerLineVectorAll,
                         Vector tableLineVectorAll, Vector tableHeaderLineVector, Vector tableMedjuZbirVector, Vector kojaPoljaMedjuZbirVector,
                         PageFormat pageFormat, final FormPrintPreview formPrintPreview, MojaTabela mt1) throws IOException {
        super();
        this.setLayout(null);
        this.pageFormat=pageFormat;     
        this.formPrintPreview = formPrintPreview;
        this.mt1 = mt1;

        this.headerLineVectorAll = headerLineVectorAll;
        this.naslovLineVectorAll = naslovLineVectorAll;
        this.uvodLineVectorAll = uvodLineVectorAll;
        this.zakljucakLineVectorAll = zakljucakLineVectorAll;        
        this.krajLineVectorAll = krajLineVectorAll;
        this.footerLineVectorAll = footerLineVectorAll;
        
        this.tableLineVectorAll = tableLineVectorAll;
        this.tableHeaderLineVector = tableHeaderLineVector;
        this.tableMedjuZbirVector = tableMedjuZbirVector;
        this.kojaPoljaMedjuZbirVector = kojaPoljaMedjuZbirVector;
        double r = (int)formPrintPreview.stampaSetujPage.getMVelFonta() * p;
        fontSize = (int) r;
        fName = formPrintPreview.stampaSetujPage.getMFont().getFontName();
        fStyle = formPrintPreview.stampaSetujPage.getMFont().getStyle();      
        font = new Font(fName, fStyle, fontSize);
        
        Mere mere = new Mere();
        preracun = mere.getMmPageFormat() / 10;

        pageInit(pageFormat);
        formPrintPreview.showTitle(this);
      
        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                Konstante konstante = new Konstante();
                p = konstante.MouseStep(e, p);                
                int kk = (int)(100 * p);
                try{formPrintPreview.stampaMenuBar.tekuceUvecanje.setText(String.valueOf(kk) + " %");
                }catch(Exception e1){} 
                revalidate();
                repaint();  
            }
        });
    }

    public PagesPripremi() {
        super();        
    }
    public void pageInit(PageFormat pageFormat) { 
        double medjY = formPrintPreview.stampaSetujPage.getMMedjY();
        double medjYDw = formPrintPreview.stampaSetujPage.getMMedjYDw();
        double medjX = formPrintPreview.stampaSetujPage.getMMedjX();        
        trenutniRbrStrane = 0;
        double y = formPrintPreview.stampaSetujPage.getMTop();        
        pageVector = new Vector();
        Vector <PoljeZaStampu> line = new Vector();
        Vector pageXX = new Vector();
        
        DeoStampeVisina deoStampeVisina = new DeoStampeVisina();
        double visinaHead      = deoStampeVisina.Preracunaj(headerLineVectorAll, medjY + medjYDw);      //HEADER   - Odredjivanje visine
        double visinaNaslov    = deoStampeVisina.Preracunaj(naslovLineVectorAll, medjY + medjYDw);      //NASLOV   - Odredjivanje visine      
        double visinaUvod      = deoStampeVisina.Preracunaj(uvodLineVectorAll, medjY + medjYDw);        //UVOD     - Odredjivanje visine
        double visinaZakljucak = deoStampeVisina.Preracunaj(zakljucakLineVectorAll, medjY + medjYDw);   //ZAKLUCAK - Odredjivanje visine    
        double visinaFoot      = deoStampeVisina.Preracunaj(footerLineVectorAll, medjY + medjYDw);      //FOOTER   - Odredjivanje visine
        double visinaTblHead   = deoStampeVisina.Preracunaj(tableHeaderLineVector, medjY + medjYDw);    //FOOTER   - Odredjivanje visine        
        double visinaTblMedjZb = deoStampeVisina.Preracunaj(tableMedjuZbirVector, medjY + medjYDw);     //MedjuZbir- Odredjivanje visine        
        
        for (int i = 0; i < headerLineVectorAll.size(); i++) {pageXX.add((Vector)headerLineVectorAll.get(i));}    //Dodavanje Header-a na stranu u PageXX                 
        for (int i = 0; i < naslovLineVectorAll.size(); i++) {pageXX.add((Vector)naslovLineVectorAll.get(i));}    //Dodavanje Naslov-a na PRVU stranu u PageXX
        for (int i = 0; i < uvodLineVectorAll.size(); i++) {pageXX.add((Vector)uvodLineVectorAll.get(i));}        //Dodavanje Uvoda-a na PRVU stranu u PageXX
        for (int i = 0; i < tableHeaderLineVector.size(); i++){pageXX.add((Vector)tableHeaderLineVector.get(i));} //Dodavanje Table Headera na PRVU stranu u PageXX      

        //na prvoj strani se stampa naslov tako da i on ulazi u ukupnu visinu i jedan medjuzbir(na kraju)       
        double visinaPomocno = visinaHead + visinaFoot + visinaNaslov + visinaUvod + visinaZakljucak + visinaTblHead + visinaTblMedjZb;        
        
        //TABELA - Stampce se ili ona ili nesto drugo u zavisnosti od oznake stampe
        //Ne trebaju kontrole jer ce stici prazna ako je stampa onog drugog i obrnuto
        double[] fff = new double[kojaPoljaMedjuZbirVector.size()];
        Vector tableMedjuZbirVectorAll = new Vector();        
        for (int iR = 0; iR < tableLineVectorAll.size(); iR++) {           
            line = (Vector)tableLineVectorAll.get(iR);
            double visina = line.elementAt(line.size()-1).getMaxVisinaFonta();
            y += visina + medjY + medjYDw;
            
            //MEDJUZBIROVI
            Vector noviLineMZ = new Vector();
            try {
                Vector <PoljeZaStampu> lineMZ = (Vector)tableMedjuZbirVector.get(0);
                RedefinisiMedjuZbir redefinisiMedjuZbir = new RedefinisiMedjuZbir();                
                for (int i = 0; i < kojaPoljaMedjuZbirVector.size(); i++){
                    noviLineMZ.addElement(redefinisiMedjuZbir.redefinisi(formPrintPreview, lineMZ, line, fff, i, (int)formPrintPreview.stampaSetujPage.getMVelFonta(), (boolean)kojaPoljaMedjuZbirVector.elementAt(i), medjX));
                }                
            }catch(Exception e){ }
            if (noviLineMZ.size()!=0)tableMedjuZbirVectorAll.addElement(noviLineMZ);
            
            //Kontrola kada je nova strana
            if (y + (visina+medjY+medjYDw) + visinaPomocno > pageFormat.getImageableHeight()) {
                try{pageXX.add((Vector)tableMedjuZbirVectorAll.get(tableMedjuZbirVectorAll.size()-2));}catch(Exception e){}    //Dodavanje MedjuZbira(prenos)- nova strana                
                for (int i = 0; i < zakljucakLineVectorAll.size(); i++) {pageXX.add((Vector)zakljucakLineVectorAll.get(i));}   //Dodavanje Zakljucka-a - stara satrana          
                for (int i = 0; i < footerLineVectorAll.size(); i++){pageXX.add((Vector)footerLineVectorAll.get(i));}          //Dodavanje Footer-a    - stara strana
                y = visina + medjY + medjYDw + formPrintPreview.stampaSetujPage.getMTop();
                pageVector.addElement(pageXX);
                //NOVA STRANA
                pageXX = new Vector();
                for (int i = 0; i < headerLineVectorAll.size(); i++){pageXX.add((Vector)headerLineVectorAll.get(i));}          //Dodavanje Header-a         - nova strana
                for (int i = 0; i < uvodLineVectorAll.size(); i++) {pageXX.add((Vector)uvodLineVectorAll.get(i));}             //Dodavanje Uvoda-a          - nova strana
                for (int i = 0; i < tableHeaderLineVector.size(); i++){pageXX.add((Vector)tableHeaderLineVector.get(i));}      //Dodavanje Table Header-a   - nova strana              
                try{pageXX.add((Vector)tableMedjuZbirVectorAll.get(tableMedjuZbirVectorAll.size()-2));}catch(Exception e){}    //Dodavanje MedjuZbira(donos)- nova strana                 
                visinaPomocno = visinaHead + visinaFoot + visinaUvod + visinaZakljucak + visinaTblHead + 2*visinaTblMedjZb; //Na svim ostalim stranama osim na prvoj se ne stampa naslov tako da on ne ulazi u ukupnu sirinu
            }           
            pageXX.addElement(line);            
        }
        try{pageXX.add((Vector)tableMedjuZbirVectorAll.get(tableMedjuZbirVectorAll.size()-1));}catch(Exception e){}         //Dodavanje MedjuZbira(ukupno)- nova strana    
        //DOVDE TABELA
        //Zadnja Strana       
        for (int i = 0; i < zakljucakLineVectorAll.size(); i++) {pageXX.add((Vector)zakljucakLineVectorAll.get(i));}      //Dodavanje Zakljucka-a  - zadnja strana    
        //KONTROLA ako TEXT KRAJA prelazi na sledecu stranu. TEXT KRAJ-a moze da ima vise linija i da predje na novu stranu.Zato se za kraj vrsi kontrola kao i za tabelu ili ono drugo
        //tj dodaju se header i footet na novoj strani ukoliko on prelazi na novu strani ali se ne dodaju uvod i zakljucak i normalno tabela jer je vec odstsampana        
        if (pageXX.size() > 0) {
            for (int iR = 0; iR < krajLineVectorAll.size(); iR++) {                    //Kraj - Dodavanje na kraj dokumenta   
                line = (Vector)krajLineVectorAll.get(iR);
                double visina = line.elementAt(line.size()-1).getMaxVisinaFonta();
                y += visina + medjY + medjYDw;
                if (y + (visina+medjY+medjYDw) + visinaPomocno > pageFormat.getImageableHeight()) {                     //Kontrola kada je nova strana
                    for (int i = 0; i < footerLineVectorAll.size(); i++){pageXX.add((Vector)footerLineVectorAll.get(i));}   //Dodavanje Footer-a
                    y = visina + medjY + medjYDw + formPrintPreview.stampaSetujPage.getMTop();                                    
                    pageVector.addElement(pageXX);
                    //NOVA STRANA - samo ako text KRAJA prelazi na sledecu stranu
                    pageXX = new Vector();
                    for (int i = 0; i < headerLineVectorAll.size(); i++){pageXX.add((Vector)headerLineVectorAll.get(i));}   //Dodavanje Header-a             
                    visinaPomocno = visinaHead + visinaFoot + visinaZakljucak + visinaTblHead;                              //Na svim ostalim stranama osim na prvoj se ne stampa naslov tako da on ne ulazi u ukupnu sirinu. Na ovoj strani se ne stampa ni uvod
                }
                pageXX.addElement(line);            
            } 
            for (int i = 0; i < footerLineVectorAll.size(); i++){pageXX.add((Vector)footerLineVectorAll.get(i));}            //Dodavanje Footer-a na zadnjoj strani
            pageVector.addElement(pageXX);
        }              
        prefSize = new Dimension((int) pageFormat.getImageableWidth(), (int) pageFormat.getImageableHeight());
        preferredSize = (new Dimension(prefSize.height, (int)prefSize.width));        
        revalidate();
        repaint();
    }

    public void paintComponent(Graphics g){
        pocetakPage = new Dimension();
        g.setFont(new Font(fName, fStyle, fontSize));        
        FontMetric fontMetric = new FontMetric(new Font(fName, fStyle, (int)formPrintPreview.stampaSetujPage.getMVelFonta()));
        double visinaPrethodno = fontMetric.getVisinaFonta();
        
        sirinaPage = prefSize.width + formPrintPreview.stampaSetujPage.getMLeft() + formPrintPreview.stampaSetujPage.getMRight();
        visinaPage = prefSize.height + formPrintPreview.stampaSetujPage.getMTop() + formPrintPreview.stampaSetujPage.getMDown();
   
        setPreferredSize(new Dimension((int)(prefSize.width*1.2*p), (int)(prefSize.height*1.2*p)));
        ukupnoOsnovno = new Dimension((int)sirinaPage, (int)visinaPage);
        double resizeP;
        if (getJesteStampa()){
            //STAMPA
            pocetakPage.width = 0; pocetakPage.height = 0;
            fontSize = (int)formPrintPreview.stampaSetujPage.getMVelFonta();
            resizeP = 1;
        }else{
            //PREVIEW
            RezolucijaEkrana re = new RezolucijaEkrana();
            Dimension fullScr = re.FullScreen();
            fontSize = (int) ((int)formPrintPreview.stampaSetujPage.getMVelFonta() * p + 0.5); 
            
            g.setFont(new Font(fName, fStyle, fontSize)); 
            fontMetric = new FontMetric(new Font(fName, fStyle, fontSize));
            visinaNovo = fontMetric.getVisinaFonta();
            
            //Odredjivanje koefcijenta povecanja/smanjenja strane u odnosu na visinu reda teksta
            double p = visinaNovo / visinaPrethodno;
            sirinaPage = sirinaPage * p;
            visinaPage = visinaPage * p;  
            if (getPreferredSize().width > fullScr.width){ pocetakPage.width = 50;                  
            }else{ pocetakPage.width = (fullScr.width - (int)sirinaPage) / 2; }
          
            pocetakPage.height = 30;
            brKopija=1;
            resizeP = p;
        }
        ukupnoSize = (new Dimension((int)sirinaPage, (int)visinaPage));        
        pageNacrtaj = new PageNacrtaj(this, resizeP);
        try {pageNacrtaj.Prikazi(pageVector, g);
        } catch (Exception ex) {Logger.getLogger(PagesPripremi.class.getName()).log(Level.SEVERE, null, ex);}
    }    
    
    public int print(Graphics g, PageFormat pageFormat, int rbrStrane) {
        if (rbrStrane >= pageVector.size()) return NO_SUCH_PAGE;
        int savedPage = trenutniRbrStrane;
        trenutniRbrStrane = rbrStrane;
        Graphics2D g2D = (Graphics2D) g;
        g2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        paint(g2D);
        trenutniRbrStrane = savedPage;
        return PAGE_EXISTS;
    }
    
    public void setPocetakPage(Dimension pocetakPage){this.pocetakPage = pocetakPage;}
    public Dimension getPocetakPage(){   return pocetakPage;   }
    public void setFont(Font font){      this.font = font;}
    public Font getFont(){               return font;  } 
    //public Dimension getPreferredSize() {return preferredSize;   }
    public Dimension getUkupnoOsnovno() {return ukupnoOsnovno;   }
    public Dimension getUkupnoSize() {   return ukupnoSize;    }
    public int getTrenutnatPage() {      return trenutniRbrStrane;    }
    public int getNumPages() {           return pageVector.size(); }
    public void sledecaStrana() {
        if (trenutniRbrStrane < pageVector.size() - 1) trenutniRbrStrane++;
        repaint();
    }
    public void prethodnaStrana() {
        if (trenutniRbrStrane > 0) trenutniRbrStrane--;
        repaint();
    }
    public void poslednjaStrana() {
        trenutniRbrStrane=pageVector.size() - 1;
        repaint();
    }
    public void prvaStrana() {
        trenutniRbrStrane=0;
        repaint();
    }
    public void ovaStrana(int brStr) {
        trenutniRbrStrane=brStr-1;
        repaint();
    }    
    public void setJestStampa(boolean jesteStampa){ this.jesteStampa = jesteStampa; }
    public boolean getJesteStampa(){  return jesteStampa; }
    public void setBrKopija(int brKopija){this.brKopija = brKopija;  }
    
    public void setHeaderLineVectorAll(Vector <PoljeZaStampu> headerLineVectorAll){
        this.headerLineVectorAll = headerLineVectorAll;
    }
    public void setNaslovLineVectorAll(Vector <PoljeZaStampu> naslovLineVectorAll){
        this.naslovLineVectorAll = naslovLineVectorAll;
    }    
    public void setUvodLineVectorAll(Vector <PoljeZaStampu> uvodLineVectorAll){
        this.uvodLineVectorAll = uvodLineVectorAll;
    }
    public void setZakljucakLineVectorAll(Vector <PoljeZaStampu> zakljucakLineVectorAll){
        this.zakljucakLineVectorAll = zakljucakLineVectorAll;
    }    
    public void setKrajLineVectorAll(Vector <PoljeZaStampu> krajLineVectorAll){
        this.krajLineVectorAll = krajLineVectorAll;
    }    
    public void setFooterLineVectorAll(Vector <PoljeZaStampu> footerLineVectorAll){
        this.footerLineVectorAll = footerLineVectorAll;
    }
    public void setLineVectorAll(Vector <PoljeZaStampu> tableLineVector){
        this.tableLineVectorAll = tableLineVector;
    }
    
    public void setTableHeaderLineVector(Vector <PoljeZaStampu> tableHeaderLineVector){
        this.tableHeaderLineVector = tableHeaderLineVector;
    }
    public void setTableMedjuZbirVector(Vector <PoljeZaStampu> tableMedjuZbirVector){
        this.tableMedjuZbirVector = tableMedjuZbirVector;
    }    
  }  