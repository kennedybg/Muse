/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.controller;

import br.com.muse.beans.ApartamentoBean;
import br.com.muse.beans.CheckInBean;
import br.com.muse.dao.CheckInDAO;
import br.com.muse.util.Propriedade;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kennedy
 */
public class CheckInControle {
    
    CheckInDAO checkInD = new CheckInDAO();
    
    public boolean efetuarCheckIn(CheckInBean checkInB) {
        
        ApartamentoBean ap = new ApartamentoBean();
        ApartamentosControle apartamentosC = new ApartamentosControle();
        
        ap = apartamentosC.buscarPorId(checkInB.getId_quarto());
        
        Propriedade.setProp("apartamentos.properties", "apt"+ap.getNum_quarto(), "ALUGADO");
                        
        return checkInD.inserir(checkInB);
    }
    
    public void mostrarCheckIns(DefaultTableModel modelo) {
        
        checkInD.mostrar(modelo);        
    }
    
    public ArrayList<CheckInBean> mostrarTodos() {
        
        return checkInD.listar();
    }
    
    public void pesquisarCheckIn(DefaultTableModel modelo, String entrada) {
        
        modelo.setNumRows(0);
        if(!entrada.isEmpty()) checkInD.pesquisar(modelo, entrada);
    }
    
    public CheckInBean buscarPorId(int id) {
        
        return checkInD.buscar(id);
    }
    
    public boolean remover(int id) {
        
        return checkInD.remover(id);
    }
    
}
