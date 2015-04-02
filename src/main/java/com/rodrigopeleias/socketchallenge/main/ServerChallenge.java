package com.rodrigopeleias.socketchallenge.main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.jws.Oneway;

import com.rodrigopeleias.socketchallenge.main.receiver.TrataClienteThread;
import com.rodrigopeleias.socketchallenge.model.Guess;
import com.rodrigopeleias.socketchallenge.model.GuessedNumberType;
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
		System.out.println("Random number Generated!!");
		
		while (true) {
			Socket client = server.accept();						
			TrataClienteThread trataCliente = new TrataClienteThread(client, randomizer, this);
			new Thread(trataCliente).start();						
		}
		
	}
	
	public void setWinned() {
		this.hasWinner = true;
	}
	
	public boolean hasWinner() {
		return hasWinner;
	}

}
