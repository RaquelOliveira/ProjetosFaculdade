package com.aeso.quercarona.service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.aeso.quercarona.bean.Publicacao;

public class ClienteService {
	
	private Socket socket;
	private ObjectOutputStream output;
	
	
	public Socket connect(String ip){
		try {
			this.socket= new Socket(ip, 5555);
			this.output= new  ObjectOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return socket;
	}
	
	public void send(Publicacao publicacao){
		
		try {
			output.writeObject(publicacao);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
