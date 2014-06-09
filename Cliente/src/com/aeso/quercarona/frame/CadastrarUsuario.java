package com.aeso.quercarona.frame;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.aeso.quercarona.bean.Publicacao;
import com.aeso.quercarona.bean.Publicacao.Action;
import com.aeso.quercarona.service.ClienteService;

public class CadastrarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private Thread t;
	private Socket socket;
	private Publicacao publicacao;
	private ClienteService service;
	private JTextField txtCarona;
	private JTextField txtDestino;
	private JTextField txtMotorista;
	private JTextField txtObservacao;
	private JButton btnPedirCarona;
	private JButton btnDarCarona;
	private JButton btnAtualizarOQuadro;
	private JButton btnConectar;
	private JButton btnSair;
	private JPanel panel_;
	private JTextArea txtPost1;
	private JTextArea txtPost2;
	private JTextArea txtPost3;
	private JTextArea txtPost4;
	private JTextArea txtPost5;
	private JTextArea txtPost6;
	private JTextField txtIp;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	public CadastrarUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				CadastrarUsuario.class.getResource("/Imagens/Carona.jpg")));
		setTitle("QueroCarona");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1107, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnPedirCarona = new JButton("Pedir Carona");
		btnPedirCarona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarPostagem();
				publicacao = capturarDados();
				publicacao.setAction(Action.ASSINAR);
				service.send(publicacao);
				limparDados();

			}
		});
		btnPedirCarona.setIcon(new ImageIcon(CadastrarUsuario.class
				.getResource("/Imagens/add.png")));
		btnPedirCarona.setBounds(892, 307, 176, 23);
		contentPane.add(btnPedirCarona);

		btnDarCarona = new JButton("Dar Carona");
		btnDarCarona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDarCarona.setIcon(new ImageIcon(CadastrarUsuario.class
				.getResource("/Imagens/edit.png")));
		btnDarCarona.setBounds(892, 375, 176, 23);
		contentPane.add(btnDarCarona);

		btnAtualizarOQuadro = new JButton("Atualizar o Quadro");
		btnAtualizarOQuadro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAtualizarOQuadro.setIcon(new ImageIcon(CadastrarUsuario.class
				.getResource("/Imagens/refresh.png")));
		btnAtualizarOQuadro.setBounds(892, 341, 176, 23);
		contentPane.add(btnAtualizarOQuadro);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Conectar",
				TitledBorder.LEADING, TitledBorder.TOP, null, null),
				"Conectar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 604, 88);
		contentPane.add(panel);
		panel.setLayout(null);

		txtName = new JTextField();
		txtName.setBounds(320, 23, 143, 20);
		panel.add(txtName);
		txtName.setColumns(10);

		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				if (!(txtName == null || txtName.getText().equals(""))) {
					if (!(txtIp == null || txtIp.getText().equals(""))) {
						String name = txtName.getText();
						String ip = txtIp.getText();

						publicacao = new Publicacao();
						publicacao.setAction(Action.CONNECT);
						publicacao.setNome(name);

						service = new ClienteService();
						socket = service.connect(ip);

						t = new Thread(new ListenerSocket(socket));
						t.start();

						// validarPostagem();
						service.send(publicacao);

					}

				} else {
					JOptionPane.showMessageDialog(null,
						"Para se conectar é necessário informar o ip do servidor e seu nome \n verifique se os dados estão preenchidos e tente novamente");
				}

			}

		});
		btnConectar.setBounds(32, 54, 89, 23);
		panel.add(btnConectar);

		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publicacao.setAction(Action.DISCONNECT);
				service.send(publicacao);
				disconnect();
			}
		});
		btnSair.setBounds(175, 54, 89, 23);
		panel.add(btnSair);

		txtIp = new JTextField();
		txtIp.setBounds(45, 23, 166, 20);
		panel.add(txtIp);
		txtIp.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(248, 26, 46, 14);
		panel.add(lblNome);

		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(10, 26, 46, 14);
		panel.add(lblIp);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Pedir Carona",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(882, 11, 199, 285);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel label = new JLabel("Nome:");
		label.setBounds(10, 31, 46, 14);
		panel_1.add(label);

		txtCarona = new JTextField();
		txtCarona.setColumns(10);
		txtCarona.setBounds(10, 56, 179, 26);
		panel_1.add(txtCarona);

		JLabel label_1 = new JLabel("Destino");
		label_1.setBounds(10, 93, 46, 14);
		panel_1.add(label_1);

		txtDestino = new JTextField();
		txtDestino.setColumns(10);
		txtDestino.setBounds(10, 108, 179, 26);
		panel_1.add(txtDestino);

		JLabel label_2 = new JLabel("Motorista:");
		label_2.setBounds(10, 145, 61, 14);
		panel_1.add(label_2);

		txtMotorista = new JTextField();
		txtMotorista.setColumns(10);
		txtMotorista.setBounds(10, 170, 179, 26);
		panel_1.add(txtMotorista);

		JLabel label_3 = new JLabel("Observa\u00E7\u00E3o:");
		label_3.setBounds(10, 207, 77, 14);
		panel_1.add(label_3);

		txtObservacao = new JTextField();
		txtObservacao.setColumns(10);
		txtObservacao.setBounds(10, 221, 179, 53);
		panel_1.add(txtObservacao);

		panel_ = new JPanel();
		panel_.setBackground(SystemColor.menu);
		panel_.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_.setBounds(10, 104, 862, 515);
		contentPane.add(panel_);
		panel_.setLayout(null);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				publicacao = capturarDados();
				publicacao.setNome(publicacao.getCarona());
				publicacao.setAction(Action.EXCLUIR);
				service.send(publicacao);
			}
		});
		btnExcluir.setBounds(892, 412, 176, 23);
		contentPane.add(btnExcluir);

		montarPost();
	}

	public class ListenerSocket implements Runnable {

		private ObjectInputStream input;

		public ListenerSocket(Socket socket) {
			try {
				this.input = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {

			Publicacao publicacao = null;

			try {
				while ((publicacao = (Publicacao) input.readObject()) != null) {
					Action action = publicacao.getAction();

					if (action.equals(Action.CONNECT)) {
						connected(publicacao);

					} else if (action.equals(Action.DISCONNECT)) {
						disconnect();

						// } else if (action.equals(Action.ASSINAR)) { // não
						// sei se é
						// necessário
						// assinar(publicacao);

					} else if (action.equals(Action.ATUALIZAR)) {
						atualizar(publicacao);
					} else if (action.equals(Action.EXCLUIR)) { // não sei se é
																// nescessário
						excluir(publicacao);

					} else if (action.equals(Action.POSTAR)) {
						receive(publicacao);

					} else if (action.equals(Action.USERS_ONLINE)) {
						atualizaOnline(publicacao);
					}

				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void connected(Publicacao publicacao) {
		if (publicacao.getObsercao().equals("NO")) {
			txtName.setText("");
			JOptionPane
					.showMessageDialog(this,
							"Conexão não realizada! \n Tente novamente com um novo nome");
			return;
		}
		this.publicacao = publicacao;
		btnConectar.setEnabled(false);
		txtName.setEditable(false);
		btnSair.setEnabled(true);

		JOptionPane.showMessageDialog(this, "Conexão realizada com sucesso!");

	}

	public void disconnect() {

		btnConectar.setEnabled(true);
		txtName.setEditable(true);
		btnSair.setEnabled(false);

		JOptionPane.showMessageDialog(this, "Você saiu da conexão");

	}

	public void receive(Publicacao publicacao) {
		// necessário antes de chegar a esse método validar se motrorista ou
		// passageiro foram preenchido e local
		// validando se quem postou foi o carona ou motorista
		// String motorista = " ";
		// String carona = "";
		// String obs = "";
		// if (publicacao.getCarona() == null
		// || publicacao.getCarona().trim().equals("")) {
		//
		// motorista = (publicacao.getNome());
		//
		// } else if (publicacao.getAssinante() == null
		// || publicacao.getAssinante().trim().equals("")) {
		//
		// carona = publicacao.getCarona();
		// }
		//
		// if (!(publicacao.getObsercao() == null || publicacao.getObsercao()
		// .trim().equals(""))) {
		// obs = (publicacao.getLocal());
		//
		// }
		//
		// String local = (publicacao.getLocal());
		// String post = "NOME:" + carona + "\n LOCAL:" + local +
		// "\n MOTORISTA:"
		// + motorista + "\n OBS.:" + obs;
		// txtPost1.setText(post);
		montarQuadro(publicacao);
	}

	public void assinar(Publicacao publicacao) {

	}

	public void atualizar(Publicacao publicacao) {

	}

	public void excluir(Publicacao publicacao) {

	}

	public void atualizaOnline(Publicacao publicacao) {
		limparQuadro();
		Set<Publicacao> posts = publicacao.getSetPosts();

		this.publicacao.setSetPosts(posts);
		System.out.println(publicacao.getSetPosts().size());
		// montarQuadro(publicacao);

		String motorista = "";
		String carona = "";
		String obs = "";
		for (Publicacao pub : publicacao.getSetPosts()) {

			if (pub.getCarona() == null || pub.getCarona().trim().equals("")) {

				motorista = (pub.getCarona());

			} else if (pub.getAssinante() == null
					|| pub.getAssinante().trim().equals("")) {

				carona = pub.getCarona();
			}

			if (!(pub.getObsercao() == null || pub.getObsercao().trim()
					.equals(""))) {
				obs = (pub.getObsercao());

			}

			String local = (pub.getLocal());

			String post = "NOME:" + carona + "\n LOCAL:" + local
					+ "\n MOTORISTA:" + motorista + "\n OBS.:" + obs;

			if (txtPost1.getText().equals("") || txtPost1 == null) {
				txtPost1.setText(post);
			} else if (txtPost2.getText().equals("") || txtPost2 == null) {
				txtPost2.setText(post);
			} else if (txtPost3.getText().equals("") || txtPost3 == null) {
				txtPost3.setText(post);
			} else if (txtPost4.getText().equals("") || txtPost4 == null) {
				txtPost3.setText(post);
			} else if (txtPost5.getText().equals("") || txtPost5 == null) {
				txtPost4.setText(post);
			} else if (txtPost6.getText().equals("") || txtPost6 == null) {
				txtPost6.setText(post);
			}
		}

	}

	// Verificar com o professor se um return seria melhor nesse caso
	public void validarPostagem() {
		// validando se o nome motorista ou carona foram preenchidos
		int verif = 0;
		try {
			if (txtCarona.getText() == null
					|| txtCarona.getText().trim().equals("")) {

				verif++;

			}
			if (txtMotorista.getText() == null
					|| txtMotorista.getText().trim().equals("")) {

				verif++;

			}
			if (verif == 2) {
				throw new IllegalArgumentException();
			}

			if (txtDestino.getText() == null
					|| txtDestino.getText().trim().equals("")) {
				throw new IllegalArgumentException();
			}

		} catch (IllegalArgumentException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"Para postar no quadro é necessário informar nome (motorista ou passageiro)  "
									+ "							e local. \n    Por favor certifique se os campos foram preenchidos  e tente novamente.");
		}

	}

	public void montarPost() {

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.info);
		panel_3.setBorder(null);
		panel_3.setLayout(null);
		panel_3.setBounds(596, 11, 256, 212);
		panel_.add(panel_3);

		JButton button_2 = new JButton("Selecionar");
		button_2.setBounds(10, 182, 105, 23);
		panel_3.add(button_2);

		txtPost3 = new JTextArea();
		txtPost3.setBackground(SystemColor.info);
		txtPost3.setBounds(10, 11, 236, 160);
		panel_3.add(txtPost3);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.info);
		panel_1.setLayout(null);
		panel_1.setBorder(null);
		panel_1.setBounds(10, 11, 256, 212);
		panel_.add(panel_1);

		JButton button = new JButton("Selecionar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publicacao = selecionar();
				txtCarona.setText(publicacao.getCarona());
				txtDestino.setText(publicacao.getLocal());
				txtMotorista.setText(publicacao.getAssinante());
				txtObservacao.setText(publicacao.getObsercao());

			}
		});
		button.setForeground(SystemColor.infoText);
		button.setBackground(SystemColor.info);
		button.setBounds(67, 178, 105, 23);
		panel_1.add(button);

		txtPost1 = new JTextArea();
		txtPost1.setBackground(SystemColor.info);
		txtPost1.setBounds(10, 11, 236, 160);
		panel_1.add(txtPost1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.info);
		panel_2.setLayout(null);
		panel_2.setBorder(null);
		panel_2.setBounds(297, 11, 256, 212);
		panel_.add(panel_2);

		JButton button_1 = new JButton("Selecionar");
		button_1.setBounds(81, 182, 105, 23);
		panel_2.add(button_1);

		txtPost2 = new JTextArea();
		txtPost2.setBackground(SystemColor.info);
		txtPost2.setBounds(10, 11, 236, 160);
		panel_2.add(txtPost2);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.info);
		panel_4.setLayout(null);
		panel_4.setBorder(null);
		panel_4.setBounds(10, 234, 256, 212);
		panel_.add(panel_4);

		JButton button_3 = new JButton("Selecionar");
		button_3.setBounds(10, 182, 105, 23);
		panel_4.add(button_3);

		txtPost4 = new JTextArea();
		txtPost4.setBackground(SystemColor.info);
		txtPost4.setBounds(10, 11, 236, 160);
		panel_4.add(txtPost4);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.info);
		panel_5.setLayout(null);
		panel_5.setBorder(null);
		panel_5.setBounds(297, 234, 256, 212);
		panel_.add(panel_5);

		JButton button_4 = new JButton("Selecionar");
		button_4.setBounds(10, 182, 105, 23);
		panel_5.add(button_4);

		txtPost5 = new JTextArea();
		txtPost5.setBackground(SystemColor.info);
		txtPost5.setBounds(10, 11, 236, 160);
		panel_5.add(txtPost5);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.info);
		panel_6.setLayout(null);
		panel_6.setBorder(null);
		panel_6.setBounds(596, 234, 256, 212);
		panel_.add(panel_6);

		JButton button_5 = new JButton("Selecionar");
		button_5.setBounds(10, 182, 105, 23);
		panel_6.add(button_5);

		txtPost6 = new JTextArea();
		txtPost6.setBackground(SystemColor.info);
		txtPost6.setBounds(10, 11, 236, 160);
		panel_6.add(txtPost6);
	}

	public void limparDados() {
		txtCarona.setText("");
		txtDestino.setText("");
		txtMotorista.setText("");
		txtObservacao.setText("");
	}

	public Publicacao selecionar() {
		Publicacao publicacao = new Publicacao();
		int local = txtPost1.getText().indexOf("LOCAL");
		int mot = txtPost1.getText().indexOf("MOTORISTA");
		int obs = txtPost1.getText().indexOf("OBS.:");
		publicacao.setCarona(txtPost1.getText().substring(5, local));
		publicacao.setLocal(txtPost1.getText().substring(local + 6, mot));
		publicacao.setAssinante(txtPost1.getText().substring(mot + 10, obs));
		publicacao.setObsercao(txtPost1.getText().substring(obs + 5));

		return publicacao;

	}

	public void montarQuadro(Publicacao publicacao) {
		int cont = 1;
		System.out.println("montarquadro" + publicacao.getLocal());
		String motorista = " ";
		String carona = "";
		String obs = "";

		for (Publicacao pub : publicacao.getSetPosts()) {

			motorista = (publicacao.getAssinante());
			carona = publicacao.getCarona();
			obs = (publicacao.getObsercao());
			String local = (publicacao.getLocal());
			String post = "NOME:" + carona + "\n LOCAL:" + local
					+ "\n MOTORISTA:" + motorista + "\n OBS.:" + obs;

			if (txtPost1.equals("") || txtPost1 == null) {
				txtPost1.setText(post);
			} else if (txtPost2.equals("") || txtPost2 == null) {
				txtPost2.setText(post);
			} else if (txtPost3.equals("") || txtPost3 == null) {
				txtPost3.setText(post);
			} else if (txtPost4.equals("") || txtPost4 == null) {
				txtPost3.setText(post);
			} else if (txtPost5.equals("") || txtPost5 == null) {
				txtPost4.setText(post);
			} else if (txtPost6.equals("") || txtPost6 == null) {
				txtPost6.setText(post);
			}

		}
	}

	public Publicacao capturarDados() {
		publicacao = new Publicacao();
		publicacao.setNome(txtName.getText());
		publicacao.setCarona(txtCarona.getText());
		publicacao.setLocal(txtDestino.getText());
		if (!(txtObservacao.getText() == null || txtObservacao.getText().trim()
				.equals(""))) {
			publicacao.setObsercao(txtDestino.getText());
		}

		return publicacao;
	}

	public void limparQuadro() {
		txtPost1.setText("");
		txtPost2.setText("");
		txtPost3.setText("");
		txtPost4.setText("");
		txtPost5.setText("");
		txtPost6.setText("");
	}
}
