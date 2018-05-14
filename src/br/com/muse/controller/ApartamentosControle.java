/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.controller;

import br.com.muse.beans.ApartamentoBean;
import br.com.muse.dao.ApartamentoDAO;
import br.com.muse.util.Cerbero;
import br.com.muse.util.Propriedade;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kennedy
 */
public class ApartamentosControle {
    
    ApartamentoDAO apartamentoD = new ApartamentoDAO();
    
    //Solicita ao DAO o cadastro de um novo funcionario.
    public boolean cadastrar(ApartamentoBean apartamentoB) {
        
        return apartamentoD.inserir(apartamentoB);
    }
    
    public boolean autenticar(String senha) {
                
        String senhaGravada = Propriedade.getProp("museconfig.properties", "muse.senha");
                
        if(Cerbero.crypt256(senha).equals(senhaGravada)) return true;
        
        return false;
    }
    
    public boolean atualizarApartamento(int id, ApartamentoBean apartamentoB) {
        
        return apartamentoD.atualizar(id, apartamentoB);
    }
    
    public Object[] listarApartamentos() {
        
        return apartamentoD.listarApartamentos().toArray();
    }
    
    public ApartamentoBean buscarApartamento(String num_quarto) {
        
        return apartamentoD.buscarPorNum(num_quarto);
    }
    
    public ApartamentoBean buscarPorId(int id) {
        
        return apartamentoD.buscar(id);
    }
    
    public boolean removerApartamento(int id) {
        
        return apartamentoD.remover(id);
    }
    
    public void mostrarApartamentos(DefaultTableModel modelo) {
        
        apartamentoD.mostrar(modelo);
    }
    
    public ArrayList<ApartamentoBean> listarTodos() {
        
        return apartamentoD.listar();
    }
    
}
