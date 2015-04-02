package com.rodrigopeleias.socketchallenge.main.receiver;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import com.rodrigopeleias.socketchallenge.model.Guess;
import com.rodrigopeleias.socketchallenge.model.GuessedNumberType;

public class ClientThread implements Runnable {
	
	private int clientId;
	
	public ClientThread(int id) {
		this.clientId = id;
	}
	
	@Override
	public void run() {		
		try {
			Socket client = new Socket("127.0.0.1", 12345);
			System.out.println("CLient connected succesfully on server!");;
			
			ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
			
			PrintStream out = new PrintStream(client.getOutputStream());
			Guess guess = new Guess(clientId, 100);
					
			while (!guess.isGuessed() && !guess.loosed()) {
				guess.tryIt();					
				outputStream.writeObject(guess);
				guess = (Guess)inputStream.readObject();
				System.out.println("Guessed pelo jogagor " + clientId + " = " + guess.getGuessedNumber());
				outputStream.reset();			
			}
			System.out.println("O valor foi descoberto pelo jogador " + clientId + "!");
			out.close();		
			client.close();
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO: handle exception
		}
		
	}
}
