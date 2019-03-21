package de.jcm.security.rsa;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

public class RSAPublicKey
{
	private BigInteger modulo;
	private BigInteger exponent;
	
	/**
	 * @param modulo
	 * @param power
	 */
	public RSAPublicKey(BigInteger modulo, BigInteger exponent)
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
	public void setEower(BigInteger exponent)
	{
		this.exponent = exponent;
	}
	
	public BigInteger encrypt(BigInteger src)
	{
		BigInteger bigInt = src.modPow(exponent, modulo);
		
		if(bigInt.compareTo(src)==0)
			throw new IllegalArgumentException("could not encrypt");
		
		return bigInt;
	}
	
	public byte[] encrypt(byte[] bytes)
	{
		int bits=modulo.bitLength()-1;

		ByteArrayOutputStream encrypted=new ByteArrayOutputStream();
		
		int len=(int) Math.floor(((double)bits)/8.0);
		int count=(int) Math.ceil(((double)bytes.length)/((double)len));
		
//		System.out.println("Encryption{count: "+count+" len: "+len+" bits:"+bits+"}");
		
		for(int i=0;i<count;i++)
		{			
			if(count>=100)
			{
				System.out.println("[DEBUG] "+(i+1)+"/"+count);
			}
			
			byte[] bts=new byte[len+1];
			
			for(int j=0;j<len;j++)
			{
				int pos=(i*len)+j;
				if(pos>=bytes.length)
				{
//					System.err.println("pos="+pos);
					break;
				}
				bts[j+1]=bytes[pos];
			}
			bts[0]=1;
			
			BigInteger big=new BigInteger(bts);
			if(big.compareTo(modulo)==1)
				throw new IllegalArgumentException("integer to encrypt bigger than modulo");

//			System.out.println("D = "+big);
			big=encrypt(big);
//			System.out.println("E = "+big);
			bts=big.toByteArray();
			
//			if(bts.length==256)
//				System.out.println(bts.length);
//			else
//				System.err.println(bts.length);
			byte[] bts2=new byte[len+1];	//???
			for(int j=bts.length;j>0;j--)
				bts2[bts2.length-j]=bts[bts.length-j];
			
//			System.out.println(bts2[1]);
			encrypted.write(bts2, 0, bts2.length);
		}
		
		return encrypted.toByteArray();
	}
}
