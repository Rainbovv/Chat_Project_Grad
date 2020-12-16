package lib.tools.loggers;

import lib.Connection;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.net.Socket;


@Aspect
public class ClientLogger {

	@Around("execution(* client.ClientChatApp.main(String[]))")
	public void log(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Client Staring");
		pjp.proceed();
		System.out.println("Client Ending");
	}

	@AfterReturning(pointcut = "within( client.ClientChatApp)", returning = "retVal")
	public void loger(JoinPoint joinPoint, Object retVal) {

		if (joinPoint.toString().equals("call(lib.Connection(String, Integer))")) {

			Socket socket = ((Connection) retVal).getSocket();

			System.out.printf("Client has been connected to the Server" +
					"{%n    address: %s%n    port: %s%n}%n",
					socket.getInetAddress(), socket.getPort());
		}

		if (joinPoint.toString().equals("call(Object lib.Connection.fetch())"))

			System.out.println("Server sends >> " + retVal);
	}
}
