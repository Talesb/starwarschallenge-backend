package br.com.starwarschallenge.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.starwarschallenge.model.Planeta;
import br.com.starwarschallenge.model.PlanetaDTO;
import br.com.starwarschallenge.model.PlanetaResponseDTO;
import br.com.starwarschallenge.model.PlanetaResponseResultDTO;
import br.com.starwarschallenge.repository.PlanetaRepository;
import br.com.starwarschallenge.restclient.StarWarsRestClient;

@RunWith(MockitoJUnitRunner.class)
public class PlanetaServiceTest {

	@InjectMocks
	PlanetaService planetaService;

	@Mock
	StarWarsRestClient starWarsRestClientMock;

	@Mock
	PlanetaRepository planetaRepositoryMock;

	@Captor
	private ArgumentCaptor<Planeta> planetaCaptor;

	@Test
	public void adicionarPlanetaTest() {

		Mockito.when(this.starWarsRestClientMock.findByName(PlanetaService.URL, "tatooine", PlanetaResponseDTO.class))
				.thenReturn(this.obterResponseResultDTOforTest());

		Mockito.verify(this.planetaRepositoryMock, Mockito.times(0)).save(planetaCaptor.capture());

		assertEquals("árido", planetaCaptor.getValue().getClima());
		assertEquals("deserto", planetaCaptor.getValue().getTerreno());
		assertEquals("tatooine", planetaCaptor.getValue().getNome());
		assertEquals(6, planetaCaptor.getValue().getQtdAparicoesFilmes());

	}

	private PlanetaDTO obterPlanetaDTOforTest() {
		PlanetaDTO planeta = new PlanetaDTO();
		planeta.setNome("tatooine");
		planeta.setClima("árido");
		planeta.setTerreno("deserto");
		return planeta;
	}

	private Optional<PlanetaResponseDTO> obterResponseResultDTOforTest() {
		PlanetaResponseDTO responseResultDTO = new PlanetaResponseDTO();
		responseResultDTO.setCount(1);
		responseResultDTO.setNext("");
		responseResultDTO.setPrevious("");
		responseResultDTO.setResults(obterPlanetaResponseDTOforTest());

		return Optional.ofNullable(responseResultDTO);
	}

	private List<PlanetaResponseResultDTO> obterPlanetaResponseDTOforTest() {
		List<PlanetaResponseResultDTO> lista = new ArrayList<PlanetaResponseResultDTO>();
		PlanetaResponseResultDTO planetaResponseDTO = new PlanetaResponseResultDTO();

		planetaResponseDTO.setName("Tatooine");
		planetaResponseDTO.setClimate("arid");
		planetaResponseDTO.setGravity("1 standard");
		planetaResponseDTO.setTerrain("desert");

		List<String> films = new ArrayList<String>();
		films.add("http://swapi.dev/api/films/1/");
		films.add("http://swapi.dev/api/films/2/");
		films.add("http://swapi.dev/api/films/3/");
		films.add("http://swapi.dev/api/films/4/");
		films.add("http://swapi.dev/api/films/5/");
		films.add("http://swapi.dev/api/films/6/");

		planetaResponseDTO.setFilms(films);

		lista.add(planetaResponseDTO);

		return lista;

	}
}
