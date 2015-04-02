package com.rodrigopeleias.socketchallenge.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GuessTest {
	
	private Guess guess;
	private Randomize random;
	
	@Before
	public void setUp() {
		random = new Randomize(100);
		random.generate();
		guess = new Guess(1, 100);		
	}

	@Test
	public void testGuessNumber() {
		int guessNumber = guess.tryIt();
		while (guessNumber != random.getRandomNumber()) {
			if (guessNumber < random.getRandomNumber()) {
				guess.change(guessNumber, GuessedNumberType.BELOW);
			}
			if (guessNumber > random.getRandomNumber()) {
				guess.change(guessNumber, GuessedNumberType.ABOVE);
			}
			guessNumber = guess.tryIt();			
		}
		guess.markAsGuessed();
		assertTrue(guess.isGuessed());
		assertEquals(guessNumber, random.getRandomNumber(), 0.001);
	}

}
