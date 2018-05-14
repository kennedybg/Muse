/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kennedy
 */
public class Conexao {
    
    private Connection conexao;
        
    public Conexao() {
        
        this.conexao = null;               
    }
    
    //Criptografia base64 (super segura a 150 anos atras).
    public void conectar() {
        
        try {
            
            String c = "conexao.properties";
            
            //Driver padrao
            Class.forName(Cerbero.decrypt64("Y29tLm15c3FsLmpkYmMuRHJpdmVy"));
            
            //Configura a conexao atraves do arquivo conexao.properties
            String servidor = Cerbero.decrypt64(Propriedade.getProp(c, "host"));
            
            String base = Cerbero.decrypt64(Propriedade.getProp(c, "base"));
            
            String porta = Cerbero.decrypt64(Propriedade.getProp(c, "porta"));
            
            String url = Cerbero.decrypt64("amRiYzpteXNxbDovLw==");
            
            String path = url+servidor+":"+porta+"/"+base;
            
            String usuario = Cerbero.decrypt64(Propriedade.getProp(c, "usuario"));
            
            String senha = Cerbero.decrypt64(Propriedade.getProp(c, "senha"));
                        
            this.conexao = DriverManager.getConnection(path, usuario, senha);            
        }
        catch(ClassNotFoundException | SQLException e) {
            
            Mensagem.erroMsg("Houve um erro ao conectar com o banco de dados!", "Erro");
        }
               
    }
    
    public void desconectar() {
        
        try {
            
            if(conexao != null) this.conexao.close();                        
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Houve um erro ao desconectar o banco de dados!", "Erro");
        }

    }
    public void reconectar(){
     
        try {
            
            if(!this.conexao.isClosed()) this.desconectar();
                
            this.conectar();
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Houve um erro ao reiniciar o banco de dados!", "Erro");            
        } 
        
    }

    public Connection getConexao() {
        
        return conexao;
    }
    
}
