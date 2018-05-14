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
public class CheckOutBean {
    
    //Atributos
    private int id_check_out;
    private int id_hospede;
    private int id_quarto;    
    private double valorFatura;
    //Atributos
    
    public CheckOutBean(int id_check_out, int id_hospede, int id_quarto, int id_fatura, double valorFatura) {

        this.id_check_out = id_check_out;
        this.id_hospede = id_hospede;
        this.id_quarto = id_quarto;        
        this.valorFatura = valorFatura;
    }

    public CheckOutBean() {

        this.id_check_out = 0;
        this.id_hospede = 0;
        this.id_quarto = 0;
        this.valorFatura = 0;
    }
    

    public int getId_quarto() {
        return id_quarto;
    }

    public void setId_quarto(int id_quarto) {
        this.id_quarto = id_quarto;
    }

    public int getId_check_out() {
        return id_check_out;
    }

    public void setId_check_out(int id_check_out) {
        this.id_check_out = id_check_out;
    }

    public int getId_hospede() {
        return id_hospede;
    }

    public void setId_hospede(int id_hospede) {
        this.id_hospede = id_hospede;
    }
    
    public double getValorFatura() {
        return valorFatura;
    }

    public void setValorFatura(double valorFatura) {
        this.valorFatura = valorFatura;
    }
    
}
