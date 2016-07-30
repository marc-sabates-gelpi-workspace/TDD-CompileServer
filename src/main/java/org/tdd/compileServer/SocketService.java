package org.tdd.compileServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService {

	private ServerSocket serverSocket;
	private int connections = 0;
	private boolean running = false;

	public void serve(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		Thread t = new Thread(new SocketServerRunner());
		t.start();
	}

	public void close() throws IOException {
		running = false;
		serverSocket.close();
	}

	public int connections() {
		return connections;
	}
	
	private class SocketServerRunner implements Runnable{
		public void run() {
			running = true;
			while(running){
				try (Socket s = serverSocket.accept()) {
					connections++;
				} catch (IOException e) {
					System.out.println("IOException: "+ e.getMessage());
				}
			}
		}
	}
}
