/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Ispis;

import Forme.Konstante.Formati;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nebojsa
 */
public class Formatiraj {

    String tipVar;
    int decVar;
    int duzinaVar;
    Object vrednostVar;
    String alignment;
    String kojaVrsta;
    boolean kljuc;

    public Object formatirajVar() {
        Object formiranaVar = "";
        alignment = "Left";

        DecimalFormat df;
        //Double tretira kao da ima 31 decimalu. Zato je mu stavljamo fizicki dve decimale        
        //if (tipVar=="DOUBLE"){decVar=2;}
        String nule = "";
        for (int i = 1; i <= decVar; i++) {
            nule += "0";
        }
        nule = decVar > 0 ? "#,##0." + nule : "#,##0";
        df = new DecimalFormat(nule);
        if (kljuc) {
            df = new DecimalFormat("#0");
        }
        String pomoc;
        switch (tipVar) {
            case "INT":
                int vV_int = Integer.valueOf(vrednostVar.toString().replace(",", ""));
                formiranaVar = df.format(vV_int);
                alignment = "Right";
                break;
            case "BIGINT":
                long vV_lng = Long.valueOf(vrednostVar.toString().replace(",", ""));
                formiranaVar = df.format(vV_lng);
                alignment = "Right";
                break;
            case "DOUBLE":
                String pomoc1 = vrednostVar.toString();
                pomoc = pomoc1.replace(",", "");
                double vV_dou = Double.parseDouble(pomoc);
                formiranaVar = df.format(vV_dou);
                alignment = "Right";
                break;
            case ("BIGDECIMAL"):
            case ("DECIMAL"):
                try {
                    BigDecimal vV_bde = new BigDecimal((String) vrednostVar.toString().replace(",", ""));
                    formiranaVar = df.format(vV_bde);
                } catch (Exception e) {
                    //if (vrednostVar!=null)formiranaVar=vrednostVar.toString();
                }
                alignment = "Right";
                break;
            case "VARCHAR":
                formiranaVar = vrednostVar;
                alignment = "Left";
                break;
            case "DATE":
                Formati constM = new Formati();
                //myDate - sistemski datum bez formata. Spreman za konverziju u koji se format zeli
                Date myDate = null;
                try {
                    SimpleDateFormat dateBaza = new SimpleDateFormat(constM.getDateFormatBaza());
                    myDate = dateBaza.parse(vrednostVar.toString());
                } catch (Exception e) {
                    try {
                        SimpleDateFormat dateTable = new SimpleDateFormat(constM.getDateFormatTable());
                        myDate = dateTable.parse(vrednostVar.toString());
                    } catch (Exception e1) {
                        try {
                            SimpleDateFormat dateText = new SimpleDateFormat(constM.getDateFormatText());
                            myDate = dateText.parse(vrednostVar.toString());
                        } catch (Exception e2) {
                        }
                    }
                }

                try {
                    switch (kojaVrsta) {
                        case "Tabela":
                            formiranaVar = new SimpleDateFormat(constM.getDateFormatTable()).format(myDate);
                            break;
                        case "TextBox":
                            formiranaVar = new SimpleDateFormat(constM.getDateFormatText()).format(myDate);
                            break;
                    }
                } catch (Exception e3) {
                    formiranaVar = vrednostVar.toString();
                }
                alignment = "Center";
                break;
        }

        return formiranaVar;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setTipVar(String tipVar) {
        this.tipVar = tipVar;
    }

    public void setDecVar(int decVar) {
        this.decVar = decVar;
    }

    public void setDuzinaVar(int duzinaVar) {
        this.duzinaVar = duzinaVar;
    }

    public void setVrednostVar(Object vrednostVar) {
        this.vrednostVar = vrednostVar;
    }

    public void setKojaVrsta(String kojaVrsta) {
        this.kojaVrsta = kojaVrsta;
    }

    public void setKljuc(boolean kljuc) {
        this.kljuc = kljuc;
    }
}
