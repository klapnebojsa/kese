/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa;

import JUnitTestPackage.Podaci.JUnitCitajVector;
import Stampa.Podaci.PoljeZaStampu;
import java.io.PrintWriter;
import java.util.Vector;
import org.junit.Test;


/**
 *
 * @author Nebojsa
 */
public class PageNacrtajTest {
    PageNacrtaj pageNacrtaj = new PageNacrtaj();
    //EmployeeDetails employee = new EmployeeDetails();
    Vector productsFromFile;
    public PageNacrtajTest() throws ClassNotFoundException{
        //********** CITANJE IZ JUnit FAJLA             
        JUnitCitajVector jUnitCitajStampa = new JUnitCitajVector();
        productsFromFile = new Vector((Vector) jUnitCitajStampa.Citaj("KeseProba"));    
    }   
    
   //test to check appraisal
   @Test
   public void testCalculateAppriasal() throws Exception {
        /*employee.setName("Rajeev");
        employee.setAge(25);
        employee.setMonthlySalary(8000);*/
        
       /*Graphics g = null;
        pageNacrtaj.Prikazi(productsFromFile, g);*/
        PrintWriter printWriter = new PrintWriter ( "JUnitTest/Rezultati/vectorPodaci" + "KeseProba" + ".txt" );
        
        Vector pages = (Vector) productsFromFile;
        for (int iPs = 0; iPs < pages.size(); iPs++) {
            Vector page = (Vector) productsFromFile.elementAt(iPs);
            printWriter.println("Page: " + (int)(iPs+1));        
            for (int iP = 0; iP < page.size(); iP++) {            
                Vector <PoljeZaStampu> lineVector = (Vector)page.get(iP);
                printWriter.println("    Line-" + (int)(iP+1) + ":");
                for (int iC=0; iC<lineVector.size(); iC++){
                    printWriter.println("         Polje-" + (int)(iC+1) + ":" + "Visina fonta: " + lineVector.elementAt(iC).getMaxVisinaFonta());                
                    //System.out.println("Line " + iP + ":" + lineVector.elementAt(lineVector.size()-1).getMaxVisinaFonta());                             
                }
            }   
        }
        printWriter.close();
        //assertEquals(500, appraisal, 0.0);
   }
}
