/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.KlaseBaze;

import Forme.Konstante.Formati;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
public class KeseProbaTest {
    KeseProba keseProba; 
    BigInteger idKese;
    String ispraviSlog;
    String upisiSlog;
    
    public KeseProbaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ParseException {
        keseProba = new KeseProba();
        keseProba.setIdKese(BigInteger.valueOf(101));        
        keseProba.setOpisKese("Probno opis kese");
        keseProba.setSKese(BigInteger.valueOf(201));
        keseProba.setFKese(BigInteger.valueOf(301));
        keseProba.setVKese(BigInteger.valueOf(401));
        keseProba.setprom(BigDecimal.valueOf(501));
        keseProba.setprom2(BigDecimal.valueOf(601));
        String stringDate = "2015-11-15";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(stringDate);        
        keseProba.setDatum(date);
        
        keseProba.setRacun1(BigDecimal.valueOf(700.51));        
        keseProba.setRacun2(BigDecimal.valueOf(700.61));        
        keseProba.setRacun3("Text Racun3");
        keseProba.setRacun4(BigDecimal.valueOf(700.71)); 
        
        Formati constM = new Formati();        
        DateFormat df = new SimpleDateFormat(constM.getDateFormatBaza());        
        ispraviSlog = "OpisKese='" + keseProba.getOpisKese() + "',  "
                    + "SKese="     + keseProba.getSKese() + " ,  "
                    + "FKese="     + keseProba.getFKese() + " ,  "
                    + "VKese="     + keseProba.getVKese() + " ,  "
                    + "prom="      + keseProba.getprom() + " ,  "
                    + "prom2="     + keseProba.getprom2() + " ,  "
                    + "Datum='"    + df.format(keseProba.getDatum()) + "',  "
                    + "Racun1="    + keseProba.getRacun1() + " ,  "
                    + "Racun2="    + keseProba.getRacun2() + " ,  "
                    + "Racun3='"   + keseProba.getRacun3() + "',  "
                    + "Racun4="    + keseProba.getRacun4() + "";        
        
        upisiSlog =  "   '" 
                + keseProba.getOpisKese() + "',  "
                + keseProba.getSKese() + " ,  "
                + keseProba.getFKese() + " ,  "
                + keseProba.getVKese() + " ,  "
                + keseProba.getprom() + " ,  "
                + keseProba.getprom2() + " , '"
                + df.format(keseProba.getDatum()) + "',  "
                + keseProba.getRacun1() + " ,  "
                + keseProba.getRacun2() + " , '"
                + keseProba.getRacun3() + "',  "
                + keseProba.getRacun4() + "";        
  
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Test of setIdKese method, of class KeseProba.
     */
    @Test
    public void testSetIdKese() {
        keseProba.setIdKese(BigInteger.valueOf(101));
        
        BigInteger expResult = BigInteger.valueOf(101);
        BigInteger result = keseProba.getIdKese();
        assertEquals(expResult, result);        
        System.out.println("SetIdKese: " + "Test:"+expResult + "  " + "Klasa:"+result);             
    }
    /**
     * Test of getIdKese method, of class KeseProba.
     */
    @Test
    public void testGetIdKese() {
        BigInteger expResult = BigInteger.valueOf(101);
        BigInteger result = keseProba.getIdKese();
        assertEquals(expResult, result);
        System.out.println("GetIdKese: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }
    /**
     * Test of getOpisKese method, of class KeseProba.
     */
    @Test
    public void testGetOpisKese() {    
        String expResult = "Probno opis kese";
        String result = keseProba.getOpisKese();
        assertEquals(expResult, result);
        System.out.println("GetOpisKese: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }
    /**
     * Test of setOpisKese method, of class KeseProba.
     */
    @Test
    public void testSetOpisKese() {
        keseProba.setOpisKese("Probno opis kese");       
        
        String expResult = "Probno opis kese";
        String result = keseProba.getOpisKese();
        assertEquals(expResult, result);        
        System.out.println("SetOpisKese: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }

    /**
     * Test of getSKese method, of class KeseProba.
     */
    @Test
    public void testGetSKese() {
        BigInteger expResult = BigInteger.valueOf(201);
        BigInteger result = keseProba.getSKese();
        assertEquals(expResult, result);
        System.out.println("getSKese: " + "Test:"+expResult + "  " + "Klasa:"+result);       
    }

    /**
     * Test of setSKese method, of class KeseProba.
     */
    @Test
    public void testSetSKese() {
        keseProba.setSKese(BigInteger.valueOf(202));        
                
        BigInteger expResult = BigInteger.valueOf(202);
        BigInteger result = keseProba.getSKese();
        assertEquals(expResult, result);
        System.out.println("setSKese: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }

    /**
     * Test of getFKese method, of class KeseProba.
     */
    @Test
    public void testGetFKese() {
        BigInteger expResult = BigInteger.valueOf(301);
        BigInteger result = keseProba.getFKese();
        assertEquals(expResult, result);
        System.out.println("getFKese: " + "Test:"+expResult + "  " + "Klasa:"+result); 
    }
    /**
     * Test of setFKese method, of class KeseProba.
     */
    @Test
    public void testSetFKese() {
        keseProba.setFKese(BigInteger.valueOf(302));        
                
        BigInteger expResult = BigInteger.valueOf(302);
        BigInteger result = keseProba.getFKese();
        assertEquals(expResult, result);
        System.out.println("setFKese: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }
    /**
     * Test of getVKese method, of class KeseProba.
     */
    @Test
    public void testGetVKese() {
        BigInteger expResult = BigInteger.valueOf(401);
        BigInteger result = keseProba.getVKese();
        assertEquals(expResult, result);
        System.out.println("getVKese: " + "Test:"+expResult + "  " + "Klasa:"+result); 
    }
    /**
     * Test of setVKese method, of class KeseProba.
     */
    @Test
    public void testSetVKese() {
        keseProba.setVKese(BigInteger.valueOf(402));        
                
        BigInteger expResult = BigInteger.valueOf(402);
        BigInteger result = keseProba.getVKese();
        assertEquals(expResult, result);
        System.out.println("setFKese: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }
    /**
     * Test of getprom method, of class KeseProba.
     */
    @Test
    public void testGetprom() {
        BigDecimal expResult = BigDecimal.valueOf(501);
        BigDecimal result = keseProba.getprom();
        assertEquals(expResult, result);
        System.out.println("getprom: " + "Test:"+expResult + "  " + "Klasa:"+result); 
    }
    /**
     * Test of setprom method, of class KeseProba.
     */
    @Test
    public void testSetprom() {
        keseProba.setprom(BigDecimal.valueOf(502));        
                
        BigDecimal expResult = BigDecimal.valueOf(502);
        BigDecimal result = keseProba.getprom();
        assertEquals(expResult, result);
        System.out.println("getprom: " + "Test:"+expResult + "  " + "Klasa:"+result); 
    }

    /**
     * Test of getprom2 method, of class KeseProba.
     */
    @Test
    public void testGetprom2() {
        BigDecimal expResult = BigDecimal.valueOf(601);
        BigDecimal result = keseProba.getprom2();
        assertEquals(expResult, result);
        System.out.println("getprom2: " + "Test:"+expResult + "  " + "Klasa:"+result); 
    }
    /**
     * Test of setprom2 method, of class KeseProba.
     */
    @Test
    public void testSetprom2() {
        keseProba.setprom2(BigDecimal.valueOf(602));        
                
        BigDecimal expResult = BigDecimal.valueOf(602);
        BigDecimal result = keseProba.getprom2();
        assertEquals(expResult, result);
        System.out.println("getprom2: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }
    /**
     * Test of getDatum method, of class KeseProba.
     */
    @Test
    public void testGetDatum() throws ParseException {
        String stringDate = "2015-11-15";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(stringDate);              
        
        Date expResult = date;
        Date result = keseProba.getDatum();
        assertEquals(expResult, result);
        System.out.println("getDatum: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }
    /**
     * Test of setDatum method, of class KeseProba.
     */
    @Test
    public void testSetDatum() throws ParseException {
        String stringDate = "2016-08-23";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(stringDate);        
        keseProba.setDatum(date);        
        
        Date expResult = date;
        Date result = keseProba.getDatum();
        assertEquals(expResult, result);
        System.out.println("setDatum: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }
    /**
     * Test of getRacun1 method, of class KeseProba.
     */
    @Test
    public void testGetRacun1() {
        BigDecimal expResult = BigDecimal.valueOf(700.51);
        BigDecimal result = keseProba.getRacun1();
        assertEquals(expResult, result);
        System.out.println("getRacun1: " + "Test:"+expResult + "  " + "Klasa:"+result); 
    }
    /**
     * Test of setRacun1 method, of class KeseProba.
     */
    @Test
    public void testSetRacun1() {
        keseProba.setRacun1(BigDecimal.valueOf(702.51));        
                
        BigDecimal expResult = BigDecimal.valueOf(702.51);
        BigDecimal result = keseProba.getRacun1();
        assertEquals(expResult, result);
        System.out.println("getRacun1: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }
    /**
     * Test of getRacun2 method, of class KeseProba.
     */
    @Test
    public void testGetRacun2() {
        BigDecimal expResult = BigDecimal.valueOf(700.61);
        BigDecimal result = keseProba.getRacun2();
        assertEquals(expResult, result);
        System.out.println("getRacun2: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }
    /**
     * Test of setRacun2 method, of class KeseProba.
     */
    @Test
    public void testSetRacun2() {
        keseProba.setRacun2(BigDecimal.valueOf(702.61));        
                
        BigDecimal expResult = BigDecimal.valueOf(702.61);
        BigDecimal result = keseProba.getRacun2();
        assertEquals(expResult, result);
        System.out.println("getRacun2: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }
    /**
     * Test of getRacun3 method, of class KeseProba.
     */
    @Test
    public void testGetRacun3() {
        String expResult = "Text Racun3";
        String result = keseProba.getRacun3();
        assertEquals(expResult, result);
        System.out.println("getRacun3: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }
    /**
     * Test of setRacun3 method, of class KeseProba.
     */
    @Test
    public void testSetRacun3() {
        keseProba.setRacun3("Text Racun31");        
                
        String expResult = "Text Racun31";
        String result = keseProba.getRacun3();
        assertEquals(expResult, result);
        System.out.println("getRacun3: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }

    /**
     * Test of getRacun4 method, of class KeseProba.
     */
    @Test
    public void testGetRacun4() {
        BigDecimal expResult = BigDecimal.valueOf(700.71);
        BigDecimal result = keseProba.getRacun4();
        assertEquals(expResult, result);
        System.out.println("getRacun4: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }

    /**
     * Test of setRacun4 method, of class KeseProba.
     */
    @Test
    public void testSetRacun4() {
        keseProba.setRacun4(BigDecimal.valueOf(702.71));        
                
        BigDecimal expResult = BigDecimal.valueOf(702.71);
        BigDecimal result = keseProba.getRacun4();
        assertEquals(expResult, result);
        System.out.println("getRacun4: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }
    /**
     * Test of ZaglavljeTabele method, of class KeseProba.
     */
    @Test
    public void testZaglavljeTabele() {
        String expResult = "Sifra,Racun4,Opis,Sirina,F....,Visina,Decimal,Double,Date,Racun1";
        String result = keseProba.ZaglavljeTabele();
        assertEquals(expResult, result);
        System.out.println("ZaglavljeTabele: " + "Test:"+expResult + "  " + "Klasa:"+result);  
    }
    /**
     * Test of PoljaBaze method, of class KeseProba.
     */
    @Test
    public void testPoljaBaze() {
        String expResult = "IdKese,Racun4,OpisKese,SKese,FKese,VKese,prom,prom2,Datum,Racun1";
        String result = keseProba.PoljaBaze();
        assertEquals(expResult, result);
        System.out.println("PoljaBaze: " + "Test:"+expResult + "  " + "Klasa:"+result); 
    }
    /**
     * Test of PoljaBazeZaUpis method, of class KeseProba.
     */
    @Test
    public void testPoljaBazeZaUpis() {
        String expResult = "OpisKese,SKese,FKese,VKese,prom,prom2,Datum,Racun1,Racun2,Racun3,Racun4";
        String result = keseProba.PoljaBazeZaUpis();
        assertEquals(expResult, result);
        System.out.println("PoljaBazeZaUpis: " + "Test:"+expResult + "  " + "Klasa:"+result); 
    }
    /**
     * Test of sourClass method, of class KeseProba.
     */
    @Test
    public void testSourClass() {
        String expResult = "Class.KlaseBaze.KeseProba";
        String result = keseProba.sourClass();
        assertEquals(expResult, result);
        System.out.println("SourClass: " + "Test:"+expResult + "  " + "Klasa:"+result); 
    }
    /**
     * Test of GetClassBaze method, of class KeseProba.
     */
    @Test
    public void testGetClassBaze() {
        String expResult = KeseProba.class.getName();
        String result = keseProba.GetClassBaze();
        assertEquals(expResult, result);
        System.out.println("GetClassBaze: " + "Test:"+expResult + "  " + "Klasa:"+result);
    }
    /**
     * Test of ImeKlase method, of class KeseProba.
     */
    @Test
    public void testImeKlase() {
        String expResult = "KeseProba";
        String result = keseProba.ImeKlase();
        assertEquals(expResult, result);
        System.out.println("ImeKlase: " + "Test:"+expResult + "  " + "Klasa:"+result);        
    }
    /**
     * Test of SifraMargina method, of class KeseProba.
     */
    @Test
    public void testSifraMargina() {
        String expResult = "12345678";
        String result = keseProba.SifraMargina();
        assertEquals(expResult, result);
        System.out.println("SifraMargina: " + "Test:"+expResult + "  " + "Klasa:"+result);     
    }

    /**
     * Test of PoljaDisabled method, of class KeseProba.
     */
    @Test
    public void testPoljaDisabled() {
        int[] expResult = {1, 9};
        int[] result = keseProba.PoljaDisabled();       
        assertArrayEquals(expResult, result);
        for (int i = 0; i < expResult.length; i++){
            System.out.println("PoljaDisabled " + (i+1) + ": "+ "Test:"+expResult[i] + "  " + "Klasa:"+result[i]);
        }
    }

    /**
     * Test of DisabledRacunati method, of class KeseProba.
     */
    @Test
    public void testDisabledRacunati() {
        BigDecimal[] expResult = {keseProba.getRacun1(), keseProba.getRacun4()};
        Object[] result = keseProba.DisabledRacunati();
        BigDecimal[] newResult = new BigDecimal[expResult.length];
        for (int i = 0; i < expResult.length; i++){
            newResult[i]= (BigDecimal)result[i];
        }
        assertArrayEquals(expResult, newResult);
        for (int i = 0; i < expResult.length; i++){
            System.out.println("DisabledRacunati " + (i+1) + ": "+ "Test:"+expResult[i] + "  " + "Klasa:"+result[i]);
        }
    }
    /**
     * Test of IzracPodatkeVanTabele method, of class KeseProba.
     */
    @Test
    public void testIzracPodatkeVanTabele() {
        boolean expResult = true;
        boolean result = keseProba.IzracPodatkeVanTabele();
        assertEquals(expResult, result);
        System.out.println("IzracPodatkeVanTabele 1: " + "Test:"+expResult + "  " + "Klasa:"+result);
        
        BigDecimal expResult2 = keseProba.getRacun2();        
        MathContext mc = new MathContext(30);
        double x2 = keseProba.getprom().doubleValue() * keseProba.getprom2().doubleValue();
        BigDecimal result2 = new BigDecimal(x2).round(mc);      
        assertEquals(expResult2, result2);
        System.out.println("IzracPodatkeVanTabele 2: " + "Test:"+expResult2 + "  " + "Klasa:"+result2);        
    }

    /**
     * Test of IzracPodatkeUTabeli method, of class KeseProba.
     */
    @Test
    public void testIzracPodatkeUTabeli() {
        boolean expResult = true;
        boolean result = keseProba.IzracPodatkeUTabeli();
        assertEquals(expResult, result);
        System.out.println("IzracPodatkeVanTabele 1: " + "Test:"+expResult + "  " + "Klasa:"+result);
        
        BigDecimal expResult2 = keseProba.getRacun1();        
        MathContext mc = new MathContext(30);
        double x2 = keseProba.getprom().doubleValue() * keseProba.getprom2().doubleValue()  / 133;
        BigDecimal result2 = new BigDecimal(x2).round(mc);         
        assertEquals(expResult2, result2);
        System.out.println("IzracPodatkeVanTabele 2: " + "Test:"+expResult2 + "  " + "Klasa:"+result2); 
        
        BigDecimal expResult3 = keseProba.getRacun4();        
        double x3 = keseProba.getprom().doubleValue() * keseProba.getprom2().doubleValue()  / 100;
        BigDecimal result3 = new BigDecimal(x3).round(mc);         
        assertEquals(expResult3, result3);
        System.out.println("IzracPodatkeVanTabele 3: " + "Test:"+expResult3 + "  " + "Klasa:"+result3);        
        
    }

    /**
     * Test of Kljuceve method, of class KeseProba.
     */
    @Test
    public void testKljuceve() {
        int[] expResult = {0};
        int[] result = keseProba.Kljuceve();      
        assertArrayEquals(expResult, result);
        for (int i = 0; i < expResult.length; i++){
            System.out.println("Kljuceve " + (i+1) + ": "+ "Test:"+expResult[i] + "  " + "Klasa:"+result[i]);
        }
    }
    /**
     * Test of ProveriVrednosti method, of class KeseProba.
     */
    @Test
    public void testProveriVrednosti() {
        Object[] expResult = {null, null};
        Object[] result = keseProba.ProveriVrednosti();        
        assertArrayEquals(expResult, result);
        for (int i = 0; i < expResult.length; i++){
            System.out.println("ProveriVrednosti " + (i+1) + ": "+ "Test:"+expResult[i] + "  " + "Klasa:"+result[i]);
        }
    }
    /**
     * Test of UslovTrazenjaSloga method, of class KeseProba.
     */
    @Test
    public void testUslovTrazenjaSloga() {
        String expResult = "IdKese=101";
        String result = keseProba.UslovTrazenjaSloga();
        assertEquals(expResult, result);
        System.out.println("SifraMargina: " + "Test:"+expResult + "  " + "Klasa:"+result);  
    }

    /**
     * Test of IspraviSlog method, of class KeseProba.
     */
    @Test
    public void testIspraviSlog() {
        String expResult = ispraviSlog;
        String result = keseProba.IspraviSlog();       
        assertEquals(expResult, result);
        System.out.println("IspraviSlog: " + "Test :"+expResult);
        System.out.println("IspraviSlog: " + "Klasa:"+result   );  
    }

    /**
     * Test of UpisiSlog method, of class KeseProba.
     */
    @Test
    public void testUpisiSlog() {
        String expResult = upisiSlog;
        String result = keseProba.UpisiSlog();       
        assertEquals(expResult, result);
        System.out.println("UpisiSlog: " + "Test :"+expResult);
        System.out.println("UpisiSlog: " + "Klasa:"+result   ); 
    }
    
}
