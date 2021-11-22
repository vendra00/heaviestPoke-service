package com.pokemonpedia.heavypokeservice.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonpedia.heavypokeservice.dto.Pokemon;
import com.pokemonpedia.heavypokeservice.dto.PokemonResponseTemplate;
import com.pokemonpedia.heavypokeservice.exception.PokemonException;
import com.pokemonpedia.heavypokeservice.model.HeaviestPokemon;
import com.pokemonpedia.heavypokeservice.service.HeaviestPokemonService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/heavyPokemon")
@Slf4j
public class HeaviestPokemonController {

	@Autowired
	private HeaviestPokemonService heaviestPokemonService;

	@PostMapping("saveHeavyPokemon/")
	private HeaviestPokemon saveHeavyPokemon(@RequestBody HeaviestPokemon p) throws PokemonException {
		log.info("Save Heavy Pokemon Controller Call");
		try {
			return heaviestPokemonService.saveHeavyPokemon(p);
		} catch (Exception e) {
			throw new PokemonException("There was a problem when saving this Pokemon");
		}
	}

	@GetMapping("getHeaviestPokemon/{id}")
	public PokemonResponseTemplate getHeaviestPokemon(@PathVariable("id") Long id) throws PokemonException {
		log.info("Get Heavy Pokemon Controller Call");
		try {
			return heaviestPokemonService.getHeaviestPokemon(id);
		} catch (Exception e) {
			throw new PokemonException("No pokemon with ID : " + id);
		}
	}

	@GetMapping("/getAllHeaviestPokemons")
	public ResponseEntity<Pokemon[]> getAllHeaviestPokemons() throws PokemonException {
		log.info("Get All Heaviest Pokemons Controller Call");
		try {
			return heaviestPokemonService.getAllHeaviestPokemons();
		} catch (Exception e) {
			throw new PokemonException("There was a problem at loading the Pokemons list from DB");
		}	
	}

	@GetMapping("/getFiveHeaviestPokemons")
	public ResponseEntity<Collection<HeaviestPokemon>> getFiveHeaviestPokemons() throws PokemonException {
		log.info("Get The 5 Heaviest Pokemons Controller Call");
		try {
			return ResponseEntity.ok(heaviestPokemonService.getFiveHeaviestPokemons());
		} catch (Exception e) {
			throw new PokemonException("There was a problem at loading the 5 Pokemons from DB");
		}	
	}
}
