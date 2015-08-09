/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme;

import Forme.Konstante.Mere;
import Forme.Tabele.MojaTabela;
import JUnitTestPackage.JUnitCitajVector;
import JUnitTestPackage.JUnitUpisiVector;
import Sistem.OsnovneDefinicije.RezolucijaEkrana;
import Stampa.PagesPripremi;
import Stampa.Podaci.PoljeZaStampu;
import Stampa.PreviewMenuBar;
import Stampa.Pripremi.PripremiFooter;
import Stampa.Pripremi.PripremiHeader;
import Stampa.Pripremi.PripremiKraj;
import Stampa.Pripremi.PripremiNaslov;
import Stampa.Pripremi.PripremiSve;
import Stampa.Pripremi.PripremiTabelu;
import Stampa.Pripremi.PripremiUvod;
import Stampa.Pripremi.PripremiZakljucak;
import Stampa.StampaSetujPage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
/**
 * @author Nebojsa
 */
public class FormPrintPreview extends JFrame implements ActionListener{
    public MojaTabela mt1;
    public FormForme koZove;
    boolean ispravanPrikaz = true;
    public PageFormat pageFormat;
    List mojeMargine;
    public PagesPripremi pagesPripremi;
    public PreviewMenuBar stampaMenuBar;
    PrinterJob pj;
    public StampaSetujPage stampaSetujPage;
   
    public Vector headerLineVectorAll;      //Header (na svakoj strani)
    public Vector naslovLineVectorAll;      //Naslov (samo na prvoj strani)
    public Vector uvodLineVectorAll;        //Uvod na vrhu strane(na svakoj strani)
    public Vector tableHeaderLineVector;    //ZaglavljeTabele
    public Vector tableMedjuZbirVector;     //MedjuZbir u tabeli (donos i prenos). Samo oblik. Vrednosti se dodaju dinamicki
    public Vector kojaPoljaMedjuZbirVector; //MedjuZbir u tabeli (donos i prenos). Samo oblik. Vrednosti se dodaju dinamicki    
    public Vector tableLineVectorAll;       //Tabela ili podaci za izvestaj
    public Vector zakljucakLineVectorAll;   //Zakljucak na dnu (na svakoj strani)
    public Vector krajLineVectorAll;        //Kraj na kraju zadnje strane
    public Vector footerLineVectorAll;      //Footer (na svakoj strani)

    public PripremiHeader pripremiHeader; 
    public PripremiNaslov pripremiNaslov;
    public PripremiUvod pripremiUvod;
    //Pripremi tabelu moze ds bude priprema tabele ako je Tabelarni prikaz ali i ne mora ako je potreban neki drugi oblik stampe
    public PripremiTabelu pripremiTabelu;
    
    public PripremiZakljucak pripremiZakljucak;
    public PripremiKraj pripremiKraj;    
    public PripremiFooter pripremiFooter;
    
    public String oznakaStampe;
    public String kojaStampa;
    public FormPrintPreview (MojaTabela mt1, FormForme koZove, String oznakaStampe, String kojaStampa){
        super();
        this.mt1 = mt1;
        this.koZove = koZove;
        this.oznakaStampe = oznakaStampe;
        this.kojaStampa = kojaStampa;
    }
    public void Prikazi() throws IOException, Exception{
        koZove.setEnabled(false);

        RezolucijaEkrana re = new RezolucijaEkrana(); //rezolucija
        Dimension fullScr = re.FullScreenMaloManje();   
        setSize(fullScr);                             //Velicina Forme (Cela Strana)
        setLocationRelativeTo(null);  
        
        pj = PrinterJob.getPrinterJob();      
        pageFormat = pj.defaultPage();
        stampaSetujPage = new StampaSetujPage();                          //Postavljanje vrednosti Margina u PrinterJob.pageDialog iz BAZE
        stampaSetujPage.SetujPageSetup(pageFormat, pj, this, koZove);

        //Priprema svih delova za Stampu - kda se bude htela stampa za neku formu a ne za tabelu u ovom delu dodati
        PripremiSve pripremiSve = new PripremiSve();
        pripremiSve.Priprema(this);

        //Preview Strane
        pagesPripremi = new PagesPripremi(headerLineVectorAll, naslovLineVectorAll, uvodLineVectorAll, 
                                    zakljucakLineVectorAll, krajLineVectorAll, footerLineVectorAll,
                                    tableLineVectorAll, tableHeaderLineVector, tableMedjuZbirVector, kojaPoljaMedjuZbirVector, pageFormat, this, mt1);
        
        ////////PODACI ZA JUNIT *******************************************************************   
        //********** UPIS U JUnit FAJL
        JUnitUpisiVector jUnitUpisiStampa = new JUnitUpisiVector();
        jUnitUpisiStampa.Upisi(koZove.brokerDAO.a.ImeKlase(), pagesPripremi);
        
        /*//********** CITANJE IZ JUnit FAJLA             
        JUnitCitajVector jUnitCitajStampa = new JUnitCitajVector();
        Vector productsFromFile = new Vector((Vector) jUnitCitajStampa.Citaj(koZove.brokerDAO.a.ImeKlase()));*/                
        //////// ************************************************************************************     
        
        JScrollPane scrollP= new JScrollPane(pagesPripremi);
        scrollP.setWheelScrollingEnabled(true);
        scrollP.getVerticalScrollBar().setUnitIncrement(40);
        scrollP.getHorizontalScrollBar().setUnitIncrement(40);  
        add(scrollP, BorderLayout.CENTER);

        //Button u vrhu strane - Stampa, prethodni, sledeci
        stampaMenuBar = new PreviewMenuBar(true, this);
        stampaMenuBar.setOpaque(false);
        add(stampaMenuBar, BorderLayout.NORTH);
        setVisible(true);       
        
        //Listner-i ------------------------------------------------------------------------------------------ 
        // X-Za zatvaranje forme
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                koZove.setEnabled(true);
            }
        });
    }   
    public void showTitle(PagesPripremi pagesPripremi) {
        int currentPage = pagesPripremi.getTrenutnatPage() + 1;
        int numPages = pagesPripremi.getNumPages();
        setTitle("Print Preview - " + koZove.getOpisForme() +  " strana " + currentPage + " od " + numPages);
        try{stampaMenuBar.tekucaStrana.setText("" + currentPage);
        }catch(Exception e){} 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String a = e.getActionCommand();
        PrinterJob pj = PrinterJob.getPrinterJob();        
        switch (a){
            //Izbor Formata Strane            
            case "pageSetupButton":
                Mere mere = new Mere();
                Double preracun = mere.getMmPageFormat() / 10;
                pageFormat = pj.pageDialog(pageFormat);
                //Setovanje koordinata, orijentacije ... - POLJA IZ MARGINA            
                stampaSetujPage.setMLeft((int)(pageFormat.getImageableX() / preracun));
                stampaSetujPage.setMRight((int)((pageFormat.getWidth() - pageFormat.getImageableX() - pageFormat.getImageableWidth() + 0.5) / preracun));                 
                stampaSetujPage.setMTop((int)(pageFormat.getImageableY() / preracun));
                stampaSetujPage.setMDown((int)((pageFormat.getHeight() - pageFormat.getImageableY() - pageFormat.getImageableHeight() + 0.5) / preracun));
               
                PripremiSve pripremiSve = new PripremiSve();
                pripremiSve.Priprema(this);
                             
                pagesPripremi.setHeaderLineVectorAll(headerLineVectorAll);
                pagesPripremi.setNaslovLineVectorAll(naslovLineVectorAll);
                pagesPripremi.setUvodLineVectorAll(uvodLineVectorAll);                
                pagesPripremi.setZakljucakLineVectorAll(zakljucakLineVectorAll);
                pagesPripremi.setKrajLineVectorAll(krajLineVectorAll);                
                pagesPripremi.setFooterLineVectorAll(footerLineVectorAll);                
                pagesPripremi.setLineVectorAll(tableLineVectorAll);
                pagesPripremi.setTableHeaderLineVector(tableHeaderLineVector);
                pagesPripremi.setTableMedjuZbirVector(tableMedjuZbirVector);
                
                if (pagesPripremi != null) pagesPripremi.pageInit(pageFormat);

                ////////PODACI ZA JUNIT *******************************************************************     
                //********** UPIS U JUnit FAJL
                JUnitUpisiVector jUnitUpisiStampa = new JUnitUpisiVector();
                jUnitUpisiStampa.Upisi(koZove.brokerDAO.a.ImeKlase(), pagesPripremi);

               /*//********** CITANJE IZ JUnit FAJLA   
               JUnitCitajVector jUnitCitajStampa = new JUnitCitajVector();
               Vector productsFromFile = new Vector();
               try {productsFromFile = jUnitCitajStampa.Citaj(koZove.brokerDAO.a.ImeKlase());
               } catch (ClassNotFoundException ex) {Logger.getLogger(FormPrintPreview.class.getName()).log(Level.SEVERE, null, ex);}*/
               //////// *************************************************************************************     
               
                //Redefinisanje izbora preview-a strane
                stampaMenuBar.setPreviewPage();       
                break;
            case "nextButton":      if (pagesPripremi != null) pagesPripremi.sledecaStrana();    break;
            case "previousButton":  if (pagesPripremi != null) pagesPripremi.prethodnaStrana();  break;                
            case "lastButton":      if (pagesPripremi != null) pagesPripremi.poslednjaStrana();  break;                
            case "firstButton":     if (pagesPripremi != null) pagesPripremi.prvaStrana();       break; 
            case "printButton":
                PrintService[] stampaci = PrinterJob.lookupPrintServices();
                DocPrintJob docPrintJob = null;
                
                for (PrintService printer : stampaci){
                    if (printer.getName().equalsIgnoreCase(stampaSetujPage.getMStampac())) {docPrintJob = printer.createPrintJob(); break;}
                }
                try {pj.setPrintService(docPrintJob.getPrintService());
                } catch (PrinterException ex) { Logger.getLogger(FormPrintPreview.class.getName()).log(Level.SEVERE, null, ex);}

                pj.setPrintable(pagesPripremi, pageFormat);
                if (pj.printDialog()) {
                    pagesPripremi.setJestStampa(true);
                    pagesPripremi.setBrKopija(pj.getCopies());
                    try {pj.print();} catch (PrinterException e1) {}                    
                    pagesPripremi.setBrKopija(1);
                    pagesPripremi.setJestStampa(false);
                }
                break;                                  
            default:
                break;
        }
        if (pagesPripremi != null) showTitle(pagesPripremi);
    }
    public void setPageFormat(PageFormat pageFormat){
        this.pageFormat = pageFormat;
    }
    
}
