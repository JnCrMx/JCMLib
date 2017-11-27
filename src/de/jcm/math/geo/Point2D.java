package de.jcm.math.geo;

public class Point2D
{
	private double x, y;

	/**
	 * Create a point with x and y coordinate
	 * 
	 * @param x X coordinate of the point
	 * @param y Y coordinate of the point
	 * 
	 * @author JCM
	 */
	public Point2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @return X coordinate
	 * 
	 * @author JCM
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * 
	 * @return Y coordinate
	 * 
	 * @author JCM
	 */
	public double getY()
	{
		return y;
	}

	/**
	 * Sets the x coordinate of the point
	 * 
	 * @param x X coordinate
	 * 
	 * @author JCM
	 */
	public void setX(double x)
	{
		this.x = x;
	}

	/**
	 * Sets the y coordinate of the point
	 * 
	 * @param y Y coordinate
	 * 
	 * @author JCM
	 */
	public void setY(double y)
	{
		this.y = y;
	}
}
