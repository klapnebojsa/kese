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
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
public class JUnitCitajPagesPripremi {
    public PagesPripremi Citaj(String imeKlase) throws IOException, ClassNotFoundException{
        PagesPripremi productsFromFile = new PagesPripremi();
        try{
            FileInputStream file = new FileInputStream( "JUnitTestPodaci/pagesPripremiPodaci" + imeKlase + ".txt" );
            BufferedInputStream buffer = new BufferedInputStream( file );
            ObjectInputStream in = new ObjectInputStream( buffer );
            try{productsFromFile = (PagesPripremi) in.readObject();
            }finally{in.close();}
        }catch(IOException ex){
            int uu = 0;
            uu++;
        }
        return productsFromFile;
    }
}
