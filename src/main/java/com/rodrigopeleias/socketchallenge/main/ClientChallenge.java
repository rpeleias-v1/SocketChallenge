package com.rodrigopeleias.socketchallenge.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import com.rodrigopeleias.socketchallenge.main.receiver.ClientThread;
import com.rodrigopeleias.socketchallenge.model.Guess;
import com.rodrigopeleias.socketchallenge.model.GuessedNumberType;

public class ClientChallenge {
	
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
