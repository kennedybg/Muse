/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.dao;

import br.com.muse.beans.EmpresaBean;
import br.com.muse.util.DAO;
import br.com.muse.util.Mensagem;
import br.com.muse.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kennedy
 */
public class EmpresaDAO implements DAO<EmpresaBean, Integer, Boolean>{
    
      
    private final Conexao c;        
    private EmpresaBean empresa = new EmpresaBean();
    private PreparedStatement sentenca = null;
    private ResultSet rs = null;
    
    protected static final String[] SQL_CMD = {
				
	"INSERT INTO empresas (nome_fantasia, razao_social, cnpj, id_endereco_empresa, id_contato_empresa) VALUES (?,?,?,?,?)",
        "SELECT * FROM empresas WHERE id_empresa=?",
	"UPDATE empresas SET nome_fantasia=?,razao_social=?, cnpj=?, id_endereco_empresa=?, id_contato_empresa=? WHERE id_empresa=?",
	"DELETE FROM empresas WHERE id_empresa=?",
        "SELECT * FROM empresas"            
    };

    public EmpresaDAO() {
        
        this.c = new Conexao();
    }    
    
    @Override
    public Boolean inserir(EmpresaBean modelo) {

        c.conectar();

        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[0]);            
            
            sentenca.setString(1, modelo.getNome_fantasia());
            sentenca.setString(2, modelo.getRazao_social());
            sentenca.setString(3, modelo.getCnpj());
            sentenca.setInt(4, modelo.getId_endereco());
            sentenca.setInt(5, modelo.getId_contato());
                        
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao inserir a empresa!");            
            return false;
        }
        finally {            
            
            c.desconectar();
        }
        
        
    }

    @Override
    public EmpresaBean buscar(Integer id) {
        
        c.conectar();
        empresa = new EmpresaBean();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[1]);            
            sentenca.setInt(1, id);            
            rs = sentenca.executeQuery();
            
            if(rs.next()) {
                
                empresa.setId_empresa(rs.getInt("id_empresa"));
                empresa.setNome_fantasia(rs.getString("nome_fantasia"));
                empresa.setRazao_social(rs.getString("razao_social"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setId_endereco(rs.getInt("id_endereco_empresa"));
                empresa.setId_contato(rs.getInt("id_contato_empresa"));
      
            }
            
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao buscar os dados da empresa!");            
        }
        finally {            
            
            c.desconectar();
        }
        
        return empresa;
    }

    @Override
    public Boolean atualizar(Integer id, EmpresaBean modelo) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[2]);  
            sentenca.setString(1, modelo.getNome_fantasia());
            sentenca.setString(2, modelo.getRazao_social());
            sentenca.setString(3, modelo.getCnpj());
            sentenca.setInt(4, modelo.getId_endereco());
            sentenca.setInt(5, modelo.getId_contato());            
            sentenca.setInt(6, id);
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao atualizar os dados da empresa!");
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
            
            Mensagem.erroMsg("Erro ao remover a empresa!");            
            return false;
        }
        finally {        
            
            c.desconectar();
        }
    }
    
    @Override
    public ArrayList<EmpresaBean> listar() {
        
        ArrayList<EmpresaBean> lista = new ArrayList<>();
        
        c.conectar();
        ResultSet rs = null;
        EmpresaBean obj = new EmpresaBean();
        
        try {            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[4]);           
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                obj = new EmpresaBean();                
                obj.setId_empresa(rs.getInt("id_empresa"));
                obj.setNome_fantasia(rs.getString("nome_fantasia"));
                obj.setRazao_social(rs.getString("razao_social"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setId_endereco(rs.getInt("id_endereco_empresa"));
                obj.setId_contato(rs.getInt("id_contato_empresa"));

                lista.add(obj);
            }
           rs.close();
           sentenca.close(); 
        }
        catch (SQLException ex) {       
            Mensagem.erroMsg("Erro ao listar as empresas!");
        }       
        finally {
            
            c.desconectar();
        }
        return lista;
    }
    
    
}
