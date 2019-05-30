package de.jcm.test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

import de.jcm.net.bridge.Answer;
import de.jcm.net.bridge.Request;
import de.jcm.net.bridge.client.BridgeClient;
import de.jcm.net.bridge.server.AuthRequired;
import de.jcm.net.bridge.server.BridgeServer;
import de.jcm.net.bridge.server.NetworkMethod;
import de.jcm.security.rsa.RSAKeyPair;

public class Test3
{
	public static void main(String[] args) throws Exception
	{
		RSAKeyPair pair=RSAKeyPair.generate(new Random(), 1024);
		
		BigInteger original=new BigInteger(511, Integer.MAX_VALUE, new Random());
		BigInteger encrypted=pair.getPublicKey().encrypt(original);
		BigInteger decrypted=pair.getPrivateKey().decrypt(encrypted);
		
		System.out.println("Original:  "+original+"\n as text: "+new String(original.toByteArray())+"\n");
		System.out.println("Encrypted: "+encrypted+"\n as text: "+new String(encrypted.toByteArray())+"\n");
		System.out.println("Decrypted: "+decrypted+"\n as text: "+new String(decrypted.toByteArray())+"\n");
		System.out.println("original == decrypted ? => "+original.equals(decrypted));

		System.out.println("e: "+pair.getPublicKey().getExponent().toByteArray().length);
		System.out.println("d: "+pair.getPrivateKey().getExponent().toByteArray().length);
		System.out.println("m: "+pair.getPublicKey().getModulo().toByteArray().length);
		
		byte[] random=new byte[100];
		new Random().nextBytes(random);
		
		byte[] abc=pair.getPublicKey().encrypt(random);
		System.out.println(abc.length);
		byte[] xyz=pair.getPrivateKey().decrypt(abc);
		System.out.println(xyz.length);
		
		System.out.println(random[98]+" "+xyz[98]);
		
		BridgeServer server = new BridgeServer(new Test3(), "localhost", 333, pair);
		server.open();
		server.start();

		BridgeClient client = new BridgeClient("localhost", 333);

		Request test = new Request("string array to string", new Object[] { System.getProperties().keySet().toArray(new String[System.getProperties().size()]) }, new Object[] { "JCM", "12345678" });
		Answer answer = client.sendRequest(test);

		System.out.println("Success  : " + answer.isSuccess());
		System.out.println("Response : " + answer.getResponse());
		System.out.println("Error    : " + answer.getError());

		server.close();
	}

	@NetworkMethod(name = "string array to string")
	@AuthRequired(auth = "de.jcm.test.TestAuth")
	public String sumStringArray(String[] in) throws InterruptedException
	{
		return Arrays.toString(in);
	}
}
