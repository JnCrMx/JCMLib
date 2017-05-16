package de.jcm.lang;

public class StringHelper 
{
	public static String fixString(String str)
	{
		byte[] nullarray={0};
		str=str.replaceAll(new String(nullarray), "");
		str=str.replaceAll(" ", "");
		str=str.replaceAll("\r\n",	"");
		str=str.replaceAll("\n", "");
		str=str.replaceAll("\r", "");
		return str;
	}
}
