package server;

import lib.Action;
import lib.Connection;
import lib.enums.OperationEnum;

import lib.proxies.SocketProxy;
import lib.targets.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class ServerChatApp {

	static Map<SocketProxy, User> users = new HashMap<>();

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		ServerSocket serverSocket = new ServerSocket(8888);

		Connection con = new Connection(serverSocket.accept());

		Action receivedAction = con.fetch();

		if (receivedAction.getType() == OperationEnum.SIGN_IN)
			users.put(new SocketProxy(con.getSocket()),(User)receivedAction.getTarget());

		con.send(new Action(OperationEnum.SUCCESS));

		receivedAction = con.fetch();

		if (receivedAction.getType() == OperationEnum.USER_LIST)
			con.send(new Action(OperationEnum.SUCCESS, users));
	}
}
