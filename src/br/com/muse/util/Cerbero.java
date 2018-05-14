/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
/**
 *
 * @author kennedy
 */
public class Cerbero {

    
    public Cerbero() {
      
    }
    
    public static String crypt256(String s) {        
        
        String coded = "";
        
        try {
            
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
            StringBuilder hexString = new StringBuilder();
            byte hash[] = algoritmo.digest(s.getBytes("UTF-8"));
            
            
            for (byte b : hash) {

                hexString.append(String.format("%02X", 0xFF & b));
            }

            coded = hexString.toString();
            
            
            }
            catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                    
                Mensagem.erroMsg("Não foi possivel criptografar\nContate o suporte\nO Muse será trancado até liberação manual!", "Falha de segurança");
            }
            
        //new String(s.getBytes("ISO-8859-1"), "UTF-8");
        
        return coded;
    }
    
    public static String cryptMD5(String s) {
        
        MessageDigest m;
        
        try {
            
            m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(),0,s.length());
            BigInteger i = new BigInteger(1, m.digest());            
            s = String.format("%1$032X", i); 
 
        }
        catch (NoSuchAlgorithmException ex) {
            
            Mensagem.alertaMsg(0);
        }
        
        return s;
    }
    
    
    
    public static String crypt64(String s) {
        
        final byte[] authBytes = s.getBytes(StandardCharsets.UTF_8);
       
        return Base64.getEncoder().encodeToString(authBytes);
    }
    
    public static String decrypt64(String s) {
        
       final byte[] authBytes = Base64.getDecoder().decode(s);
           
        return new String(authBytes);
    }
     
}
