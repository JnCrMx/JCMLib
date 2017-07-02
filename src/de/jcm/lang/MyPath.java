package de.jcm.lang;

import java.io.File;
import java.net.URISyntaxException;

public class MyPath
{
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
