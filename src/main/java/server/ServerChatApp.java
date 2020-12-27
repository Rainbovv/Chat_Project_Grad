package server;

import lib.connections.ServerConnection;
import java.io.IOException;

public class ServerChatApp {

	public static void main(String[] args) throws IOException {

		new ServerConnection(8888)
			.start();

	}
}
