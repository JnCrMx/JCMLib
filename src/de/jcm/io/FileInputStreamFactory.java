package de.jcm.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileInputStreamFactory implements InputStreamFactory
{
	private File file;
	
	public FileInputStreamFactory(File file)
	{
		this.file=file;
	}
	
	@Override
	public InputStream createInputStream()
	{
		try
		{
			return new FileInputStream(file);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
