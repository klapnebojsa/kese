/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme;

import Class.DAO.BrokerDAO;
import Forme.Konstante.FunkcijskiTasteri;
import Forme.Polja.Prikazi.PoljaIzTabeleDefinicija;
import Forme.Tabele.MojaTabela;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;

/**
 *
 * @author Nebojsa
 */
public class FormaKoja {
    MojaTabela mt1;
    FormForme formForme;
    BrokerDAO brokerDAO;
    
    public FormaKoja(MojaTabela mt1, FormForme formForme, BrokerDAO brokerDAO){
        this.mt1 = mt1;
        this.formForme = formForme;
        this.brokerDAO = brokerDAO;        
    }
    public void Margine(String sifraMargine, PoljaIzTabeleDefinicija poljaIzTabele) throws AWTException{
        formForme.scrPane.setSize(0, 0);
        formForme.scrPane.setLocation(0, 0);
        mt1.setWidthTbl(0);
        mt1.setHeightTbl(0);
            
        mt1.getModel().selectRow(0, 0);
        mt1.getTable().requestFocus();
                
        FunkcijskiTasteri ft = new FunkcijskiTasteri();
       
        Robot robot = new Robot();  
        if (mt1.getTable().getRowCount()>0){           
            robot.keyPress(ft.getFtIspravi());
            //mt1.getTable().setVisible(false);
        }else{
            robot.keyPress(ft.getFtNovi());
            //mt1.getTable().setVisible(false);            
            
            /*poljaIzTabele.getTexts()[0].setEnabled(true);
            poljaIzTabele.getTexts()[0].setText(sifraMargine);*/

        }
        formForme.setSize(new Dimension(800,400));
        formForme.setLocationRelativeTo(null);
        
        formForme.infolinija.obavestenje.setSize(formForme.getWidth(), formForme.infolinija.obavestenje.getHeight()/2);              
        formForme.infolinija.obavestenje.setLocation(0, formForme.getHeight()-formForme.infolinija.obavestenje.getHeight()*3);
                  
    }
}
