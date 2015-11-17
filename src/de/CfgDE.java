/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author edatabit
 */
public class CfgDE {

    private Properties prop = null;
    private String ConfFile = null;

    CfgDE(String file) {
        prop = new Properties();
        ConfFile = file;
    }

    public void SaveProp(String title, String value) {

        try {
            prop.setProperty(title, value);
            prop.store(new FileOutputStream(ConfFile), null);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public String ReadProp(String title) {
        String value = "";
        try {
            prop.load(new FileInputStream(ConfFile));
            value = prop.getProperty(title);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return value;
    }

}
