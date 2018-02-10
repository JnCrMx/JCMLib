package de.jcm.math.geo;

import java.math.BigInteger;
import java.util.ArrayList;

public class ExtendedEuclideanAlgorithm
{
	private BigInteger a, b, c, x, y;
	
	public ExtendedEuclideanAlgorithm(BigInteger a, BigInteger b)
	{
		this.a=a;
		this.b=b;
	}
	
	public void calculate()
	{
		BigInteger tempA=new BigInteger(a.toByteArray());
		BigInteger tempB=new BigInteger(b.toByteArray());
		BigInteger tempR = BigInteger.ZERO, tempX = BigInteger.ZERO, tempY = BigInteger.ONE;
		
		ArrayList<BigInteger> gs=new ArrayList<>();
		
		while(true)
		{
			BigInteger[] t0=tempA.divideAndRemainder(tempB);
			
			if(t0[1].signum()==0)
			{
				this.c=tempR;
				break;
			}
			
			gs.add(t0[0]);
			tempR=t0[1];
			
			tempA=tempB;
			tempB=tempR;
		}
		
		for(int i=gs.size()-1;i>=0;i--)
		{
			BigInteger tty=tempX.subtract(tempY.multiply(gs.get(i)));
			tempX=tempY;
			tempY=tty;
		}
		
		this.x=tempX;
		this.y=tempY;
	}

	/**
	 * @return the a
	 */
	public BigInteger getA()
	{
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(BigInteger a)
	{
		this.a = a;
	}

	/**
	 * @return the b
	 */
	public BigInteger getB()
	{
		return b;
	}

	/**
	 * @param b the b to set
	 */
	public void setB(BigInteger b)
	{
		this.b = b;
	}

	/**
	 * @return the c
	 */
	public BigInteger getC()
	{
		return c;
	}

	/**
	 * @return the x
	 */
	public BigInteger getX()
	{
		return x;
	}

	/**
	 * @return the y
	 */
	public BigInteger getY()
	{
		return y;
	}
}
