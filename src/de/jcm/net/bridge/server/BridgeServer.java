package de.jcm.net.bridge.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import de.jcm.net.NetworkHelper;
import de.jcm.net.bridge.Answer;
import de.jcm.net.bridge.Request;
import de.jcm.net.bridge.server.AuthRequired.AuthMethodType;

public class BridgeServer 
{
	private ServerSocket serverSocket;
	private String hostname;
	private int port;
	private boolean open;	
	
	private Object object;
	
	public BridgeServer(Object object, String hostname, int port) 
	{
		this.object=object;
		
		this.hostname=hostname;
		this.port=port;
		
		performMethodCheck();
	}
	
	public void open() throws IOException
	{
		serverSocket=new ServerSocket(port, 50, InetAddress.getByName(hostname));
		open=true;
	}
	
	public void start() throws IOException
	{
		if(open)
		{
			WaitingThread thread=new WaitingThread(serverSocket);
			thread.start();
		}
		else
		{
			throw new IllegalStateException("Socket not open");
		}
	}
	
	public void close() throws IOException
	{
		if(open)
		{
			serverSocket.close();
			open=false;
		}
	}
	
	private static final Class<?>[] allowedTypes=
	{
			Boolean.class,
			Byte.class,
			Character.class,
			Integer.class,
			Long.class,
			Float.class,
			Double.class,
			String.class,
			
			Boolean[].class,
			Byte[].class,
			Character[].class,
			Integer[].class,
			Long[].class,
			Float[].class,
			Double[].class,
			String[].class
	};
	
	private boolean checkType(Class<?> type)
	{
		for (Class<?> class1 : allowedTypes)
		{
			if(class1==type)
				return true;
		}
		return false;
	}
	
	private void performMethodCheck()
	{
		boolean ar=true;
		
		Class<?> clazz=object.getClass();
		
		Method[] methods=clazz.getDeclaredMethods();
		for (Method method : methods) 
		{
			if(method.isAnnotationPresent(NetworkMethod.class))
			{
				NetworkMethod networkMethod=method.getAnnotation(NetworkMethod.class);
				
				Class<?> returnType=method.getReturnType();
				if(returnType != void.class)
				{
					if(!checkType(returnType))
					{
						ar=false;
						System.err.println("\""+method.getName()+"\" / \""+networkMethod.name()+"\" : Return type \""+returnType.getName()+"\" is not allowed in network methods!");
					}
				}
				
				Class<?>[] args=method.getParameterTypes();
				for (int i=0;i<args.length;i++) 
				{
					Class<?> class1=args[i];
					if(i==args.length-1)
					{
						if(class1!=Request.class)
						{
							if(!checkType(class1))
							{
								ar=false;
								System.err.println("\""+method.getName()+"\" / \""+networkMethod.name()+"\" : Argument type \""+class1.getName()+"\" is not allowed in network methods!");
							}
						}
					}
					else if(!checkType(class1))
					{
						ar=false;
						System.err.println("\""+method.getName()+"\" / \""+networkMethod.name()+"\" : Argument type \""+class1.getName()+"\" is not allowed in network methods!");
					}
				}
			}
		}
		
		if(!ar)
		{
			System.err.println("Tip: Do not use primitive types (like \"int\" or \"double\") in network methods! For example use \"java.lang.Integer\" instead of \"int\"!");
		}
	}
	
	public Answer handleRequest(Request request)
	{
		String methodName=request.getMethodName();
		Method method=findNetworkMethod(object.getClass(), methodName);
		
		if(method!=null)
		{
			try 
			{
				if(checkAuth(method, request))
				{
					Class<?>[] pars=method.getParameterTypes();
					
					boolean giveRequest=false;
					
					if(pars.length>0)
					if(pars[pars.length-1]==Request.class)
					{
						giveRequest=true;
						pars=Arrays.copyOf(pars, pars.length-1);
					}
					
					if(pars.length!=request.getArguments().length)
						return new Answer(false, null, "Wrong argument count! "+"Given: "+request.getArguments().length+" Exspected: "+pars.length);
					
					for (int i=0;i<pars.length;i++) 
					{
						Class<?> class1=pars[i];
						Class<?> class2=request.getArguments()[i].getClass();
						if(class1 != class2)
						{
							return new Answer(false, null, "Wrong argument type for argument "+i+"! "+"Given: "+class2.getName()+" Exspected: "+class1.getName());
						}
					}
					
					try
					{
						Object[] args=request.getArguments();
						if(giveRequest)
						{
							args=Arrays.copyOf(args, args.length+1);
							args[args.length-1]=request;
						}
						Object reponse=method.invoke(object, args);
						return new Answer(true, reponse);
					}
					catch (Exception e) 
					{
						return new Answer(false, null, "Exception while executing method: "+e.getCause());
					}
				}
				else
				{
					return new Answer(false, null, "Auth failed");
				}
			}
			catch (Exception e) 
			{
				return new Answer(false, null, "Unknown exception: "+e.toString());
			} 
		}
		else
		{
			return new Answer(false, null, "Method not found");
		}
	}
	
	protected Method findNetworkMethod(Class<?> clazz, String name)
	{
		Method[] methods=clazz.getDeclaredMethods();
		for(Method method : methods)
		{
			if(method.isAnnotationPresent(NetworkMethod.class))
			{
				NetworkMethod networkMethod=method.getAnnotation(NetworkMethod.class);
				if(networkMethod.name().equals(name))
				{
					return method;
				}
			}
		}
		return null;
	}
	
	protected boolean checkAuth(Method method, Request request)
	{
		try
		{
			if(method.isAnnotationPresent(AuthRequired.class))
			{
				AuthRequired auth=(AuthRequired) method.getAnnotation(AuthRequired.class);
				
				if(!request.isAuthGiven())
					return false;
				
				if(auth.type()==AuthMethodType.TOKEN)
				{
					if(request.getAuthArguments().length==1)
					{
						if(request.getAuthArguments()[0] instanceof byte[])
						{
							Class<?> authClass=Class.forName(auth.auth());
							try
							{
								Method instance=authClass.getMethod("instance", new Class[0]);
								if(instance!=null)
								{
									if(Modifier.isStatic(instance.getModifiers()))
									{
										if(instance.getReturnType()==authClass)
										{
											AuthMethodToken authMethod=(AuthMethodToken) instance.invoke(null, new Object[0]);
											return authMethod.isTokenValid((byte[]) request.getAuthArguments()[0]);
										}
									}
								}
								else
								{
									AuthMethodToken authMethod=(AuthMethodToken) Class.forName(auth.auth()).newInstance();
									return authMethod.isTokenValid((byte[]) request.getAuthArguments()[0]);
								}
							}
							catch (Exception e) 
							{
								AuthMethodToken authMethod=(AuthMethodToken) Class.forName(auth.auth()).newInstance();
								return authMethod.isTokenValid((byte[]) request.getAuthArguments()[0]);
							}
						}
					}
					return false;
				}
				else if(auth.type()==AuthMethodType.ALWAYS_AGAIN)
				{
					if(request.getAuthArguments().length==2)
					{
						if(request.getAuthArguments()[0] instanceof String && request.getAuthArguments()[1] instanceof String)
						{
							Class<?> authClass=Class.forName(auth.auth());
							try
							{
								Method instance=authClass.getMethod("instance", new Class[0]);
								if(instance!=null)
								{
									if(Modifier.isStatic(instance.getModifiers()))
									{
										if(instance.getReturnType()==authClass)
										{
											AuthMethodAlwayAgain authMethod=(AuthMethodAlwayAgain) instance.invoke(null, new Object[0]);
											return authMethod.auth((String)request.getAuthArguments()[0], (String)request.getAuthArguments()[1]);
										}
									}
								}
								else
								{
									AuthMethodAlwayAgain authMethod=(AuthMethodAlwayAgain) Class.forName(auth.auth()).newInstance();
									return authMethod.auth((String)request.getAuthArguments()[0], (String)request.getAuthArguments()[1]);
								}
							}
							catch (Exception e)
							{
								AuthMethodAlwayAgain authMethod=(AuthMethodAlwayAgain) Class.forName(auth.auth()).newInstance();
								return authMethod.auth((String)request.getAuthArguments()[0], (String)request.getAuthArguments()[1]);
							}
						}
					}
					return false;
				}
			}
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	private class WaitingThread extends Thread
	{
		private ServerSocket serverSocket;
		
		public WaitingThread(ServerSocket serverSocket)
		{
			this.serverSocket=serverSocket;
		}
		
		@Override
		public void run()
		{
			try
			{
				while(!serverSocket.isClosed())
				{
					Socket socket=serverSocket.accept();
					ReceivingThread thread=new ReceivingThread(socket);
					thread.start();
				}
			}
			catch(IOException e)
			{
				if(!(e instanceof SocketException))
					e.printStackTrace();
			}
		}
	}
	
	private class ReceivingThread extends Thread
	{
		private Socket socket;
		private DataInputStream in;
		private DataOutputStream out;
		
		public ReceivingThread(Socket socket) throws IOException 
		{
			this.socket=socket;
			this.in=new DataInputStream(this.socket.getInputStream());
			this.out=new DataOutputStream(this.socket.getOutputStream());
		}
		
		@Override
		public void run()
		{
			try
			{
				byte magic=in.readByte();
				if(magic==0x33)
				{
					out.writeByte(0x33);
					
					String methodName=NetworkHelper.readString(in);	
					Object[] args=NetworkHelper.readFields(in);
					
					boolean isAuthGiven=in.readBoolean();
					Object[] authArgs=NetworkHelper.readFields(in);
					
					Request request=new Request(socket, methodName, args, isAuthGiven, authArgs);
					Answer answer=handleRequest(request);
					
					out.writeBoolean(answer.isSuccess());
					
					NetworkHelper.writeField(out, answer.getResponse());
					
					String error=answer.getError();
					if(error==null)
						error="";
					NetworkHelper.writeString(out, error, StandardCharsets.UTF_8);
				}
				socket.close();
			}
			catch(IOException e)
			{
				if(!(e instanceof SocketException))
					e.printStackTrace();
			}
		}
	}
}
