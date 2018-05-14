/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.controller;

import br.com.muse.util.Cerbero;
import br.com.muse.util.Propriedade;
import java.util.ArrayList;

/**
 *
 * @author kennedy
 */
public class PermissoesControle implements PropertieManager<Integer> {
    
    
    @Override
    public void alterar(ArrayList<Integer> p) {

        
        Propriedade.setProp("permissoes.properties", "hospede.adicionar", String.valueOf(p.get(0)));
        Propriedade.setProp("permissoes.properties", "hospede.editar", String.valueOf(p.get(1)));
        Propriedade.setProp("permissoes.properties", "hospede.visualizar", String.valueOf(p.get(2)));
        
        Propriedade.setProp("permissoes.properties", "reserva.adicionar", String.valueOf(p.get(3)));
        Propriedade.setProp("permissoes.properties", "reserva.editar", String.valueOf(p.get(4)));
        Propriedade.setProp("permissoes.properties", "reserva.visualizar", String.valueOf(p.get(5)));
        
        Propriedade.setProp("permissoes.properties", "checkin.adicionar", String.valueOf(p.get(6)));
        Propriedade.setProp("permissoes.properties", "checkin.editar", String.valueOf(p.get(7)));
        Propriedade.setProp("permissoes.properties", "checkin.visualizar", String.valueOf(p.get(8)));
        
        Propriedade.setProp("permissoes.properties", "checkout.adicionar", String.valueOf(p.get(9)));
        Propriedade.setProp("permissoes.properties", "checkout.editar", String.valueOf(p.get(10)));
        Propriedade.setProp("permissoes.properties", "checkout.visualizar", String.valueOf(p.get(11)));
        
        Propriedade.setProp("permissoes.properties", "apartamento.adicionar", String.valueOf(p.get(12)));
        Propriedade.setProp("permissoes.properties", "apartamento.editar", String.valueOf(p.get(13)));
        Propriedade.setProp("permissoes.properties", "apartamento.visualizar", String.valueOf(p.get(14)));
        
        Propriedade.setProp("permissoes.properties", "funcionario.adicionar", String.valueOf(p.get(15)));
        Propriedade.setProp("permissoes.properties", "funcionario.editar", String.valueOf(p.get(16)));
        Propriedade.setProp("permissoes.properties", "funcionario.visualizar", String.valueOf(p.get(17)));

    }
    
    @Override
    public ArrayList<Integer> carregar() {
        
        ArrayList<Integer> permissoes = new ArrayList<>();
        
        //Hospede        
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "hospede.adicionar")));
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "hospede.editar")));
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "hospede.visualizar")));
        //Reserva
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "reserva.adicionar")));
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "reserva.editar")));
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "reserva.visualizar")));
        //Check In
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "checkin.adicionar")));
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "checkin.editar")));
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "checkin.visualizar")));
        //Check Out
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "checkout.adicionar")));
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "checkout.editar")));
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "checkout.visualizar")));
        //Apartamento
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "apartamento.adicionar")));
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "apartamento.editar")));
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "apartamento.visualizar")));
        //Funcionario
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "funcionario.adicionar")));
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "funcionario.editar")));
        permissoes.add(Integer.parseInt(Propriedade.getProp("permissoes.properties", "funcionario.visualizar")));
        
        return permissoes;
    }
    
    @Override
    public void redefinir() {
        
        Propriedade.resetPropFile("permissoes");
    }
    
    @Override
    public boolean autenticar(String senha) {
                
        String senhaGravada = Propriedade.getProp("museconfig.properties", "muse.senha");
                
        if(Cerbero.crypt256(senha).equals(senhaGravada)) return true;
        
        return false;
    }
    
}
