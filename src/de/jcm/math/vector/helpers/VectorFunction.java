package de.jcm.math.vector.helpers;

import de.jcm.math.vector.Vector2D;

public class VectorFunction
{
	/**
	 * Generates a pitch value from a vector
	 * 
	 * @param v2d Vector to get pitch from
	 * @return
	 * 
	 * @see de.jcm.math.vector.Vector2D
	 * @see de.jcm.math.functions.LinearFunction
	 * @see de.jcm.math.functions.LinearFunction#LinearFunction(double, double)
	 */
	public static double getPitch(Vector2D v2d)
	{
		return v2d.getY()/v2d.getX();
	}
}
