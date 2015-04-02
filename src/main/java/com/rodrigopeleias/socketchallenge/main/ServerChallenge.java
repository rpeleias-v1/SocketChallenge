package com.rodrigopeleias.socketchallenge.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.rodrigopeleias.socketchallenge.main.receiver.ServerThread;
import com.rodrigopeleias.socketchallenge.model.Randomize;

public class ServerChallenge {
	
	private ServerSocket server;
	private boolean hasWinner = false;
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		new ServerChallenge().executa();
	}

	public void executa() throws IOException, InterruptedException, ClassNotFoundException {
		server = new ServerSocket(12345);
		System.out.println("Open port: 12345. Waiting for connections..");
		
		Randomize randomizer = new Randomize(100);
		randomizer.generate();
		System.out.println(randomizer.getRandomNumber());
		System.out.println("Random number Generated!!");
		
		while (true) {
			Socket client = server.accept();						
			ServerThread serverThread = new ServerThread(client, randomizer, this);
			new Thread(serverThread).start();						
		}	
	}
	
	public synchronized void setWinned() {
		this.hasWinner = true;
	}
	
	public boolean hasWinner() {
		return hasWinner;
	}

}
