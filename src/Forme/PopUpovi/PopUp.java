/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.PopUpovi;

import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Nebojsa
 */
public class PopUp {

    String text;
    Frame frame;

    public PopUp(Frame frame, String text) {
        this.text = text;
        this.frame = frame;
    }

    // Da/Ne
    //Vraca (0 za Da) i (1 za Ne)
    public int DaNe() {
        Object[] options = {"Da", "Ne"};
        int n = JOptionPane.showOptionDialog(
                frame, //Kojem Frame-u pripada
                text + "\n \n", //Pitanje
                frame.getTitle(), //Opis u zaglavlju         
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //Icon
                options,
                options[0]);
        return n;
    }

    public void Ok() {
        JOptionPane.showMessageDialog(
                frame,
                text + "\n \n",
                frame.getTitle(),
                JOptionPane.INFORMATION_MESSAGE);
    }
}
