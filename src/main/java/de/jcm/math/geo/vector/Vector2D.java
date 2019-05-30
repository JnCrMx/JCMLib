package de.jcm.math.geo.vector;

import de.jcm.math.geo.functions.LinearFunction;
import de.jcm.math.geo.vector.helpers.VectorAngle;
import de.jcm.math.geo.vector.helpers.VectorFunction;

public class Vector2D
{
	private double x, y;

	/**
	 * Constructs a vector with a position on the x and on on the y axis.
	 * 
	 * @param x
	 *            Position on x axis
	 * @param y
	 *            Position on y axis
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:38
	 */
	public Vector2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Increases the vector by x and y. Returns itself, but apply the changes to
	 * itself, not only to the returned object.
	 * 
	 * @param x
	 *            The value to increase the vector on x axis
	 * @param y
	 *            The value to increase the vector on y axis
	 * 
	 * @return Itself
	 * 
	 * @see de.jcm.math.geo.vector.Vector2D#add(Vector2D)
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:30
	 */
	public Vector2D add(double x, double y)
	{
		this.x = this.x + x;
		this.y = this.y + y;
		return this;
	}

	/**
	 * Increases the vector by another vector. Returns itself, but apply the
	 * changes to itself, not only to the returned object.
	 * 
	 * @param v2d
	 *            The vector to increase
	 * 
	 * @return Itself
	 * 
	 * @see de.jcm.math.geo.vector.Vector2D#add(double, double)
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:33
	 */
	public Vector2D add(Vector2D v2d)
	{
		this.x = this.x + v2d.x;
		this.y = this.y + v2d.y;
		return this;
	}

	/**
	 * Returns the coordinate of the vector on the x axis.
	 * 
	 * @return The vector´s x value
	 * 
	 * @see de.jcm.math.geo.vector.Vector2D#getY()
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:36
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * Returns the coordinate of the vector on the y axis.
	 * 
	 * @return The vector´s y value
	 * 
	 * @see de.jcm.math.geo.vector.Vector2D#getX()
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:36
	 */
	public double getY()
	{
		return y;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	/**
	 * Decreases the vector by x and y. Returns itself, but apply the changes to
	 * itself, not only to the returned object.
	 * 
	 * @param x
	 *            The value to decrease the vector on x axis
	 * @param y
	 *            The value to decrease the vector on y axis
	 * 
	 * @return Itself
	 * 
	 * @see de.jcm.math.geo.vector.Vector2D#substract(Vector2D)
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:30
	 */
	public Vector2D substract(double x, double y)
	{
		this.x = this.x - x;
		this.y = this.y - y;
		return this;
	}

	/**
	 * Decreases the vector by another vector. Returns itself, but apply the
	 * changes to itself, not only to the returned object.
	 * 
	 * @param v2d
	 *            The vector to decrease
	 * 
	 * @return Itself
	 * 
	 * @see de.jcm.math.geo.vector.Vector2D#substract(double, double)
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:34
	 */
	public Vector2D substract(Vector2D v2d)
	{
		this.x = this.x - v2d.x;
		this.y = this.y - v2d.y;
		return this;
	}

	public Vector2D getIntersection(Vector2D vector)
	{
		double p1 = VectorFunction.getPitch(this);
		double p2 = VectorFunction.getPitch(vector);

		LinearFunction function1 = new LinearFunction(p1, y);
		LinearFunction function2 = new LinearFunction(p2, vector.y);
		
		return function1.getIntersection(function2);
	}
	
	@Override
	public String toString()
	{
		return "v2d<"+Math.round(x*1000)/1000.0+" "+Math.round(y*1000)/1000.0+">";
	}
	
	public Vector2D normalize()
	{
		double angle = VectorAngle.getAngleByVector2D(this);
		return VectorAngle.createVector2D(angle, 1);
	}
	
	public Vector2D rotate(double angle)
	{
		double angle1 = VectorAngle.getAngleByVector2D(this);
		double value = VectorAngle.getValueByVector2D(this);
		double angle2 = angle1 + angle;
		return VectorAngle.createVector2D(angle2, value);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Vector2D))
			return false;
		Vector2D v = (Vector2D) obj;

		double x1 = Math.round(x*1000)/1000.0;
		double x2 = Math.round(v.x*1000)/1000.0;
		double y1 = Math.round(y*1000)/1000.0;
		double y2 = Math.round(v.y*1000)/1000.0;
		
		return x1==x2 && y1==y2; 
	}
}
