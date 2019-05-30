package de.jcm.lang;

public class StringHelper
{
	/** Don't let anyone instantiate this class */
	private StringHelper() {}
	
	/**
	 * Removes all \r, \n and \0 from a string
	 * 
	 * @param str The string to remove these marks from
	 * @return The string
	 * 
	 * @author JCM
	 */
	public static String fixString(String str)
	{
		byte[] nullarray = { 0 };
		str = str.replaceAll(new String(nullarray), "");
		str = str.replaceAll(" ", "");
		str = str.replaceAll("\r\n", "");
		str = str.replaceAll("\n", "");
		str = str.replaceAll("\r", "");
		return str;
	}
}
