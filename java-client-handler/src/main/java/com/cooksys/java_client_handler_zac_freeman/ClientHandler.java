package com.cooksys.java_client_handler_zac_freeman;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try (
			InputStream in = new BufferedInputStream(new DataInputStream(this.socket.getInputStream()));
			OutputStream out = new BufferedOutputStream(new DataOutputStream(this.socket.getOutputStream()));
		) {
			byte[] bytes = new byte[8 * 1024];
			in.read(bytes);
			sendMessage(out, bytes);

			} catch (IOException e) {
				System.out.println("Error trying to interact with InputStream, or OutputStream:");
				e.printStackTrace();
			}
	}

	private void sendMessage(OutputStream out, byte[] bytes) throws IOException {
		String message = new String(bytes);
		message = "Message Accepted: " + message;
		out.write(message.getBytes());
		out.flush();
	}
}