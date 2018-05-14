/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.controller;

import br.com.muse.beans.CheckOutBean;
import br.com.muse.dao.CheckOutDAO;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kennedy
 */
public class CheckOutControle {
    
    
    CheckOutDAO checkOutD = new CheckOutDAO();
    
    public boolean efetuarCheckOut(CheckOutBean checkInB) {
           
        return checkOutD.inserir(checkInB);
    }
    
    public void mostrarCheckOuts(DefaultTableModel modelo) {
        
        checkOutD.mostrar(modelo);
    }
    
    public void pesquisarCheckOut(DefaultTableModel modelo, String entrada) {
        
        modelo.setNumRows(0);
        if(!entrada.isEmpty()) checkOutD.pesquisar(modelo, entrada);
    }
    
}
