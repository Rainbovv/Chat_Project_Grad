package lib.connections;

import lib.Action;
import lib.targets.Message;
import lib.targets.User;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerConnection extends AbstractConnection {

	private final ServerSocket serverSocket;
	private Map<Socket, User> clients;


	public ServerConnection(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
		this.clients = new ConcurrentHashMap<>();
	}

	public ServerConnection(Integer port) throws IOException {
		this(new ServerSocket(port));
	}

	///////////// Higher level logic

	public void start() {
		try {
			while (true) {

				Socket clientSocket = serverSocket.accept();

				getClientObserver(clientSocket).start();

				System.out.println("New user has connected!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Thread getClientObserver(Socket socket) {

		return new Thread(() -> {
			while (true) {
				try {
					Action action = fetchAction(socket);

					switch (action.getType()) {

						case MESSAGE:
							processMessage(action, socket);
							break;

						case SIGN_IN:
							processSignIn(action, socket);
							break;
					}
				} catch (SocketException e) {
					broadcasting(clients.get(socket).getName() +
							" has left the room!", socket);
					clients.remove(socket);
					System.out.println("Total users >> " + clients.size());
					return;

				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void broadcasting(String message, Socket socketFrom) {

		for (Socket socketTo: clients.keySet())
			if (!socketTo.equals(socketFrom))
				sendUTF(message, socketTo);
	}

	private void processMessage(Action action, Socket socket) {

		Message message = (Message) action.getTarget();
		broadcasting('[' + message.getUser().getName() + "]: " +
				message.getBody(), socket);

	}

	private void processSignIn(Action action, Socket socket) {

		User user = (User) action.getTarget();

		clients.put(socket, user);

		System.out.println("Total users >> " + clients.size());
		broadcasting(user.getName() + " has entered the room!", socket);
	}
}
