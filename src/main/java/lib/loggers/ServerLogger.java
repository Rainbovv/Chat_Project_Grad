package lib.loggers;

import lib.Connection;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import java.net.Socket;


@Aspect
public class ServerLogger {

	final String line = "----------------------------------------------------------";

	@Around("execution(* server.ServerChatApp.main(String[]))")
	public void log(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Server Staring\n" + line);
		pjp.proceed();
		System.out.println("Server Ending");
	}

	@AfterReturning(pointcut = "within( server.ServerChatApp)", returning = "retVal")
	public void loger(JoinPoint joinPoint, Object retVal) {

		if (joinPoint.toString().equals("call(lib.Connection(Socket))")) {

			Socket socket = ((Connection) retVal).getSocket();

			System.out.printf("Server has accepted a new connection" +
							"{%n    address: %s%n    port: %s%n}%n%s%n",
					socket.getInetAddress(), socket.getPort(),line);
		}

		if (joinPoint.toString().equals("call(void lib.Connection.send(Action))"))
			System.out.println("Sending >> " + joinPoint.getArgs()[0].toString() + "\n" + line);

		if (joinPoint.toString().equals("call(Action lib.Connection.fetch())"))
			System.out.println("Client sends >> " + retVal + "\n" + line);

	}
}
