/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Polja.Prikazi;

import Forme.Konstante.Boje;
import Sistem.OsnovneDefinicije.RezolucijaEkrana;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Nebojsa
 */
public class InfoLinija {

    public JLabel obavestenje;

    public JLabel DefInfoLiniju() {
        RezolucijaEkrana re = new RezolucijaEkrana();
        Dimension fullScr = re.FullScreen();
        obavestenje = new JLabel();
        Font obavFont = new Font(obavestenje.getName(), Font.PLAIN, 12);
        obavestenje.setFont(obavFont);

        String text = "";
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        int textwidth = (int) (obavFont.getStringBounds(text, frc).getWidth());
        int textheight = (int) (obavFont.getStringBounds(text, frc).getHeight());

        textheight = Math.max(textheight, 40);
        obavestenje.setSize(fullScr.width, textheight);
        Rectangle cistEkran = null;
        try {
            cistEkran = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        } catch (Exception e) {
        }
        int x = (cistEkran != null) ? cistEkran.height - textheight : fullScr.height - 100;
        obavestenje.setLocation(0, x);

        obavestenje.setOpaque(true);
        Boje boje = new Boje();
        obavestenje.setBackground(boje.getColorObavestenjeBackground());
        obavestenje.setForeground(boje.getColorObavestenjeText());
        obavestenje.setHorizontalAlignment(JTextField.CENTER);
        obavestenje.setVerticalAlignment(JTextField.TOP);
        obavestenje.setText("");
        return obavestenje;
    }
}
