/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.gui;

import br.com.muse.beans.ContatoBean;
import br.com.muse.beans.EnderecoBean;
import br.com.muse.beans.HospedeBean;
import br.com.muse.controller.ContatoControle;
import br.com.muse.controller.EnderecoControle;
import br.com.muse.controller.HospedeControle;
import br.com.muse.util.Mensagem;
import br.com.muse.util.Propriedade;
import br.com.muse.util.Utilidades;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kennedy
 */
public class HospedeGUI extends javax.swing.JInternalFrame implements IPosicaoGUI{
    
    private final String estados[] = { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO" };
    private String modo;
    public DefaultTableModel modelo;
    private HospedeBean hospedeAtual = new HospedeBean();;
    private EnderecoBean enderecoAtual = new EnderecoBean();
    private ContatoBean contatoAtual = new ContatoBean();

    /**
     * Creates new form HospedeGUI
     */
    public HospedeGUI() {
        
        initComponents();        
        cadastro(false);
        this.modo = "editar";
        this.bt_editar.setEnabled(false);
        this.bt_salvar.setEnabled(false);
        modelo = (DefaultTableModel) tabela.getModel();
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
        
        this.tf_nome.setEnabled(b);
        this.tf_sobrenome.setEnabled(b);
        this.tf_rua.setEnabled(b);
        this.tf_complemento.setEnabled(b);
        this.tf_cep.setEnabled(b);
        this.tf_numero.setEnabled(b);
        this.tf_cidade.setEnabled(b);
        this.tf_pais.setEnabled(b);
        this.cb_uf.setEnabled(b);
        this.tf_celular1.setEnabled(b);
        this.tf_celular2.setEnabled(b);
        this.tf_celular3.setEnabled(b);
        this.tf_celular4.setEnabled(b);
        this.tf_fax.setEnabled(b);
        this.tf_caixa_postal.setEnabled(b);
        this.tf_residencial.setEnabled(b);
        this.tf_cpf.setEnabled(b);
        this.tf_rg.setEnabled(b);        
        this.rd_bt_feminino.setEnabled(b);
        this.rd_bt_masculino.setEnabled(b);        
        this.rd_bt_fisica.setEnabled(b);
        this.rd_bt_juridica.setEnabled(b);        
        this.tf_profissao.setEnabled(b);
        this.tf_nacionalidade.setEnabled(b);
        this.tf_nascimento.setEnabled(b);        
        this.bt_salvar.setEnabled(b);
    }

    private void limparCampos() {
        
        this.tf_nome.setText("");
        this.tf_sobrenome.setText("");
        this.tf_rua.setText("");
        this.tf_complemento.setText("");
        this.tf_cep.setText("");
        this.tf_numero.setText("");
        this.tf_cidade.setText("");
        this.tf_pais.setText("");
        this.cb_uf.setSelectedItem(Propriedade.getProp("museconfig.properties", "muse.uf"));
        this.tf_celular1.setText("");
        this.tf_celular2.setText("");
        this.tf_celular3.setText("");
        this.tf_celular4.setText("");
        this.tf_fax.setText("");
        this.tf_caixa_postal.setText("");
        this.tf_residencial.setText("");        
        this.tf_cpf.setText("");
        this.tf_rg.setText("");       
        this.rd_bt_feminino.setSelected(false);
        this.rd_bt_masculino.setSelected(false);        
        this.rd_bt_fisica.setSelected(false);
        this.rd_bt_juridica.setSelected(false);        
        this.tf_profissao.setText("");
        this.tf_nacionalidade.setText("");
        this.tf_nascimento.setText("");        
   
        this.tf_pesquisar.setText("");
    }
    
    private void preencherCampos() {
        
        EnderecoControle enderecoC = new EnderecoControle();
        ContatoControle contatoC = new ContatoControle();        
        
        enderecoAtual = enderecoC.buscarEndereco(hospedeAtual.getId_endereco());
        contatoAtual = contatoC.buscarContato(hospedeAtual.getId_contato());        
        
        //Dados funcionario
        this.tf_nome.setText(hospedeAtual.getNome());
        this.tf_sobrenome.setText(hospedeAtual.getSobrenome());        
        this.tf_cpf.setText(hospedeAtual.getCpf());
        this.tf_rg.setText(hospedeAtual.getRg());
        
        if(hospedeAtual.getSexo().equals("FEMININO")) this.rd_bt_feminino.setSelected(true);
        else this.rd_bt_masculino.setSelected(true);
        
        if(hospedeAtual.getPessoa().equals("FISICA")) this.rd_bt_fisica.setSelected(true);
        else this.rd_bt_juridica.setSelected(true);        
        
        this.tf_profissao.setText(hospedeAtual.getProfissao());
        this.tf_nacionalidade.setText(hospedeAtual.getNacionalidade());
        this.tf_nascimento.setText(hospedeAtual.getNascimento());

        //Dados endereco
        this.tf_rua.setText(enderecoAtual.getRua());
        this.tf_numero.setText(enderecoAtual.getNumero());
        this.tf_complemento.setText(enderecoAtual.getComplemento());
        this.tf_cidade.setText(enderecoAtual.getCidade());
        this.cb_uf.setSelectedItem(enderecoAtual.getEstado());
        this.tf_cep.setText(enderecoAtual.getCep());
        this.tf_pais.setText(enderecoAtual.getPais());
        //Dados contato
        this.tf_residencial.setText(contatoAtual.getResidencial());
        this.tf_celular1.setText(contatoAtual.getCelular1());
        this.tf_celular2.setText(contatoAtual.getCelular2());
        this.tf_celular3.setText(contatoAtual.getCelular3());
        this.tf_celular4.setText(contatoAtual.getCelular4());
        this.tf_fax.setText(contatoAtual.getFax());
        this.tf_caixa_postal.setText(contatoAtual.getCaixa_postal());
        
    }
    
    private void reset() {
        
        this.modo = "editar";
        this.bt_editar.setEnabled(false);
        this.bt_salvar.setEnabled(false);
        this.bt_cadastrar.setEnabled(true);
        limparCampos();        
        cadastro(false);
        this.modelo.setNumRows(0);
        this.tf_pesquisar.setEnabled(true);
        this.tf_pesquisar.requestFocus();        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rd_bt_sexo = new javax.swing.ButtonGroup();
        rd_bt_pessoa = new javax.swing.ButtonGroup();
        bt_salvar = new javax.swing.JButton();
        bt_cadastrar = new javax.swing.JButton();
        bt_editar = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        tf_pesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tf_rua = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tf_numero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tf_complemento = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_cep = new javax.swing.JTextField();
        tf_cidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tf_pais = new javax.swing.JTextField();
        cb_uf = new javax.swing.JComboBox(estados);
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_nome = new javax.swing.JTextField();
        tf_sobrenome = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        tf_celular2 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        tf_celular3 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        tf_celular4 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        tf_fax = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        tf_caixa_postal = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        tf_residencial = new javax.swing.JTextField();
        tf_celular1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        tf_cpf = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tf_rg = new javax.swing.JTextField();
        lb_sexo = new javax.swing.JLabel();
        rd_bt_feminino = new javax.swing.JRadioButton();
        rd_bt_masculino = new javax.swing.JRadioButton();
        lb_pessoa = new javax.swing.JLabel();
        rd_bt_fisica = new javax.swing.JRadioButton();
        rd_bt_juridica = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        tf_profissao = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tf_nacionalidade = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tf_nascimento = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setClosable(true);
        setResizable(true);
        setTitle("Gerenciar Hospedes");
        setVisible(true);

        bt_salvar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        bt_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/apply_icon.png"))); // NOI18N
        bt_salvar.setText("Salvar");
        bt_salvar.setPreferredSize(new java.awt.Dimension(60, 27));
        bt_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salvarActionPerformed(evt);
            }
        });

        bt_cadastrar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        bt_cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/user_add_icon.png"))); // NOI18N
        bt_cadastrar.setText("Cadastrar");
        bt_cadastrar.setPreferredSize(new java.awt.Dimension(60, 27));
        bt_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cadastrarActionPerformed(evt);
            }
        });

        bt_editar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        bt_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/user_edit_icon.png"))); // NOI18N
        bt_editar.setText("Editar");
        bt_editar.setPreferredSize(new java.awt.Dimension(60, 27));
        bt_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editarActionPerformed(evt);
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

        tf_pesquisar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        tf_pesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_pesquisarKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel1.setText("Pesquisar:");

        jPanel1.setBorder(null);

        tf_rua.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        jLabel4.setText("Nº:");

        tf_numero.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel5.setText("Complemento:");

        tf_complemento.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel6.setText("Cidade:");

        tf_cep.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        tf_cidade.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel8.setText("CEP:");

        jLabel7.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel7.setText("UF:");

        tf_pais.setText("BRASIL");

        jLabel9.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel9.setText("País:");

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel2.setText("Nome:");

        tf_nome.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        tf_sobrenome.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel30.setText("Sobrenome:");

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel3.setText("Rua:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tf_complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_rua, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_sobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(tf_cep, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(tf_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel7)
                                    .addGap(3, 3, 3)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cb_uf, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(tf_pais, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_sobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_rua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_complemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tf_cep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tf_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cb_uf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tf_pais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cb_uf.setSelectedItem(Propriedade.getProp("museconfig.properties", "muse.uf"));

        jPanel2.setBorder(null);

        jLabel16.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel16.setText("*Celular 1:");

        jLabel24.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel24.setText("Celular 2:");

        jLabel25.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel25.setText("Celular 3:");

        jLabel26.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel26.setText("Celular 4:");

        jLabel27.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel27.setText("Fax:");

        jLabel28.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel28.setText("Caixa Postal:");

        tf_caixa_postal.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel29.setText("Tel.Residencial:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel16)
                                .addComponent(jLabel24))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tf_celular1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_celular2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tf_celular4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tf_celular3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel28)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tf_caixa_postal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tf_fax, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_residencial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tf_celular1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(tf_celular2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_celular3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_celular4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_fax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(tf_caixa_postal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_residencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(null);

        jLabel10.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel10.setText("CPF:");

        tf_cpf.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel11.setText("RG:");

        tf_rg.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        lb_sexo.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        lb_sexo.setText("Sexo:");

        rd_bt_sexo.add(rd_bt_feminino);
        rd_bt_feminino.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        rd_bt_feminino.setText("Feminino");

        rd_bt_sexo.add(rd_bt_masculino);
        rd_bt_masculino.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        rd_bt_masculino.setText("Masculino");

        lb_pessoa.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        lb_pessoa.setText("Pessoa:");

        rd_bt_pessoa.add(rd_bt_fisica);
        rd_bt_fisica.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        rd_bt_fisica.setText("Física");

        rd_bt_pessoa.add(rd_bt_juridica);
        rd_bt_juridica.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        rd_bt_juridica.setText("Jurídica");

        jLabel14.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel14.setText("Profissão:");

        tf_profissao.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel15.setText("Nacionalidade:");

        tf_nacionalidade.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        tf_nacionalidade.setText("BRASILEIRO");

        jLabel17.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel17.setText("Nascimento:");

        tf_nascimento.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_rg, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(tf_profissao, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tf_nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tf_nacionalidade))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_pessoa)
                    .addComponent(lb_sexo))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rd_bt_feminino)
                    .addComponent(rd_bt_fisica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rd_bt_juridica)
                    .addComponent(rd_bt_masculino))
                .addGap(26, 26, 26))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tf_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tf_rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_sexo)
                    .addComponent(rd_bt_feminino)
                    .addComponent(rd_bt_masculino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_pessoa)
                    .addComponent(rd_bt_fisica)
                    .addComponent(rd_bt_juridica))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tf_profissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tf_nacionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(tf_nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Sobrenome", "CPF", "RG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_pesquisar))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_cancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed

        this.dispose();
    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void bt_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cadastrarActionPerformed
        
        limparCampos();
        cadastro(true);        
        this.modelo.setNumRows(0);
        this.modo = "cadastrar";
        this.bt_salvar.setEnabled(true);
        this.bt_cadastrar.setEnabled(false);
        this.bt_editar.setEnabled(true);
        this.tf_pesquisar.setEnabled(false);               
        this.tf_nome.requestFocus();       
        
    }//GEN-LAST:event_bt_cadastrarActionPerformed

    private void bt_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editarActionPerformed
        
        limparCampos();
        cadastro(false);
        this.modo = "editar";
        this.bt_editar.setEnabled(false);
        this.bt_cadastrar.setEnabled(true);
        this.modelo.setNumRows(0);
        this.tf_pesquisar.setEnabled(true);        
        this.tf_pesquisar.requestFocus();
    }//GEN-LAST:event_bt_editarActionPerformed

    private void bt_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salvarActionPerformed
        
        
        GUIFX g = new GUIFX();        
        g.start();
        
        if(Utilidades.textoVazio(tf_nome)) g.animar(tf_nome, GUIFX.EFEITO_TREMER, 40);
        
        else if(Utilidades.textoVazio(tf_sobrenome)) g.animar(tf_sobrenome, GUIFX.EFEITO_TREMER, 40);
        
        else if(Utilidades.textoVazio(tf_rua)) g.animar(tf_rua, GUIFX.EFEITO_TREMER, 40);
        
        else if(Utilidades.textoVazio(tf_cep)) g.animar(tf_cep, GUIFX.EFEITO_TREMER, 40);
        
        else if(Utilidades.textoVazio(tf_numero)) g.animar(tf_numero, GUIFX.EFEITO_TREMER, 40);
        
        else if(Utilidades.textoVazio(tf_cidade)) g.animar(tf_cidade, GUIFX.EFEITO_TREMER, 40);
        
        else if(Utilidades.textoVazio(tf_pais)) g.animar(tf_pais, GUIFX.EFEITO_TREMER, 40);
        
        else if(Utilidades.textoVazio(tf_celular1)) g.animar(tf_celular1, GUIFX.EFEITO_TREMER, 40);
        
        else if(Utilidades.textoVazio(tf_cpf)) g.animar(tf_cpf, GUIFX.EFEITO_TREMER, 40);
        
        else if(Utilidades.textoVazio(tf_rg)) g.animar(tf_rg, GUIFX.EFEITO_TREMER, 40);
                
        else if(!rd_bt_feminino.isSelected() && !rd_bt_masculino.isSelected()) g.animar(lb_sexo, GUIFX.EFEITO_TREMER, 40);
        
        else if(!rd_bt_fisica.isSelected() && !rd_bt_juridica.isSelected()) g.animar(lb_pessoa, GUIFX.EFEITO_TREMER, 40);
        
        else if(Utilidades.textoVazio(tf_profissao)) g.animar(tf_profissao, GUIFX.EFEITO_TREMER, 40);
        
        else if(Utilidades.textoVazio(tf_nacionalidade)) g.animar(tf_nacionalidade, GUIFX.EFEITO_TREMER, 40);
        
        else if(Utilidades.textoVazio(tf_nascimento)) g.animar(tf_nascimento, GUIFX.EFEITO_TREMER, 40);
        
        else {
            
            ContatoControle contatoC = new ContatoControle();            
            EnderecoControle enderecoC = new EnderecoControle();
            HospedeControle hospedeC = new HospedeControle();
            
            if(modo.equals("cadastrar")) {
                
                hospedeAtual = new HospedeBean();
                contatoAtual = new ContatoBean();
                enderecoAtual = new EnderecoBean();                        
            }
            
            //Dados de contato
            contatoAtual.setResidencial(tf_residencial.getText());
            contatoAtual.setCelular1(tf_celular1.getText());
            contatoAtual.setCelular2(tf_celular2.getText());
            contatoAtual.setCelular3(tf_celular3.getText());
            contatoAtual.setCelular4(tf_celular4.getText());
            contatoAtual.setFax(tf_fax.getText());
            contatoAtual.setCaixa_postal(tf_caixa_postal.getText());
                        
            //Dados de endereco
            enderecoAtual.setRua(tf_rua.getText());
            enderecoAtual.setNumero(tf_numero.getText());
            enderecoAtual.setComplemento(tf_complemento.getText());
            enderecoAtual.setCidade(tf_cidade.getText());
            enderecoAtual.setEstado(String.valueOf(cb_uf.getSelectedItem()));
            enderecoAtual.setCep(tf_cep.getText());
            enderecoAtual.setPais(tf_pais.getText());
            
            //Dados do hospede
            hospedeAtual.setNome(tf_nome.getText());
            hospedeAtual.setSobrenome(tf_sobrenome.getText());
            hospedeAtual.setCpf(tf_cpf.getText());
            hospedeAtual.setRg(tf_rg.getText());
            hospedeAtual.setSexo((rd_bt_feminino.isSelected())?"FEMININO":"MASCULINO");
            hospedeAtual.setPessoa((rd_bt_fisica.isSelected())?"FISICA":"JURIDICA");            
            hospedeAtual.setProfissao(tf_profissao.getText());
            hospedeAtual.setNacionalidade(tf_nacionalidade.getText());
            hospedeAtual.setNascimento(tf_nascimento.getText());
            
            switch (modo) {
                
                case "cadastrar":
                        
                    if(hospedeC.cadastrar(hospedeAtual, contatoAtual, enderecoAtual)) {
                        
                        reset();
                        Mensagem.okMsg("Hóspede cadastrado com sucesso!", "Sucesso");
                    }
                    
                    else Mensagem.erroMsg("Houve um erro ao cadastrar", "Falha no cadastro");
                    
                break;
                case "editar":
                    
                    if(hospedeC.atualizarHospede(hospedeAtual, contatoAtual, enderecoAtual)) {
                        
                        reset();
                        Mensagem.okMsg("Hóspede atualizado com sucesso!", "Sucesso");
                    }
                    
                    else Mensagem.erroMsg("Houve um erro ao atualizar", "Falha no cadastro");
                break;
            }
            
        }
        
    }//GEN-LAST:event_bt_salvarActionPerformed

    private void tf_pesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_pesquisarKeyReleased
        
        this.modo = "editar";
        
        HospedeControle hospedeC = new HospedeControle();
        
        hospedeC.pesquisarHospede(modelo, tf_pesquisar.getText());
        
    }//GEN-LAST:event_tf_pesquisarKeyReleased

    private void tabelaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMousePressed
        
        this.modo = "editar";
        
        HospedeControle hospedeC = new HospedeControle();
        hospedeAtual = new HospedeBean();

        String cpf = String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 2));
        
        hospedeAtual = hospedeC.buscarHospede(cpf);
        
        cadastro(true);
        preencherCampos();        
    }//GEN-LAST:event_tabelaMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cadastrar;
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_editar;
    private javax.swing.JButton bt_salvar;
    private javax.swing.JComboBox cb_uf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_pessoa;
    private javax.swing.JLabel lb_sexo;
    private javax.swing.JRadioButton rd_bt_feminino;
    private javax.swing.JRadioButton rd_bt_fisica;
    private javax.swing.JRadioButton rd_bt_juridica;
    private javax.swing.JRadioButton rd_bt_masculino;
    private javax.swing.ButtonGroup rd_bt_pessoa;
    private javax.swing.ButtonGroup rd_bt_sexo;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField tf_caixa_postal;
    private javax.swing.JTextField tf_celular1;
    private javax.swing.JTextField tf_celular2;
    private javax.swing.JTextField tf_celular3;
    private javax.swing.JTextField tf_celular4;
    private javax.swing.JTextField tf_cep;
    private javax.swing.JTextField tf_cidade;
    private javax.swing.JTextField tf_complemento;
    private javax.swing.JTextField tf_cpf;
    private javax.swing.JTextField tf_fax;
    private javax.swing.JTextField tf_nacionalidade;
    private javax.swing.JTextField tf_nascimento;
    private javax.swing.JTextField tf_nome;
    private javax.swing.JTextField tf_numero;
    private javax.swing.JTextField tf_pais;
    private javax.swing.JTextField tf_pesquisar;
    private javax.swing.JTextField tf_profissao;
    private javax.swing.JTextField tf_residencial;
    private javax.swing.JTextField tf_rg;
    private javax.swing.JTextField tf_rua;
    private javax.swing.JTextField tf_sobrenome;
    // End of variables declaration//GEN-END:variables

    
}
