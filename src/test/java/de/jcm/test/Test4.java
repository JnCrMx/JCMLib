package de.jcm.test;

import de.jcm.math.phys.PhysThrow;

public class Test4
{
	public static void main(String[] args)
	{
		double angle = 45;
		double velocity0 = 50;

		PhysThrow throw1 = new PhysThrow(angle, velocity0);

		System.out.println(throw1.getEndPoint().getY());
	}
}
