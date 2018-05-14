/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.gui;

import br.com.muse.beans.CheckInBean;
import br.com.muse.beans.HospedeBean;
import br.com.muse.beans.ReservaBean;
import br.com.muse.controller.CheckInControle;
import br.com.muse.controller.HospedeControle;
import br.com.muse.controller.ReservaControle;
import br.com.muse.util.Mensagem;
import br.com.muse.util.Propriedade;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyVetoException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kennedy
 */
public class CheckInGUI extends javax.swing.JInternalFrame implements IPosicaoGUI{
    
    private ReservaControle reservaC = new ReservaControle();
    private ReservaBean reservaAtual = new ReservaBean();
    private String valorDiaria;
    private String valorTotal;
    private double somaFatura;
    NumberFormat doubleformat = NumberFormat.getInstance();
    private String modo;
    public DefaultTableModel modelo;

    /**
     * Creates new form CheckInGUI
     */
    public CheckInGUI() {
        
        this.modo = "editar";
        doubleformat.setMinimumFractionDigits(2);
        doubleformat.setMaximumFractionDigits(2);
        valorDiaria = String.valueOf(Muse.valorDiaria);
        valorDiaria = "R$ "+valorDiaria.replace('.', ','); 
        valorTotal = valorDiaria;
        somaFatura=0;
        initComponents();
        modelo = (DefaultTableModel) tabela.getModel();
        popularApartamentos(0);
        popularHospedes(0);
        cadastro(false);
        listarCheckIns();        
    }
    @Override
    public void ajustar(Dimension d) {
        
        this.setLocation((d.width - this.getSize().width) / 2, 10);
        
        try {
            this.setSelected(true);
            
        } catch (PropertyVetoException ex) {
        
            
        }
        
    }
    
    private void cadastro(boolean b) {
        
        this.cb_hospedes.setEnabled(b);
        this.cb_apartamentos.setEnabled(b);        
        this.tf_entrada.setEnabled(b);
        this.tf_saida.setEnabled(b);        
        this.bt_salvar1.setEnabled(b);
        this.spn_qtd.setEnabled(b);
        this.spn_qtd_adultos.setEnabled(b);
        this.spn_qtd_criancas.setEnabled(b);
        this.tf_diaria.setEnabled(b);
        this.tf_valor_total.setEnabled(b);
    }
    
    private void popularHospedes(int id) {
        
        HospedeControle hospedeC = new HospedeControle();
        HospedeBean hospedeB = new HospedeBean();
        
        ArrayList<HospedeBean> hospedes = new ArrayList<>();
        
        hospedes = hospedeC.listarTodos();
        
        
        //Ordenar
        Collections.sort(hospedes, (HospedeBean  h1, HospedeBean  h2) -> h1.getNome().compareTo(h2.getNome()));
      
        if(hospedes.size()>0) {
            
            for(HospedeBean hospede : hospedes) {
                
                this.cb_hospedes.addItem(hospede);
                
                if(hospede.getId_hospede()==id) cb_hospedes.setSelectedItem(hospede);
            }
        }
        else {
            
            cadastro(false);
            this.bt_salvar1.setEnabled(false);
            this.bt_sem_reserva.setEnabled(true);
            Mensagem.erroMsg("Não há hospedes cadastrados!", "Houve um erro");
            this.tf_pesquisar.requestFocus();
        }
                
    }
    
    private void popularApartamentos(int ap) {
        
        boolean b = false;
        
        for(int x=1;x<=250;x++) {
                        
            if(Propriedade.getProp("apartamentos.properties", "apt"+x).equals("CADASTRADO") || x == ap) {
                
                b = true;
                this.cb_apartamentos.addItem(x);
                if(x==ap) cb_apartamentos.setSelectedItem(x);
            }
        }
        
        if(!b) {
            
            limparCampos();
            cadastro(false);
            this.bt_salvar1.setEnabled(false);
            this.bt_sem_reserva.setEnabled(true);
            Mensagem.erroMsg("Não há apartamentos disponíveis!", "Houve um erro");
            this.tf_pesquisar.requestFocus();
        }
        
    }
    
    private void limparCampos() {
        
        this.cb_apartamentos.removeAllItems();
        this.cb_hospedes.removeAllItems();
        this.tf_entrada.setText("");
        this.tf_saida.setText("");
        this.spn_qtd.setValue(1);
        this.spn_qtd_adultos.setValue(1);
        this.spn_qtd_criancas.setValue(0);
        this.tf_diaria.setText(valorDiaria);
        this.tf_valor_total.setText(valorTotal);       
    }
    
    private void preencher() {
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tf_valor_total = new javax.swing.JTextField();
        tf_pesquisar = new javax.swing.JTextField();
        spn_qtd = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        spn_qtd_adultos = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        spn_qtd_criancas = new javax.swing.JSpinner();
        cb_hospedes = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cb_apartamentos = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        tf_entrada = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        bt_sem_reserva = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        bt_salvar1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        tf_saida = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tf_diaria = new javax.swing.JTextField();
        bt_com_reserva = new javax.swing.JButton();

        setClosable(true);
        setTitle("Gerenciar check in");
        setVisible(true);

        jLabel9.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel9.setText("Qtd.diárias:");

        jLabel10.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel10.setText("Valor total:");

        jLabel1.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel1.setText("Check In:");

        tf_valor_total.setEditable(false);
        tf_valor_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_valor_total.setText(valorTotal);

        tf_pesquisar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        tf_pesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_pesquisarKeyReleased(evt);
            }
        });

        spn_qtd.setValue(1);
        spn_qtd.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spn_qtdStateChanged(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Check In", "Hospede", "Quarto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabela);
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(0).setMinWidth(100);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(0).setMaxWidth(100);
            tabela.getColumnModel().getColumn(2).setMinWidth(100);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        jLabel11.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel11.setText("Adultos:");

        spn_qtd_adultos.setValue(1);
        spn_qtd_adultos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spn_qtd_adultosStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        jLabel2.setText("Efetuar check In:");

        jLabel12.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel12.setText("Crianças:");

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel3.setText("Hospede:");

        spn_qtd_criancas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spn_qtd_criancasStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel4.setText("Quarto:");

        jLabel5.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel5.setText("Entrada:");

        tf_entrada.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        jLabel6.setText("Pesquisar:");

        bt_sem_reserva.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        bt_sem_reserva.setText("Sem reserva");
        bt_sem_reserva.setPreferredSize(new java.awt.Dimension(60, 27));
        bt_sem_reserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sem_reservaActionPerformed(evt);
            }
        });

        bt_cancelar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        bt_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/cancel_icon.png"))); // NOI18N
        bt_cancelar.setText("Cancelar");
        bt_cancelar.setPreferredSize(new java.awt.Dimension(60, 27));
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        bt_salvar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/apply_icon.png"))); // NOI18N
        bt_salvar1.setText("Salvar");
        bt_salvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salvar1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel7.setText("Saída:");

        tf_saida.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel8.setText("Valor diária:");

        tf_diaria.setEditable(false);
        tf_diaria.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_diaria.setText(valorDiaria);

        bt_com_reserva.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        bt_com_reserva.setText("Com reserva");
        bt_com_reserva.setPreferredSize(new java.awt.Dimension(60, 27));
        bt_com_reserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_com_reservaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addContainerGap(712, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cb_apartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel5)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tf_entrada, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                            .addComponent(tf_saida)))
                                    .addComponent(cb_hospedes, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(spn_qtd_adultos)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel10))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(spn_qtd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel8)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tf_valor_total)
                                            .addComponent(tf_diaria, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(spn_qtd_criancas, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bt_salvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bt_sem_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bt_com_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cb_hospedes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cb_apartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(tf_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tf_saida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(tf_diaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(spn_qtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(tf_valor_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(spn_qtd_adultos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(spn_qtd_criancas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(bt_salvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_sem_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_com_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void spn_qtdStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spn_qtdStateChanged

        int x = Integer.valueOf(String.valueOf(this.spn_qtd.getValue()));
        int y = Integer.valueOf(String.valueOf(this.spn_qtd_adultos.getValue()));
        int z = Integer.valueOf(String.valueOf(this.spn_qtd_criancas.getValue()));
        double diaria = Muse.valorDiaria;

        if(x <= 0) {

            spn_qtd.setValue(1);
            somaFatura = ((diaria * y) + ((diaria/2) *z)) * 1;
            String total = doubleformat.format(somaFatura);
            total = total.replace('.', ',');
            this.tf_valor_total.setText("R$ "+total);
        }

        else {
            somaFatura = ((diaria * y) + ((diaria/2) *z)) * x;
            String total = doubleformat.format(somaFatura);
            total = total.replace('.', ',');
            this.tf_valor_total.setText("R$ "+total);
        }

    }//GEN-LAST:event_spn_qtdStateChanged

    private void spn_qtd_adultosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spn_qtd_adultosStateChanged

        int x = Integer.valueOf(String.valueOf(this.spn_qtd_adultos.getValue()));
        int y = Integer.valueOf(String.valueOf(this.spn_qtd_criancas.getValue()));
        int z = Integer.valueOf(String.valueOf(this.spn_qtd.getValue()));
        double diaria = Muse.valorDiaria;

        if(x<=0) spn_qtd_adultos.setValue(1);

        else {
            somaFatura = ((diaria * x) + ((diaria/2) *y)) * z;
            String total = doubleformat.format(somaFatura);
            total = total.replace('.', ',');
            this.tf_valor_total.setText("R$ "+total);
        }

    }//GEN-LAST:event_spn_qtd_adultosStateChanged

    private void spn_qtd_criancasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spn_qtd_criancasStateChanged

        int x = Integer.valueOf(String.valueOf(this.spn_qtd_criancas.getValue()));
        int y = Integer.valueOf(String.valueOf(this.spn_qtd_adultos.getValue()));
        int z = Integer.valueOf(String.valueOf(this.spn_qtd.getValue()));
        double diaria = Muse.valorDiaria;

        if(x<0) spn_qtd_criancas.setValue(0);

        else {
            somaFatura = ((diaria * y) + ((diaria/2) *x)) * z;
            String total = doubleformat.format(somaFatura);
            total = total.replace('.', ',');
            this.tf_valor_total.setText("R$ "+total);
        }
    }//GEN-LAST:event_spn_qtd_criancasStateChanged

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed

        this.dispose();
    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void bt_sem_reservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_sem_reservaActionPerformed
    
        cadastro(true);
        this.bt_sem_reserva.setEnabled(false);
        this.bt_com_reserva.setEnabled(true);
        
    }//GEN-LAST:event_bt_sem_reservaActionPerformed

    private void bt_com_reservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_com_reservaActionPerformed
        
        cadastro(false);
        this.bt_sem_reserva.setEnabled(true);
        this.bt_com_reserva.setEnabled(false);
                
        JPanel panel = new JPanel(new GridLayout(3,1));
        JLabel label1 = new JLabel("Informe o numero da reserva:");        
        JComboBox reservas = new JComboBox();
        panel.add(label1);        
        panel.add(reservas);
        
        String[] options = new String[]{"Confirmar", "Cancelar"};
                
        ReservaControle reservaC = new ReservaControle();
        
        ArrayList<ReservaBean> todas = reservaC.mostrarTodas();
        
        if(todas.size() > 0) {
            
            for(ReservaBean t : todas) {
            
                reservas.addItem(t);
            }
                
            int option = JOptionPane.showOptionDialog(null, panel, "Check In",JOptionPane.NO_OPTION, 2,null, options, reservas);
        
            if (option == 0) {

                if(checkIn(Integer.parseInt(String.valueOf(reservas.getSelectedItem())))) {

                    limparCampos();
                    cadastro(false);
                    listarCheckIns();
                    Mensagem.okMsg("Check In efetuado com sucesso!", "Sucesso");                    
                    this.bt_sem_reserva.setEnabled(true);
                    this.tf_pesquisar.requestFocus();
                }
            }
            
        }
        else {
            
            Mensagem.erroMsg("Não há reservas efetuadas!", "Houve um erro");
            
        }
        
        
        
    }//GEN-LAST:event_bt_com_reservaActionPerformed

    private boolean checkIn(int id) {
        
        CheckInControle checkInC = new CheckInControle();
        
        
        CheckInBean checkInB = new CheckInBean();
        ReservaBean reservaB = new ReservaBean();
            
        reservaB = reservaC.buscarReserva(id);
            
        checkInB.setId_hospede(reservaB.getId_hospede());
        checkInB.setId_quarto(reservaB.getId_quarto());
            
        //Salva no BD
        boolean c = checkInC.efetuarCheckIn(checkInB);
            
        //Remove a reserva
        boolean r = reservaC.removerReserva(reservaB.getId_reserva(), false);
                
        return c&&r;
    }
    
    private void bt_salvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salvar1ActionPerformed
        
        HospedeBean hospedeB = (HospedeBean) cb_hospedes.getSelectedItem();
        
        reservaAtual.setId_hospede(hospedeB.getId_hospede());
        reservaAtual.setId_quarto(Integer.parseInt(String.valueOf(cb_apartamentos.getSelectedItem())));        
        reservaAtual.setData_entrada(tf_entrada.getText());
        reservaAtual.setData_saida(tf_saida.getText());
        reservaAtual.setAdultos(Integer.parseInt(String.valueOf(spn_qtd_adultos.getValue())));
        reservaAtual.setCriancas(Integer.parseInt(String.valueOf(spn_qtd_criancas.getValue())));
        reservaAtual.setDiaria(Muse.valorDiaria);
        reservaAtual.setValorTotal(somaFatura);
        reservaAtual.setQtdReservas(Integer.parseInt(String.valueOf(spn_qtd.getValue())));
        
        if(reservaC.cadastrar(reservaAtual)) {
            
            ReservaBean pesquisa = reservaC.buscarPorPesquisa(reservaAtual.getId_hospede(), reservaAtual.getId_quarto());
            if(checkIn(pesquisa.getId_reserva())) {
                
                this.modo = "editar";
                limparCampos();
                cadastro(false);
                listarCheckIns();
                Mensagem.okMsg("Check In efetuado com sucesso!", "Sucesso");                    
                this.bt_sem_reserva.setEnabled(true);
                this.tf_pesquisar.requestFocus();
            }
        }
    }//GEN-LAST:event_bt_salvar1ActionPerformed

    private void tf_pesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_pesquisarKeyReleased
        
        
        CheckInControle checkInC = new CheckInControle();
        
        checkInC.pesquisarCheckIn(modelo, tf_pesquisar.getText());         
    }//GEN-LAST:event_tf_pesquisarKeyReleased

    private void listarCheckIns() {
        
        CheckInControle checkInC = new CheckInControle();
        
        this.modelo.setNumRows(0);
        
        checkInC.mostrarCheckIns(modelo);        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_com_reserva;
    private javax.swing.JButton bt_salvar1;
    private javax.swing.JButton bt_sem_reserva;
    private javax.swing.JComboBox cb_apartamentos;
    private javax.swing.JComboBox cb_hospedes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spn_qtd;
    private javax.swing.JSpinner spn_qtd_adultos;
    private javax.swing.JSpinner spn_qtd_criancas;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField tf_diaria;
    private javax.swing.JTextField tf_entrada;
    private javax.swing.JTextField tf_pesquisar;
    private javax.swing.JTextField tf_saida;
    private javax.swing.JTextField tf_valor_total;
    // End of variables declaration//GEN-END:variables

    
}
