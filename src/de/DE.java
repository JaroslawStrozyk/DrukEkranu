package de;

import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 *
 * @author edatabit
 */
public class DE {

    public static CfgDE conf = null;
    public static Zegar zg = null;
    public static Okno ok = null;
    public static Smb smb = null;
    public static ScreenShot scrsh = null;
    public static Drukarka druk = null;
    public static START st = null;

    public static Boolean TylkoPlik;
    public static Boolean AutoDruk;
    public static String DrukRano;
    public static String DrukWieczor;
    public static String LadujRano;
    public static String LadujWieczor;
    public static String KopiaDruk1 = "";
    public static String KopiaDruk2 = "";
    public static String OpisDruk2;
    public static String FormPlik;

    public static String NazwaPliku;

    public static void main(String[] args) {

        System.out.println("START");
        conf = new CfgDE("config.conf");
        GetConf();

        st = new START();

        ok = new Okno();
        ok.setVisible(true);

        zg = new Zegar();
        zg.ZegarUstaw();

        scrsh = new ScreenShot();
        smb = new Smb();
        druk = new Drukarka();

    }

    private static void GetConf() {

        TylkoPlik = !(ustawWartosc(conf.ReadProp("TylkoPlik")));
        AutoDruk = ustawWartosc(conf.ReadProp("AutoDruk"));
        DrukRano = conf.ReadProp("DrukRano");
        DrukWieczor = conf.ReadProp("DrukWieczor");
        LadujRano = conf.ReadProp("LadujRano");
        LadujWieczor = conf.ReadProp("LadujWieczor");
        KopiaDruk1 = conf.ReadProp("KopiaDruk1");
        KopiaDruk2 = conf.ReadProp("KopiaDruk2");

        if (KopiaDruk2.equals("")) {
            OpisDruk2 = "n/d";
        } else {
            OpisDruk2 = "MARS\\\\RaportDyzurnych";
        }

        FormPlik = conf.ReadProp("FormatPliku");
    }

    private static Boolean ustawWartosc(String s) {
        Boolean b = false;
        String znak = "tak";
        if (znak.equals(s)) {
            b = true;
        } else {
            b = false;
        }
        return b;
    }

    public static void wykKopieAuto() {
        // Opcja druku automatycznego
        if (AutoDruk) {
            String rano = DrukRano + ":00";
            String wieczor = DrukWieczor + ":00";
            String lrano = LadujRano + ":00";
            String lwieczor = LadujWieczor + ":00";

            if (alarmCzas(zg.podajCzas(), rano)) {
                st.start();
            }

            if (alarmCzas(zg.podajCzas(), wieczor)) {
                st.start();
            }

            if (alarmCzas(zg.podajCzas(), lrano)) {
                GetConf();
                ok.odswiezWidok();
            }

            if (alarmCzas(zg.podajCzas(), lwieczor)) {
                GetConf();
                ok.odswiezWidok();
            }
            //System.out.println(DE.KopiaDruk1+"\\"+zg.podajRok()+"\\"+zg.podajMiesiac());
        }
    }

    public static Boolean alarmCzas(String czas, String wzor) {
        Boolean b = false;

        b = czas.equals(wzor);

        return b;
    }
}
