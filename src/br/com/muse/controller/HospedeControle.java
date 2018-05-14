/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.controller;

import br.com.muse.beans.ContatoBean;
import br.com.muse.beans.EnderecoBean;
import br.com.muse.beans.HospedeBean;
import br.com.muse.dao.HospedeDAO;
import br.com.muse.util.Cerbero;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kennedy
 */
public class HospedeControle {
    
    
    HospedeDAO hospedeD = new HospedeDAO();
    
    
    //Solicita ao DAO o cadastro de um novo funcionario.
    public boolean cadastrar(HospedeBean hospedeB, ContatoBean contatoB, EnderecoBean enderecoB) {
        
        Random r = new Random();
        ContatoControle contatoC = new ContatoControle();
        EnderecoControle enderecoC = new EnderecoControle();        
        
        //O vinculo é uma forma de evitar que cadastros paralelos usem o mesmo contato/endereco.
        String vinculo = Cerbero.cryptMD5(String.valueOf(r.nextInt()));                    
                
        contatoB.setVinculo(vinculo);
        enderecoB.setVinculo(vinculo);
        
        if(contatoC.cadastrar(contatoB) && enderecoC.cadastrar(enderecoB)) {
                        
            hospedeB.setId_contato(contatoC.recuperarIdContato(contatoB.getCelular1(), vinculo));
            hospedeB.setId_endereco(enderecoC.recuperarIdEndereco(enderecoB.getCep(), vinculo));
        }
        else return false;        
        
        return hospedeD.inserir(hospedeB);
    }
    
    public HospedeBean buscarHospede(String cpf) {
        
        return hospedeD.buscarPorCpf(cpf);
    }
    
    public HospedeBean buscarPorId(int id) {
        
        return hospedeD.buscar(id);
    }
    
    public void pesquisarHospede(DefaultTableModel modelo, String entrada) {
        
        modelo.setNumRows(0);
        if(!entrada.isEmpty()) hospedeD.pesquisar(modelo, entrada);        
    }
    
    public boolean atualizarHospede(HospedeBean hospedeB, ContatoBean contatoB, EnderecoBean enderecoB) {
        
        ContatoControle contatoC = new ContatoControle();
        EnderecoControle enderecoC = new EnderecoControle();
                
        boolean h = hospedeD.atualizar(hospedeB.getId_hospede(), hospedeB);
        boolean c = contatoC.atualizarContato(contatoB.getId_contato(), contatoB);
        boolean e = enderecoC.atualizarEndereco(enderecoB.getId_endereco(), enderecoB);
        
        return h&&c&&e;
    }
    
    public ArrayList<HospedeBean> listarTodos() {
        
        return hospedeD.listar();
    }
    
}
