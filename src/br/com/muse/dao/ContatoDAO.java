/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.dao;

import br.com.muse.beans.ContatoBean;
import br.com.muse.util.DAO;
import br.com.muse.util.Mensagem;
import br.com.muse.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kennedy
 */
public class ContatoDAO implements DAO<ContatoBean, Integer, Boolean>{
    
    private final Conexao c;        
    private ContatoBean contato = new ContatoBean();
    private PreparedStatement sentenca = null;
    private ResultSet rs = null;
    
    protected static final String[] SQL_CMD = {
				
	"INSERT INTO contatos (residencial, celular1, celular2, celular3, celular4, fax, caixa_postal, vinculo) VALUES (?,?,?,?,?,?,?,?)",
        "SELECT * FROM contatos WHERE id_contato=?",
	"UPDATE contatos SET residencial=?,celular1=?, celular2=?, celular3=?, celular4=?, fax=?, caixa_postal=?, vinculo=? WHERE id_contato=?",
	"DELETE FROM contatos WHERE id_contato=?",
        "SELECT * FROM contatos",
        "SELECT id_contato FROM contatos WHERE celular1=? AND vinculo=?",
        "UPDATE contatos SET vinculo=? WHERE celular1=? AND vinculo=?"
    };

    public ContatoDAO() {
        
        this.c = new Conexao();
    }
    
    public int recuperarID(String celular1, String vinculo) {
            
        c.conectar();
        int id = 0;
        
        try {
                    
            sentenca = c.getConexao().prepareStatement(SQL_CMD[5]);            
            sentenca.setString(1, celular1);
            sentenca.setString(2, vinculo);     
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                id = rs.getInt("id_contato");
            }
            sentenca.close();
            rs.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            
            c.desconectar();
        }
        
        return id;
    }
    public void desvincular(String celular1, String vinculo) {
        
        c.conectar();
        
        try {
                    
            sentenca = c.getConexao().prepareStatement(SQL_CMD[6]);            
            sentenca.setString(1, "");
            sentenca.setString(2, celular1);     
            sentenca.setString(3, vinculo);
            sentenca.execute(); 
            sentenca.close();
            rs.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            
            c.desconectar();
        }
    }
    
    @Override
    public Boolean inserir(ContatoBean modelo) {

        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[0]);            
            
            sentenca.setString(1, modelo.getResidencial());
            sentenca.setString(2, modelo.getCelular1());
            sentenca.setString(3, modelo.getCelular2());
            sentenca.setString(4, modelo.getCelular3());
            sentenca.setString(5, modelo.getCelular4());
            sentenca.setString(6, modelo.getFax());
            sentenca.setString(7, modelo.getCaixa_postal());
            sentenca.setString(8, modelo.getVinculo());
                        
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch (SQLException ex) {  
                    
            Mensagem.erroMsg("Erro ao inserir o contato!"); 
            System.out.println("#######################################################################");            
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        finally {            
            
            c.desconectar();
        }
        
        
    }

    @Override
    public ContatoBean buscar(Integer id) {
        
        c.conectar();
        contato = new ContatoBean();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[1]);            
            sentenca.setInt(1, id);            
            rs = sentenca.executeQuery();
            
            if(rs.next()) {
                
                contato.setId_contato(rs.getInt("id_contato"));
                contato.setResidencial(rs.getString("residencial"));
                contato.setCelular1(rs.getString("celular1"));
                contato.setCelular2(rs.getString("celular2"));
                contato.setCelular3(rs.getString("celular3"));
                contato.setCelular4(rs.getString("celular4"));                
                contato.setFax(rs.getString("fax"));
                contato.setCaixa_postal(rs.getString("caixa_postal"));
                contato.setVinculo(rs.getString("vinculo"));                      
            }
            sentenca.close();
            rs.close(); 
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao buscar os dados do contato!");            
        }
        finally {            
            
            c.desconectar();
        }
        
        return contato;
    }

    @Override
    public Boolean atualizar(Integer id, ContatoBean modelo) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[2]);                        
                       
            sentenca.setString(1, modelo.getResidencial());
            sentenca.setString(2, modelo.getCelular1());
            sentenca.setString(3, modelo.getCelular2());
            sentenca.setString(4, modelo.getCelular3());
            sentenca.setString(5, modelo.getCelular4());
            sentenca.setString(6, modelo.getFax());
            sentenca.setString(7, modelo.getCaixa_postal());            
            sentenca.setString(8, modelo.getVinculo());            
  
            sentenca.setInt(9, id);
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao atualizar os dados do contato!");
            return false;
        }
        finally {        
            
            c.desconectar();
        }
    }

    @Override
    public Boolean remover(Integer id) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[3]);            
            sentenca.setInt(1, id);
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao remover o contato!");  
            return false;
        }
        finally {        
            
            c.desconectar();
        }
    }
    
    @Override
    public ArrayList<ContatoBean> listar() {
        
        ArrayList<ContatoBean> lista = new ArrayList<>();
        
        c.conectar();
        ResultSet rs = null;
        ContatoBean obj = new ContatoBean();
        
        try {            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[4]);           
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                obj = new ContatoBean();                
                obj.setId_contato(rs.getInt("id_contato"));
                obj.setResidencial(rs.getString("residencial"));
                obj.setCelular1(rs.getString("celular1"));
                obj.setCelular2(rs.getString("celular2"));
                obj.setCelular3(rs.getString("celular3"));
                obj.setCelular4(rs.getString("celular4"));
                obj.setFax(rs.getString("fax"));
                obj.setCaixa_postal(rs.getString("caixa_postal"));
                obj.setVinculo(rs.getString("vinculo"));                
               
                lista.add(obj);
            }
           rs.close();
           sentenca.close(); 
        }
        catch (SQLException ex) {       
            Mensagem.erroMsg("Erro ao listar os contatos!");
        }       
        finally {
            
            c.desconectar();
        }
        return lista;
    }
        
    
}
