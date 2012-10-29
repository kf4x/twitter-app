/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterclient;

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javierAle
 */
public class Main {
     
    public static void main(String[] args) {
        // TODO code application logic here
        LoginFrame frame = null;
        try {
            frame = new LoginFrame();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.setVisible(true);
        frame.setTitle("Javier's Twitter Client");
    }
}
