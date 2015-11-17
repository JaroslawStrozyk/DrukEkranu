/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de;

//import static de.DE.NazwaPliku;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edatabit
 */
public class START implements Runnable {

    private Thread watek;
    private int pauza = 1000;

    START() {

    }

    /**
     * GENEROWANIE WĄTKU WYKONAWCZEGO
     */
    public void start() {
        if (watek == null) {
            watek = new Thread(this);
            watek.start();
        }
    }

    /**
     * WYWOLANIE REALIZOWANE W OKREŚLONYCH ODCINKACH CZASU
     */
    public void run() {
        while (watek == Thread.currentThread()) {
            //

            BufferedImage bf = DE.scrsh.getScreen();
            String plik = DE.NazwaPliku;

            if (!DE.KopiaDruk2.equals("")) {
                DE.smb.wyslijPlik(DE.KopiaDruk2, plik, bf);
            }

            if (!DE.KopiaDruk1.equals("")) {
                DE.scrsh.doFile(DE.FormPlik, DE.KopiaDruk1 + "\\" + DE.zg.podajRok() + "\\" + DE.zg.podajMiesiac(), plik, bf);
                if (DE.TylkoPlik) {
                    DE.druk.doDruku(DE.KopiaDruk1 + "\\" + DE.zg.podajRok() + "\\" + DE.zg.podajMiesiac() + "\\" + plik);
                };
            };
            //          
            try {
                watek.sleep(pauza);
            } catch (InterruptedException ex) {
                Logger.getLogger(START.class.getName()).log(Level.SEVERE, null, ex);
            }
            stop();
        }
    }

    /**
     * ZATRZYMANIE WĄTKU WYKONAWCZEGO
     */
    public void stop() {
        watek = null;
    }
    /**
     *
     */

}
