package br.com.starwarschallenge.model;

import java.io.Serializable;
import java.util.List;

public class PlanetaResponseDTO extends ResponseResultDTO implements Serializable {

	private List<PlanetaResponseResultDTO> results;

	public List<PlanetaResponseResultDTO> getResults() {
		return results;
	}

	public void setResults(List<PlanetaResponseResultDTO> results) {
		this.results = results;
	}

}
