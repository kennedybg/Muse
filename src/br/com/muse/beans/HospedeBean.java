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
public class HospedeBean {
    
    //Atributos
    private int id_hospede;
    private String nome;
    private String sobrenome;
    private int id_endereco;
    private int id_contato;
    private String cpf;
    private String rg;
    private String sexo;
    private String tratamento;
    private String pessoa;
    private int id_empresa;
    private String profissao;
    private String nacionalidade;
    private String nascimento;
    //Atributos
    
    public HospedeBean(int id_hospede, String nome, int id_endereco, int id_contato, String cpf, String rg, String sexo, String tratamento, String pessoa, int id_empresa, String profissao, String nacionalidade, String nascimento) {

        this.id_hospede = id_hospede;
        this.nome = nome;
        this.id_endereco = id_endereco;
        this.id_contato = id_contato;
        this.cpf = cpf;
        this.rg = rg;
        this.sexo = sexo;
        this.tratamento = tratamento;
        this.pessoa = pessoa;
        this.id_empresa = id_empresa;
        this.profissao = profissao;
        this.nacionalidade = nacionalidade;
        this.nascimento = nascimento;
    }

    public HospedeBean() {
        
        this.id_hospede = 0;
        this.nome = "";
        this.id_endereco = 0;
        this.id_contato = 0;
        this.cpf = "";
        this.rg = "";
        this.sexo = "";
        this.tratamento = "";
        this.pessoa = "";
        this.id_empresa = 0;
        this.profissao = "";
        this.nacionalidade = "";
        this.nascimento = "";
    }

    public int getId_hospede() {
        return id_hospede;
    }

    public void setId_hospede(int id_hospede) {
        this.id_hospede = id_hospede;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String toString() {
        return nome +" "+ sobrenome;
    }
    
    
}
