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
public class MuseConfigControle implements PropertieManager<String>{

    @Override
    public void alterar(ArrayList<String> p) {
        
        String file = "museconfig.properties";
        
        Propriedade.setProp(file, "muse.video", Cerbero.crypt64(p.get(0)));
        Propriedade.setProp(file, "muse.rede", Cerbero.crypt64(p.get(1)));
        Propriedade.setProp(file, "muse.usuario", Cerbero.crypt64(p.get(2)));        
        if(!p.get(3).isEmpty()) Propriedade.setProp(file, "muse.senha", Cerbero.crypt256(p.get(3)));        
        Propriedade.setProp(file, "muse.tempo", p.get(4));
        Propriedade.setProp(file, "muse.uf", p.get(5));
    }

    @Override
    public ArrayList<String> carregar() {
        
        ArrayList<String> MuseConfigs = new ArrayList<>();
        
        MuseConfigs.add(Cerbero.decrypt64(Propriedade.getProp("museconfig.properties", "muse.video")));
        MuseConfigs.add(Cerbero.decrypt64(Propriedade.getProp("museconfig.properties", "muse.rede")));
        MuseConfigs.add(Cerbero.decrypt64(Propriedade.getProp("museconfig.properties", "muse.usuario")));        
        MuseConfigs.add(Propriedade.getProp("museconfig.properties", "muse.senha"));
        MuseConfigs.add(Propriedade.getProp("museconfig.properties", "muse.tempo"));
        MuseConfigs.add(Propriedade.getProp("museconfig.properties", "muse.uf"));
        
        return MuseConfigs;
    }

    @Override
    public void redefinir() {
        
        Propriedade.resetPropFile("museconfig");
    }  
    
    @Override
    public boolean autenticar(String senha) {
        
        String senhaGravada = Propriedade.getProp("museconfig.properties", "muse.senha");
                
        if(Cerbero.crypt256(senha).equals(senhaGravada)) return true;
        
        return false;
    }
    
}
