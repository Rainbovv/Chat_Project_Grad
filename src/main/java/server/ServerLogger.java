package server;

import lib.Connection;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.net.Socket;


@Aspect
public class ServerLogger {

	@Around("execution(* server.ServerChatApp.main(String[]))")
	public void log(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Server Staring");
		pjp.proceed();
		System.out.println("Server Ending");
	}

	@AfterReturning(pointcut = "within( server.ServerChatApp)", returning = "retVal")
	public void loger(JoinPoint joinPoint, Object retVal) {

		if (joinPoint.toString().equals("call(lib.Connection(ServerSocket))")) {

			Socket socket = ((Connection) retVal).getSocket();

			System.out.printf("Server has accepted a new connection" +
							"{%n    address: %s%n    port: %s%n}%n",
					socket.getInetAddress(), socket.getPort());
		}

		if (joinPoint.toString().equals("call(Object lib.Connection.fetch())"))
			System.out.println("Client sends >> " + retVal);

	}
}
