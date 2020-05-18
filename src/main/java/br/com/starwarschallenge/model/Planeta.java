package br.com.starwarschallenge.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planetas")
public class Planeta implements Serializable {

	private static final long serialVersionUID = -7922368291192477983L;

	@Id
	private String id;
	private String nome;
	private String clima;
	private String terreno;
	private int qtdAparicoesFilmes;

	public Planeta() {
		super();
	}

	public Planeta(String id, String nome, String clima, String terreno, int qtdAparicoesFilmes) {
		super();
		this.id = id;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.qtdAparicoesFilmes = qtdAparicoesFilmes;
	}

	public Planeta(String nome, String clima, String terreno, int qtdAparicoesFilmes) {
		super();
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.qtdAparicoesFilmes = qtdAparicoesFilmes;
	}

	@Transient
	public static final String SEQUENCE_NAME = "planetas_sequence";

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

	@Override
	public String toString() {
		return "Planeta [id=" + id + ", nome=" + nome + ", clima=" + clima + ", terreno=" + terreno
				+ ", qtdAparicoesFilmes=" + qtdAparicoesFilmes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clima == null) ? 0 : clima.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + qtdAparicoesFilmes;
		result = prime * result + ((terreno == null) ? 0 : terreno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planeta other = (Planeta) obj;
		if (clima == null) {
			if (other.clima != null)
				return false;
		} else if (!clima.equals(other.clima))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (qtdAparicoesFilmes != other.qtdAparicoesFilmes)
			return false;
		if (terreno == null) {
			if (other.terreno != null)
				return false;
		} else if (!terreno.equals(other.terreno))
			return false;
		return true;
	}

	public PlanetaDTO toDTO() {
		PlanetaDTO dto = new PlanetaDTO();
		dto.setId(this.id);
		dto.setNome(this.nome);
		dto.setClima(this.clima);
		dto.setTerreno(this.terreno);
		dto.setQtdAparicoesFilmes(this.qtdAparicoesFilmes);

		return dto;
	}

}
