package de.jcm.lang;

import java.io.File;
import java.net.URISyntaxException;

public class MyPath
{
	/** Don't let anyone instantiate this class */
	private MyPath() {}
	
	/**
	 * Returns the absolute path of the file where the program runs
	 * 
	 * @param clazz The current class
	 * @return A absolute path
	 * 
	 * @author JCM
	 */
	public static String getClassPath(Class<?> clazz)
	{
		try
		{
			return clazz.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns the absolute file of the file where the program runs
	 * 
	 * @param clazz The current class
	 * @return A absolute file
	 * 
	 * @author JCM
	 */
	public static File getClassPathFile(Class<?> clazz)
	{
		try
		{
			return new File(clazz.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
