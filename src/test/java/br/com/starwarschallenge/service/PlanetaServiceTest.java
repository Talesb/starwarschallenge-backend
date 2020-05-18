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
import org.powermock.api.mockito.PowerMockito;

import br.com.starwarschallenge.exception.PlanetaNotFoundException;
import br.com.starwarschallenge.model.Planeta;
import br.com.starwarschallenge.model.PlanetaDTO;
import br.com.starwarschallenge.model.PlanetaResponseDTO;
import br.com.starwarschallenge.model.PlanetaResponseResultDTO;
import br.com.starwarschallenge.repository.PlanetaRepository;
import br.com.starwarschallenge.restclient.StarWarsRestClient;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PlanetaServiceTest {

	@InjectMocks
	PlanetaService planetaService;

	@Mock
	StarWarsRestClient starWarsRestClientMock;

	@Mock
	PlanetaRepository planetaRepositoryMock;

	@Captor
	private ArgumentCaptor<Planeta> planetaCaptor;

	@Captor
	private ArgumentCaptor<String> idCaptor;

	@Test
	public void adicionarPlanetaTest() throws Exception {

		Mockito.when(this.starWarsRestClientMock.findByName(PlanetaService.URL, "tatooine", PlanetaResponseDTO.class))
				.thenReturn(this.obterResponseResultDTOforTest(true));

		Mockito.when(this.planetaRepositoryMock.save(this.planetaCaptor.capture())).thenReturn(new Planeta());
		this.planetaService.adicionarPlaneta(obterPlanetaDTOforTest());

		assertEquals("árido", planetaCaptor.getValue().getClima());
		assertEquals("deserto", planetaCaptor.getValue().getTerreno());
		assertEquals("tatooine", planetaCaptor.getValue().getNome());
		assertEquals(6, planetaCaptor.getValue().getQtdAparicoesFilmes());

	}

	@Test
	public void adicionarPlanetaGerandoExcessao() {

		Mockito.when(this.starWarsRestClientMock.findByName(PlanetaService.URL, "tatooine", PlanetaResponseDTO.class))
				.thenReturn(this.obterResponseResultDTOforTest(false));

		Mockito.when(this.planetaRepositoryMock.save(this.planetaCaptor.capture())).thenReturn(new Planeta());
		try {
			this.planetaService.adicionarPlaneta(obterPlanetaDTOforTest());
		} catch (Exception e) {
			assertEquals("Erro durante a requisição, não foi localizado planeta com o nome informado", e.getMessage());
		}

	}

	@Test
	public void listarPlanetasTest() {

		Mockito.when(this.planetaRepositoryMock.findAll()).thenReturn(obterPlanetasListForTest());

		List<PlanetaDTO> resultado = this.planetaService.listarPlanetas();
		assertEquals(3, resultado.size());
		assertEquals("testnome1", resultado.get(0).getNome());

	}

	@Test
	public void buscarPorNomeTest() throws PlanetaNotFoundException {

		Mockito.when(this.planetaRepositoryMock.findByName("nometest"))
				.thenReturn(new Planeta("nometest", "climatest", "terrenotest", 2));

		PlanetaDTO resultado = this.planetaService.buscarPorNome("nometest");

		assertEquals("nometest", resultado.getNome());
		assertEquals("climatest", resultado.getClima());
		assertEquals("terrenotest", resultado.getTerreno());
		assertEquals(2, resultado.getQtdAparicoesFilmes());

	}

	@Test
	public void buscarPorNomeGerandoExcessao() {

		Mockito.when(this.planetaRepositoryMock.findByName("nometest")).thenReturn(null);
		try {
			this.planetaService.buscarPorNome("nometest");
		} catch (Exception e) {
			assertEquals("Planeta Não encontrado", e.getMessage());
		}

	}

	@Test
	public void buscarPorIdTest() throws PlanetaNotFoundException {
		Mockito.when(this.planetaRepositoryMock.findById("ashasdddsas324sfsdf3444"))
				.thenReturn(Optional.of(new Planeta("nometest", "climatest", "terrenotest", 2)));

		PlanetaDTO resultado = this.planetaService.buscarPorId("ashasdddsas324sfsdf3444");

		assertEquals("nometest", resultado.getNome());
		assertEquals("climatest", resultado.getClima());
		assertEquals("terrenotest", resultado.getTerreno());
		assertEquals(2, resultado.getQtdAparicoesFilmes());
	}

	@Test
	public void buscarPorIdGerandoExcessao() {
		Mockito.when(this.planetaRepositoryMock.findById("ashasdddsas324sfsdf3444")).thenReturn(null);
		try {
			this.planetaService.buscarPorNome("nometest");
		} catch (Exception e) {
			assertEquals("Planeta Não encontrado", e.getMessage());
		}

	}

	@Test
	public void removerPlanetaTest() {

		PowerMockito.doNothing().when(this.planetaRepositoryMock).deleteById(idCaptor.capture());
		this.planetaService.removerplaneta("efsdfsdwewjghjg");
		assertEquals("efsdfsdwewjghjg", idCaptor.getValue());

	}

	private PlanetaDTO obterPlanetaDTOforTest() {
		PlanetaDTO planeta = new PlanetaDTO();
		planeta.setNome("tatooine");
		planeta.setClima("árido");
		planeta.setTerreno("deserto");
		return planeta;
	}

	private List<Planeta> obterPlanetasListForTest() {
		List<Planeta> list = new ArrayList<Planeta>();
		list.add(new Planeta("testnome1", "testclima2", "testterreno3", 0));
		list.add(new Planeta("testnome4", "testclima5", "testterreno6", 1));
		list.add(new Planeta("testnome7", "testclima8", "testterreno9", 2));
		return list;

	}

	private Optional<PlanetaResponseDTO> obterResponseResultDTOforTest(boolean possuiResults) {
		PlanetaResponseDTO responseResultDTO = new PlanetaResponseDTO();
		responseResultDTO.setCount(1);
		responseResultDTO.setNext("");
		responseResultDTO.setPrevious("");
		if (possuiResults) {
			responseResultDTO.setResults(obterPlanetaResponseDTOforTest());
		}

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
