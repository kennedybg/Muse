/*
 * Projeto Muse.
 * Gerenciamento Hoteleiro
 *  I.T WORKS.
 */
package br.com.muse.gui;

import br.com.muse.beans.ContatoBean;
import br.com.muse.beans.EnderecoBean;
import br.com.muse.beans.FuncionarioBean;
import br.com.muse.controller.ContatoControle;
import br.com.muse.controller.EnderecoControle;
import br.com.muse.controller.FuncionarioControle;
import br.com.muse.util.Cerbero;
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
public class FuncionarioGUI extends javax.swing.JInternalFrame implements IPosicaoGUI{

    private final String estados[] = { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO" };
    private final String niveis[] = {"Nível 1", "Nível 2", "Nível 3"};
    private String modo;
    public DefaultTableModel modelo;
    private FuncionarioBean funcionarioAtual = new FuncionarioBean();;
    private EnderecoBean enderecoAtual = new EnderecoBean();
    private ContatoBean contatoAtual = new ContatoBean();
    
    
    /**
     * Creates new form FuncionarioGUI
     */
    public FuncionarioGUI() {
        
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
        this.tf_usuario.setEnabled(b);
        this.pf_senha.setEnabled(b);
        this.cb_acesso.setEnabled(b);                
        this.cb_ativo.setEnabled(b);
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
        this.tf_usuario.setText("");
        this.pf_senha.setText("");
        this.cb_acesso.setSelectedIndex(0);   
        this.tf_pesquisar.setText("");
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
    
    private void preencherCampos() {
        
        EnderecoControle enderecoC = new EnderecoControle();
        ContatoControle contatoC = new ContatoControle();        
        
        enderecoAtual = enderecoC.buscarEndereco(funcionarioAtual.getId_endereco());
        contatoAtual = contatoC.buscarContato(funcionarioAtual.getId_contato());        
        
        //Dados funcionario
        this.tf_nome.setText(funcionarioAtual.getNome());
        this.tf_sobrenome.setText(funcionarioAtual.getSobreNome());
        this.tf_usuario.setText(funcionarioAtual.getLogin());
        this.tf_usuario.setText(funcionarioAtual.getLogin());
        this.cb_acesso.setSelectedIndex(funcionarioAtual.getNivel_acesso()-1);
        this.cb_ativo.setSelected(funcionarioAtual.isAtivo());
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
        
        if(!funcionarioAtual.isAtivo()) cadastro(false);
        this.cb_ativo.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_salvar = new javax.swing.JButton();
        bt_cadastrar = new javax.swing.JButton();
        bt_editar = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tf_pesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
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
        cb_ativo = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        cb_acesso = new javax.swing.JComboBox(niveis);
        jLabel23 = new javax.swing.JLabel();
        tf_usuario = new javax.swing.JTextField();
        pf_senha = new javax.swing.JPasswordField();
        jLabel22 = new javax.swing.JLabel();
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

        setClosable(true);
        setResizable(true);
        setTitle("Gerenciar funcionários");
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

        jLabel1.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel1.setText("Pesquisar:");

        tf_pesquisar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        tf_pesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_pesquisarKeyReleased(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Sobrenome", "Login", "Nivel de Acesso", "Ativo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        cb_ativo.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        cb_ativo.setText("Ativo");
        cb_ativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ativocb_ativoActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel21.setText("Acesso:");

        jLabel23.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel23.setText("Usuario:");

        tf_usuario.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel22.setText("Senha:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tf_usuario, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cb_acesso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pf_senha, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cb_ativo))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(tf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(pf_senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cb_acesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_ativo)
                .addContainerGap(116, Short.MAX_VALUE))
        );

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
        tf_cep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_cepKeyReleased(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_pesquisar))
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_cancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
        
        this.dispose();
    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void bt_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cadastrarActionPerformed

        limparCampos();
        cadastro(true);
        this.cb_ativo.setSelected(true);
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
        
        else if(Utilidades.textoVazio(tf_usuario)) g.animar(tf_usuario, GUIFX.EFEITO_TREMER, 40);
        
        else if(this.modo.equals("cadastrar") && Utilidades.senhaVazia(pf_senha)) g.animar(pf_senha, GUIFX.EFEITO_TREMER, 40);
        
        else {
            
            ContatoControle contatoC = new ContatoControle();            
            EnderecoControle enderecoC = new EnderecoControle();
            FuncionarioControle funcionarioC = new FuncionarioControle();
            
            if(modo.equals("cadastrar")) {
                
                funcionarioAtual = new FuncionarioBean();
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
            
            //Dados do funcionario            
            funcionarioAtual.setNome(tf_nome.getText());
            funcionarioAtual.setSobreNome(tf_sobrenome.getText());
            funcionarioAtual.setLogin(tf_usuario.getText());
            if(this.modo.equals("cadastrar"))funcionarioAtual.setSenha(new String(pf_senha.getPassword()));
            
            else if(this.modo.equals("editar")) if(!Utilidades.senhaVazia(pf_senha)) funcionarioAtual.setSenha(Cerbero.crypt256(new String(pf_senha.getPassword())));
            
            funcionarioAtual.setNivel_acesso(cb_acesso.getSelectedIndex()+1);
            funcionarioAtual.setAtivo(cb_ativo.isSelected());
                                    
            switch (modo) {
                
                case "cadastrar":
                        
                    if(funcionarioC.cadastrar(funcionarioAtual, contatoAtual, enderecoAtual)) {
                        
                        reset();
                        Mensagem.okMsg("Funcionário cadastrado com sucesso!", "Sucesso");
                    }
                    
                    else Mensagem.erroMsg("Houve um erro ao cadastrar", "Falha no cadastro");
                    
                break;
                case "editar":
                    
                    if(funcionarioC.atualizarFuncionario(funcionarioAtual, contatoAtual, enderecoAtual)) {
                        
                        reset();
                        Mensagem.okMsg("Funcionário atualizado com sucesso!", "Sucesso");
                    }
                    
                    else Mensagem.erroMsg("Houve um erro ao atualizar", "Falha no cadastro");
                break;
            }            
        }         
    }//GEN-LAST:event_bt_salvarActionPerformed

    
    private void tf_pesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_pesquisarKeyReleased
        
        this.modo = "editar";
        
        FuncionarioControle funcionarioC = new FuncionarioControle();
        
        funcionarioC.pesquisarFuncionario(modelo, tf_pesquisar.getText());        
    }//GEN-LAST:event_tf_pesquisarKeyReleased

    private void tabelaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMousePressed
        
        this.modo = "editar";
        
        FuncionarioControle funcionarioC = new FuncionarioControle();
        funcionarioAtual = new FuncionarioBean();

        String login = String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 2));
        
        funcionarioAtual = funcionarioC.buscarFuncionario(login);
        
        cadastro(true);
        preencherCampos();
    }//GEN-LAST:event_tabelaMousePressed

    private void cb_ativocb_ativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ativocb_ativoActionPerformed
        
        
        if(this.modo.equals("editar")) {
            
            if(this.cb_ativo.isSelected()) cadastro(true);
            else {
                cadastro(false);
                this.cb_ativo.setEnabled(true);
            } 
                
                
        }
            
    }//GEN-LAST:event_cb_ativocb_ativoActionPerformed

    private void tf_cepKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_cepKeyReleased
        
        tf_cep.setText(Utilidades.mascarar("#####-###", tf_cep.getText()));
    }//GEN-LAST:event_tf_cepKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cadastrar;
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_editar;
    private javax.swing.JButton bt_salvar;
    private javax.swing.JComboBox cb_acesso;
    private javax.swing.JCheckBox cb_ativo;
    private javax.swing.JComboBox cb_uf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JPasswordField pf_senha;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField tf_caixa_postal;
    private javax.swing.JTextField tf_celular1;
    private javax.swing.JTextField tf_celular2;
    private javax.swing.JTextField tf_celular3;
    private javax.swing.JTextField tf_celular4;
    private javax.swing.JTextField tf_cep;
    private javax.swing.JTextField tf_cidade;
    private javax.swing.JTextField tf_complemento;
    private javax.swing.JTextField tf_fax;
    private javax.swing.JTextField tf_nome;
    private javax.swing.JTextField tf_numero;
    private javax.swing.JTextField tf_pais;
    private javax.swing.JTextField tf_pesquisar;
    private javax.swing.JTextField tf_residencial;
    private javax.swing.JTextField tf_rua;
    private javax.swing.JTextField tf_sobrenome;
    private javax.swing.JTextField tf_usuario;
    // End of variables declaration//GEN-END:variables
}
