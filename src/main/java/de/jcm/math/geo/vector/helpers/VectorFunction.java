package de.jcm.math.geo.vector.helpers;

import de.jcm.math.geo.vector.Vector2D;

public class VectorFunction
{
	/** Don't let anyone instantiate this class */
	private VectorFunction() {}
	
	/**
	 * Generates a pitch value from a vector
	 * 
	 * @param v2d
	 *            Vector to get pitch from
	 * @return Pitch
	 * 
	 * @see de.jcm.math.geo.vector.Vector2D
	 * @see de.jcm.math.geo.functions.LinearFunction
	 * @see de.jcm.math.geo.functions.LinearFunction#LinearFunction(double,
	 *      double)
	 */
	public static double getPitch(Vector2D v2d)
	{
		return v2d.getY() / v2d.getX();
	}
}
