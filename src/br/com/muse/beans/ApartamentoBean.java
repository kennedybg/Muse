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
public class ApartamentoBean {
    
    
    //Atributos
    private int id_quarto;
    private int num_quarto;
    private boolean alugado;
    private boolean reservado;
    //Atributos
    
    public ApartamentoBean(int id_quarto, boolean alugado, boolean reservado) {

        this.id_quarto = id_quarto;
        this.alugado = alugado;
        this.reservado = reservado;        
    }

    public ApartamentoBean() {

        this.id_quarto = 0;
        this.alugado = false;
        this.reservado = false;
    }

    public int getId_quarto() {
        return id_quarto;
    }

    public void setId_quarto(int id_quarto) {
        this.id_quarto = id_quarto;
    }

    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    public int getNum_quarto() {
        return num_quarto;
    }

    public void setNum_quarto(int num_quarto) {
        this.num_quarto = num_quarto;
    }
    
    @Override
    public String toString() {
        return String.valueOf(num_quarto);
    }
    
}
