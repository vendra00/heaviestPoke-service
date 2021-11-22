package com.pokemonpedia.heavypokeservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pokemonpedia.heavypokeservice.dto.Pokemon;
import com.pokemonpedia.heavypokeservice.dto.PokemonResponseTemplate;
import com.pokemonpedia.heavypokeservice.model.HeaviestPokemon;

public interface HeaviestPokemonService {

	HeaviestPokemon saveHeavyPokemon(HeaviestPokemon p);

	PokemonResponseTemplate getHeaviestPokemon(Long id);
	
	ResponseEntity<Pokemon[]> getAllHeaviestPokemons();
	
	List<HeaviestPokemon> getFiveHeaviestPokemons();

}
