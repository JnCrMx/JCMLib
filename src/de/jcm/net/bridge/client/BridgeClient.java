package de.jcm.net.bridge.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import de.jcm.net.NetworkHelper;
import de.jcm.net.bridge.Answer;
import de.jcm.net.bridge.Request;

public class BridgeClient 
{
	private String hostname;
	private int port;
	
	public BridgeClient(String hostname, int port) 
	{
		this.hostname=hostname;
		this.port=port;
	}
	
	public Answer sendRequest(Request request) throws IOException
	{
		Answer answer=new Answer(false, null, "Unknown error");
		try
		{
			Socket socket=new Socket(hostname, port);
			
			DataInputStream in=new DataInputStream(socket.getInputStream());
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			
			out.writeByte(0x33);
			byte magic=in.readByte();
			
			if(magic==0x33)
			{
				NetworkHelper.writeString(out, request.getMethodName(), StandardCharsets.UTF_8);
				NetworkHelper.writeFields(out, request.getArguments());
				
				out.writeBoolean(request.isAuthGiven());
				Object[] aa=request.getAuthArguments();
				if(!request.isAuthGiven())
					aa=new Object[0];
				NetworkHelper.writeFields(out, aa);
				
				boolean success=in.readBoolean();
				Object response=NetworkHelper.readField(in);
				String error=NetworkHelper.readString(in);
				
				answer=new Answer(success, response, error);
			}
	
			socket.close();
			in.close();
			out.close();
		}
		catch(Exception e)
		{	
			e.printStackTrace();
			answer=new Answer(false, null, "Network error: "+e.toString()); 
		}
		return answer;
	}
}
