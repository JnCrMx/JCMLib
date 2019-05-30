package de.jcm.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestComm
{
	
	public static void main(String[] args) throws IOException
	{
		InputStream in=new FileInputStream("\\\\.\\COM3");
		
		int r;
		while((r=in.read())!=-1)
		{
			System.out.print((char)r);
		}
		System.out.println();
		
		in.close();
	}
	
}
