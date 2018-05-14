/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.util;


import java.text.ParseException;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author kennedy
 */
public class Utilidades {
    

    //Teste...
    public static String paraSTRING(String s) {
        
        return s.substring(8,10)+"/"+s.substring(5, 7)+"/"+s.substring(0, 4);
    }        
    //Teste...
    public static String paraSQL(String s) {
        
        return s.substring(6,10)+"-"+s.substring(3, 5)+"-"+s.substring(0, 2);
    }
    
    public static boolean textoVazio(JTextField s) {
        
        if(s.getText().isEmpty()) return true;
        
        else return false;
    }
    
    public static boolean senhaVazia(JPasswordField ps) {
        
        if(ps.getPassword().length == 0) return true;
        
        else return false;
    }
    //Teste...
    public static String mascarar(String pattern, Object value) {
        
        MaskFormatter mask;
        
        try {
            
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            
            return mask.valueToString(value);
        }
        catch (ParseException e) {
            
            throw new RuntimeException(e);
        }
    }
    
}
