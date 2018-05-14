/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.dao;

import br.com.muse.beans.ApartamentoBean;
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
public class ApartamentoDAO implements DAO<ApartamentoBean, Integer, Boolean>{
    
    private final Conexao c;        
    private ApartamentoBean quarto = new ApartamentoBean();
    private PreparedStatement sentenca = null;
    private ResultSet rs = null;
    
    protected static final String[] SQL_CMD = {
				
	"INSERT INTO apartamentos (num_quarto) VALUES (?)",
        "SELECT * FROM apartamentos WHERE id_quarto=?",
	"UPDATE apartamentos SET alugado=?, reservado=?, num_quarto=? WHERE id_quarto=?",
	"DELETE FROM apartamentos WHERE id_quarto=?",
        "SELECT * FROM apartamentos",
        "SELECT * FROM apartamentos WHERE num_quarto=?",
        "SELECT * FROM apartamentos WHERE id_andar_quarto=?",
        "SELECT * FROM apartamentos WHERE tipo_quarto=?",
        "SELECT id_quarto FROM apartamentos WHERE num_quarto=?"
    };

    public ApartamentoDAO() {
        
        this.c = new Conexao();
    }
    
    
    @Override
    public Boolean inserir(ApartamentoBean modelo) {

        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[0]);            
            
            sentenca.setInt(1, modelo.getNum_quarto());                                   
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao inserir o apartamento!");            
            return false;
        }
        finally {            
            
            c.desconectar();
        }
        
        
    }

    @Override
    public ApartamentoBean buscar(Integer id) {
        
        c.conectar();
        quarto = new ApartamentoBean();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[1]);            
            sentenca.setInt(1, id);            
            rs = sentenca.executeQuery();
            
            if(rs.next()) {
                
                quarto.setId_quarto(rs.getInt("id_quarto"));               
                quarto.setAlugado(rs.getBoolean("alugado"));
                quarto.setReservado(rs.getBoolean("reservado"));
                quarto.setNum_quarto(rs.getInt("num_quarto"));
            }

            rs.close();
            
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao buscar os dados do quarto!");            
        }
        finally {            
            
            c.desconectar();
        }
        
        return quarto;
    }

    @Override
    public Boolean atualizar(Integer id, ApartamentoBean modelo) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[2]);                        
            
            sentenca.setBoolean(1, modelo.isAlugado());
            sentenca.setBoolean(2, modelo.isReservado());
            sentenca.setInt(3, modelo.getNum_quarto());

            sentenca.setInt(4, id);
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            Logger.getLogger(ApartamentoDAO.class.getName()).log(Level.SEVERE, null, e);
            Mensagem.erroMsg("Erro ao atualizar os dados do quarto!");
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
            
            Mensagem.erroMsg("Erro ao remover o quarto!");      
            return false;
        }
        finally {        
            
            c.desconectar();
        }
    }
    
    public ArrayList<ApartamentoBean> listar() {
        
        ArrayList<ApartamentoBean> lista = new ArrayList<>();
        
        c.conectar();
        ResultSet rs = null;
        ApartamentoBean obj = new ApartamentoBean();
        
        try {            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[4]);           
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                obj = new ApartamentoBean();                
                obj.setId_quarto(rs.getInt("id_quarto"));
                obj.setAlugado(rs.getBoolean("alugado"));
                obj.setReservado(rs.getBoolean("reservado"));
                obj.setNum_quarto(rs.getInt("num_quarto"));
                
                lista.add(obj);
            }
           rs.close();
           sentenca.close(); 
        }
        catch (SQLException ex) {       
            Mensagem.erroMsg("Erro ao listar os quartos!");
        }       
        finally {
            
            c.desconectar();
        }
        return lista;
    }
    
    public void pesquisarNumero(DefaultTableModel modelo, String entrada) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[5]);            
            sentenca.setString(1, entrada);
            
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                modelo.addRow(new Object[] {rs.getString("num_quarto"), rs.getString("id_andar_quarto"), rs.getString("alugado"), rs.getString("reservado"), rs.getString("tipo_quarto")});                       
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(ApartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }

    }
    
    public void pesquisarAndar(DefaultTableModel modelo, String entrada) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[6]);            
            sentenca.setString(1, entrada);
            
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                modelo.addRow(new Object[] {rs.getString("num_quarto"), rs.getString("id_andar_quarto"), rs.getString("alugado"), rs.getString("reservado"), rs.getString("tipo_quarto")});                       
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(ApartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }

    }
    
    public void pesquisarTipo(DefaultTableModel modelo, String entrada) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[7]);            
            sentenca.setString(1, entrada);
            
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                modelo.addRow(new Object[] {rs.getString("num_quarto"), rs.getString("id_andar_quarto"), rs.getString("alugado"), rs.getString("reservado"), rs.getString("tipo_quarto")});                       
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(ApartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }

    }
    
    public ArrayList<String> listarApartamentos() {
        
        ArrayList<String> tipos = new ArrayList<>();
        String s = "";
        c.conectar();
        
        try {            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[4]);           
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                s = new String();
                s = rs.getString("num_quarto");
                tipos.add(s.toUpperCase());                     
            }
           rs.close();
           sentenca.close();
        }
        catch (SQLException ex) {       
            Mensagem.erroMsg("Erro ao listar os apartamentos!");
        }       
        finally {
            
            c.desconectar();
        }

        return tipos;
    }
    
    public ApartamentoBean buscarPorNum(String num_quarto) {
        
        c.conectar();
        quarto = new ApartamentoBean();
                
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[8]);   
            sentenca.setString(1, num_quarto);
            rs = sentenca.executeQuery();
            
            if(rs.next()) quarto = buscar(rs.getInt("id_quarto"));
              
        }
        catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }
        
        return quarto;
    }
    
    public void mostrar(DefaultTableModel modelo) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[4]);            
            rs = sentenca.executeQuery();
            
            while(rs.next()) {

                modelo.addRow(new Object[] {rs.getString("num_quarto"), (rs.getBoolean("alugado"))?"SIM":"NAO", (rs.getBoolean("reservado"))?"SIM":"NAO"});                       
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(ApartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }

    }
       
    
}
