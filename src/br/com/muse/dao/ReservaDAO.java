/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.dao;

import br.com.muse.beans.ApartamentoBean;
import br.com.muse.beans.HospedeBean;
import br.com.muse.beans.ReservaBean;
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
public class ReservaDAO implements DAO<ReservaBean, Integer, Boolean>{
    
    private final Conexao c;        
    private ReservaBean reserva = new ReservaBean();
    private PreparedStatement sentenca = null;
    private ResultSet rs = null;
    
    protected static final String[] SQL_CMD = {
				
	"INSERT INTO reservas (id_hospede_reserva, id_quarto_reserva, data_entrada, data_saida, adultos, criancas, diaria, valorTotal, qtd_reservas) VALUES (?,?,?,?,?,?,?,?,?)",
        "SELECT * FROM reservas WHERE id_reserva=?",
	"UPDATE reservas SET id_hospede_reserva=?, id_quarto_reserva=?, data_entrada=?, data_saida=?, adultos=?, criancas=?, diaria=?, valorTotal=?, qtd_reservas=? WHERE id_reserva=?",
	"DELETE FROM reservas WHERE id_reserva=?",
        "SELECT * FROM reservas",
        "SELECT * FROM reservas WHERE id_reserva LIKE ?",
        "SELECT * FROM reservas WHERE id_hospede_reserva=? AND id_quarto_reserva=?"
    };

    public ReservaDAO() {
        
        
        this.c = new Conexao();
    }
    
    @Override
    public Boolean inserir(ReservaBean modelo) {

        c.conectar();

        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[0]);            
            
            sentenca.setInt(1, modelo.getId_hospede());
            sentenca.setInt(2, modelo.getId_quarto());
            sentenca.setString(3, modelo.getData_entrada());
            sentenca.setString(4, modelo.getData_saida());
            sentenca.setInt(5, modelo.getAdultos());
            sentenca.setInt(6, modelo.getCriancas());
            sentenca.setDouble(7, modelo.getDiaria());
            sentenca.setDouble(8, modelo.getValorTotal());
            sentenca.setInt(9, modelo.getQtdReservas());
            
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, e);
            Mensagem.erroMsg("Erro ao inserir a reserva!");            
            return false;
        }
        finally {            
            
            c.desconectar();
        }
        
        
    }

    @Override
    public ReservaBean buscar(Integer id) {
        
        c.conectar();
        reserva = new ReservaBean();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[1]);            
            sentenca.setInt(1, id);            
            rs = sentenca.executeQuery();
            
            if(rs.next()) {
                
                reserva.setId_reserva(rs.getInt("id_reserva"));
                reserva.setId_hospede(rs.getInt("id_hospede_reserva"));
                reserva.setId_quarto(rs.getInt("id_quarto_reserva"));
                reserva.setData_entrada(rs.getString("data_entrada"));
                reserva.setData_saida(rs.getString("data_saida"));
                reserva.setAdultos(rs.getInt("adultos"));
                reserva.setCriancas(rs.getInt("criancas"));
                reserva.setDiaria(rs.getDouble("diaria"));
                reserva.setValorTotal(rs.getDouble("valorTotal"));                   
                reserva.setQtdReservas(rs.getInt("qtd_reservas"));
            }

            rs.close();
            
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao buscar os dados da reserva!");            
        }
        finally {            
            
            c.desconectar();
        }
        
        return reserva;
    }

    @Override
    public Boolean atualizar(Integer id, ReservaBean modelo) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[2]);                        
                                   
            sentenca.setInt(1, modelo.getId_hospede());
            sentenca.setInt(2, modelo.getId_quarto());
            sentenca.setString(3, modelo.getData_entrada());
            sentenca.setString(4, modelo.getData_saida());
            sentenca.setInt(5, modelo.getAdultos());
            sentenca.setInt(6, modelo.getCriancas());
            sentenca.setDouble(7, modelo.getDiaria());
            sentenca.setDouble(8, modelo.getValorTotal());
            sentenca.setInt(9, modelo.getQtdReservas());
            
            sentenca.setInt(10, id);
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, e);
            Mensagem.erroMsg("Erro ao atualizar os dados da reserva!");
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
            
            Mensagem.erroMsg("Erro ao remover a reserva!");            
            return false;
        }
        finally {        
            
            c.desconectar();
        }
    }
    
    @Override
    public ArrayList<ReservaBean> listar() {
        
        ArrayList<ReservaBean> lista = new ArrayList<>();
        
        c.conectar();
        ResultSet rs = null;
        ReservaBean obj = new ReservaBean();
        
        try {            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[4]);           
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                obj = new ReservaBean();                
                obj.setId_reserva(rs.getInt("id_reserva"));
                obj.setId_hospede(rs.getInt("id_hospede_reserva"));
                obj.setId_quarto(rs.getInt("id_quarto_reserva"));
                obj.setData_entrada(rs.getString("data_entrada"));
                obj.setData_saida(rs.getString("data_saida"));
                obj.setAdultos(rs.getInt("adultos"));
                obj.setCriancas(rs.getInt("criancas"));
                obj.setDiaria(rs.getDouble("diaria"));
                obj.setValorTotal(rs.getDouble("valorTotal"));
                obj.setQtdReservas(rs.getInt("qtd_reservas"));

                lista.add(obj);
            }
           rs.close();
           sentenca.close(); 
        }
        catch (SQLException ex) {       
            Mensagem.erroMsg("Erro ao listar as reservas!");
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

            
                ApartamentoBean ap = apartamentosC.buscarPorId(rs.getInt("id_quarto_reserva"));
                HospedeBean hospede = hospedeC.buscarPorId(rs.getInt("id_hospede_reserva"));
                
                modelo.addRow(new Object[] {rs.getInt("id_reserva"), hospede, ap.getNum_quarto(), rs.getString("data_entrada"), rs.getString("data_saida")});
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
                
                ApartamentoBean ap = apartamentosC.buscarPorId(rs.getInt("id_quarto_reserva"));
                HospedeBean hospede = hospedeC.buscarPorId(rs.getInt("id_hospede_reserva"));
                modelo.addRow(new Object[] {rs.getInt("id_reserva"), hospede, ap.getNum_quarto(), rs.getString("data_entrada"), rs.getString("data_saida")});
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(HospedeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }
    }
    
    public ReservaBean buscarPorDados(int id_hospede, int id_quarto) {
        
        c.conectar();
        reserva = new ReservaBean();
                
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[6]);   
            sentenca.setInt(1, id_hospede);
            sentenca.setInt(2, id_quarto);
            rs = sentenca.executeQuery();
            
            if(rs.next()) reserva = buscar(rs.getInt("id_reserva"));
              
        }
        catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }
        
        return reserva;
    }
    
}
