/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.beans;

/**
 *
 * @author kennedy
 */
public class ContatoBean {
    
        
    //Atributos
    private int id_contato;
    private String residencial;    
    private String celular1;
    private String celular2;
    private String celular3;
    private String celular4;
    private String fax;
    private String caixa_postal; 
    private String vinculo;    
    //Atributos
    
    public ContatoBean(int id_contato, String residencial, String celular1, String celular2, String celular3, String celular4, String fax, String caixa_postal, String vinculo) {

        this.id_contato = id_contato;
        this.residencial = residencial;
        this.celular1 = celular1;
        this.celular2 = celular2;
        this.celular3 = celular3;
        this.celular4 = celular4;
        this.fax = fax;
        this.caixa_postal = caixa_postal;
        this.vinculo = vinculo;    
    }

    public ContatoBean() {

        this.id_contato = 0;
        this.residencial = "";
        this.celular1 = "";
        this.celular2 = "";
        this.celular3 = "";
        this.celular4 = "";
        this.fax = "";
        this.caixa_postal = "";        
        this.vinculo = "";
    }

    public int getId_contato() {
        return id_contato;
    }

    public void setId_contato(int id_contato) {
        this.id_contato = id_contato;
    }

    public String getResidencial() {
        return residencial;
    }

    public void setResidencial(String residencial) {
        this.residencial = residencial;
    }

    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    public String getCelular3() {
        return celular3;
    }

    public void setCelular3(String celular3) {
        this.celular3 = celular3;
    }

    public String getCelular4() {
        return celular4;
    }

    public void setCelular4(String celular4) {
        this.celular4 = celular4;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCaixa_postal() {
        return caixa_postal;
    }

    public void setCaixa_postal(String caixa_postal) {
        this.caixa_postal = caixa_postal;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }
    
}
