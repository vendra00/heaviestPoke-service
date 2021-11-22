package com.pokemonpedia.heavypokeservice.dto;

import com.pokemonpedia.heavypokeservice.model.HeaviestPokemon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonResponseTemplate {

	private HeaviestPokemon heaviestPokemon;
}
