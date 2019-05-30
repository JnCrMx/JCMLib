package de.jcm.net.bridge.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import de.jcm.net.NetworkHelper;
import de.jcm.net.bridge.Answer;
import de.jcm.net.bridge.Request;
import de.jcm.security.rsa.RSAKeyPair;
import de.jcm.security.rsa.RSAPublicKey;

public class BridgeClient
{
	private String hostname;
	private int port;
	
	private boolean encrypting;
	private int keyBitCount = 512;
	
	private RSAKeyPair keys;

	public BridgeClient(String hostname, int port)
	{
		this.hostname = hostname;
		this.port = port;
	}
	
	public void generateKeys()
	{
		this.keys=RSAKeyPair.generate(new SecureRandom(), keyBitCount);
	}

	public Answer sendRequest(Request request) throws Exception
	{
		Answer answer = new Answer(false, null, "Unknown error");
		try
		{			
			Socket socket = new Socket(hostname, port);

			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			out.writeByte(0x33);
			byte magic = in.readByte();

			if (magic == 0x33)
			{
				if(encrypting)
				{
					out.writeByte(0x30);
					out.writeByte(0x01);
					out.writeInt(keyBitCount);
					
					byte[] exponent=new byte[keyBitCount/8];
					in.read(exponent);
					byte[] modulo=new byte[keyBitCount/8];
					in.read(modulo);
					
					@SuppressWarnings("unused")
					RSAPublicKey serverKey=new RSAPublicKey(new BigInteger(modulo), new BigInteger(exponent));

					@SuppressWarnings("unused")
					byte[] pe=keys.getPublicKey().getExponent().toByteArray();
					@SuppressWarnings("unused")
					byte[] pm=keys.getPublicKey().getModulo().toByteArray();
					
					out.write(keys.getPublicKey().getExponent().toByteArray());
				}
				else
				{
					NetworkHelper.writeString(out, request.getMethodName(), StandardCharsets.UTF_8);
					NetworkHelper.writeFields(out, request.getArguments());
	
					out.writeBoolean(request.isAuthGiven());
					Object[] aa = request.getAuthArguments();
					if (!request.isAuthGiven())
						aa = new Object[0];
					NetworkHelper.writeFields(out, aa);
	
					boolean success = in.readBoolean();
					Object response = NetworkHelper.readField(in);
					String error = NetworkHelper.readString(in);
	
					answer = new Answer(success, response, error);
				}
			}

			socket.close();
			in.close();
			out.close();
		}
		catch (Exception e)
		{
			answer = new Answer(false, null, "Network error: " + e.toString());
			throw e;
		}
		return answer;
	}

	public boolean isEncrypting()
	{
		return encrypting;
	}

	public void setEncrypting(boolean encrypting)
	{
		this.encrypting = encrypting;
	}

	public int getKeyBitCount()
	{
		return keyBitCount;
	}

	public void setKeyBitCount(int keyBitCount)
	{
		this.keyBitCount = keyBitCount;
	}
}
