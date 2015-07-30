/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stampa.Upiti;

import Stampa.Apstraktne.OblikStampe;
import Stampa.Obrasci.Tabele.TabelaClasic;
import Stampa.Obrasci.Tabele.TabelaHeader;
import Stampa.Obrasci.Tabele.TabelaHorizontal;
import Stampa.Obrasci.Tabele.TabelaOkvir;
import Stampa.Obrasci.Tabele.TableMedjuZbir;


/**
 *
 * @author Nebojsa
 */
public class KojaTabela {
    OblikStampe tabele;
    String imeKlase;
    String fontNameOsn;
    int fontSizeOsn;
    int red;
    int col;
    int maxCol;
    String vrednost;
    String alignment;
    String boldItalic;

    public KojaTabela(String imeKlase, String fontNameOsn, int fontSizeOsn, int red, int col, int maxCol, String vrednost, String alignment, String boldItalic){
        this.imeKlase = imeKlase;
        this.fontNameOsn = fontNameOsn;
        this.fontSizeOsn = fontSizeOsn;
        this.red = red;
        this.col = col;
        this.maxCol = maxCol;
        this.vrednost = vrednost;
        this.alignment = alignment;
        this.boldItalic = boldItalic;

    } 
    public OblikStampe OdrediTabelu(){
        switch (imeKlase){
            case "Partneri":
                tabele = new TabelaHorizontal(fontNameOsn, fontSizeOsn, red, col, maxCol, vrednost, alignment, boldItalic);
                break;
            case "TableHeader":
                tabele = new TabelaHeader(fontNameOsn, fontSizeOsn, red, col, maxCol, vrednost, alignment, boldItalic); 
                break;
            case "TableMedjuZbir":
                tabele = new TableMedjuZbir(fontNameOsn, fontSizeOsn, red, col, maxCol, vrednost, alignment, boldItalic); 
                break;                 
            default:
                tabele = new TabelaClasic(fontNameOsn, fontSizeOsn, red, col, maxCol, vrednost, alignment, boldItalic);
                break;
        }
        return tabele;
    }
}
