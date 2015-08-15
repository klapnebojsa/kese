/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnitTestPackage.Podaci;

import Stampa.PagesPripremi;
import java.awt.Graphics;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Nebojsa
 */
public class JUnitCitajGraphics {
    public Graphics Citaj(String imeKlase) throws IOException, ClassNotFoundException{
        Graphics productsFromFile = null;
        try{
            FileInputStream file = new FileInputStream( "JUnitTest/Podaci/Graphic" + imeKlase + ".txt" );
            BufferedInputStream buffer = new BufferedInputStream( file );
            ObjectInputStream in = new ObjectInputStream( buffer );
            try{productsFromFile = (Graphics) in.readObject();
            }finally{in.close();}
        }catch(IOException ex){
            int uu = 0;
            uu++;
        }
        return productsFromFile;
    }    
}
