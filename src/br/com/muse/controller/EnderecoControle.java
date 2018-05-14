/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.controller;

import br.com.muse.beans.EnderecoBean;
import br.com.muse.dao.EnderecoDAO;

/**
 *
 * @author kennedy
 */
public class EnderecoControle {
    
    
    EnderecoDAO enderecoD = new EnderecoDAO();
    
    //Solicita ao DAO o cadastro de um novo endereco.
    public boolean cadastrar(EnderecoBean endereco) {
        
        return enderecoD.inserir(endereco);
    }
    
    
    public int recuperarIdEndereco(String cep, String vinculo) {
        
        int id = enderecoD.recuperarID(cep, vinculo);
        enderecoD.desvincular(cep, vinculo);
        
        return id;
    }
    
    public EnderecoBean buscarEndereco(int id) {
        
        return enderecoD.buscar(id);
    }
    
    public boolean atualizarEndereco(int id, EnderecoBean endereco) {
        
        return enderecoD.atualizar(id, endereco);
    }
    
}
