package com.aeso.quercarona.frame;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;


public class FrameInserirPost extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textDestino;
	private JLabel lblNome_Ass;
	private JTextField textNomeAss;
	private JLabel lblContato;
	private JTextField textContato;
	private JLabel lblObs;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameInserirPost frame = new FrameInserirPost();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameInserirPost() {
		setTitle("QueroCarona");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrameInserirPost.class.getResource("/Imagens/Carona.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 337, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(66, 8, 245, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setBounds(10, 49, 46, 14);
		contentPane.add(lblDestino);
		
		textDestino = new JTextField();
		textDestino.setBounds(66, 39, 245, 34);
		contentPane.add(textDestino);
		textDestino.setColumns(10);
		
		lblNome_Ass = new JLabel("Nome:");
		lblNome_Ass.setBounds(10, 123, 46, 14);
		contentPane.add(lblNome_Ass);
		
		textNomeAss = new JTextField();
		textNomeAss.setBounds(66, 120, 245, 20);
		contentPane.add(textNomeAss);
		textNomeAss.setColumns(10);
		
		lblContato = new JLabel("Contato:");
		lblContato.setBounds(10, 160, 46, 14);
		contentPane.add(lblContato);
		
		textContato = new JTextField();
		textContato.setBounds(66, 155, 245, 25);
		contentPane.add(textContato);
		textContato.setColumns(10);
		
		lblObs = new JLabel("Obs.:");
		lblObs.setBounds(10, 222, 46, 14);
		contentPane.add(lblObs);
		
		textField = new JTextField();
		textField.setBounds(10, 239, 301, 44);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.setIcon(new ImageIcon(FrameInserirPost.class.getResource("/Imagens/new.png")));
		btnPublicar.setBounds(10, 287, 89, 23);
		contentPane.add(btnPublicar);
		
		JButton btnAssinar = new JButton("Assinar");
		btnAssinar.setIcon(new ImageIcon(FrameInserirPost.class.getResource("/Imagens/edit.png")));
		btnAssinar.setBounds(118, 287, 89, 23);
		contentPane.add(btnAssinar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(FrameInserirPost.class.getResource("/Imagens/trash.png")));
		btnExcluir.setBounds(222, 287, 89, 23);
		contentPane.add(btnExcluir);
	}
}
