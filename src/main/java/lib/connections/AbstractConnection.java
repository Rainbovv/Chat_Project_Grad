package lib.connections;

import lib.Action;
import java.io.*;
import java.net.Socket;

public abstract class AbstractConnection {

	public void sendAction(Action action, Socket socket) {

		try {
			ObjectOutputStream objectOutStream = new ObjectOutputStream(socket.getOutputStream());

			objectOutStream.writeObject(action);
			objectOutStream.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendUTF(String message, Socket socket) {

		try {
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			dataOutputStream.writeUTF(message);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Action fetchAction(Socket socket) throws IOException, ClassNotFoundException {

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
