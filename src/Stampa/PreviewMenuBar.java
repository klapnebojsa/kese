/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa;

import Forme.FormPrintPreview;
import Forme.Napuni.ComboBoxovi.NapuniCombo;
import Forme.Polja.Listeneri.ComboChangePagePercent;
import Forme.Polja.Listeneri.ComboChangedPageNo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

/**
 *
 * @author Nebojsa
 */
public class PreviewMenuBar extends JPanel {
    boolean prviPut;
    public FormPrintPreview formPrintPreview;
    public JLabel tekucaStrana;
    public JLabel tekuceUvecanje;
    public JComboBox procWidth;
    public JComboBox previewPage;
    
    private enum Actions {
        pageSetupButton,
        firstButton,
        previousButton,
        nextButton,
        lastButton,
        printButton
    } 
    
    public PreviewMenuBar(boolean prviPut, FormPrintPreview formPrintPreview){
       this.prviPut = prviPut;
       this.formPrintPreview = formPrintPreview;
    }
    
    @Override
    protected void paintComponent(Graphics g) { 
        if (prviPut){
            super.paintComponent(g);

            JButton pageSetupButton = new JButton("Page Setup");
            Font font = new Font(pageSetupButton.getName(), Font.PLAIN, 11); 
            pageSetupButton.setFont(font);
            add(pageSetupButton);
            pageSetupButton.setActionCommand(Actions.pageSetupButton.name());            
            pageSetupButton.addActionListener(formPrintPreview);
            
            JLabel prazno = new JLabel(); 
            Dimension d = new Dimension();
            d.setSize(40, prazno.getPreferredSize().height);
            prazno.setPreferredSize(d);
            add(prazno);            

            JButton firstButton = new JButton("<<< ");
            firstButton.setFont(font);
            add(firstButton);
            firstButton.setActionCommand(Actions.firstButton.name());            
            firstButton.addActionListener(formPrintPreview);
            
            JButton previousButton = new JButton("< ");
            previousButton.setFont(font);
            add(previousButton);
            previousButton.setActionCommand(Actions.previousButton.name());            
            previousButton.addActionListener(formPrintPreview);
            
            tekucaStrana = new JLabel("1");
            tekucaStrana.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
            //tekucaStrana.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5)); 
            tekucaStrana.setFont(new Font(tekucaStrana.getName(), Font.PLAIN, 12));
            tekucaStrana.setOpaque(true);
            Dimension d1 = new Dimension();
            d1.setSize(tekucaStrana.getPreferredSize().width+20, tekucaStrana.getPreferredSize().height);
            tekucaStrana.setPreferredSize(d1);
            tekucaStrana.setHorizontalAlignment(SwingConstants.CENTER);
            tekucaStrana.setForeground(Color.BLACK);
            //tekucaStrana.setBackground(prazno.getForeground());
            add(tekucaStrana);
            
            JButton nextButton = new JButton(" >");
            nextButton.setFont(font);
            add(nextButton);
            nextButton.setActionCommand(Actions.nextButton.name());            
            nextButton.addActionListener(formPrintPreview);            
            
            JButton lastButton = new JButton(" >>>");
            lastButton.setFont(font);
            add(lastButton);
            lastButton.setActionCommand(Actions.lastButton.name());            
            lastButton.addActionListener(formPrintPreview); 
            
            prazno = new JLabel(); 
            prazno.setPreferredSize(d);            
            add(prazno);      
            
            previewPage = new JComboBox();
            JLabel strLabel = new JLabel("Strana:");
            strLabel.setFont(font);
            add(strLabel);
            previewPage.setEditable(false);
            setPreviewPage();
            //Change listener za ComboBox
            ComboChangedPageNo comboChangedPageNo = new ComboChangedPageNo(this);            
            previewPage.addItemListener(comboChangedPageNo);
            add(previewPage);            

            prazno = new JLabel(); 
            prazno.setPreferredSize(d);            
            add(prazno);
            
            tekuceUvecanje = new JLabel("100 %");
            tekuceUvecanje.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
            tekuceUvecanje.setFont(new Font(tekuceUvecanje.getName(), Font.PLAIN, 12));
            tekucaStrana.setOpaque(true);
            d1 = new Dimension();
            d1.setSize(tekuceUvecanje.getPreferredSize().width+20, tekuceUvecanje.getPreferredSize().height);
            tekuceUvecanje.setPreferredSize(d1);
            tekuceUvecanje.setHorizontalAlignment(SwingConstants.CENTER);
            tekuceUvecanje.setForeground(Color.BLACK);
            add(tekuceUvecanje);
            
            procWidth = new JComboBox();
            ((JLabel)procWidth.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
            procWidth.setEditable(false);
            NapuniCombo napuniCombo = new NapuniCombo();
            String elProc = napuniCombo.procPreviewWidth();
            try {
                String[] elementi = elProc.split("@@");
                for (int i = 0; i < elementi.length; i++) procWidth.addItem(elementi[i]);
            } catch (Exception e) {} 
            //Change listener za ComboBox
            ComboChangePagePercent comboChange = new ComboChangePagePercent(this);            
            procWidth.addItemListener(comboChange);
            
            add(procWidth);
            
            prazno = new JLabel(); 
            prazno.setPreferredSize(d);            
            add(prazno);
                        
            JButton printButton = new JButton("Print");
            printButton.setFont(font);
            printButton.setPreferredSize(pageSetupButton.getPreferredSize());
            add(printButton);
            printButton.setActionCommand(Actions.printButton.name());            
            printButton.addActionListener(formPrintPreview); 
            
            prviPut=false;
        }
    }

    public void setPreviewPage(){
        previewPage.removeAllItems();
        for (int i = 1; i <= formPrintPreview.pagesPripremi.getNumPages(); i++) previewPage.addItem(i);        
    }
}
  
