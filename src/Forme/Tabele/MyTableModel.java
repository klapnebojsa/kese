/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Tabele;

import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nebojsa
 */
public class MyTableModel extends AbstractTableModel {

    //public String [] zaglTabele;
    public JTable table;
    public List data;
    public String[] columnNames;
    int selectRow;
    int selectCell;

    //Koji je tip varijable, koliko znakova ima i koliko decimala ima u polju 
    String[] tipVar;
    int[] decVar;
    int[] duzinaVar;

    public MyTableModel(String[] tipVar, int[] decVar, int[] duzinaVar) {
        this.tipVar = tipVar;
        this.decVar = decVar;
        this.duzinaVar = duzinaVar;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        List rowTabele = (List) data.get(row);
        Object elementTabele = rowTabele.get(col);
        return elementTabele;
    }

    public void setSelectRow(int selectRow) {
        this.selectRow = selectRow;
    }

    public int getSelectRow() {
        return selectRow;
    }

    public void setSelectCell(int selectCell) {
        this.selectCell = selectCell;
    }

    public int getSelectCell() {
        return selectCell;
    }

    public void selectRow(int i, int j) {
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionInterval(i, j);
    }
    //Ova metoda vraca vrednosti po deafolt tabeli tj cuva tabelu u izvornom obliku
    //Njoj pristupamo prek       texts[i].setText(mt1.table.getModel().getValueAt(viewRow, i).toString());
    //Trenutnim vrednostima u tabeli posle sorta ili pretrage pristupamo
    //                           texts[i].setText(mt1.table.getValueAt(viewRow, i).toString());

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    //Ovo treba ako zelimo da unosimo podatke direktno u tabelu
    public boolean isCellEditable(int row, int col) {
        if (col < 100) {
            return false;
        } else {
            return true;
        }
    }

    public void setValueAt(Object value, int row, int col) {
        //if (DEBUG) {
        System.out.println("Setovanje vrednost u " + row + "," + col
                + " do " + value
                + " (sa "
                + value.getClass() + ")");
            //}

        //data[row][col] = value;
        //if (DEBUG) {
        System.out.println("Nova vrednost:");
        printDebugData();
        //}
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                //System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
