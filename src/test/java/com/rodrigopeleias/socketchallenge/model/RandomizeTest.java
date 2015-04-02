package com.rodrigopeleias.socketchallenge.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RandomizeTest {
	
	private Randomize randomize;
	
	@Before
	public void setUp() {
		randomize = new Randomize(1);
	}

	@Test
	public void testEqualsRandomGeneration() {
		randomize.generate();
		assertEquals(randomize.getRandomNumber(), 1, 0.001);
	}
	
	@Test
	public void testChangeRandomNumber() {
		randomize.changeMaxNumber(100);
		randomize.generate();
		assertNotEquals(randomize.getRandomNumber(), 40, 0.001);
	}
}
