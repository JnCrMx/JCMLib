package de.jcm.math.geo;

public class Point3D
{
	private double x, y, z;

	/**
	 * Create a point with x, y and z coordinate
	 * 
	 * @param x X coordinate of the point
	 * @param y Y coordinate of the point
	 * @param z Z coordinate of the point
	 * 
	 * @author JCM
	 */
	public Point3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
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
	 * 
	 * @return Z coordinate
	 * 
	 * @author JCM
	 */
	public double getZ()
	{
		return z;
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
	
	/**
	 * Sets the z coordinate of the point
	 * 
	 * @param z Z coordinate
	 * 
	 * @author JCM
	 */
	public void setZ(double z)
	{
		this.z = z;
	}
}
