package com.pokemonpedia.heavypokeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemonpedia.heavypokeservice.model.HeaviestPokemon;

@Repository
public interface HeaviestPokemonRepository extends JpaRepository<HeaviestPokemon, Long>{

	HeaviestPokemon findByHeaviestPokemonId(Long id);

}
