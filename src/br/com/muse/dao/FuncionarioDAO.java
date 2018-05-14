/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.dao;

import br.com.muse.beans.FuncionarioBean;
import br.com.muse.util.Cerbero;
import br.com.muse.util.DAO;
import br.com.muse.util.Mensagem;
import br.com.muse.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kennedy
 */
public class FuncionarioDAO implements DAO<FuncionarioBean, Integer, Boolean>{
    
    private final Conexao c;        
    private FuncionarioBean funcionario = new FuncionarioBean();
    private PreparedStatement sentenca = null;
    private ResultSet rs = null;
    
    protected static final String[] SQL_CMD = {
				
	"INSERT INTO funcionarios (nome, id_contato_funcionario, id_endereco_funcionario, login, senha, nivel_acesso, sobrenome, ativo) VALUES (?,?,?,?,?,?,?,?)",
        "SELECT * FROM funcionarios WHERE id_funcionario=?",
	"UPDATE funcionarios SET nome=?, sobrenome=?, id_contato_funcionario=?, id_endereco_funcionario=?, login=?, senha=?, nivel_acesso=?, ativo=? WHERE id_funcionario=?",
	"DELETE FROM funcionarios WHERE id_funcionario=?",
        "SELECT * FROM funcionarios",
        "SELECT * FROM funcionarios WHERE upper(nome) LIKE ? OR upper(sobrenome) LIKE ? OR upper(login) LIKE ? OR nivel_acesso LIKE ? OR upper(ativo) LIKE ?",
        "SELECT id_funcionario FROM funcionarios WHERE login=?"
        
    };

    public FuncionarioDAO() {
        
        this.c = new Conexao();
    }
    
    public int recuperarID(String login) {
            
        c.conectar();
        int id = 0;
        
        try {
                    
            sentenca = c.getConexao().prepareStatement(SQL_CMD[6]);            
            sentenca.setString(1, login);            
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                id = rs.getInt("id_funcionario");
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

    @Override
    public Boolean inserir(FuncionarioBean modelo) {

        c.conectar();
                
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[0]);            
            
            sentenca.setString(1, modelo.getNome());
            sentenca.setInt(2, modelo.getId_contato());
            sentenca.setInt(3, modelo.getId_endereco());
            sentenca.setString(4, modelo.getLogin());
            sentenca.setString(5, Cerbero.crypt256(modelo.getSenha()));
            sentenca.setInt(6, modelo.getNivel_acesso());            
            sentenca.setString(7, modelo.getSobreNome());
            sentenca.setBoolean(8, modelo.isAtivo());
                                   
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch (SQLException ex) {  
                    
            Mensagem.erroMsg("Erro ao inserir o funcionario!");            
            System.out.println("#######################################################################");
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally {            
            
            c.desconectar();
        }
        
        
    }

    @Override
    public FuncionarioBean buscar(Integer id) {
        
        c.conectar();
        funcionario = new FuncionarioBean();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[1]);            
            sentenca.setInt(1, id);            
            rs = sentenca.executeQuery();
            
            if(rs.next()) {
                
                funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSobreNome(rs.getString("sobrenome"));
                funcionario.setId_contato(rs.getInt("id_contato_funcionario"));
                funcionario.setId_endereco(rs.getInt("id_endereco_funcionario"));
                funcionario.setLogin(rs.getString("login"));                                
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setNivel_acesso(rs.getInt("nivel_acesso"));
                funcionario.setAtivo(rs.getBoolean("ativo"));                       
            }
            
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao buscar os dados do funcionario!");            
        }
        finally {            
            
            c.desconectar();
        }
        
        return funcionario;
    }

    @Override
    public Boolean atualizar(Integer id, FuncionarioBean modelo) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[2]);                        
                       
            sentenca.setString(1, modelo.getNome());
            sentenca.setString(2, modelo.getSobreNome());
            sentenca.setInt(3, modelo.getId_contato());
            sentenca.setInt(4, modelo.getId_endereco());
            sentenca.setString(5, modelo.getLogin());
            sentenca.setString(6, modelo.getSenha());
            sentenca.setInt(7, modelo.getNivel_acesso());
            sentenca.setBoolean(8, modelo.isAtivo());
            sentenca.setInt(9, id);
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao atualizar os dados do funcionario!");
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
            
            Mensagem.erroMsg("Erro ao remover o funcionario!");  
            return false;
        }
        finally {        
            
            c.desconectar();
        }
    }
    
    @Override
    public ArrayList<FuncionarioBean> listar() {
        
        ArrayList<FuncionarioBean> lista = new ArrayList<>();
        
        c.conectar();
        ResultSet rs = null;
        FuncionarioBean obj = new FuncionarioBean();
        
        try {            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[4]);           
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                obj = new FuncionarioBean();                
                obj.setId_funcionario(rs.getInt("id_funcionario"));
                obj.setNome(rs.getString("nome"));
                obj.setSobreNome(rs.getString("sobrenome"));
                obj.setId_contato(rs.getInt("id_contato_funcionario"));
                obj.setId_endereco(rs.getInt("id_endereco_funcionario"));
                obj.setLogin(rs.getString("login"));                
                obj.setNivel_acesso(rs.getInt("nivel_acesso"));                
                obj.setAtivo(rs.getBoolean("ativo"));
 
                lista.add(obj);
            }
           rs.close();
           sentenca.close(); 
        }
        catch (SQLException ex) {       
            Mensagem.erroMsg("Erro ao listar os funcionários!");
        }       
        finally {
            
            c.desconectar();
        }
        return lista;
    }
    
    public void pesquisar(DefaultTableModel modelo, String entrada) {
        
        c.conectar();
        
        entrada = entrada.toUpperCase();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[5]);            
            sentenca.setString(1, "%"+entrada+"%");
            sentenca.setString(2, "%"+entrada+"%");
            sentenca.setString(3, "%"+entrada+"%");
            sentenca.setString(4, entrada);
            
            if(entrada.equalsIgnoreCase("SIM")) entrada = "1";
            else if(entrada.equalsIgnoreCase("NÂO") || entrada.equalsIgnoreCase("NAO")) entrada = "0";
            
            sentenca.setString(5, entrada);
            rs = sentenca.executeQuery();
            
            while(rs.next()) {

                modelo.addRow(new Object[] {rs.getString("nome"), rs.getString("sobrenome"), rs.getString("login"), rs.getString("nivel_acesso"), (rs.getString("ativo").equals("1")? "SIM" : "NAO")});                       
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }

    }
    
    public FuncionarioBean buscarPorLogin(String login) {
        
        c.conectar();
        funcionario = new FuncionarioBean();
                
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[6]);   
            sentenca.setString(1, login);
            rs = sentenca.executeQuery();
            
            if(rs.next()) funcionario = buscar(rs.getInt("id_funcionario"));
              
        }
        catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }
        
        return funcionario;
    }
        
}
