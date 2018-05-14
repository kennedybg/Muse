/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.dao;

import br.com.muse.beans.EnderecoBean;
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
public class EnderecoDAO implements DAO<EnderecoBean, Integer, Boolean>{
    
    private final Conexao c;        
    private EnderecoBean endereco = new EnderecoBean();
    private PreparedStatement sentenca = null;
    private ResultSet rs = null;
    
    protected static final String[] SQL_CMD = {
				
	"INSERT INTO enderecos (rua, numero, complemento, cidade, estado, cep, pais, vinculo) VALUES (?,?,?,?,?,?,?,?)",
        "SELECT * FROM enderecos WHERE id_endereco=?",
	"UPDATE enderecos SET rua=?,numero=?, complemento=?, cidade=?, estado=?, cep=?, pais=?, vinculo=? WHERE id_endereco=?",
	"DELETE FROM enderecos WHERE id_endereco=?",
        "SELECT * FROM enderecos",
        "SELECT id_endereco FROM enderecos WHERE cep=? AND vinculo=?",
        "UPDATE enderecos SET vinculo=? WHERE cep=? AND vinculo=?"
    };

    public EnderecoDAO() {
        
        this.c = new Conexao();
    }
    
    public int recuperarID(String cep, String vinculo) {
        
        c.conectar();
        int id = 0;
        
        try {
        
            sentenca = c.getConexao().prepareStatement(SQL_CMD[5]);            
            sentenca.setString(1, cep);          
            sentenca.setString(2, vinculo);
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                                
                id = rs.getInt("id_endereco");
            }
            sentenca.close();
            rs.close();
        }
        catch (SQLException ex) {
            
            Mensagem.erroMsg("Nao foi possivel carregar o endereco!");
        }
        finally {
            
            c.desconectar();            
        }
        
        return id;
    }
    
    public void desvincular(String cep, String vinculo) {
        
        c.conectar();
        
        try {
                    
            sentenca = c.getConexao().prepareStatement(SQL_CMD[6]);            
            sentenca.setString(1, "");
            sentenca.setString(2, cep);     
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
    public Boolean inserir(EnderecoBean modelo) {

        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[0]);            
            
            sentenca.setString(1, modelo.getRua());
            sentenca.setString(2, modelo.getNumero());
            sentenca.setString(3, modelo.getComplemento());
            sentenca.setString(4, modelo.getCidade());
            sentenca.setString(5, modelo.getEstado());
            sentenca.setString(6, modelo.getCep());
            sentenca.setString(7, modelo.getPais());            
            sentenca.setString(8, modelo.getVinculo());                                   
                        
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch (SQLException ex) {  
                    
            Mensagem.erroMsg("Erro ao inserir o endereco!");  
            System.out.println("#######################################################################");
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally {            
            
            c.desconectar();
        }
        
        
    }

    @Override
    public EnderecoBean buscar(Integer id) {
        
        c.conectar();
        endereco = new EnderecoBean();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[1]);            
            sentenca.setInt(1, id);            
            rs = sentenca.executeQuery();
            
            if(rs.next()) {
                
                endereco.setId_endereco(rs.getInt("id_endereco"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setCep(rs.getString("cep"));
                endereco.setPais(rs.getString("pais"));      
                endereco.setVinculo(rs.getString("vinculo"));                            
            }
              
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao buscar os dados do endereço!");            
        }
        finally {            
            
            c.desconectar();
        }
        
        return endereco;
    }

    @Override
    public Boolean atualizar(Integer id, EnderecoBean modelo) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[2]);                        
                       
            sentenca.setString(1, modelo.getRua());
            sentenca.setString(2, modelo.getNumero());
            sentenca.setString(3, modelo.getComplemento());
            sentenca.setString(4, modelo.getCidade());
            sentenca.setString(5, modelo.getEstado());
            sentenca.setString(6, modelo.getCep());
            sentenca.setString(7, modelo.getPais());
            sentenca.setString(8, modelo.getVinculo());            
            
            sentenca.setInt(9, id);
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao atualizar o endereço");
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
            
            Mensagem.erroMsg("Erro ao remover o endereço");            
            return false;
        }
        finally {        
            
            c.desconectar();
        }
    }
    
    @Override
    public ArrayList<EnderecoBean> listar() {
        
        ArrayList<EnderecoBean> lista = new ArrayList<>();
        
        c.conectar();
        ResultSet rs = null;
        EnderecoBean obj = new EnderecoBean();
        
        try {            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[4]);           
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                obj = new EnderecoBean();                
                obj.setId_endereco(rs.getInt("id_endereco"));
                obj.setRua(rs.getString("rua"));
                obj.setNumero(rs.getString("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                obj.setCep(rs.getString("cep"));
                obj.setPais(rs.getString("pais"));
                obj.setVinculo(rs.getString("vinculo"));
                    
                lista.add(obj);
            }
           rs.close();
           sentenca.close(); 
        }
        catch (SQLException ex) {       
            Mensagem.erroMsg("Erro ao listar os endereços!");
        }       
        finally {
            
            c.desconectar();
        }
        return lista;
    }
    
}
