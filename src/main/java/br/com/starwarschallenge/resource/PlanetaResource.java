package br.com.starwarschallenge.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.starwarschallenge.model.PlanetaDTO;
import br.com.starwarschallenge.service.PlanetaService;

@RestController
@RequestMapping("/planeta")
public class PlanetaResource {

	@Autowired
	private PlanetaService planetaService;

	@GetMapping
	public List<PlanetaDTO> obterTodosOsPlanetas() throws Exception {
		return planetaService.listarPlanetas();
	}

	@PostMapping
	public ResponseEntity adicionarPlaneta(PlanetaDTO planetaDTO) throws Exception {
		planetaService.adicionarPlaneta(planetaDTO);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@GetMapping(path = "/{nome}")
	public PlanetaDTO buscarPorNome(@PathVariable("nome") String nome) throws Exception {
		return planetaService.buscarPorNome(nome);
	}

	@GetMapping(path = "/{id}")
	public PlanetaDTO buscarPorID(@PathVariable("id") String id) throws Exception {
		return planetaService.buscarPorId(id);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity removerPlaneta(@PathVariable("id") String id) throws Exception {
		this.planetaService.removerplaneta(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
