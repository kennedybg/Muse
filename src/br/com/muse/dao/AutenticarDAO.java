/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.dao;

import br.com.muse.util.Cerbero;
import br.com.muse.util.Mensagem;
import br.com.muse.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kennedy
 */
public class AutenticarDAO {
    
    private final Conexao c;
    private final String SQL_CMD;
    private PreparedStatement sentenca = null;
    private ResultSet rs = null;

    public AutenticarDAO() {
        
        this.c = new Conexao();                
        this.SQL_CMD = "SELECT id_funcionario FROM funcionarios WHERE login=? AND senha=?;";
    }
    
    
    public int logar(String usuario, String senha) {
        
        c.conectar();
          
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD);            
            sentenca.setString(1, usuario);            
            sentenca.setString(2, Cerbero.crypt256(senha));            
            
            rs = sentenca.executeQuery();
            
            if(rs.next()) return rs.getInt("id_funcionario");
                
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao tentar logar no sistema!"); 
            
            return 0;
        }
        finally {            
            
            c.desconectar();
        }
        
        return 0;
    }   
    
    
}
