package br.com.starwarschallenge.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

public class Planeta {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Planeta")
	@TableGenerator(name = "Planeta", table = "sequences", pkColumnName = "id")
	private long id;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "CLIMA", nullable = false)
	private String clima;

	@Column(name = "TERRENO", nullable = false)
	private String terreno;

	@Column(name = "QTDAPARICOESFILMES", nullable = false)
	private int qtdAparicoesFilmes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	@Override
	public String toString() {
		return "Planeta [id=" + id + ", nome=" + nome + ", clima=" + clima + ", terreno=" + terreno
				+ ", qtdAparicoesFilmes=" + qtdAparicoesFilmes + "]";
	}

}
