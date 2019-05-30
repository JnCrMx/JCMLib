package de.jcm.math.geo.functions;

import de.jcm.math.geo.vector.Vector2D;

public class LinearFunction extends Function
{
	private double m;
	private double n;

	/**
	 * Creates a linear function from pitch and axis section
	 *
	 * @param m
	 *            Pitch of the function
	 * @param n
	 *            Axis section of the function
	 *
	 * @author JCM
	 * @since 17.05.2017 20:08
	 */
	public LinearFunction(double m, double n)
	{
		this.m = m;
		this.n = n;
	}

	/**
	 * Gets the y value form a given x value
	 * <p>
	 *
	 * <code>
	 * double y = (m*x)+n;
	 * </code>
	 *
	 * @param x
	 *            x value the get y value from
	 * @return y value from x value
	 *
	 * @author JCM
	 * @since 17.05.2017 20:06
	 */
	@Override
	public double getAt(double x)
	{
		return (m * x) + n;
	}

	/**
	 * Get the intersection of this linear function with an other <b>linear</b>
	 * function
	 *
	 * @param function
	 *            Function to get intersection with
	 * @return The intersection point
	 *
	 * @see de.jcm.math.geo.functions.LinearFunction#getAt(double)
	 *
	 * @author JCM
	 * @since 17.05.2017 19:58
	 */
	@Override
	public Vector2D getIntersection(Function function)
	{
		if (!(function instanceof LinearFunction))
		{
			throw new IllegalArgumentException("function type not supported");
		}

		LinearFunction f = (LinearFunction) function;

		double m1 = this.m;
		double n1 = this.n;

		double m2 = f.m;
		double n2 = f.n;

		double a = n2 - n1;
		double b = m1 - m2;

		double x = a / b;
		double y = this.getAt(x);

		return new Vector2D(x, y);
	}

	@Override
	public String toString()
	{
		return "LinearFunction [m=" + m + ", n=" + n + "]";
	}

	/**
	 * @return the m
	 */
	public double getM()
	{
		return m;
	}

	/**
	 * @param m the m to set
	 */
	public void setM(double m)
	{
		this.m = m;
	}

	/**
	 * @return the n
	 */
	public double getN()
	{
		return n;
	}

	/**
	 * @param n the n to set
	 */
	public void setN(double n)
	{
		this.n = n;
	}
}
