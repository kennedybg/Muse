/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.controller;

import br.com.muse.beans.ContatoBean;
import br.com.muse.beans.EnderecoBean;
import br.com.muse.beans.FuncionarioBean;
import br.com.muse.dao.FuncionarioDAO;
import br.com.muse.util.Cerbero;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kennedy
 */
public class FuncionarioControle {
    
    FuncionarioDAO funcionarioD = new FuncionarioDAO();
    
    //Solicita ao DAO o cadastro de um novo funcionario.
    public boolean cadastrar(FuncionarioBean funcionarioB, ContatoBean contatoB, EnderecoBean enderecoB) {
        
        Random r = new Random();
        ContatoControle contatoC = new ContatoControle();
        EnderecoControle enderecoC = new EnderecoControle();        
        String vinculo = Cerbero.cryptMD5(String.valueOf(r.nextInt()));                    
        
        
        contatoB.setVinculo(vinculo);
        enderecoB.setVinculo(vinculo);
        
        if(contatoC.cadastrar(contatoB) && enderecoC.cadastrar(enderecoB)) {
                        
            funcionarioB.setId_contato(contatoC.recuperarIdContato(contatoB.getCelular1(), vinculo));
            funcionarioB.setId_endereco(enderecoC.recuperarIdEndereco(enderecoB.getCep(), vinculo));
        }
        else return false;        
        
        return funcionarioD.inserir(funcionarioB);
    }
    
    public void pesquisarFuncionario(DefaultTableModel modelo, String entrada) {
        
        modelo.setNumRows(0);
        if(!entrada.isEmpty()) funcionarioD.pesquisar(modelo, entrada);        
    }
    
    public FuncionarioBean buscarFuncionario(String login) {
        
        return funcionarioD.buscarPorLogin(login);
    }
    
    public boolean atualizarFuncionario(FuncionarioBean funcionarioB, ContatoBean contatoB, EnderecoBean enderecoB) {
        
        ContatoControle contatoC = new ContatoControle();
        EnderecoControle enderecoC = new EnderecoControle();
                
        boolean f = funcionarioD.atualizar(funcionarioB.getId_funcionario(), funcionarioB);
        boolean c = contatoC.atualizarContato(contatoB.getId_contato(), contatoB);
        boolean e = enderecoC.atualizarEndereco(enderecoB.getId_endereco(), enderecoB);
        
        return f&&c&&e;
    }
    
}