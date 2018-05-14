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
public class FuncionarioBean {
    
    //Atributos
    private int id_funcionario;
    private String nome;
    private String sobreNome;
    private int id_contato;
    private int id_endereco;
    private String login;
    private String senha;
    private int nivel_acesso;
    private boolean ativo;    
    //Atributos
    
    public FuncionarioBean(int id_funcionario, String nome, int id_contato, int id_endereco, String login, String senha, int nivel_acesso, boolean ativo) {
 
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.id_contato = id_contato;
        this.id_endereco = id_endereco;
        this.login = login;
        this.senha = senha;
        this.nivel_acesso = nivel_acesso;
        this.ativo = ativo;        
    }

    public FuncionarioBean() {

        this.id_funcionario = 0;
        this.nome = "";
        this.id_contato = 0;
        this.id_endereco = 0;
        this.login = "";
        this.senha = "";
        this.nivel_acesso = 3;
        this.ativo = true;      
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }    

    public int getId_contato() {
        return id_contato;
    }

    public void setId_contato(int id_contato) {
        this.id_contato = id_contato;
    }

    public int getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNivel_acesso() {
        return nivel_acesso;
    }

    public void setNivel_acesso(int nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
