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
public class CheckInBean {
    
    //Atributos
    private int id_check_in;
    private int id_hospede;
    private int id_quarto;
    //Atributos
    
    public CheckInBean(int id_check_in, int id_hospede, int id_quarto) {

        this.id_check_in = id_check_in;
        this.id_hospede = id_hospede;
        this.id_quarto = id_quarto;
    }

    public CheckInBean() {
        
        this.id_check_in = 0;
        this.id_hospede = 0;
        this.id_quarto = 0;
    }

    public int getId_quarto() {
        return id_quarto;
    }

    public void setId_quarto(int id_quarto) {
        this.id_quarto = id_quarto;
    }

    public int getId_check_in() {
        return id_check_in;
    }

    public void setId_check_in(int id_check_in) {
        this.id_check_in = id_check_in;
    }

    public int getId_hospede() {
        return id_hospede;
    }

    public void setId_hospede(int id_hospede) {
        this.id_hospede = id_hospede;
    }

    @Override
    public String toString() {
        
        return String.valueOf(id_check_in);
    }
    
    
    
}
