package server;

import lib.connections.ServerConnection;
import java.io.IOException;

public class ServerChatApp {

	public static void main(String[] args) throws IOException {

		System.out.println("Server starting...");

		new ServerConnection(8888)
			.start();

	}
}
