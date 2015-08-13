/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa;
/**
 *
 * @author Nebojsa
 */
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class PageNacrtajRunner1   extends JFrame {
    JComboBox klasa = new JComboBox();    
 
    public void OdrediKlasu(){
        /*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setTitle("Odaberite Klasu za kontrolu");
        klasa.addItem("");
        klasa.addItem("KeseProba");
        klasa.addItem("Partneri");       
        klasa.addItem("Vlasnik");
        klasa.addItem("KutijaProba");     

        setMinimumSize(new Dimension(350, 50));

        JButton button = new JButton("OK");
        JPanel topPanel = new JPanel();
        topPanel.add(button);
        topPanel.add(klasa); 
        add(topPanel);        

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //try {testCalculateAppriasal();
                //} catch (Exception ex) {Logger.getLogger(PageNacrtajTest.class.getName()).log(Level.SEVERE, null, ex);}
            }
        };        
        button.addActionListener(actionListener);        
        pack();
        setLocationRelativeTo(null);  
        setVisible(true);*/   
    }   
 
    public static void main(String[] args) {
        
        //OdrediKlasu();
        Result result = JUnitCore.runClasses(PageNacrtajTest1.class);
        for (Failure failure : result.getFailures()) {
           System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
