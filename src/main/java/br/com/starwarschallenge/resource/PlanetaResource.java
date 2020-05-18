package br.com.starwarschallenge.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.starwarschallenge.model.PlanetaDTO;
import br.com.starwarschallenge.service.PlanetaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/planeta")
@Api(value = "Planetas")
public class PlanetaResource {

	@Autowired
	private PlanetaService planetaService;

	@ApiOperation(value = "Obter todos os planetas")
	@GetMapping
	public List<PlanetaDTO> obterTodosOsPlanetas() throws Exception {
		return planetaService.listarPlanetas();
	}

	@ApiOperation(value = "Adicionar planeta")
	@PostMapping
	public ResponseEntity adicionarPlaneta(@RequestBody PlanetaDTO planetaDTO) throws Exception {
		planetaService.adicionarPlaneta(planetaDTO);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Buscar planeta por nome")
	@GetMapping(path = "nome/{nome}")
	public PlanetaDTO buscarPorNome(@PathVariable("nome") String nome) throws Exception {
		return planetaService.buscarPorNome(nome);
	}

	@ApiOperation(value = "Buscar planeta por id")
	@GetMapping(path = "id/{id}")
	public PlanetaDTO buscarPorID(@PathVariable("id") String id) throws Exception {
		return planetaService.buscarPorId(id);
	}

	@ApiOperation(value = "Remover planeta")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity removerPlaneta(@PathVariable("id") String id) throws Exception {
		this.planetaService.removerplaneta(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
