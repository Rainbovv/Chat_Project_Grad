package lib;

import java.io.*;
import java.net.Socket;

public class Connection {

	private Socket socket;


	public Connection(Socket socket) {
		this.socket = socket;
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

	public void send(Action action) throws IOException {

		ObjectOutputStream objectOutStream = new ObjectOutputStream(socket.getOutputStream());

		objectOutStream.writeObject(action);
		objectOutStream.flush();
	}

	public Action fetch() throws IOException, ClassNotFoundException {

		ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

		return (Action)objectInputStream.readObject();
	}

//	private void sendJson(JSONObject json) {
//
//		PrintWriter printWriter = new PrintWriter(new DataOutputStream(outputStream));
//
//		printWriter.println(json.toString());
//		printWriter.flush();
//	}

//	public JSONObject fetchJson() throws IOException {
//
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
//				new DataInputStream(inputStream)));
//
//		return new JSONObject(bufferedReader.readLine());
//	}
}
