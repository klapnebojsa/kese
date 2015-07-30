/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.Kljucevi;

/**
 *
 * @author Nebojsa
 */
public class RasporediKljuceve {

    public int[] PreracunajRbr(String[] pK, String[] poljaBaze) {
        String p = "";
        for (int i = 0; i < poljaBaze.length; i++) {
            for (int j = 0; j < pK.length; j++) {
                if (poljaBaze[i].equals(pK[j])) {
                    p += i + ",";
                }
            }
        }
        String[] pS = p.split(",");
        int[] kljucevi = new int[pS.length];
        for (int i = 0; i < pS.length; i++) {
            try {
                kljucevi[i] = Integer.parseInt(pS[i]);
            } catch (Exception e) {
                kljucevi = null;
            }
        }
        return kljucevi;
    }

}
