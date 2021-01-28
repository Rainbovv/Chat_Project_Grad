package client;

import lib.connections.ClientConnection;
import lib.targets.User;

import java.io.IOException;
import java.util.Scanner;

public class ClientChatApp {

	public static void main(String[] args) throws IOException {

		System.out.println("Please enter you login:");

		new ClientConnection("localhost", 8888,
							new User(new Scanner(System.in).nextLine()))
			.start();
	}
}
