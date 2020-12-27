package lib.loggers;

import lib.connections.ClientConnection;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.net.Socket;


@Aspect
public class ClientLogger {

	final String line = "----------------------------------------------------------";

	@Around("execution(* client.ClientChatApp.main(String[]))")
	public void startEndLogger(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Client Staring\n" + line);
		pjp.proceed();
		System.out.println("Client Ending");
	}

	@Before("within( client.ClientChatApp)")
	public void logger(JoinPoint joinPoint) {

		if (joinPoint.toString().equals("call(String java.util.Scanner.nextLine())"))
			System.out.println("Please enter your user_name: ");
	}

	@AfterReturning(pointcut = "within( client.ClientChatApp)", returning = "retVal")
	public void connectionLogger(JoinPoint joinPoint, Object retVal) {

		if (joinPoint.toString().equals("call(lib.connections.ClientConnection(String, Integer, User))")
			|| joinPoint.toString().equals("call(lib.connections.ClientConnection(String, Integer))")
			|| joinPoint.toString().equals("call(lib.connections.ClientConnection(Socket, User))")
			|| joinPoint.toString().equals("call(lib.connections.ClientConnection(Socket))")) {

			Socket socket = ((ClientConnection) retVal).getSocket();

			System.out.printf("You have been connected to the Server" +
					"{%n    address: %s%n    port: %s%n}%n%s%n",
					socket.getInetAddress(), socket.getPort(), line);
		}
	}
}
