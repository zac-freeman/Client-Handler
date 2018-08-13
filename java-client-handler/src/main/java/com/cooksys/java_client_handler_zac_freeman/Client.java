package com.cooksys.java_client_handler_zac_freeman;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client implements Runnable{
	private String ip;
	private int port;
	private String filename;

	public Client(String ip, int port, String filename) {
		this.ip = ip;
		this.port = port;
		this.filename = filename;
	}

	public void run() {
		try (
			Socket socket = new Socket(this.ip, this.port);
			InputStream in = new BufferedInputStream(new DataInputStream(socket.getInputStream()));
			OutputStream out = new BufferedOutputStream(new DataOutputStream(socket.getOutputStream()));
		) {
			byte[] bytes = new byte[8 * 1024];
			Thread.sleep(3000);
			out.write(generateMessage(this.filename).getBytes());
			out.flush();

			in.read(bytes);
			System.out.println(new String(bytes));

		} catch (InterruptedException e) {
			System.out.println("Client interrupted:");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("File not found:");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error somewhere in client:");
			e.printStackTrace();
		}
	}

	private String generateMessage(String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filename));
		int lineNum = Math.abs(new Random().nextInt()) % 23;

		for (int i = 0; i < lineNum; i++) {
			scanner.nextLine();
		}
		String message = scanner.nextLine();
		scanner.close();
		return message;
	}
}
