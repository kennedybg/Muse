/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.gui;

import br.com.muse.util.Mensagem;
import java.awt.Component;
import javax.swing.JComponent;

/**
 *
 * @author kennedy
 */
class GUIFX extends Thread{

  public JComponent component;
  private int efeito;
  private int atraso;
  
  public static final int EFEITO_TREMER = 1;
  public static final int EFEITO_DESAPARECER_PARA_BAIXO = 2;
  public static final int EFEITO_DESAPARECER_PARA_DIREITA = 3;
  public static final int EFEITO_DESAPARECER_E_FECHAR = 4;
  
  public GUIFX(){
     
  }   

  @Override
  public void run(){

      switch(efeito) {
          
          case EFEITO_TREMER:
              
            for(int x=0; x<5;x++) {

              try {
                    component.requestFocus();
                    component.setBounds(component.getX()+2, component.getY(), component.getSize().width, component.getSize().height);
                    Thread.sleep(atraso);
                    component.setBounds(component.getX()-2, component.getY(), component.getSize().width, component.getSize().height);           
                    Thread.sleep(atraso);
              } catch (InterruptedException ex) {
                  
                    Mensagem.alertaMsg(0);
                }
              
            } 
          break;
              
          case EFEITO_DESAPARECER_PARA_BAIXO:
              
                try {  
                        
                    Thread.sleep(atraso);                        
                                         
                    for(int x=0; x<20;x++) {
                        
                        component.setBounds(component.getX(), component.getY()-1, component.getSize().width, component.getSize().height);
                        Thread.sleep(5);
                    }
                    
                    for(int x=0; x<300;x++) {
                        
                        component.setBounds(component.getX(), component.getY()+4, component.getSize().width, component.getSize().height);
                        Thread.sleep(1);
                    }
                }
                catch (InterruptedException ex) {

                    Mensagem.alertaMsg(0);
                }   
                    
                component.setVisible(false);
              
          break;
              
          case EFEITO_DESAPARECER_PARA_DIREITA:
              
                try {  
                       
                    Thread.sleep(atraso);                        
                     
                    
                    for(int x=0; x<20;x++) {
                        
                        component.setBounds(component.getX()-2, component.getY(), component.getSize().width, component.getSize().height);
                        Thread.sleep(2);
                    }
                    
                    for(int x=0; x<300;x++) {
                            
                        component.setBounds(component.getX()+1, component.getY(), component.getSize().width, component.getSize().height);                        
                        Thread.sleep(1);
                    }
                }
                catch (InterruptedException ex) {

                    Mensagem.alertaMsg(0);
                }   
                    
                component.setVisible(false);
              
          break;
              case EFEITO_DESAPARECER_E_FECHAR:
                                
                  Component c[] = component.getComponents();
                                    
                  for(Component cm : c){
                      
                    try {
                                         
                    for(int x=0; x<20;x++) {                        
                        cm.setBounds(cm.getX(), cm.getY()-1, cm.getSize().width, cm.getSize().height);
                        
                        Thread.sleep(1);
                    }
                    
                    for(int x=0; x<300;x++) {

                        cm.setBounds(cm.getX(), cm.getY()+(cm.getHeight()/10), cm.getSize().width, cm.getSize().height);
                        Thread.sleep(1);
                    }   
                        
                    }
                    catch (InterruptedException ex) {

                        Mensagem.alertaMsg(0);
                    }
                      
                    cm.setVisible(false);
                  }
                  try {
                      
                      Thread.sleep(1);                      
                      System.exit(0);
                  }
                  catch (InterruptedException ex) {

                        Mensagem.alertaMsg(0);
                  }
              
          break;
              
          default:
              
              
          break;
          
          
          
      }
      
  }
  
  public void animar(JComponent comp, int efeito, int atraso) {
      
      this.component = comp;
      this.efeito = efeito;
      this.atraso = atraso;
      
  }
  
}
