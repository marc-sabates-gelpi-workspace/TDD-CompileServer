package org.tdd.compileServer;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.Socket;

import org.junit.Test;

public class SocketServiceTest {

	private static final int CONNECTIONS = 10;
	private static final int PORT = 9999;

//	@Test
//	public void shouldConnectOnce() throws IOException {
//		SocketService ss = new SocketService();
//		ss.serve(PORT);
//		connect(PORT);
//		ss.close();
//		assertEquals(1, ss.connections());
//	}
	
	@Test
	public void shouldConnectManyTimes() throws Exception {
		SocketService ss = new SocketService();
		ss.serve(PORT);
		for(int i = 0; i < CONNECTIONS; i++){
			connect(PORT);
		}
		ss.close();
		assertEquals(CONNECTIONS, ss.connections());
	}

	private void connect(int port) {
		try (Socket s = new Socket("localhost", port);) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		} catch (IOException e) {
			fail("could not connect");
		}
	}

}
