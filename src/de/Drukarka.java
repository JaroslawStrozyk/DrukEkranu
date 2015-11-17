package de;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;

/**
 *
 * @author edatabit
 */
public class Drukarka {
    
    Drukarka(){
        
    }

    public static void doDruku(String plik) {

        FileInputStream fin = null;
        try {
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            pras.add(new Copies(1));
            pras.add(MediaSizeName.ISO_A4);
            pras.add(OrientationRequested.LANDSCAPE);
            PrintService pss[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.GIF, pras);
            if (pss.length == 0) {
                throw new RuntimeException("Brak dostępnych drukarek.");
            }
            PrintService ps = pss[0]; // 0 lub 1 - drukarki
            DocPrintJob job = ps.createPrintJob();
            fin = new FileInputStream(plik);
            Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.GIF, null);
            try {
                job.print(doc, pras);
            } catch (PrintException ex) {
                Logger.getLogger(Drukarka.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fin.close();
            } catch (IOException ex) {
                Logger.getLogger(Drukarka.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Drukarka.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fin.close();
            } catch (IOException ex) {
                Logger.getLogger(Drukarka.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
