package com.rodrigopeleias.socketchallenge.main;

import java.io.IOException;

import com.rodrigopeleias.socketchallenge.main.receiver.ClientThread;

public class ClientChallenge {
	
	public static boolean hasWinner = false;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		new ClientChallenge().executa();		
	}
	
	public void executa() {
		for(int i = 0; i < 5; i++) {
			ClientThread clientThread = new ClientThread(i);
			new Thread(clientThread).start();
		}
	}

}
