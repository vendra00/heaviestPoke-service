package com.pokemonpedia.heavypokeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {

	private Long id;
	private String name;
	private int height;
	private int weight;
	private int base_experience;
}
