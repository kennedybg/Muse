/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.controller;

import br.com.muse.beans.ContatoBean;
import br.com.muse.dao.ContatoDAO;

/**
 *
 * @author kennedy
 */
public class ContatoControle {
    
    
    
    ContatoDAO contatoD = new ContatoDAO();
    
    public boolean cadastrar(ContatoBean contato) {
        
        return contatoD.inserir(contato);
    }
    
    public int recuperarIdContato(String celular1, String vinculo) {
        
        int id = contatoD.recuperarID(celular1, vinculo);        
        contatoD.desvincular(celular1, vinculo);
        
        return id;
    }
    
    public ContatoBean buscarContato(int id) {
        
        return contatoD.buscar(id);
    }
    
    public boolean atualizarContato(int id, ContatoBean contato) {
        
        return contatoD.atualizar(id, contato);
    }
    
}
