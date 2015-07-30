/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.AutoSeteri;

import Class.Apstraktne.AbstractDAO;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Forme.Konstante.Formati;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nebojsa
 */
public class SetKlaseBaze {

    public boolean setPodatkeKlaseBaze(String[] podaciZaUpis, ResultSetMetaData metaData, String sourceClass, String className,
            String[] elementi, AbstractDAO abstractDAO, boolean SetAutoIncrement) throws ParseException, IllegalAccessException, InvocationTargetException, SQLException {
        boolean setPodatke = true;
        int BrPolja = podaciZaUpis.length;
        String[] tipVar = new String[BrPolja];
        int[] decVar = new int[BrPolja];
        int[] duzinaVar = new int[BrPolja];

        Class c = null;
        try {
            c = Class.forName(sourceClass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(className).log(Level.SEVERE, null, ex);
        }

        String methodName[] = new String[BrPolja];
        for (int i = 0; i < BrPolja; i++) {
            methodName[i] = "set" + elementi[i];
        }
        try {
            for (int i = 0; i < BrPolja; i++) {
                tipVar[i] = metaData.getColumnTypeName(i + 1);
                decVar[i] = metaData.getScale(i + 1);
                duzinaVar[i] = metaData.getPrecision(i + 1);
            }
        } catch (SQLException ex) {
            setPodatke = false;
            Logger.getLogger(className).log(Level.SEVERE, null, ex);
        }

        Method m = null;
        for (int i = 0; i < BrPolja; i++) {
            if (!metaData.isAutoIncrement(i + 1) || SetAutoIncrement) {
                switch (tipVar[i]) {
                    case "BIGINT":
                        try {
                            m = c.getDeclaredMethod(methodName[i], BigInteger.class);
                        } catch (NoSuchMethodException | SecurityException ex) {
                            setPodatke = false;
                            Logger.getLogger(sourceClass).log(Level.SEVERE, null, ex);
                        }
                        try {
                            podaciZaUpis[i] = podaciZaUpis[i].replace(",", "");
                            BigInteger x = null;
                            try {
                                x = BigInteger.valueOf(Long.parseLong(podaciZaUpis[i]));
                            } catch (Exception e) {
                            }
                            m.invoke(abstractDAO, x);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            setPodatke = false;
                            Logger.getLogger(sourceClass).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "VARCHAR":
                        try {
                            m = c.getDeclaredMethod(methodName[i], String.class);
                        } catch (NoSuchMethodException | SecurityException ex) {
                            setPodatke = false;
                            Logger.getLogger(sourceClass).log(Level.SEVERE, null, ex);
                        }
                        try {
                            m.invoke(abstractDAO, podaciZaUpis[i]);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            setPodatke = false;
                            Logger.getLogger(sourceClass).log(Level.SEVERE, null, ex);
                        }
                        break;
                    //
                    case "INT":
                        try {
                            m = c.getDeclaredMethod(methodName[i], Integer.class);
                        } catch (NoSuchMethodException | SecurityException ex) {
                            setPodatke = false;
                            Logger.getLogger(sourceClass).log(Level.SEVERE, null, ex);
                        }
                        try {
                            podaciZaUpis[i] = podaciZaUpis[i].replace(",", "");
                            //Integer x = Integer.parseInt(podaciZaUpis[i]);                            

                            Integer x = null;
                            try {
                                x = Integer.parseInt(podaciZaUpis[i]);
                            } catch (Exception e) {
                            }
                            m.invoke(abstractDAO, x);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            setPodatke = false;
                            Logger.getLogger(sourceClass).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "DOUBLE":
                        try {
                            m = c.getDeclaredMethod(methodName[i], Double.class);
                        } catch (NoSuchMethodException | SecurityException ex) {
                            setPodatke = false;
                            Logger.getLogger(sourceClass).log(Level.SEVERE, null, ex);
                        }
                        try {
                            podaciZaUpis[i] = podaciZaUpis[i].replace(",", "");
                            //Double x = Double.valueOf(podaciZaUpis[i]);

                            Double x = null;
                            try {
                                x = Double.valueOf(podaciZaUpis[i]);
                            } catch (Exception e) {}
                            m.invoke(abstractDAO, x);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            setPodatke = false;
                            Logger.getLogger(sourceClass).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "DATE":
                        try {
                            m = c.getDeclaredMethod(methodName[i], Date.class);
                        } catch (NoSuchMethodException | SecurityException ex) {
                            setPodatke = false;
                            Logger.getLogger(sourceClass).log(Level.SEVERE, null, ex);
                        }
                        try {
                            Formati constM = new Formati();
                            SimpleDateFormat dateBaza = new SimpleDateFormat(constM.getDateFormatBaza());

                            //Date x = dateBaza.parse(podaciZaUpis[i]);
                            Date x = null;
                            try {
                                x = dateBaza.parse(podaciZaUpis[i]);
                            } catch (Exception e) {
                            }
                            m.invoke(abstractDAO, x);
                        } catch (IllegalArgumentException ex) {
                            setPodatke = false;
                            Logger.getLogger(sourceClass).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "DECIMAL":
                        try {
                            m = c.getDeclaredMethod(methodName[i], BigDecimal.class);
                        } catch (NoSuchMethodException | SecurityException ex) {
                            setPodatke = false;
                            Logger.getLogger(sourceClass).log(Level.SEVERE, null, ex);
                        }

                        try {
                            //DecimalFormat simboli = new DecimalFormat();
                            //simboli.setGroupingSeparator(',');
                            //simboli.setDecimalSeparator('.');
                            String format = "#,##0.00";
                            DecimalFormat decimalFormat = new DecimalFormat(format);
                            decimalFormat.setParseBigDecimal(true);

                            //BigDecimal x = (BigDecimal) decimalFormat.parse(podaciZaUpis[i]);
                            BigDecimal x = null;
                            try {
                                //Mora i double zato sto se kroz decimal provuce ako ide prvo cifra pa onda slova
                                podaciZaUpis[i] = podaciZaUpis[i].replace(",", "");
                                Double xD = Double.valueOf(podaciZaUpis[i]);
                                x = (BigDecimal) decimalFormat.parse(podaciZaUpis[i]);
                            } catch (Exception  e) {}
                            m.invoke(abstractDAO, x);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            setPodatke = false;
                            Logger.getLogger(sourceClass).log(Level.SEVERE, null, ex);
                        }

                        break;
                }
            }
        }
        return setPodatke;
    }
}
