package com.aeso.quercarona.app;

import java.awt.EventQueue;

import com.aeso.quercarona.frame.CadastrarUsuario;

public class Cliente {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarUsuario frame = new CadastrarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
