package lib.connections;

import lib.Action;
import lib.enums.OperationEnum;
import lib.targets.Message;
import lib.targets.User;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnection extends AbstractConnection {

	private Socket socket;
	private User user;


	public ClientConnection(String host, Integer port)
			throws IOException {
		this(new Socket(host,port));
	}

	public ClientConnection(Socket socket) {
		this(socket, new User("Anonymous"));
	}

	public ClientConnection(String host, Integer port, User user)
			throws IOException {
		this(new Socket(host,port), user);
	}

	public ClientConnection(Socket socket, User user) {
		this.socket = socket;
		this.user = user;
		send(new Action(OperationEnum.SIGN_IN, user));
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	///////////// Higher level logic

	public void send(Action action) {

		super.sendAction(action, socket);
	}

	public Action fetch() throws IOException, ClassNotFoundException {

		return super.fetchAction(socket);
	}

	public Thread getInboxObserver() {

		return new Thread(() -> {
			try {
				DataInputStream dataInputStream =
						new DataInputStream(socket.getInputStream());

				while (true) {

					if (dataInputStream.available() > 0) {

						System.err.println(dataInputStream.readUTF());
					}
				}
			} catch (IOException e) {
			e.printStackTrace();
			}
		});
	}

	public void start() {

		getInboxObserver().start();

		while (true) {
			System.out.println('[' + user.getName() + "]: ");
			Message message = new Message(user, new Scanner(System.in).nextLine());

			send(new Action(OperationEnum.MESSAGE, message));
		}
	}
}
