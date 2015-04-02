package com.rodrigopeleias.socketchallenge.model;

public class Randomize {
	
	private int maxNumber;
	private int randomNumber;

	public Randomize(int maxNumber) {	
		this.maxNumber = maxNumber;
	}
	
	public void generate() {
		this.randomNumber =  (int)(1 + (Math.random() * (maxNumber - 1)));
	}
	
	public void changeMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
	
	public int getRandomNumber() {
		return randomNumber;
	}

	public int getMaxNumber() {
		return maxNumber;
	}
}
