package com.rodrigopeleias.socketchallenge.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
	
	public  boolean process(ObjectInputStream inputStream, ObjectOutputStream outpuStream) throws IOException, ClassNotFoundException {
		Guess guess = (Guess)inputStream.readObject();
		int guessedNumber = guess.getGuessedNumber();
		System.out.println("Received number  = " + guessedNumber);					
		if (guessedNumber != this.getRandomNumber()) {
			if (guessedNumber < this.getRandomNumber()) {
				outpuStream.writeObject(GuessedNumberType.BELOW);																							
			}
			if (guessedNumber > this.getRandomNumber()) {
				outpuStream.writeObject(GuessedNumberType.ABOVE);																	
			}				
			return false;
		}	
		else {						
			System.out.println("Cliente acertou!!! Valor = " + guessedNumber);	
			outpuStream.writeObject(GuessedNumberType.GUESSED);					
			return true;
		}
	}
}
