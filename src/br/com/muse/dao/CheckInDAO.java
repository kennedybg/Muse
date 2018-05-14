/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.dao;

import br.com.muse.beans.ApartamentoBean;
import br.com.muse.beans.CheckInBean;
import br.com.muse.beans.HospedeBean;
import br.com.muse.controller.ApartamentosControle;
import br.com.muse.controller.HospedeControle;
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
public class CheckInDAO implements DAO<CheckInBean, Integer, Boolean>{
    
    private final Conexao c;        
    private CheckInBean checkIn = new CheckInBean();
    private PreparedStatement sentenca = null;
    private ResultSet rs = null;
    
    protected static final String[] SQL_CMD = {
				
	"INSERT INTO check_ins (id_hospede_check_in, id_quarto_check_in) VALUES (?,?)",
        "SELECT * FROM check_ins WHERE id_check_in=?",
	"UPDATE check_ins SET id_hospede_check_in=?,id_quarto_check_in=? WHERE id_check_in=?",
	"DELETE FROM check_ins WHERE id_check_in=?",
        "SELECT * FROM check_ins",
        "SELECT * FROM check_ins WHERE id_check_in LIKE ?"
    };

    public CheckInDAO() {
        
        this.c = new Conexao();
    }
    
    @Override
    public Boolean inserir(CheckInBean modelo) {

        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[0]);            
            sentenca.setInt(1, modelo.getId_hospede());
            sentenca.setInt(2, modelo.getId_quarto());            
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            Logger.getLogger(CheckInDAO.class.getName()).log(Level.SEVERE, null, e);
            Mensagem.erroMsg("Erro ao inserir o check-in!");            
            return false;
        }
        finally {            
            
            c.desconectar();
        }
        
        
    }

    @Override
    public CheckInBean buscar(Integer id) {
        
        c.conectar();
        checkIn = new CheckInBean();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[1]);            
            sentenca.setInt(1, id);            
            rs = sentenca.executeQuery();
            
            if(rs.next()) {
                
                checkIn.setId_check_in(rs.getInt("id_check_in"));
                checkIn.setId_hospede(rs.getInt("id_hospede_check_in"));
                checkIn.setId_quarto(rs.getInt("id_quarto_check_in"));
                
            }
             
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao buscar os dados do check-in!");            
        }
        finally {            
            
            c.desconectar();
        }
        
        return checkIn;
    }

    @Override
    public Boolean atualizar(Integer id, CheckInBean modelo) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[2]);                        
            sentenca.setInt(1, modelo.getId_hospede());
            sentenca.setInt(2, modelo.getId_quarto());            
            sentenca.setInt(3, id);
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao atualizar os dados do check-in");
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
            
            Mensagem.erroMsg("Erro ao remover o check-in!");            
            return false;
        }
        finally {        
            
            c.desconectar();
        }
    }
    
    @Override
    public ArrayList<CheckInBean> listar() {
        
        ArrayList<CheckInBean> lista = new ArrayList<>();
        
        c.conectar();
        ResultSet rs = null;
        CheckInBean obj = new CheckInBean();
        
        try {            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[4]);           
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                obj = new CheckInBean();                
                obj.setId_check_in(rs.getInt("id_check_in"));
                obj.setId_hospede(rs.getInt("id_hospede_check_in"));
                obj.setId_quarto(rs.getInt("id_quarto_check_in"));
                
                lista.add(obj);
            }
           rs.close();
           sentenca.close(); 
        }
        catch (SQLException ex) {       
            Mensagem.erroMsg("Erro ao listar os check-ins efetuados!");
        }       
        finally {
            
            c.desconectar();
        }
        return lista;
    }
    
    public void mostrar(DefaultTableModel modelo) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[4]);            
            rs = sentenca.executeQuery();

            ApartamentosControle apartamentosC = new ApartamentosControle();
            HospedeControle hospedeC = new HospedeControle();
            
            while(rs.next()) {
                
                ApartamentoBean ap = apartamentosC.buscarPorId(rs.getInt("id_quarto_check_in"));
                HospedeBean hospede = hospedeC.buscarPorId(rs.getInt("id_hospede_check_in"));

                modelo.addRow(new Object[] {rs.getInt("id_check_in"), hospede, ap });
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }
        
        

    }
    public void pesquisar(DefaultTableModel modelo, String entrada) {
        
        c.conectar();
        
        entrada = entrada.toUpperCase();
                
        try {
                        
            sentenca = c.getConexao().prepareStatement(SQL_CMD[5]);            
            sentenca.setString(1, "%"+entrada+"%");
            rs = sentenca.executeQuery();
            
            ApartamentosControle apartamentosC = new ApartamentosControle();
            HospedeControle hospedeC = new HospedeControle();
            
            while(rs.next()) {
                
                ApartamentoBean ap = apartamentosC.buscarPorId(rs.getInt("id_quarto_check_in"));
                HospedeBean hospede = hospedeC.buscarPorId(rs.getInt("id_hospede_check_in"));

                modelo.addRow(new Object[] {rs.getInt("id_check_in"), hospede, ap });
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(HospedeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }
    }
    
    
    
}
