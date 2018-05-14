/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muse.util;

import javax.swing.JOptionPane;

/**
 *
 * @author Kennedy
 */
public class Mensagem {

    
    public static void erroMsg() {
        
        JOptionPane.showMessageDialog(null, "Houve um erro na execução\nPor favor, contate o suporte \nNº erro: #404", "Erro fatal", 0);
    }
    
    public static void erroMsg(String msg) {
                
        JOptionPane.showMessageDialog(null, msg, "Algo deu errado", 0);               
    }
    public static void erroMsg(String msg, String titulo) {
                
        JOptionPane.showMessageDialog(null, msg, titulo, 0);               
    }
    
    public static void alertaMsg(String msg) {
                
        JOptionPane.showMessageDialog(null, msg, "Alerta", 2);               
    }
    
    public static void alertaMsg(String msg, String titulo) {
                
        JOptionPane.showMessageDialog(null, msg, titulo, 2);               
    }
    
    public static void alertaMsg(int x) {
         
        //Erros LOG, TO DO.
    }   
    
    public static void okMsg(String msg) {
                
        JOptionPane.showMessageDialog(null, msg, "Sucesso", 1);               
    }
    
    public static void okMsg(String msg, String titulo) {
                
        JOptionPane.showMessageDialog(null, msg, titulo, 1);               
    }
    
    
    
}
