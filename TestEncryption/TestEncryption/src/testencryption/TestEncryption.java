/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testencryption;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.management.openmbean.InvalidKeyException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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
