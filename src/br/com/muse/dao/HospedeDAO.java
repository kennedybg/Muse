/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.dao;

import br.com.muse.beans.HospedeBean;
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
public class HospedeDAO implements DAO<HospedeBean, Integer, Boolean>{
    

    private final Conexao c;        
    private HospedeBean hospede = new HospedeBean();
    private PreparedStatement sentenca = null;
    private ResultSet rs = null;
    
    protected static final String[] SQL_CMD = {
				
	"INSERT INTO hospedes (nome, id_endereco_hospede, id_contato_hospede, cpf, rg, sexo, pessoa, profissao, nacionalidade, nascimento, sobrenome) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
        "SELECT * FROM hospedes WHERE id_hospede=?",
	"UPDATE hospedes SET nome=?, id_endereco_hospede=?, id_contato_hospede=?, cpf=?, rg=?, sexo=?, sobrenome=?, pessoa=?, profissao=?, nacionalidade=?, nascimento=? WHERE id_hospede=?",
	"DELETE FROM hospedes WHERE id_hospede=?",
        "SELECT * FROM hospedes",
        "SELECT * FROM hospedes WHERE upper(nome) LIKE ? OR upper(sobrenome) LIKE ? OR cpf LIKE ? OR upper(rg) LIKE ?",
        "SELECT id_hospede FROM hospedes WHERE cpf=?"
    };

    public HospedeDAO() {
        
        this.c = new Conexao();
    }
    
    @Override
    public Boolean inserir(HospedeBean modelo) {

        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[0]);
            sentenca.setString(1, modelo.getNome());
            sentenca.setInt(2, modelo.getId_endereco());
            sentenca.setInt(3, modelo.getId_contato());
            sentenca.setString(4, modelo.getCpf());
            sentenca.setString(5, modelo.getRg());            
            sentenca.setString(6, modelo.getSexo());
            sentenca.setString(7, modelo.getPessoa());
            sentenca.setString(8, modelo.getProfissao());            
            sentenca.setString(9, modelo.getNacionalidade());
            sentenca.setString(10, modelo.getNascimento());
            sentenca.setString(11, modelo.getSobrenome());
                                   
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao inserir o hóspede!");            
            return false;
        }
        finally {            
            
            c.desconectar();
        }
        
        
    }

    @Override
    public HospedeBean buscar(Integer id) {
        
        c.conectar();
        hospede = new HospedeBean();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[1]);            
            sentenca.setInt(1, id);            
            rs = sentenca.executeQuery();
            
            if(rs.next()) {
                
                hospede.setId_hospede(rs.getInt("id_hospede"));
                hospede.setNome(rs.getString("nome"));
                hospede.setSobrenome(rs.getString("sobrenome"));
                hospede.setId_endereco(rs.getInt("id_endereco_hospede"));
                hospede.setId_contato(rs.getInt("id_contato_hospede"));
                hospede.setCpf(rs.getString("cpf"));
                hospede.setRg(rs.getString("rg"));
                hospede.setSexo(rs.getString("sexo"));                
                hospede.setPessoa(rs.getString("pessoa"));                
                hospede.setProfissao(rs.getString("profissao"));
                hospede.setNacionalidade(rs.getString("nacionalidade"));
                hospede.setNascimento(rs.getString("nascimento"));
                
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(HospedeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }
        
        return hospede;
    }

    @Override
    public Boolean atualizar(Integer id, HospedeBean modelo) {
        
        c.conectar();
        
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[2]);                        
                       
            sentenca.setString(1, modelo.getNome());            
            sentenca.setInt(2, modelo.getId_endereco());
            sentenca.setInt(3, modelo.getId_contato());
            sentenca.setString(4, modelo.getCpf());
            sentenca.setString(5, modelo.getRg());
            sentenca.setString(6, modelo.getSexo());            
            sentenca.setString(7, modelo.getSobrenome());
            sentenca.setString(8, modelo.getPessoa());            
            sentenca.setString(9, modelo.getProfissao());
            sentenca.setString(10, modelo.getNacionalidade());
            sentenca.setString(11, modelo.getNascimento());            
            sentenca.setInt(12, id);
            sentenca.execute();
            sentenca.close();
            return true;
        }
        catch(SQLException e) {
            
            Mensagem.erroMsg("Erro ao atualizar os dados do hóspede!");
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
            
            Mensagem.erroMsg("Erro ao remover o hóspede!");            
            return false;
        }
        finally {        
            
            c.desconectar();
        }
    }
    
    @Override
    public ArrayList<HospedeBean> listar() {
        
        ArrayList<HospedeBean> lista = new ArrayList<>();
        
        c.conectar();
        ResultSet rs = null;
        HospedeBean obj = new HospedeBean();
        
        try {            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[4]);           
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                obj = new HospedeBean();                
                obj.setId_hospede(rs.getInt("id_hospede"));
                obj.setNome(rs.getString("nome"));
                obj.setSobrenome(rs.getString("sobrenome"));
                obj.setId_endereco(rs.getInt("id_endereco_hospede"));
                obj.setId_contato(rs.getInt("id_contato_hospede"));
                obj.setCpf(rs.getString("cpf"));
                obj.setRg(rs.getString("rg"));
                obj.setSexo(rs.getString("sexo"));
                obj.setPessoa(rs.getString("pessoa"));
                obj.setProfissao(rs.getString("profissao"));
                obj.setNacionalidade(rs.getString("nacionalidade"));
                obj.setNascimento(rs.getString("nascimento"));

                lista.add(obj);
            }
           rs.close();
           sentenca.close(); 
        }
        catch (SQLException ex) {       
            Mensagem.erroMsg("Erro ao listar os hospedes!");
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
            sentenca.setString(3, entrada);                        
            sentenca.setString(4, entrada);
            rs = sentenca.executeQuery();
            
            while(rs.next()) {
                
                modelo.addRow(new Object[] {rs.getString("nome"), rs.getString("sobrenome"), rs.getString("cpf"), rs.getString("rg")});                       
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(HospedeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }
    }
    
    public HospedeBean buscarPorCpf(String cpf) {
        
        c.conectar();
        hospede = new HospedeBean();
                
        try {
            
            sentenca = c.getConexao().prepareStatement(SQL_CMD[6]);   
            sentenca.setString(1, cpf);
            rs = sentenca.executeQuery();
            
            if(rs.next()) hospede = buscar(rs.getInt("id_hospede"));
              
        }
        catch (SQLException ex) {
            Logger.getLogger(HospedeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {            
            
            c.desconectar();
        }
        
        return hospede;
    }
   
}
