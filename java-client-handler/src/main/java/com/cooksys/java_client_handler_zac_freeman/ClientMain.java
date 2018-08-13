package com.cooksys.java_client_handler_zac_freeman;

public class ClientMain 
{
	public static void main( String[] args )
	{
		for (int i = 0; i < 10; i++) {
		Thread clientThread = new Thread(new Client("localhost", 8080, "messages.txt"));
		clientThread.start();
		}
	}
}