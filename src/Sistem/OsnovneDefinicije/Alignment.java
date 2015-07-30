/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistem.OsnovneDefinicije;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Nebojsa
 */
public class Alignment {

    DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();

    public DefaultTableCellRenderer getTblRight() {
        tableRenderer.setHorizontalAlignment(JLabel.RIGHT);
        return tableRenderer;
    }

    public DefaultTableCellRenderer getTblLeft() {
        tableRenderer.setHorizontalAlignment(JLabel.LEFT);
        return tableRenderer;
    }

    public DefaultTableCellRenderer getTblCenter() {
        tableRenderer.setHorizontalAlignment(JLabel.CENTER);
        return tableRenderer;
    }

    //Na osnovu alignmenta tabele odredjujemo alignment text boxa
    public void setTxtAlignment(JTextField tF, TableCellRenderer tR) {
        String k = tR.toString();
        if (k.toUpperCase().contains("horizontalAlignment=RIGHT".toUpperCase())) {
            tF.setHorizontalAlignment(JTextField.RIGHT);
        };
        if (k.toUpperCase().contains("horizontalAlignment=LEFT".toUpperCase())) {
            tF.setHorizontalAlignment(JTextField.LEFT);
        };
        if (k.toUpperCase().contains("horizontalAlignment=CENTER".toUpperCase())) {
            tF.setHorizontalAlignment(JTextField.CENTER);
        };

    }
    //Na osnovu alignmenta tabele odredjujemo alignment text boxa
    public String getCellAlignment(TableCellRenderer tR) {
        String cellAlignm = "Left";
        String k = tR.toString();
        
        if (k.toUpperCase().contains("horizontalAlignment=RIGHT".toUpperCase())) {
            cellAlignm = "Right";
        };
        if (k.toUpperCase().contains("horizontalAlignment=CENTER".toUpperCase())) {
            cellAlignm = "Center";
        };
        return cellAlignm;
    }
}
