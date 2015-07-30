/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class.Provere;

import Forme.Konstante.Formati;
import Forme.Polja.Opisi.OpisiFormata;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author neso
 */
public class Provere {

    OpisiFormata opisiFormata;

    public Provere() {
        opisiFormata = new OpisiFormata();
    }

    public Boolean proveriNotBigDecimal(String donos) {
        Boolean proveriNotBigDecimal = false;
        donos = donos.replace(",", "");
        if (!proveriPrazno(donos)) {
            try {
                BigDecimal xx = new BigDecimal(donos);
            } catch (Exception e) {
                proveriNotBigDecimal = true;
            }
        } else {
            proveriNotBigDecimal = true;
        }

        return proveriNotBigDecimal;
    }

    public Boolean proveriNotInteger(String donos) {
        Boolean proveriNotInteger = false;
        donos = donos.replace(",", "");
        if (!proveriPrazno(donos)) {
            try {
                Integer xx = Integer.parseInt(donos);
            } catch (Exception e) {
                proveriNotInteger = true;
            }
        } else {
            proveriNotInteger = true;
        }

        return proveriNotInteger;
    }

    public Boolean proveriNotDouble(String donos) {
        Boolean proveriNotDouble = false;
        donos = donos.replace(",", "");
        if (!proveriPrazno(donos)) {
            try {
                Double xx = Double.parseDouble(donos);
            } catch (Exception e) {
                proveriNotDouble = true;
            }
        } else {
            proveriNotDouble = true;
        }

        return proveriNotDouble;
    }

    public Boolean proveriPrazno(String donos) {
        boolean prazno = false;
        if (donos == null || "".equals(donos.replace(" ", ""))) {
            prazno = true;
        }
        return prazno;
    }

    public String proveriBigInteger(BigInteger vrednost, String opis) {
        String proveriBigInteger = null;
        String vr = null;
        try {
            vr = vrednost.toString();
            vr = vr.replace(",", "");
        } catch (Exception e) {
        }

        if (!proveriPrazno(vr)) {
            try {
                BigInteger xx = new BigInteger(vr);
                //Poruka za neadekvatno uneseno polje
            } catch (Exception e) {
                proveriBigInteger = "\n<html> Unesite polje, <b><u><i>" + opis + "</b></u></i>, u formatu " + opisiFormata.FormatBigInteger();
            }
            //Poruka za prazno polje
        } else {
            proveriBigInteger = "\n<html> Unesite polje, <b><u><i>" + opis + "</b></u></i>, u formatu " + opisiFormata.FormatBigInteger();
        }

        return proveriBigInteger;
    }
    public String proveriInteger(int vrednost, String opis) {
        String proveriInteger = null;
        String vr = null;
        try {
            vr = Integer.toString(vrednost);
            vr = vr.replace(",", "");
        } catch (Exception e) {
        }

        if (!proveriPrazno(vr)) {
            try {
                int xx = Integer.parseInt(vr);
                //Poruka za neadekvatno uneseno polje
            } catch (Exception e) {
                proveriInteger = "\n<html> Unesite polje, <b><u><i>" + opis + "</b></u></i>, u formatu " + opisiFormata.FormatInteger();
            }
            //Poruka za prazno polje
        } else {
            proveriInteger = "\n<html> Unesite polje, <b><u><i>" + opis + "</b></u></i>, u formatu " + opisiFormata.FormatInteger();
        }

        return proveriInteger;
    }
    public String proveriBigDecimal(BigDecimal vrednost, String opis) {
        String proveriBigDecimal = null;
        String vr = null;
        try {
            vr = vrednost.toString();
            vr = vr.replace(",", "");
        } catch (Exception e) {
        }

        if (!proveriPrazno(vr)) {
            try {
                BigDecimal xx = new BigDecimal(vr);
                //Poruka za neadekvatno uneseno polje
            } catch (Exception e) {
                proveriBigDecimal = "\n<html> Unesite polje, <b><u><i>" + opis + "</b></u></i>, u formatu " + opisiFormata.FormatBigDecimal();
            }
            //Poruka za prazno polje
        } else {
            proveriBigDecimal = "\n<html> Unesite polje, <b><u><i>" + opis + "</b></u></i>, u formatu " + opisiFormata.FormatBigDecimal();
        }

        return proveriBigDecimal;
    }

    public String proveriDouble(Double vrednost, String opis) {
        String proveriDouble = null;
        String vr = null;
        try {
            vr = vrednost.toString();
            vr = vr.replace(",", "");
        } catch (Exception e) {
        }

        if (!proveriPrazno(vr)) {
            try {
                Double xx = new Double(vr);
                //Poruka za neadekvatno uneseno polje
            } catch (Exception e) {
                proveriDouble = "\n<html> Unesite polje, <b><u><i>" + opis + "</b></u></i>, u formatu " + opisiFormata.FormatDouble();
            }
            //Poruka za prazno polje
        } else {
            proveriDouble = "\n<html> Unesite polje, <b><u><i>" + opis + "</b></u></i>, u formatu " + opisiFormata.FormatDouble();
        }

        return proveriDouble;
    }

    public String proveriDatumTxt(Date vrednost, String opis) {
        String proveriDate = null;
        String vr = null;
        try {
            vr = vrednost.toString();
            vr = vr.replace(",", "");
        } catch (Exception e) {
        }

        if (!proveriPrazno(vr)) {
            try {
                Formati constM = new Formati();
                Object formiranaVar = new SimpleDateFormat(constM.getDateFormatText()).format(vrednost);

                SimpleDateFormat dateText = new SimpleDateFormat(constM.getDateFormatText());
                Date xx = dateText.parse(formiranaVar.toString());
                //Poruka za neadekvatno uneseno polje
            } catch (Exception e) {
                proveriDate = "\n<html> Unesite polje, <b><u><i>" + opis + "</b></u></i>, u formatu " + opisiFormata.FormatDate();
            }
            //Poruka za prazno polje
        } else {
            proveriDate = "\n<html> Unesite polje, <b><u><i>" + opis + "</b></u></i>, u formatu " + opisiFormata.FormatDate();
        }

        return proveriDate;
    }

    public String proveriString(String vrednost, String opis) {
        String proveriString = null;
        String vr = vrednost;

        if (!proveriPrazno(vr)) {
        } else {
            proveriString = "\n<html> Unesite polje, <b><u><i>" + opis + "</b></u></i>, u formatu " + opisiFormata.FormatString();
        }

        return proveriString;
    }
}
