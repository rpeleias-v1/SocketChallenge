package com.rodrigopeleias.socketchallenge.model;

import java.io.Serializable;

public class Guess implements Serializable{
		
	private static final long serialVersionUID = -1624696097116507309L;
	
	private int guessId;
	private int minNumber;
	private int maxNumber;		
	private int guessedNumber;
	private boolean guessed;
	private boolean loose;

	public Guess(int guessId, int guessNumber) {
		this.guessId = guessId;
		this.minNumber = 1;
		this.maxNumber = guessNumber;
		this.guessed = false;
	}
	
	public int tryIt() {
		guessedNumber = (int)(minNumber + (Math.random() * (maxNumber - minNumber) + 1)); 
		return guessedNumber;
	}
	
	public void change(int number, GuessedNumberType guessedNumberType) {
		if (guessedNumberType.equals(GuessedNumberType.BELOW)) {
			this.changeMin(number);
		}
		if (guessedNumberType.equals(GuessedNumberType.ABOVE)) {
			this.changeMax(number);
		}
	}
	private void changeMin(int number) {
		this.minNumber = number;
	}
	
	private void changeMax(int number) {
		this.maxNumber = number;
	}

	public int getMinNumber() {
		return minNumber;
	}

	public int getMaxNumber() {
		return maxNumber;
	}
	
	public int getGuessedNumber() {
		return guessedNumber;
	}

	public void markAsGuessed() {
		this.guessed = true;
	}
	
	public boolean isGuessed() {
		return guessed;
	}

	public int getGuessId() {
		return guessId;
	}

	public boolean loosed() {
		return loose;
	}

	public void markAsLoosed() {
		this.loose = true;
	}
	
	
}
