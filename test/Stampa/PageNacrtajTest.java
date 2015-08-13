/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa;

import Stampa.*;
import JUnitTestPackage.Podaci.JUnitCitajVector;
import Stampa.Podaci.PoljeZaStampu;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


/**
 *
 * @author Nebojsa
 */
public class PageNacrtajTest  extends JFrame {
    PageNacrtaj pageNacrtaj = new PageNacrtaj();
    //EmployeeDetails employee = new EmployeeDetails();
    Vector productsFromFile;
    JPanel contentPane;
    String kojaKlasa;
    JComboBox klasa = new JComboBox();

   //test to check appraisal
   @Test
   public void testCalculateAppriasal() throws Exception {
       
        PageNacrtaj pageNacrtaj = new PageNacrtaj(); 
        
        
        //pageNacrtaj.pageVector = pages;
                
       /*Graphics g = null;
        pageNacrtaj.Prikazi(productsFromFile, g);*/        
        //kojaKlasa = klasa.getSelectedItem().toString();
        //if (!"".equals(kojaKlasa)){
       
            //********** CITANJE IZ JUnit FAJLA             
            JUnitCitajVector jUnitCitajStampa = new JUnitCitajVector();
            productsFromFile = new Vector((Vector) jUnitCitajStampa.Citaj(kojaKlasa));

            PrintWriter printWriter = new PrintWriter ( "JUnitTest/Rezultati/vectorPodaci" + kojaKlasa + ".txt" );
            System.out.println(kojaKlasa);

            Vector pages = (Vector) productsFromFile;
            //pageNacrtaj.pageVector = pages;                    
            pageNacrtaj.Prikazi(pages, null);  
            
            
            for (int iPs = 0; iPs < pages.size(); iPs++) {
                Vector page = (Vector) productsFromFile.elementAt(iPs);
                
                printWriter.println("Page: " + (int)(iPs+1));        
                for (int iP = 0; iP < page.size(); iP++) {            
                    Vector <PoljeZaStampu> lineVector = (Vector)page.get(iP);

                    printWriter.println("    Line-" + (int)(iP+1) + ":");
                    for (int iC=0; iC<lineVector.size(); iC++){
                        //printWriter.println("         Polje-" + (int)(iC+1) + ":" + "Visina fonta: " + lineVector.elementAt(iC).getMaxVisinaFonta());
                        printWriter.println("         Polje-" + (int)(iC+1) + ":" + "Visina fonta: " + lineVector.elementAt(iC).getMaxVisinaFonta());
                        
                        //pageNacrtaj.Prikazi(pages, null);                        
                        
                        //System.out.println("Line " + iP + ":" + lineVector.elementAt(lineVector.size()-1).getMaxVisinaFonta());etMaxVisinaFonta());                
                                                     
                    }
                }   
            }
            printWriter.close();
            //assertEquals(500, appraisal, 0.0);
            System.out.println(true);            
       //}
   }
   
        //execute before class
         @BeforeClass
         public static void beforeClass() {
            System.out.println("in before class");
         }

        //execute after class
        @AfterClass
        public static void  afterClass() {
           System.out.println("in after class");
        }

        //execute before test
        @Before
        public void before() {
            kojaKlasa="KeseProba";
            pageNacrtaj.medjY=10;
            pageNacrtaj.medjYDw=10;
            pageNacrtaj.medjX=10;
            
            System.out.println("in before- " + ", KojaKlasa:" + kojaKlasa + ", medjY:" + pageNacrtaj.medjY + ", medjYDw:" + pageNacrtaj.medjYDw + ", medjX:" + pageNacrtaj.medjX);
        }

        //execute after test
        @After
        public void after() {
           System.out.println("in after");
        }

        //test case
        @Test
        public void test() {
           System.out.println("in test");
        }

        //test case ignore and will not execute
        @Ignore
        public void ignoreTest() {
           System.out.println("in ignore test");
        }
   
   
   
}
