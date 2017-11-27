package de.jcm.net.bridge;

import java.net.Socket;

public class Request
{
	private Socket socket;

	private String methodName;
	private Object[] arguments;

	private boolean authGiven;
	private Object[] authArguments;

	public Request(Socket socket, String methodName, Object[] arguments, boolean authGiven, Object[] authArguments)
	{
		this.socket = socket;
		this.methodName = methodName;
		this.arguments = arguments;
		this.authGiven = authGiven;
		this.authArguments = authArguments;
	}

	public Request(String methodName, Object[] arguments, Object[] authArguments)
	{
		this.socket = null;
		this.methodName = methodName;
		this.arguments = arguments;
		this.authGiven = true;
		this.authArguments = authArguments;
	}
	
	public Request(String methodName, Object[] arguments)
	{
		this.socket = null;
		this.methodName = methodName;
		this.arguments = arguments;
		this.authGiven = false;
		this.authArguments=null;
	}

	public Object[] getArguments()
	{
		return arguments;
	}

	public Object[] getAuthArguments()
	{
		return authArguments;
	}

	public String getMethodName()
	{
		return methodName;
	}

	public Socket getSocket()
	{
		return socket;
	}

	public boolean isAuthGiven()
	{
		return authGiven;
	}

}
