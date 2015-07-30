/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistem.OsnovneDefinicije;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author Nebojsa
 */
public class RezolucijaEkrana {

    public Dimension DeoScreen(double koliko) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice ekran = env.getScreenDevices()[0];
        DisplayMode rezolucija = ekran.getDisplayMode();
        Dimension vel = new Dimension((int) (rezolucija.getWidth() * koliko), (int) (rezolucija.getHeight() * koliko));
        return vel;
    }

    public Dimension FullScreen() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice ekran = env.getScreenDevices()[0];
        DisplayMode rezolucija = ekran.getDisplayMode();
        Dimension vel = new Dimension((int) (rezolucija.getWidth()), (int) (rezolucija.getHeight()));
        return vel;
    }
    public Dimension FullScreenMaloManje() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice ekran = env.getScreenDevices()[0];
        DisplayMode rezolucija = ekran.getDisplayMode();
        Dimension vel = new Dimension((int) (rezolucija.getWidth()), (int) (rezolucija.getHeight() - 40));
        return vel;
    }    
}
