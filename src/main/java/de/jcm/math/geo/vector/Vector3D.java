package de.jcm.math.geo.vector;

import de.jcm.math.geo.Dimension;

public class Vector3D
{
	private double x, y, z;

	public Vector3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getZ()
	{
		return z;
	}

	@Override
	public String toString()
	{
		return "v3d<"+Math.round(x*1000)/1000.0+" "+Math.round(y*1000)/1000.0+" "+Math.round(z*1000)/1000.0+">";
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Vector3D))
			return false;
		Vector3D v = (Vector3D) obj;

		double x1 = Math.round(x*1000)/1000.0;
		double x2 = Math.round(v.x*1000)/1000.0;
		double y1 = Math.round(y*1000)/1000.0;
		double y2 = Math.round(v.y*1000)/1000.0;
		double z1 = Math.round(z*1000)/1000.0;
		double z2 = Math.round(v.z*1000)/1000.0;
		
		return x1==x2 && y1==y2 && z1==z2; 
	}
	
	public double getRotationY()
	{
		return Math.atan2(z, x);
	}
	
	public double getRotationZ()
	{
		return Math.atan2(y, Math.sqrt(x*x+z*z));
	}

	public double getValue()
	{
		return Math.sqrt(x*x+y*y+z*z);
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x)
	{
		this.x = x;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y)
	{
		this.y = y;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(double z)
	{
		this.z = z;
	}
	
	public Vector2D removeDimension(Dimension dimension)
	{
		switch(dimension)
		{
			case X:
				return new Vector2D(y, z);
			case Y:
				return new Vector2D(x, z);
			case Z:
				return new Vector2D(x, y);
			default:
				throw new IllegalArgumentException("invalid dimension");
		}
	}
}
