package br.com.starwarschallenge.model;

import java.io.Serializable;

public class PlanetaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String nome;
	private String clima;
	private String terreno;
	private int qtdAparicoesFilmes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public int getQtdAparicoesFilmes() {
		return qtdAparicoesFilmes;
	}

	public void setQtdAparicoesFilmes(int qtdAparicoesFilmes) {
		this.qtdAparicoesFilmes = qtdAparicoesFilmes;
	}

}
