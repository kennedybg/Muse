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
public class EmpresaBean {
    
        
    //Atributos
    private int id_empresa;
    private String nome_fantasia;
    private String razao_social;
    private String cnpj;
    private int id_endereco;
    private int id_contato;
    //Atributos
    
    public EmpresaBean(int id_empresa, String nome_fantasia, String razao_social, String cnpj, int id_endereco, int id_contato) {

        this.id_empresa = id_empresa;
        this.nome_fantasia = nome_fantasia;
        this.razao_social = razao_social;
        this.cnpj = cnpj;
        this.id_endereco = id_endereco;
        this.id_contato = id_contato;
    }

    public EmpresaBean() {

        this.id_empresa = 0;
        this.nome_fantasia = "";
        this.razao_social = "";
        this.cnpj = "";
        this.id_endereco = 0;
        this.id_contato = 0;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }

    public int getId_contato() {
        return id_contato;
    }

    public void setId_contato(int id_contato) {
        this.id_contato = id_contato;
    }
    
}
