/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Listeneri;

import Forme.Konstante.FunkcijskiTasteri;
import Forme.Polja.Prikazi.PoljaIzTabeleDefinicija;
import Forme.Polja.Prikazi.PoljaIzTabeleNapuni;
import Forme.PopUpovi.TablePopUp;
import Forme.Tabele.MojaTabela;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Nebojsa
 */
public class ClickTbl{
    JMenuItem menuItemNovi;
    JMenuItem menuItemIzmeni;
    public void TableClick(final MojaTabela mt1, final PoljaIzTabeleDefinicija poljaIzTabele, final int[] kljucevi, final int[] poljaDisabled) {
        //MouseClick na tabelu ili up-down
        mt1.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                try {
                    PoljaIzTabeleNapuni poljaIzTabeleNapuni = new PoljaIzTabeleNapuni(mt1, poljaIzTabele);
                    poljaIzTabeleNapuni.Napuni();
        
                    mt1.getModel().setSelectRow(mt1.getTable().getSelectedRow());
                    mt1.getModel().setSelectCell(mt1.getTable().getSelectedColumn());
                } catch (Exception e) {
                }

            }
        }
        );

        mt1.getTable().addMouseListener(new MouseAdapter() {
            //Mouse double click            
            @Override
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2 && me.getButton() == MouseEvent.BUTTON1) {          
                    FunkcijskiTasteri ft = new FunkcijskiTasteri();
                    Robot robot = null;
                    try {robot = new Robot();
                    } catch (AWTException ex) {Logger.getLogger(TablePopUp.class.getName()).log(Level.SEVERE, null, ex);}
            
                    robot.keyPress(ft.getFtIspravi());
                }
                //Mouse right click 
                if ( me.getButton() == MouseEvent.BUTTON3){
                    TablePopUp tablePopUp = new TablePopUp(mt1, me, poljaIzTabele);
                    tablePopUp.rightClick();
                }
            }
        });          
    }
}
