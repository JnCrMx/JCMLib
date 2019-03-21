package de.jcm.math;

import java.util.Locale;

public class MathUtils
{
	public static double round(double number, int count)
	{
		double d1 = number * Math.pow(10, count);
		double d2 = Math.round(d1);
		double d3 = d2 / Math.pow(10, count);
		
		return d3;
	}
	
	public static String formatInteger(int number, Locale locale)
	{
		char split = (locale==Locale.GERMAN)?'.':',';
		
		String string = Integer.toString(number);
		String string2 = "";
		
		for(int a=string.length()-1;a>=0;a--)
		{
			if(a%3==0)
			{
				string2=Character.toString(string.charAt(a))+Character.toString(split)+string2;
			}
			else
			{
				string2=Character.toString(string.charAt(a))+string2;
			}
		}
		
		return string2;
	}
}
