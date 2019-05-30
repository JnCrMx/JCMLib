package de.jcm.test;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.junit.Test;

import de.jcm.security.rsa.RSAKeyPair;

class ModPowTest
{
	@Test
	void test()
	{
		SecureRandom random = new SecureRandom();
		for(int i=0;i<1000;i++)
		{
			RSAKeyPair pair = RSAKeyPair.generate(random, 1024);

			byte[] lotsOfBytes = new byte[127];
			random.nextBytes(lotsOfBytes);

			BigInteger original = new BigInteger(1, lotsOfBytes);
			BigInteger encrypt = pair.getPrivateKey().decrypt(original);
			BigInteger decrypt = pair.getPublicKey().encrypt(encrypt);

			assertEquals(0, original.compareTo(decrypt));
		}
	}
}
