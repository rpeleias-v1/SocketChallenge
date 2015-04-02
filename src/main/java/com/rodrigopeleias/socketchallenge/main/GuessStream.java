package com.rodrigopeleias.socketchallenge.main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GuessStream {
	
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	
	public GuessStream(ObjectOutputStream outputStream,	ObjectInputStream inputStream) {		
		this.outputStream = outputStream;
		this.inputStream = inputStream;
	}

	public ObjectOutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(ObjectOutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public ObjectInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ObjectInputStream inputStream) {
		this.inputStream = inputStream;
	}
}
