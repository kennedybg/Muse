/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.controller;

import java.util.ArrayList;

/**
 *
 * @author kennedy
 * @param <Wrapper>
 */
public interface PropertieManager<Wrapper> {
    
    public void alterar(ArrayList<Wrapper> p);
    
    public ArrayList<Wrapper> carregar();
    
    public void redefinir();    
    
    public boolean autenticar(String senha);
        
}
