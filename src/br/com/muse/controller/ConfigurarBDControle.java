/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.controller;

import br.com.muse.util.Cerbero;
import br.com.muse.util.Mensagem;
import br.com.muse.util.Propriedade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author kennedy
 */
public class ConfigurarBDControle implements PropertieManager<String> {
    
    
    private Connection conexao = null;
    
    @Override
    public void alterar(ArrayList<String> p) {
        
        Propriedade.setProp("conexao.properties", "host", Cerbero.crypt64(p.get(0)));
        Propriedade.setProp("conexao.properties", "porta", Cerbero.crypt64(p.get(1)));
        Propriedade.setProp("conexao.properties", "base", Cerbero.crypt64(p.get(2)));        
        Propriedade.setProp("conexao.properties", "usuario", Cerbero.crypt64(p.get(3)));
        Propriedade.setProp("conexao.properties", "senha", Cerbero.crypt64(p.get(4)));
    }
    
    @Override
    public ArrayList<String> carregar() {
        
        ArrayList<String> permissoes = new ArrayList<>();
        
        permissoes.add(Cerbero.decrypt64(Propriedade.getProp("conexao.properties", "host")));
        permissoes.add(Cerbero.decrypt64(Propriedade.getProp("conexao.properties", "porta")));
        permissoes.add(Cerbero.decrypt64(Propriedade.getProp("conexao.properties", "base")));        
        permissoes.add(Cerbero.decrypt64(Propriedade.getProp("conexao.properties", "usuario")));
        permissoes.add(Cerbero.decrypt64(Propriedade.getProp("conexao.properties", "senha")));
        
        return permissoes;
    }
    
    @Override
    public void redefinir() {
        
        Propriedade.resetPropFile("conexao");
    }
    
    @Override
    public boolean autenticar(String senha) {
                
        String senhaGravada = Propriedade.getProp("museconfig.properties", "muse.senha");
                
        if(Cerbero.crypt256(senha).equals(senhaGravada)) return true;
        
        return false;
    }
    
    public boolean testarConexao(String host, String porta, String base, String usuario, String senha) {
        
        try {
            
            //Driver padrao
            Class.forName(Cerbero.decrypt64("Y29tLm15c3FsLmpkYmMuRHJpdmVy"));            
            String url = Cerbero.decrypt64("amRiYzpteXNxbDovLw==");
            String path = url+host+":"+porta+"/"+base;
            
            this.conexao = DriverManager.getConnection(path, usuario, senha);
            
            if(conexao != null) return true;
            
        }
        catch (ClassNotFoundException | SQLException ex) {
            
            Mensagem.alertaMsg(0);                    
        }
        
        return false;
    }
    
}
