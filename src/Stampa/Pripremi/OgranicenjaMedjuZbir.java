/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Pripremi;

import Forme.FormForme;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Nebojsa
 */
public class OgranicenjaMedjuZbir {

    private String TabeleSaMedjuZbirom(){
        return "KeseProbaStampaTabele,";
    }
    
    public boolean trebaMedjuZbir(String koZoveName, String kojaStampa){
        boolean trebaLi = false;
        if (TabeleSaMedjuZbirom().contains(koZoveName + kojaStampa)) trebaLi = true; 
        return trebaLi;
    }
    
    public Vector KolonaMedjuZbir(FormForme koZove, String kojaStampa) throws SQLException{
        boolean imaLiPolja=false;
        ResultSetMetaData metaData = koZove.metaData;
        String[] poljaBaze = koZove.k.PoljaBaze().split(",");
        Vector trebaLi = new Vector();
        for(int i=0; i<poljaBaze.length;i++){
            for (int j=0; j<metaData.getColumnCount();j++){
                if (poljaBaze[i].equals( metaData.getColumnLabel(j+1))){
                    String tipVar = metaData.getColumnTypeName(j+1);
                    int brZnakova = metaData.getPrecision(j+1) - metaData.getScale(j+1);
                    //Ako je u tabeli baze polje definisano kao decimal ili double onda se racuna medjuzbir te kolone
                    //pod uslovom da je za tu formu drfinisana stampa medjuzbirova
                    if ((tipVar == "DECIMAL" || tipVar == "DOUBLE") && brZnakova>8) {
                        trebaLi.add(true);
                        imaLiPolja = true;
                    }else{
                        trebaLi.add(false);}                    
                    break;
                }
            }            
        }
        if (!imaLiPolja)trebaLi=new Vector();
        return trebaLi;
    }    
}
