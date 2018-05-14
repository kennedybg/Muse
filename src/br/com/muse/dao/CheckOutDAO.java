/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.dao;

import br.com.muse.beans.ApartamentoBean;
import br.com.muse.beans.CheckOutBean;
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
public class CheckOutDAO implements DAO<CheckOutBean, Integer, Boolean>{
    
    private final Conexao c;        
    private CheckOutBean checkOut = new CheckOutBean();
    private PreparedStatement sentenca = null;
    private ResultSet rs = null;
    
    protected static final String[] SQL_CMD = {
				
	"INSERT INTO check_outs (id_hospede_check_out, id_quarto_check_out, valorFatura) VALUES (?,?,?)",
        "SELECT * FROM check_outs WHERE id_check_out=?",
	"UPDATE check_outs SET id_hospede_check_out=?,id_quarto_check_out=?, valorFatura=? WHERE id_check_out=?",
	"DELETE FROM check_outs WHERE id_check_out=?",
        "SELECT * FROM check_outs",
        "SELECT * FROM check_outs WHERE id_check_out LIKE ?"
    };

    public CheckOutDAO() {
        
        this.c = new Conexao();
    }
    
    @Override
    public Boolean inserir(CheckOutBean modelo) {

        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[0]);            
            sentenca.setInt(1, modelo.getId_hospede());
            sentenca.setInt(2, modelo.getId_quarto());
            sentenca.setDouble(3, modelo.getValorFatura());
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao inserir o check-out!");            
            return false;
        }
        finally {            
            
            c.desconectar();
        }
        
        
    }

    @Override
    public CheckOutBean buscar(Integer id) {
        
        c.conectar();
        checkOut = new CheckOutBean();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[1]);            
            sentenca.setInt(1, id);            
            rs = sentenca.executeQuery();
            
            if(rs.next()) {
                
                checkOut.setId_check_out(rs.getInt("id_check_out"));
                checkOut.setId_hospede(rs.getInt("id_hospede_check_out"));
                checkOut.setId_quarto(rs.getInt("id_quarto_check_out"));
                checkOut.setValorFatura(rs.getDouble("valorFatura"));         
            }
            
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao buscar os dados do check-out");            
        }
        finally {            
            
            c.desconectar();
        }
        
        return checkOut;
    }

    @Override
    public Boolean atualizar(Integer id, CheckOutBean modelo) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[2]);                        
            sentenca.setInt(1, modelo.getId_hospede());
            sentenca.setInt(2, modelo.getId_quarto());            
            sentenca.setDouble(3, modelo.getValorFatura());            
            sentenca.setInt(4, id);
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao atualizar os dados do check-out");
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
            
            Mensagem.erroMsg("Erro ao remover o check-out!");            
            return false;
        }
        finally {        
            
            c.desconectar();
        }
    }
    
    @Override
    public ArrayList<CheckOutBean> listar() {
        
        ArrayList<CheckOutBean> lista = new ArrayList<>();
        
        c.conectar();
        ResultSet rs = null;
        CheckOutBean obj = new CheckOutBean();
        
        try {            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[4]);           
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                obj = new CheckOutBean();                
                obj.setId_check_out(rs.getInt("id_check_in"));
                obj.setId_hospede(rs.getInt("id_hospede_check_out"));
                obj.setId_quarto(rs.getInt("id_quarto_check_out"));
                obj.setValorFatura(rs.getDouble("valorFatura"));
                
                lista.add(obj);
            }
           rs.close();
           sentenca.close(); 
        }
        catch (SQLException ex) {       
            Mensagem.erroMsg("Erro ao listar os check-outs efetuados!");
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
                
                ApartamentoBean ap = apartamentosC.buscarPorId(rs.getInt("id_quarto_check_out"));
                HospedeBean hospede = hospedeC.buscarPorId(rs.getInt("id_hospede_check_out"));
                double fatura = rs.getDouble("valorFatura");
                
                modelo.addRow(new Object[] {rs.getInt("id_check_out"), hospede, ap, fatura });
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
                
                ApartamentoBean ap = apartamentosC.buscarPorId(rs.getInt("id_quarto_check_out"));
                HospedeBean hospede = hospedeC.buscarPorId(rs.getInt("id_hospede_check_out"));
                double fatura = rs.getDouble("valorFatura");
                
                modelo.addRow(new Object[] {rs.getInt("id_check_out"), hospede, ap, fatura });
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
