package lib.proxies;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class SocketProxy implements Serializable {

	private final String host;
	private final int port;


	public SocketProxy(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public SocketProxy(Socket socket) {
		this.host = socket.getInetAddress().toString();
		this.port = socket.getPort();
	}

	public Socket toSocket() throws IOException {

		return new Socket(host, port);
	}

	@Override
	public String toString() {
		return "Socket(Proxy) { " + "host='" + host + '\'' + ", port=" + port + " }";
	}
}
