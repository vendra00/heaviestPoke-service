package com.pokemonpedia.heavypokeservice.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pokemonpedia.heavypokeservice.dto.Pokemon;
import com.pokemonpedia.heavypokeservice.dto.PokemonResponseTemplate;
import com.pokemonpedia.heavypokeservice.model.HeaviestPokemon;
import com.pokemonpedia.heavypokeservice.repository.HeaviestPokemonRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HeaviestPokemonServiceImpl implements HeaviestPokemonService{

	@Autowired
	private HeaviestPokemonRepository heaviestPokemonRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String GET_ALL_POKEMONS_DB = "http://localhost:8090/pokemon/getAllPokemonsDb";

	@Override
	public HeaviestPokemon saveHeavyPokemon(HeaviestPokemon p) {
		log.info("Save Heaviest Pokemon Service Call");
		return heaviestPokemonRepository.save(p);
	}

	@Override
	public PokemonResponseTemplate getHeaviestPokemon(Long heaviestPokemonId) {
		log.info("Get Heavy Pokemon Service Call");
		PokemonResponseTemplate response = new PokemonResponseTemplate();
		HeaviestPokemon heaviestPokemon = heaviestPokemonRepository.findByHeaviestPokemonId(heaviestPokemonId);
		response.setHeaviestPokemon(heaviestPokemon);
		return response;
	}
	
	@Override
	public ResponseEntity<Pokemon[]> getAllHeaviestPokemons() {
		log.info("Get All Heaviest Pokemons Service Call");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<Pokemon[]> response = restTemplate.exchange(GET_ALL_POKEMONS_DB, HttpMethod.GET, entity, Pokemon[].class);
		
		findHeaviestFivePokemons(response);
		
		return response;
		
	}
	
	@Override
	public List<HeaviestPokemon> getFiveHeaviestPokemons() {
		if(heaviestPokemonRepository.findAll().isEmpty()) {
			getAllHeaviestPokemons();
		}
		return  heaviestPokemonRepository.findAll().stream().collect(Collectors.toList());
	}
	
	private void findHeaviestFivePokemons(ResponseEntity<Pokemon[]> response) {
		log.info("Find The 5 Heaviest Pokemons Service Call");
		List<Pokemon> list = Arrays.asList(response.getBody());
			
		sortPokemonsByWheight(list);	
		getTheFiveHeaviestPokemonsAndSave(list);				 
		
	}

	private void getTheFiveHeaviestPokemonsAndSave(List<Pokemon> list) {
		log.info("Find The 5 Heaviest Pokemons And Save Service Call");
		if(heaviestPokemonRepository.findAll().isEmpty()) {
			saveHeavyPokemon(list);
		}
	}

	private void saveHeavyPokemon(List<Pokemon> list) {
		log.info("Save A Heavy Pokemon And Save On DB Service Call");
		for(int i = 0; i<=4; i++) {
			HeaviestPokemon p = new HeaviestPokemon();
			p.setName(list.get(i).getName());
			p.setWeight(list.get(i).getWeight());
			p.setHeight(list.get(i).getHeight());
			p.setBase_experience(list.get(i).getBase_experience());
			saveHeavyPokemon(p);
		}
	}

	private void sortPokemonsByWheight(List<Pokemon> list) {	
		log.info("Sort All Pokemons By Wheight Service Call");
		Collections.sort(list, new Comparator<Pokemon>() {
			@Override
			public int compare(Pokemon o1, Pokemon o2) {
				return Integer.valueOf(o2.getWeight()).compareTo(o1.getWeight());
			}
		});		
	}

	
}
