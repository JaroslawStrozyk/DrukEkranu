package de;

import java.io.FileNotFoundException;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Zegar {

    private String aktualnyCzas;
    private String aktualnaData;
    private String aktualnyRok;
    private String aktualnyMiesiac;
    private String NazwaPliku;

    public String podajCzas() {
        return aktualnyCzas;
    }

    public String podajDate() {
        return aktualnaData;
    }

    public String podajRok() {
        return aktualnyRok;
    }

    public String podajMiesiac() {
        return aktualnyMiesiac;
    }

    public String podajNazwePliku() {
        return NazwaPliku;
    }

    private void ustawCzas() {
        String s = "";
        String s1 = "";
        String plik = "";
        int m = 0;

        Calendar cal = new GregorianCalendar();

        m = cal.get(Calendar.MONTH);
        m = m + 1;
        if (m < 10) {
            s = "0" + Integer.toString(m);
        } else {
            s = Integer.toString(m);
        }
        aktualnyMiesiac = s;
        m = cal.get(Calendar.DATE);
        if (m < 10) {
            s1 = "0" + Integer.toString(m);
        } else {
            s1 = Integer.toString(m);
        }
        plik = Integer.toString(cal.get(Calendar.YEAR)) + "-" + s + "-" + s1 + "_";
        s = s1 + "." + s + "." + Integer.toString(cal.get(Calendar.YEAR));

        aktualnyRok = Integer.toString(cal.get(Calendar.YEAR));
        aktualnaData = s;

        // WYŚWIETLANIE GODZINY
        m = cal.get(Calendar.HOUR_OF_DAY);
        if (m < 10) {
            s = "0" + Integer.toString(m);
        } else {
            s = Integer.toString(m);
        }
        s1 = s;
        m = cal.get(Calendar.MINUTE);
        if (m < 10) {
            s = "0" + Integer.toString(m);
        } else {
            s = Integer.toString(m);
        }
        plik = plik + s1 + s;
        s1 = s1 + ":" + s;

        m = cal.get(Calendar.SECOND);
        if (m < 10) {
            s = "0" + Integer.toString(m);
        } else {
            s = Integer.toString(m);
        }
        s1 = s1 + ":" + s;
        plik = plik + s;

        aktualnyCzas = s1;
        NazwaPliku = plik;
    }

    public void ZegarUstaw() {

        Thread clock = new Thread() {
            public void run() {
                for (;;) {

                    ustawCzas();

                    DE.NazwaPliku = podajNazwePliku() + "." + DE.FormPlik;
                    DE.ok.cykliczneDane(podajDate(), podajCzas(), DE.NazwaPliku);
                    DE.wykKopieAuto();
                    
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Zegar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        clock.start();

    }

}
