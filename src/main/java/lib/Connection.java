package lib;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {

	private Socket socket;


	public Connection(Socket socket) {
		this.socket = socket;
	}

	public Connection(ServerSocket serverSocket) throws IOException {
		this.socket = serverSocket.accept();
	}

	public Connection(String host, Integer port) throws IOException {
		this(new Socket(host,port));
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	///////////// Higher level logic

	public void send(Object object) throws IOException {

		ObjectOutputStream objectOutStream = new ObjectOutputStream(socket.getOutputStream());

		objectOutStream.writeObject(object);
		objectOutStream.flush();
	}

	public Object fetch() throws IOException, ClassNotFoundException {

		ObjectInputStream objectInStream = new ObjectInputStream(socket.getInputStream());

		return objectInStream.readObject();
	}
}
