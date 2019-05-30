package de.jcm.util;

import java.io.PrintStream;

public class Dump
{
	public static void dumpByteArray(byte[] array)
	{
		dumpByteArray(array, System.out);
	}
	
	public static void dumpByteArray(byte[] array, PrintStream out)
	{
		out.println(byteArrayToString(array, " "));
	}
	
	public static String byteArrayToString(byte[] array, String seperator)
	{
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<array.length-1;i++)
		{
			builder.append(byteToHexString(array[i]));
			builder.append(seperator);
		}
		builder.append(byteToHexString(array[array.length-1]));
		
		return builder.toString();
	}
	
	public static String byteToHexString(byte b)
	{
		StringBuilder builder = new StringBuilder(Integer.toHexString(Byte.toUnsignedInt(b)));
		while(builder.length()<2)
			builder.insert(0, "0");
		return builder.toString();
	}
}
