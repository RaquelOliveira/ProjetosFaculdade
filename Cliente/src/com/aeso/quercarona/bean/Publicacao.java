package com.aeso.quercarona.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Publicacao implements Serializable {

	private String nome;
	private String Carona;
	private String local;
	private String assinante;
	private String obsercao;
	private Set<Publicacao> setPosts = new HashSet<Publicacao>();
	private Set<String> setOnlines = new HashSet<String>();
	private Action action;

	public String getCarona() {
		return Carona;
	}
	public void setCarona(String carona) {
		Carona = carona;
	}


	
	public Set<String> getSetOnlines() {
		return setOnlines;
	}
	public void setSetOnlines(Set<String> setOnlines) {
		this.setOnlines = setOnlines;
	}
	public Set<Publicacao> getSetPosts() {
		return setPosts;
	}
	public void setSetPosts(Set<Publicacao> setPosts) {
		this.setPosts = setPosts;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getAssinante() {
		return assinante;
	}

	public void setAssinante(String assinante) {
		this.assinante = assinante;
	}

	public String getObsercao() {
		return obsercao;
	}

	public void setObsercao(String obsercao) {
		this.obsercao = obsercao;
	}

	public enum Action {
		CONNECT, DISCONNECT, POSTAR, ASSINAR, ATUALIZAR, EXCLUIR, USERS_ONLINE ;
	}

}
