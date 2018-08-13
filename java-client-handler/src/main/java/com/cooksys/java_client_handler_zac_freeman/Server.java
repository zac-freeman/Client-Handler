package com.cooksys.java_client_handler_zac_freeman;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	private int port;

	public Server(int port) {
		this.port = port;

		try (
			ServerSocket server = new ServerSocket(this.port);
		) {
			while (true) {
				ClientHandler client = new ClientHandler(server.accept());
				new Thread(client).start();
			}
		} catch (IOException e) {
			System.out.println("Error trying to initialize ServerSocket, or accept Socket:");
			e.printStackTrace();
		}
	}
}
