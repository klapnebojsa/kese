/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnitTestPackage.Podaci;

import Stampa.PagesPripremi;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author Nebojsa
 */
public class JUnitUpisiPagesPripremi {
    public void Upisi(String imeKlase, PagesPripremi pagesPripremi){
        try{        
            OutputStream file = new FileOutputStream( "JUnitTestPodaci/pagesPripremiPodaci" + imeKlase + ".txt" );
            OutputStream buffer = new BufferedOutputStream( file );
            ObjectOutput output = new ObjectOutputStream( buffer ); 
            try{output.writeObject(pagesPripremi);
            }finally{output.close();}
        }catch(IOException ex){
            /*int uu=0;
            uu++;*/
        }        
    }
}
