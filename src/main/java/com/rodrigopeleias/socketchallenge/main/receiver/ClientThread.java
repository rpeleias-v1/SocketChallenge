package com.rodrigopeleias.socketchallenge.main.receiver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.rodrigopeleias.socketchallenge.model.Guess;
import com.rodrigopeleias.socketchallenge.model.GuessedNumberType;

public class ClientThread implements Runnable {
	
	private int clientId;
	private Guess guess;
	
	public ClientThread(int id) {
		this.clientId = id;
		guess = new Guess(clientId, 100);		
	}
	
	@Override
	public void run() {		
		try {
			Socket client = new Socket("127.0.0.1", 12345);
			System.out.println("CLient connected succesfully on server!");;			
			ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
						
			while (true) {
				int guessedNumber = guess.tryIt();							
				outputStream.writeObject(guess);
				GuessedNumberType guessedNumberType = (GuessedNumberType)inputStream.readObject();
				if (guessedNumberType.equals(GuessedNumberType.GUESSED)) {
					System.out.println("Correct value by player " + guess.getGuessId() + " = " + guessedNumber);
					break;
				}				
				guess.change(guessedNumber, guessedNumberType);									
				outputStream.reset();
			}			
			client.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO: handle exception
		}
		
	}
}
