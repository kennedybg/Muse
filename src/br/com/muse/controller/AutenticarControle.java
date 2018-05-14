/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.controller;

import br.com.muse.dao.AutenticarDAO;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author kennedy
 */
public class AutenticarControle {

    public AutenticarControle() {
                
        
    }
    
    public int verificarLogin(JTextField usuario, JPasswordField senha) {
        
        AutenticarDAO autenticarD = new AutenticarDAO();
        
        return autenticarD.logar(usuario.getText(), new String(senha.getPassword()));        
    }
    
}
