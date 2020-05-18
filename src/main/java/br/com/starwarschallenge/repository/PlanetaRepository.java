package br.com.starwarschallenge.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.starwarschallenge.model.Planeta;

@Repository
public interface PlanetaRepository extends MongoRepository<Planeta, String> {

	List<Planeta> findAll();

	@Query("{ 'nome' : {$regex : ?0, $options: 'i'} }")
	Planeta findByName(String nome);

}
