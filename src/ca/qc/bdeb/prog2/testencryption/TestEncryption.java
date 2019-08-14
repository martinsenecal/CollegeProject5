/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog2.testencryption;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author greno_000
 */
public class TestEncryption {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String clé = "";

        try {
            Encryption encryption = new Encryption();
            String target = "123456";
            String encrypted = encryption.encrypt(target);
            String decrypted = encryption.decrypt(encrypted);

            System.out.println("String To Encrypt: " + target);
            System.out.println("Encrypted String:" + encrypted);
            System.out.println("Decrypted String:" + decrypted);
        } catch (Exception ex) {
            Logger.getLogger(TestEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
