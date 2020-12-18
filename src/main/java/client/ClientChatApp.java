package client;

import lib.Action;
import lib.Connection;
import lib.enums.OperationEnum;
import lib.proxies.SocketProxy;
import lib.targets.User;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class ClientChatApp {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter you username:");
		Action action = new Action(OperationEnum.SIGN_IN, new User(scanner.nextLine()));

		Connection con = new Connection("localhost", 8888);

		con.send(action);

		Action receivedAction = con.fetch();

		if (receivedAction.getType() == OperationEnum.SUCCESS)
			con.send(new Action(OperationEnum.USER_LIST));

		receivedAction = con.fetch();

		if (receivedAction.getType() == OperationEnum.SUCCESS) {

			Map<SocketProxy, User> map = ((Map<SocketProxy, User>) receivedAction.getTarget());

			for (SocketProxy socket : map.keySet()) {
				System.out.println(map.get(socket).toString() + " -> "+ socket);
			}
		}
	}
}
