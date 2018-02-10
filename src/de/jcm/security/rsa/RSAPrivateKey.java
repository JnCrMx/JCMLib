package de.jcm.security.rsa;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

public class RSAPrivateKey
{
	private BigInteger modulo;
	private BigInteger exponent;
	
	/**
	 * @param modulo
	 * @param power
	 */
	public RSAPrivateKey(BigInteger modulo, BigInteger exponent)
	{
		this.modulo = modulo;
		this.exponent = exponent;
	}
	
	/**
	 * @return the modulo
	 */
	public BigInteger getModulo()
	{
		return modulo;
	}
	/**
	 * @param modulo the modulo to set
	 */
	public void setModulo(BigInteger modulo)
	{
		this.modulo = modulo;
	}
	
	/**
	 * @return the exponent
	 */
	public BigInteger getExponent()
	{
		return exponent;
	}
	/**
	 * @param exponent the exponent to set
	 */
	public void setExponent(BigInteger exponent)
	{
		this.exponent = exponent;
	}
	
	public BigInteger decrypt(BigInteger src)
	{
		return src.modPow(exponent, modulo);
	}
	
	public byte[] decrypt(byte[] bytes)
	{
		int bits=modulo.bitLength();

		int len=(int) Math.ceil(((double)bits)/8.0);
		int count=(int) Math.ceil(((double)bytes.length)/((double)len));
		
		ByteArrayOutputStream decrypted=new ByteArrayOutputStream();

//		System.out.println("Decryption{count: "+count+" len: "+len+" bits:"+bits+"}");
		
		for(int i=0;i<count;i++)
		{			
			if(count>=100)
			{
				System.out.println("[DEBUG] "+(i+1)+"/"+count);
			}
			
			byte[] array2=new byte[len];
			for(int j=0;j<len;j++)
			{
				int pos=(i*len)+j;
				if(pos>=bytes.length)
				{
//					System.err.println("pos="+pos);
					break;
				}
				array2[j]=bytes[pos];
			}
			
//			System.out.println(array2[1]);
			
			BigInteger integer=new BigInteger(array2);		
			
//			System.out.println("E = "+integer);
			
			integer=decrypt(integer);
			array2=integer.toByteArray();
			
//			System.out.println("D = "+integer);
			
			if(array2[0]==1)
			{
				decrypted.write(array2, 1, array2.length-1);
			}
			else
			{
				decrypted.write(array2, 0, array2.length);
			}
		}
		
		return decrypted.toByteArray();
	}
}
