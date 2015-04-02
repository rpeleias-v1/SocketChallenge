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

public class TrataClienteThread implements Runnable{
	
	private Socket client;
	private Randomize randomizer;
	private ServerChallenge serverChallenge;
	
	public TrataClienteThread(Socket client, Randomize randomize, ServerChallenge serverChallenge) {
		this.client = client;
		this.randomizer = randomize;
		this.serverChallenge = serverChallenge;
	}

	@Override
	public void run() {
		try {
			ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());		
			ObjectOutputStream outpuStream = new ObjectOutputStream(client.getOutputStream());					
			
			Object block = new Object();	
						
			while (true) {
				//synchronized (block) {
					Guess guess = (Guess)inputStream.readObject();
					int guessedNumber = guess.getGuessedNumber();
					System.out.println("Received number from client " + guess.getGuessId() + " = " + guessedNumber);
					if (!serverChallenge.hasWinner()) {
						if (guessedNumber != randomizer.getRandomNumber()) {
							if (guessedNumber < randomizer.getRandomNumber()) {
								guess.change(guessedNumber, GuessedNumberType.BELOW);																
							}
							if (guessedNumber > randomizer.getRandomNumber()) {
								guess.change(guessedNumber, GuessedNumberType.ABOVE);																	
							}							
						}	
						else {						
							System.out.println("Cliente " + guess.getGuessId() + " acertou!!!");	
							guess.markAsGuessed();
							serverChallenge.setWinned();						
						}	
					}
					else {
						guess.markAsLoosed();
					}					
					outpuStream.writeObject(guess);		
				}
				//client.close();			
			//}	
			
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}

}
