package br.com.starwarschallenge.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starwarschallenge.exception.BusinessException;
import br.com.starwarschallenge.exception.PlanetaNotFoundException;
import br.com.starwarschallenge.model.Planeta;
import br.com.starwarschallenge.model.PlanetaDTO;
import br.com.starwarschallenge.model.PlanetaResponseDTO;
import br.com.starwarschallenge.model.ResponseResultDTO;
import br.com.starwarschallenge.repository.PlanetaRepository;
import br.com.starwarschallenge.restclient.StarWarsRestClient;

@Service
public class PlanetaService {

	public final static String URL = "https://swapi.dev/api/planets/";

	@Autowired
	StarWarsRestClient starWarsRestClient;

	@Autowired
	PlanetaRepository planetaRepository;

	public void adicionarPlaneta(PlanetaDTO planetaDTO) throws Exception {

		this.verificarDTO(planetaDTO);

		Optional<ResponseResultDTO> responseOptional = starWarsRestClient.findByName(URL, planetaDTO.getNome(),
				ResponseResultDTO.class);

		ResponseResultDTO responseEncontrado = responseOptional
				.orElseThrow(() -> new PlanetaNotFoundException("Erro durante a requisição, nada foi encontrado"));

		if (!responseEncontrado.getResults().isEmpty()) {

			@SuppressWarnings("unchecked")
			List<PlanetaResponseDTO> planetaEncontrados = (List<PlanetaResponseDTO>) (List<?>) responseEncontrado
					.getResults();

			List<String> films = planetaEncontrados.get(0).getFilms();

			Planeta planetaAserSalvo = new Planeta(planetaDTO.getNome(), planetaDTO.getClima(), planetaDTO.getTerreno(),
					films.size());

			planetaRepository.save(planetaAserSalvo);
		} else {
			throw new PlanetaNotFoundException(
					"Erro durante a requisição, não foi localizado planeta com o nome informado");
		}
	}

	public List<PlanetaDTO> listarPlanetas() {
		return this.planetaRepository.findAll().stream().map(planeta -> planeta.toDTO()).collect(Collectors.toList());
	}

	public PlanetaDTO buscarPorNome(String nome) throws PlanetaNotFoundException {
		if (this.planetaRepository.findByName(nome) != null) {
			return this.planetaRepository.findByName(nome).toDTO();
		} else {
			throw new PlanetaNotFoundException();
		}

	}

	public PlanetaDTO buscarPorId(String id) throws PlanetaNotFoundException {
		return this.planetaRepository.findById(id).orElseThrow(() -> new PlanetaNotFoundException()).toDTO();
	}

	public void removerplaneta(String id) {
		this.planetaRepository.deleteById(id);
	}

	private void verificarDTO(PlanetaDTO planetaDTO) throws BusinessException {
		if (planetaDTO.getNome() == null) {
			throw new BusinessException("É necessário informar um nome para o planeta");
		}
		if (planetaDTO.getClima() == null) {
			throw new BusinessException("É necessário informar um clima para o planeta");
		}
		if (planetaDTO.getTerreno() == null) {
			throw new BusinessException("É necessário informar um terreno para o planeta");
		}
	}

}
