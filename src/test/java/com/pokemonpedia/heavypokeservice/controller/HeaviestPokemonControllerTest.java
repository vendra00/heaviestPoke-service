package com.pokemonpedia.heavypokeservice.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pokemonpedia.heavypokeservice.AbstractTest;
import com.pokemonpedia.heavypokeservice.model.HeaviestPokemon;

public class HeaviestPokemonControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void createPokemon() throws Exception {
		String uri = "/heavyPokemon/saveHeavyPokemon/";
		HeaviestPokemon c = new HeaviestPokemon();
		c.setHeaviestPokemonId((long) 1);
		c.setName("Gabriel");
		c.setHeight(10);;
		c.setWeight(50);;
		c.setBase_experience(120);;
		String inputJson = super.mapToJson(c);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}
