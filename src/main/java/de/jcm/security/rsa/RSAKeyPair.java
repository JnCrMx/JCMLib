package de.jcm.security.rsa;

import java.math.BigInteger;
import java.util.Random;

import de.jcm.math.ExtendedEuclideanAlgorithm;

public class RSAKeyPair
{
	private RSAPrivateKey privateKey;
	private RSAPublicKey publicKey;
	
	
	
	/**
	 * @param privateKey
	 * @param publicKey
	 */
	public RSAKeyPair(RSAPrivateKey privateKey, RSAPublicKey publicKey)
	{
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}
	/**
	 * @return the privateKey
	 */
	public RSAPrivateKey getPrivateKey()
	{
		return privateKey;
	}
	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(RSAPrivateKey privateKey)
	{
		this.privateKey = privateKey;
	}
	/**
	 * @return the publicKey
	 */
	public RSAPublicKey getPublicKey()
	{
		return publicKey;
	}
	/**
	 * @param publicKey the publicKey to set
	 */
	public void setPublicKey(RSAPublicKey publicKey)
	{
		this.publicKey = publicKey;
	}
	
	public static RSAKeyPair generate(Random random, int bits)
	{
		BigInteger p=new BigInteger(bits/2-1, Integer.MAX_VALUE, random);
		BigInteger q=new BigInteger(bits/2-1, Integer.MAX_VALUE, random);
		
		BigInteger modulo=p.multiply(q);
		BigInteger phi=(p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));
		BigInteger e=BigInteger.valueOf(65537L);
		
		RSAPublicKey publicKey=new RSAPublicKey(modulo, e);
		
		ExtendedEuclideanAlgorithm al=new ExtendedEuclideanAlgorithm(e, phi);
		al.calculate();
		
		BigInteger d=al.getX();
		d = d.mod(phi);
		if(d.compareTo(BigInteger.ZERO)==-1)
		    d.add(phi);
		
		RSAPrivateKey privateKey=new RSAPrivateKey(modulo, d);
		
		return new RSAKeyPair(privateKey, publicKey);
	}
	
}
