package server;

import lib.Action;
import lib.Connection;
import lib.enums.ActionName;

import java.io.IOException;
import java.net.ServerSocket;

/*  HW: - (create lib.tools.Logger) using AOP -> Server/Client loggers
* */

public class ServerChatApp {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Connection con = new Connection(new ServerSocket(7777));

		con.fetch();

		con.send(new Action(ActionName.CONFIRMED));
	}
}
