/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.Provere;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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
public class ProvereTest {
    
    public ProvereTest() {
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
     * Test of proveriNotBigDecimal method, of class Provere.
     */
    @Test
    public void testProveriNotBigDecimal() {
        System.out.println("testProveriNotBigDecimal:" );        
        String[] vrednost = {"187.3", "123,518.25", "1Akjsa", "kdasrej1", "11178.56", "56578", "18788", "/98/!@#", "^67878kj&^%", "231,545"};
        boolean[] expResult = {false, false, true, true, false, false, false, true, true, false};
        Provere instance = new Provere();
        for (int i=0; i<vrednost.length; i++){
            boolean result = instance.proveriNotBigDecimal(vrednost[i]);
            assertEquals("Neispravno: " + expResult[i] + "," + vrednost[i], expResult[i], result);
            System.out.println("    " + "Test:"+expResult[i] + "  " + "Klasa:"+result + "   Vrednost - " + vrednost[i]);                          
        }
    }

    /**
     * Test of proveriNotInteger method, of class Provere.
     */
    @Test
    public void testProveriNotInteger() {
        System.out.println("testProveriNotInteger:" );        
        String[] vrednost = {"123", "18", "1Asa", "kj1", "178.56", "56578", "18788", "//!@#", "^&^%", "1,545"};
        boolean[] expResult = {false, false, true, true, true, false, false, true, true, false};
        Provere instance = new Provere();
        for (int i=0; i<vrednost.length; i++){
            boolean result = instance.proveriNotInteger(vrednost[i]);
            assertEquals("Neispravno: " + expResult[i] + "," + vrednost[i], expResult[i], result);
            System.out.println("    " + "Test:"+expResult[i] + "  " + "Klasa:"+result + "   Vrednost - " + vrednost[i]);                          
        }
    }

    /**
     * Test of proveriNotDouble method, of class Provere.
     */
    @Test
    public void testProveriNotDouble() {
        System.out.println("testProveriNotDouble:" );        
        String[] vrednost = {"187.3546564", "123,518.26545455", "jsa", "kdas0j1", "1541178.56", "56,578", "1854788", "54/98/!@#", "^67878kkjhkj&^%", "231,545.0002365455"};
        boolean[] expResult = {false, false, true, true, false, false, false, true, true, false};
        Provere instance = new Provere();
        for (int i=0; i<vrednost.length; i++){
            boolean result = instance.proveriNotDouble(vrednost[i]);
            assertEquals("Neispravno: " + expResult[i] + "," + vrednost[i], expResult[i], result);
            System.out.println("    " + "Test:"+expResult[i] + "  " + "Klasa:"+result + "   Vrednost - " + vrednost[i]);                          
        }
    }

    /**
     * Test of proveriPrazno method, of class Provere.
     */
    @Test
    public void testProveriPrazno() {
        System.out.println("testProveriPrazno:" );        
        String[] vrednost = {"7.35564", "518.26545", "", "", "1541178.56", "56,578", "1854788", "", "", "2kjhj02365455"};
        boolean[] expResult = {false, false, true, true, false, false, false, true, true, false};
        Provere instance = new Provere();
        for (int i=0; i<vrednost.length; i++){
            boolean result = instance.proveriPrazno(vrednost[i]);
            assertEquals("Neispravno: " + expResult[i] + "," + vrednost[i], expResult[i], result);
            System.out.println("    " + "Test:"+expResult[i] + "  " + "Klasa:"+result + "   Vrednost - " + vrednost[i]);                          
        }
    }

    /**
     * Test of proveriString method, of class Provere.
     */
    @Test
    public void testProveriString() {
        System.out.println("testProveriString:" );
        String opisPr = "\n<html> Unesite polje, <b><u><i></b></u></i>, u formatu teksta";
        String[] vrednost = {"7.35564", "5lkl18.26545", "", "", "mnghnfhj56", "56,578", "1854788", "", "", "jkhhk"};
        String[] expResult = {null, null, opisPr, opisPr, null, null, null, opisPr, opisPr, null};
        Provere instance = new Provere();
        for (int i=0; i<vrednost.length; i++){
            String result = instance.proveriString(vrednost[i], "");          
            assertEquals("Neispravno: " + expResult[i] + "," + vrednost[i], expResult[i], result);
            System.out.println("    " + "Test:"+expResult[i] + "  " + "Klasa:"+result + "   Vrednost - " + vrednost[i]);                          
        }
    }
}
