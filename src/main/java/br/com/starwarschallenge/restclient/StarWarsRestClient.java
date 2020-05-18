package br.com.starwarschallenge.restclient;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarWarsRestClient {

	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(String url, Class<T> classType) {
		return (List<T>) this.restTemplate.getForObject(url, List.class);
	}

	public <T> Optional<T> findById(String url, String id, Class<T> classType) {
		return Optional.ofNullable(this.restTemplate.getForObject(url.concat("/").concat(id), classType));

	}

	public <T> Optional<T> findByName(String url, String name, Class<T> classType) {
		return Optional.ofNullable(this.restTemplate.getForObject(url.concat("/?search=").concat(name), classType));

	}

}
