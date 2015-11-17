/*
 * http://jcifs.samba.org/
 */
package de;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import jcifs.*;
import jcifs.smb.*;

/**
 *
 * @author edatabit
 */
public class Smb {

    private static ByteArrayOutputStream baos = null;
    
    private static String user = "radiokomunikacja";
    private static String pass = "rkom2011!";

    private static String path = "";

    Smb() {

    }

    public static void wyslijPlik(String adres, String plik, BufferedImage bf) {
        
        baos = new ByteArrayOutputStream();
        
        try {
            ImageIO.write( bf, "jpg", baos );
            baos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Smb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            path = adres + "/" + plik;
            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", user, pass);
            SmbFile smbFile = new SmbFile(path, auth);
            SmbFileOutputStream smbfos = new SmbFileOutputStream(smbFile);
            //smbfos.write("Test zapisu do pliku".getBytes());
            smbfos.write(baos.toByteArray());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Smb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SmbException ex) {
            Logger.getLogger(Smb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Smb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Smb.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
