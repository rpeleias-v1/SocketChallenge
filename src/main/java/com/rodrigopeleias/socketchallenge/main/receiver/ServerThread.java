package com.rodrigopeleias.socketchallenge.main.receiver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import com.rodrigopeleias.socketchallenge.main.ServerChallenge;
import com.rodrigopeleias.socketchallenge.model.Guess;
import com.rodrigopeleias.socketchallenge.model.GuessedNumberType;
import com.rodrigopeleias.socketchallenge.model.Randomize;

public class ServerThread implements Runnable{
	
	private Socket client;
	private Randomize randomizer;
	private ServerChallenge serverChallenge;
	
	public ServerThread(Socket client, Randomize randomize, ServerChallenge serverChallenge) {
		this.client = client;
		this.randomizer = randomize;
		this.serverChallenge = serverChallenge;
	}

	@Override
	public void run() {
		try {
			ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());		
			ObjectOutputStream outpuStream = new ObjectOutputStream(client.getOutputStream());					
					
			boolean hasWinner = false;
			while (!hasWinner) {				
				hasWinner = process(inputStream, outpuStream);									
			}			
			System.out.println("Server closed!");
			System.exit(0);
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}

	public synchronized boolean process(ObjectInputStream inputStream, ObjectOutputStream outpuStream) throws IOException, ClassNotFoundException {
		Guess guess = (Guess)inputStream.readObject();
		int guessedNumber = guess.getGuessedNumber();
		System.out.println("Received number by player " + guess.getGuessId() +  " = " + guessedNumber);					
		if (guessedNumber != randomizer.getRandomNumber()) {
			if (guessedNumber < randomizer.getRandomNumber()) {
				outpuStream.writeObject(GuessedNumberType.BELOW);																							
			}
			if (guessedNumber > randomizer.getRandomNumber()) {
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
