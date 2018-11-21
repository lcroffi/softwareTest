/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import model.Contato;
import model.Criptografia;
import model.ServicoContatoImpl;

/**
 *
 * @author fabriciogmc
 */
public class EditarCadastro extends javax.swing.JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	public List<Contato> contatos;
	public static String nomeUsuario;

	public static String nome;
	public static String[] user;
	public Contato cAnt, cAtual;
	Criptografia c = new Criptografia();

	public EditarCadastro(String[] user, String nome) throws IOException {
		EditarCadastro.user = user;
		EditarCadastro.nome = nome;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 * 
	 * @throws IOException
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() throws IOException {

		jTextField1 = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jTextField4 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

		jLabel1.setText("Nome");

		jLabel2.setText("Telefone");

		jLabel3.setText("Email");

		jLabel4.setText("Endere�o");

		jButton1.setText("Editar");
		jButton1.addActionListener(this);

		nomeUsuario = "";
		for (String s : user) {
			nomeUsuario = nomeUsuario + s;
		}

		String nomi = "";
		String telefone = "";
		String email = "";
		String endereco = "";
		String arq = "data_u" + c.cripto(nomeUsuario, 3) + ".txt";
		ServicoContatoImpl servCont = new ServicoContatoImpl(arq);
		contatos = servCont.listarTodosContatos();
		for (Contato contato : contatos) {
			if (contato.getNomeContato().equals(nome)) {
				nomi = contato.getNomeContato();
				telefone = contato.getTelefone();
				email = contato.getEmail();
				endereco = contato.getEndereco();
				cAnt = contato;
			}
		}
		jTextField1.setText(nomi.trim());
		jTextField2.setText(endereco.trim());
		jTextField3.setText(telefone.trim());
		jTextField4.setText(email.trim());

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(32, 32, 32).addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel1).addComponent(jLabel3)
														.addComponent(jLabel2))
										.addGap(21, 21, 21)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 211,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 211,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 211,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup().addComponent(jLabel4).addGap(18, 18, 18)
										.addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 211,
														Short.MAX_VALUE))))
								.addContainerGap(55, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(42, 42, 42)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel1))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(layout
						.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel3).addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18).addComponent(jLabel4)).addComponent(jTextField2,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18).addComponent(jButton1).addContainerGap(79, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] user) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting
		// code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
		 * html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(EditarCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(EditarCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(EditarCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(EditarCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new EditarCadastro(user, nome).setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	// End of variables declaration//GEN-END:variables


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButton1) {
			String nome2 = jTextField1.getText();
			String telefone2 = jTextField3.getText();
			String email2 = jTextField4.getText();
			String endereco2 = jTextField2.getText();
			String arq = "data_u" + c.cripto(nomeUsuario, 3) + ".txt";
			ServicoContatoImpl servCont = new ServicoContatoImpl(arq);
			cAtual = servCont.inserir(nome2, telefone2, email2, endereco2);
			if (cAtual == null) {
				JOptionPane.showMessageDialog(null, servCont.getAviso());
				}
			else {
				if (servCont.atualizarContato(cAnt, cAtual) != null) {
					JOptionPane.showMessageDialog(null, "Contato atualizado com sucesso.");
					dispose();
				}
			}
		}

	}

}