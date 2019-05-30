package de.jcm.test;

import java.math.BigInteger;
import java.security.SecureRandom;

import de.jcm.security.rsa.RSAKeyPair;

public class InversRSA
{

	public static void main(String[] args)
	{
		RSAKeyPair pair = RSAKeyPair.generate(new SecureRandom(), 1024);

		BigInteger original = new BigInteger("1234567890123456789012345678901234567890");
		System.out.println(original);

		BigInteger encrypted = pair.getPrivateKey().decrypt(original);
		System.out.println(encrypted);

		BigInteger decrypted = pair.getPublicKey().encrypt(encrypted);
		System.out.println(decrypted);
	}

}
