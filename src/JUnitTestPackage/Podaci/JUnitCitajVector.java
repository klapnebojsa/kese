/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnitTestPackage.Podaci;

import Stampa.PagesPripremi;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
public class JUnitCitajVector {
    public Vector Citaj(String imeKlase) throws ClassNotFoundException{
        Vector productsFromFile = new Vector();
        try{
            FileInputStream file = new FileInputStream( "JUnitTest/Podaci/vectorPodaci" + imeKlase + ".txt" );
            BufferedInputStream buffer = new BufferedInputStream( file );
            ObjectInputStream in = new ObjectInputStream( buffer );            
            try{productsFromFile = new Vector((Vector) in.readObject());
            }finally{in.close();}
        }catch(IOException ex){}
        return productsFromFile;
    }
}
