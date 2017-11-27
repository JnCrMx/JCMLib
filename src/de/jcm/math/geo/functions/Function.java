package de.jcm.math.geo.functions;

import de.jcm.math.geo.Point2D;

public abstract class Function
{
	/**
	 * 
	 * @param x A x value
	 * @return The y value at the given x value
	 * 
	 * @author JCM
	 */
	public abstract double getAt(double x);

	/**
	 * 
	 * @param function Another function
	 * @return The intersection point with another function
	 * 
	 * @author JCM
	 */
	public abstract Point2D getIntersection(Function function);
}
