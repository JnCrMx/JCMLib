package de.jcm.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPHelper
{
	/** Don't let anyone instantiate this class */
	private UDPHelper() {}
	
	public static void arduinoSleep()
	{
		try
		{
			Thread.sleep(2500);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static byte[] request(DatagramSocket sck, InetSocketAddress to, byte[] req, boolean debug) throws Exception
	{
		long time1 = System.currentTimeMillis();
		DatagramPacket send = new DatagramPacket(req, req.length, to);
		sck.send(send);
		byte[] empty = new byte[255];
		DatagramPacket recv = new DatagramPacket(empty, 255);
		sck.receive(recv);
		long time2 = System.currentTimeMillis();
		if (debug)
			System.out.println("Request took " + (time2 - time1) + "ms!");
		return recv.getData();
	}

	public static void send(DatagramSocket sck, InetSocketAddress to, byte[] req, boolean debug) throws Exception
	{
		DatagramPacket send = new DatagramPacket(req, req.length, to);
		sck.send(send);
	}
}
