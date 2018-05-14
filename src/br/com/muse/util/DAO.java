/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.util;

import java.util.ArrayList;

/**
 *
 * @author kennedy
 * @param <Bean>
 * @param <Num>
 * @param <Wrapper>
 */
public interface DAO<Bean,Num, Wrapper> {

    public Wrapper inserir(Bean modelo);

    public Bean buscar(Num id);

    public Wrapper atualizar(Num id, Bean modelo);

    public Wrapper remover(Num id);
    
    public ArrayList<Bean> listar();

}