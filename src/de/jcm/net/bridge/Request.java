package de.jcm.net.bridge;

import java.net.Socket;

public class Request {
	private Socket socket;
	
	private String methodName;
	private Object[] arguments;

	private boolean authGiven;
	private Object[] authArguments;

	public Request(Socket socket, String methodName, Object[] arguments, boolean authGiven, Object[] authArguments) {
		this.socket=socket;
		this.methodName = methodName;
		this.arguments = arguments;
		this.authGiven = authGiven;
		this.authArguments = authArguments;
	}

	public String getMethodName() {
		return methodName;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public boolean isAuthGiven() {
		return authGiven;
	}

	public Object[] getAuthArguments() {
		return authArguments;
	}

	public Socket getSocket() {
		return socket;
	}
	
	
}
