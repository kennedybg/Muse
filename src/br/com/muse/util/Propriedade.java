/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author kennedy
 */
public abstract class Propriedade {

    private static FileInputStream fileIn;
    private static FileOutputStream fileOut;
    private static Properties props = new Properties();
    
    public static void createPropFile(String arquivo, String key, String subKey,String padrao, int start, int end) {
              
        try {
            
            props = new Properties();
            
            for(int x=start;x<=end;x++){
                
                if(subKey == null)  props.setProperty(key+x, padrao);
                
                else props.setProperty(key+"."+subKey+x, padrao);                
                               
                System.out.println(key+x+" "+ padrao);
            }
            
            File saida = new File("properties/"+arquivo+".properties");

            fileOut = new FileOutputStream(saida);
            props.store(fileOut,null);
        }
        catch (IOException ex) {
            
            Mensagem.erroMsg("Erro ao criar arquivo de propriedades!", "Erro de arquivo");
        }
        
    }
    
    public static void resetPropFile(String arquivo) {
              
        try {
            
            fileIn = new FileInputStream("properties/"+arquivo+"BKP.properties");
            props = new Properties();
            props.load(fileIn);
            fileIn.close();
            fileOut = new FileOutputStream("properties/"+arquivo+".properties");
            props.store(fileOut,null);
        }
        catch (IOException ex) {
            
            Mensagem.erroMsg("Erro ao redefinir arquivo de propriedades!", "Erro de arquivo");
        }
        
    }
    
    public static void setProp(String arquivo, String key, String value) {
              
        try {
            
            fileIn = new FileInputStream("properties/"+arquivo);
            props = new Properties();
            props.load(fileIn);
            fileIn.close();
            fileOut = new FileOutputStream("properties/"+arquivo);
            props.setProperty(key, value);            
            props.store(fileOut,null);
            
        }
        catch (IOException ex) {
            
            Mensagem.erroMsg("Erro ao definir propriedades!", "Erro de arquivo");
        }
        
        
        
    }
    
    public static String getProp(String arquivo, String key) {
            
        String s = "";
        
        try {
            
            
            Properties config = new Properties();  
            
            InputStream inFile = new FileInputStream((new File("properties/"+arquivo)).getAbsoluteFile());  
            config.load(inFile);  
            inFile.close();
            
            s = config.getProperty(key);            
            
           
        }
        catch (IOException ex) {
            
            Mensagem.erroMsg("Erro ao acessar propriedade!", "Erro de arquivo");
        }
        
        
        return s;
    }
    
    
}
