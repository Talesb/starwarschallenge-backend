package br.com.starwarschallenge.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.starwarschallenge.model.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String>{
	
	
	List<Planeta> findAll();
	
	@Query("{ 'nome' : {$regex : ?0, $options: 'i'} }")
	Planeta findByName(String nome);
	
}
