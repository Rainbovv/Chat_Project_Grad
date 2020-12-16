package client;

import lib.Action;
import lib.Connection;
import lib.enums.ActionName;

import java.io.IOException;

public class ClientChatApp {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Connection con = new Connection("localhost", 7777);

		con.send(new Action(ActionName.SIGNIN));

		con.fetch();
	}
}
