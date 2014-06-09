package com.aeso.quercarona.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.aeso.quercarona.bean.Publicacao;
import com.aeso.quercarona.bean.Publicacao.Action;

public class ServidorService {

	private ServerSocket serverSocket;
	private Socket socket;
	private Map<String, ObjectOutputStream> mapOnline = new HashMap<String, ObjectOutputStream>();
	private Map<Publicacao, ObjectOutputStream> mapPost = new HashMap<Publicacao, ObjectOutputStream>();

	public ServidorService() {
		try {
			serverSocket = new ServerSocket(5555);
			System.out.println("servidor on!");
			while (true) {
				socket = serverSocket.accept();

				new Thread(new ListenerSocket(socket)).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class ListenerSocket implements Runnable {

		private ObjectOutputStream output;
		private ObjectInputStream input;

		public ListenerSocket(Socket socket) {
			try {
				this.output = new ObjectOutputStream(socket.getOutputStream());
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
						boolean isConnect = connect(publicacao, output);
						if (isConnect) {
							mapOnline.put(publicacao.getNome(), output);
							atualizarQuadro();
						}
					} else if (action.equals(Action.DISCONNECT)) {
						disconnect(publicacao, output);
						return;

					} else if (action.equals(Action.ASSINAR)) {
						mapPost.put(publicacao, output);
						//sendAll(publicacao);
						atualizarQuadro();

					} else if (action.equals(Action.POSTAR)) {
						// sendOne
						sendOne(publicacao, output);
						//atualizarQuadro();

					} else if (action.equals(Action.EXCLUIR)) {
						remove(publicacao);
						atualizarQuadro();
					}else if(action.equals(Action.USERS_ONLINE)){
						atualizarQuadro();
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				disconnect(publicacao, output);
				System.out.println(publicacao.getNome() + " saio do quadro");
				// e.printStackTrace();
			}

		}
	}

	private boolean connect(Publicacao publicacao, ObjectOutputStream output) {
		if (mapOnline.size() == 0) {
			publicacao.setObsercao("YES");
			sendOne(publicacao, output);
			return true;
		}

		for (Map.Entry<String, ObjectOutputStream> kv : mapOnline.entrySet()) {
			if (kv.getKey().trim().equals(publicacao.getNome())) {
				publicacao.setObsercao("NO");
				//sendOne(publicacao, output);
				return false;
			} else {
				publicacao.setObsercao("YES");
				sendOne(publicacao, output);
				return true;
			}

		}
		return false;
	}

	private void disconnect(Publicacao publicacao, ObjectOutputStream output) {
		mapOnline.remove(publicacao.getNome());
		publicacao.setObsercao("Até logo!");
		publicacao.setAction(Action.POSTAR);
		sendAll(publicacao);
		System.out.println("user" + publicacao.getNome() + " saiu da sala");
	}

	private void sendOne(Publicacao publicacao, ObjectOutputStream output) {
		//mapPost.containsKey(publicacao);
		try {
			
			output.writeObject(publicacao);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void sendAll(Publicacao publicacao) {
		
		for (Map.Entry<String, ObjectOutputStream> kv : mapOnline.entrySet()) {
			
			publicacao.setAction(Action.POSTAR);
			try {

				kv.getValue().writeObject(publicacao);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public void  darcarona(Publicacao publicacao){
		mapPost.remove(publicacao);
		
	}

	private void atualizarQuadro() {

		Set<Publicacao> setPosts = new HashSet<Publicacao>();
		for (Map.Entry<Publicacao, ObjectOutputStream> map : mapPost.entrySet()) {
			setPosts.add(map.getKey());
		}
		Publicacao publicacao = new Publicacao();
		publicacao.setAction(Action.USERS_ONLINE); // verificar depois se é
		publicacao.setSetPosts(setPosts);          // realmente essa ação
		
		System.out.println(publicacao.getSetPosts().size());
		for (Map.Entry<String, ObjectOutputStream> kv : mapOnline.entrySet()) {
			
			try {
				System.out.println("name -- "+ kv.getKey());
				kv.getValue().writeObject(publicacao);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
//	private void disconnect(Publicacao publicacao, ObjectOutputStream output) {
//		mapOnline.remove(publicacao.getNome());
//		publicacao.setObsercao("Até logo!");
//		publicacao.setAction(Action.POSTAR);
//		sendAll(publicacao);
//		System.out.println("user" + publicacao.getNome() + " saiu da sala");
//	}
	public void remove(Publicacao publicacao){
		
		publicacao.setAction(Action.ASSINAR);
		for (Map.Entry<Publicacao, ObjectOutputStream> map : mapPost.entrySet()) {
			if(map.getKey().equals(publicacao)){
				System.out.println("ok");
			}	
		}
		
		//publicacao.setAction(Action.EXCLUIR);
		
	}

}
