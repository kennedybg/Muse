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
public class ReservaBean {
    
    //Atributos
    private int id_reserva;
    private int id_hospede;
    private int id_quarto;    
    private String data_entrada;
    private String data_saida;
    private int adultos;   
    private int criancas;
    private double diaria;
    private double valorTotal;
    private int qtdReservas;
    //Atributos 
    
    
    public ReservaBean(int id_reserva, int id_hospede, int id_quarto, String data_entrada, String data_saida, int adultos, int criancas, double diaria, double valorTotal, int qtdReservas) {

        this.id_reserva = id_reserva;
        this.id_hospede = id_hospede;
        this.id_quarto = id_quarto;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.adultos = adultos;
        this.criancas = criancas;
        this.diaria = diaria;
        this.valorTotal = valorTotal;
        this.qtdReservas = qtdReservas;
    }

    public ReservaBean() {

        this.id_reserva = 0;
        this.id_hospede = 0;
        this.id_quarto = 0;
        this.data_entrada = "";
        this.data_saida = "";
        this.adultos = 1;
        this.criancas = 0;
        this.diaria = 0;
        this.valorTotal = 0;
        this.qtdReservas = 1;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_hospede() {
        return id_hospede;
    }

    public void setId_hospede(int id_hospede) {
        this.id_hospede = id_hospede;
    }

    public int getId_quarto() {
        return id_quarto;
    }

    public void setId_quarto(int id_quarto) {
        this.id_quarto = id_quarto;
    }

    public String getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(String data_entrada) {
        this.data_entrada = data_entrada;
    }

    public String getData_saida() {
        return data_saida;
    }

    public void setData_saida(String data_saida) {
        this.data_saida = data_saida;
    }

    public int getAdultos() {
        return adultos;
    }

    public void setAdultos(int adultos) {
        this.adultos = adultos;
    }

    public int getCriancas() {
        return criancas;
    }

    public void setCriancas(int criancas) {
        this.criancas = criancas;
    }

    public double getDiaria() {
        return diaria;
    }

    public void setDiaria(double diaria) {
        this.diaria = diaria;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getQtdReservas() {
        return qtdReservas;
    }

    public void setQtdReservas(int qtdReservas) {
        this.qtdReservas = qtdReservas;
    }

    @Override
    public String toString() {
        
        return String.valueOf(id_reserva);
    }
    
    
}
