/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.controller;

import br.com.muse.beans.ApartamentoBean;
import br.com.muse.beans.ReservaBean;
import br.com.muse.dao.ReservaDAO;
import br.com.muse.util.Cerbero;
import br.com.muse.util.Propriedade;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kennedy
 */
public class ReservaControle {
    
    ReservaDAO reservaD = new ReservaDAO();

    public boolean cadastrar(ReservaBean reservaB) {
        
        ApartamentosControle apartamentosC = new ApartamentosControle();
        
        Propriedade.setProp("apartamentos.properties", "apt"+reservaB.getId_quarto(), "RESERVADO");

        ApartamentoBean ap = apartamentosC.buscarApartamento(String.valueOf(reservaB.getId_quarto()));
        
        ap.setReservado(true);
        apartamentosC.atualizarApartamento(ap.getId_quarto(), ap);        
        
        reservaB.setId_quarto(ap.getId_quarto());
        
        return reservaD.inserir(reservaB);
    }
    
    public boolean atualizarReserva(ReservaBean reservaB) {
        
        ApartamentosControle apartamentosC = new ApartamentosControle();

        ApartamentoBean ap = apartamentosC.buscarApartamento(String.valueOf(reservaB.getId_quarto()));
        
        reservaB.setId_quarto(ap.getId_quarto());
        
        return reservaD.atualizar(reservaB.getId_reserva(), reservaB);
    }
    
    public void pesquisarReserva(DefaultTableModel modelo, String entrada) {
        
        modelo.setNumRows(0);
        if(!entrada.isEmpty()) reservaD.pesquisar(modelo, entrada);
    }
    
    public void mostrarReservas(DefaultTableModel modelo) {
        
        reservaD.mostrar(modelo);
    }
    
    public boolean autenticar(String senha) {
                
        String senhaGravada = Propriedade.getProp("museconfig.properties", "muse.senha");
                
        if(Cerbero.crypt256(senha).equals(senhaGravada)) return true;
        
        return false;
    }
    
    public boolean removerReserva(int id, boolean liberar) {
                       
        ReservaBean buscar = reservaD.buscar(id);
        
        ApartamentosControle apartamentosC = new ApartamentosControle();
        
        ApartamentoBean ap = apartamentosC.buscarPorId(buscar.getId_quarto());
        
        ap.setReservado(false);
        apartamentosC.atualizarApartamento(ap.getId_quarto(), ap);
        
        if(liberar) Propriedade.setProp("apartamentos.properties", "apt"+ap.getNum_quarto(), "CADASTRADO");
        
        return reservaD.remover(id);
    }
    
    public ReservaBean buscarPorPesquisa(int id_hospede, int id_quarto) {
        
        return reservaD.buscarPorDados(id_hospede, id_quarto);
    }
    
    public ReservaBean buscarReserva(int id) {
        
        return reservaD.buscar(id);
    }
    
    public ArrayList<ReservaBean> mostrarTodas() {
        
        return reservaD.listar();
    }
    
}