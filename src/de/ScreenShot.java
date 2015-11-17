package de;

//import java.awt.*;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author edatabit
 */
public class ScreenShot {

    private static Rectangle Rectangle;

    ScreenShot() {

    }

    public static BufferedImage getScreen() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(ScreenShot.class.getName()).log(Level.SEVERE, null, ex);
        }

        Dimension a = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle = new Rectangle(a);
        return robot.createScreenCapture(Rectangle);
    }

    public static void doFile(String ff, String path, String plik, BufferedImage bf) {

        try {
            ImageIO.write(bf, ff, new File(path + "\\" + plik));
        } catch (IOException ex) {
            Logger.getLogger(ScreenShot.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
