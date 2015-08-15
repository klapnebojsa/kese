/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Ispis;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nebojsa
 */
public class FormatirajTest {

    Formatiraj formatiraj = new Formatiraj();    
    
    public FormatirajTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of formatirajVar method, of class Formatiraj.
     */
    @Test
    public void testFormatirajVar() {
        formatiraj.setTipVar("INT");
        formatiraj.setDecVar(0);
        formatiraj.setDuzinaVar(10);
        formatiraj.setVrednostVar("50");
        formatiraj.setKojaVrsta("");
        formatiraj.setKljuc(false);  
        String expResult = "50";
        Object result = formatiraj.formatirajVar();
        assertEquals(expResult, result.toString());
        System.out.println("Formatiraj : " + formatiraj.tipVar + " Test:"+expResult + "  " + "Klasa:"+result.toString()); 
        
        
        formatiraj.setTipVar("DOUBLE");
        formatiraj.setDecVar(3);
        formatiraj.setDuzinaVar(10);
        formatiraj.setVrednostVar("1256.325");
        formatiraj.setKojaVrsta("");
        formatiraj.setKljuc(false);
        expResult = "1,256.325";
        result = formatiraj.formatirajVar();
        assertEquals(expResult, result.toString());
        System.out.println("Formatiraj : " + formatiraj.tipVar + " Test:"+expResult + "  " + "Klasa:"+result.toString());
        
        formatiraj.setTipVar("DECIMAL");
        formatiraj.setDecVar(6);
        formatiraj.setDuzinaVar(20);
        formatiraj.setVrednostVar("1231256.123325");
        formatiraj.setKojaVrsta("");
        formatiraj.setKljuc(false); 
        expResult = "1,231,256.123325";
        result = formatiraj.formatirajVar();
        assertEquals(expResult, result.toString());
        System.out.println("Formatiraj : " + formatiraj.tipVar + " Test:"+expResult + "  " + "Klasa:"+result.toString());        
        
        
        //TipVarijable, decimalnih mesta, duzina sa decimalama, vrednost, tabela ili text box, jeste kljuc, ocekivana vrednost
        Object[][] ulazIzlaz = {
            {"INT", 0, 10, "50", "", false, "50"},
            {"DOUBLE", 3, 10, "1256.325", "", false, "1,256.325"},
            {"DECIMAL", 6, 20, "1231256.123325", "", false, "1,231,256.123325"},
            {"BIGDECIMAL", 6, 20, "1256831256.123325", "", false, "1,256,831,256.123325"},
            {"VARCHAR", 0, 50, "Ovo je polje VARCHAR", "", false, "Ovo je polje VARCHAR"},
            {"BIGINT", 0, 20, "123656565", "", false, "123,656,565"},
            {"DATE", 0, 20, "2015-12-25", "TextBox", false, "25.12.2015"}, 
            {"DATE", 0, 20, "2017-10-14", "Tabela", false, "2017/10/14"}    
        }; 
        for (int i=0; i<ulazIzlaz.length; i++){
                formatiraj.setTipVar(ulazIzlaz[i][0].toString());
                formatiraj.setDecVar((int) ulazIzlaz[i][1]);
                formatiraj.setDuzinaVar((int) ulazIzlaz[i][2]);
                formatiraj.setVrednostVar(ulazIzlaz[i][3].toString());
                formatiraj.setKojaVrsta(ulazIzlaz[i][4].toString());
                formatiraj.setKljuc((boolean) ulazIzlaz[i][5]);
                
                result = formatiraj.formatirajVar();
                assertEquals(ulazIzlaz[i][6], result.toString());
                System.out.println("Formatiraj : " + formatiraj.tipVar + " Test:"+ulazIzlaz[i][6] + "  " + "Klasa:"+result.toString());                 
        }
        
    }
}
