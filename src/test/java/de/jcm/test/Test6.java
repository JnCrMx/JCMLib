package de.jcm.test;

import java.math.BigInteger;
import java.security.SecureRandom;

import de.jcm.security.rsa.RSAKeyPair;

public class Test6
{
	
	public static void main(String[] args)
	{
		RSAKeyPair pair=RSAKeyPair.generate(new SecureRandom(), 16);

		System.out.println("Modulo: ");
		out(pair.getPublicKey().getModulo());
		
		System.out.println("Private key: ");
		out(pair.getPrivateKey().getExponent());
		
		System.out.println("Public key: ");
		out(pair.getPublicKey().getExponent());
		
		System.out.println();
		
		String string="Buguser in der \"Realität\" :)";
		
		for(byte byte1 : string.getBytes())
		{
			BigInteger a=BigInteger.valueOf(Byte.toUnsignedLong(byte1));
			BigInteger b=pair.getPublicKey().encrypt(a);
			BigInteger c=pair.getPrivateKey().decrypt(b);

			if(a.equals(c))
			{
				out(b);
			}
			else
			{
				System.err.println("a="+a+" b="+b+" c="+c);
			}
		}
	}
	
	public static void out(BigInteger i)
	{
		String str=Integer.toBinaryString(i.intValueExact());
		
		while(str.length()<16)
			str="0"+str;
		
		System.out.println(str);
	}
	
}
