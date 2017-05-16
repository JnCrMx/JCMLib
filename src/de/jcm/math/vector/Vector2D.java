package de.jcm.math.vector;

public class Vector2D 
{
	private double x,y;
	
	/**
	 * Constructs a vector with a position on the x and on on the y axis.
	 * 
	 * @param x Position on x axis
	 * @param y Position on y axis
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:38
	 */
	public Vector2D(double x,double y) 
	{
		this.x=x;
		this.y=y;
	}

	/**
	 * Returns the coordinate of the vector on the x axis.
	 * 
	 * @return The vector´s x value
	 * 
	 * @see de.jcm.math.vector.Vector2D#getY()
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:36
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the coordinate of the vector on the y axis.
	 * 
	 * @return The vector´s y value
	 * 
	 * @see de.jcm.math.vector.Vector2D#getX()
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:36
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Increases the vector by another vector.
	 * Returns itself, but apply the changes to itself, not only to the returned object.
	 * 
	 * @param v2d The vector to increase
	 * 
	 * @return Itself
	 * 
	 * @see de.jcm.math.vector.Vector2D#add(double, double)
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:33
	 */
	public Vector2D add(Vector2D v2d)
	{
		this.x=this.x+v2d.x;
		this.y=this.y+v2d.y;
		return this;
	}
	
	/**
	 * Increases the vector by x and y.
	 * Returns itself, but apply the changes to itself, not only to the returned object.
	 * 
	 * @param x The value to increase the vector on x axis
	 * @param y The value to increase the vector on y axis
	 * 
	 * @return Itself
	 * 
	 * @see de.jcm.math.vector.Vector2D#add(Vector2D)
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:30
	 */
	public Vector2D add(double x,double y)
	{
		this.x=this.x+x;
		this.y=this.y+y;
		return this;
	}
	
	
	/**
	 * Decreases the vector by another vector.
	 * Returns itself, but apply the changes to itself, not only to the returned object.
	 * 
	 * @param v2d The vector to decrease
	 * 
	 * @return Itself
	 * 
	 * @see de.jcm.math.vector.Vector2D#remove(double, double)
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:34
	 */
	public Vector2D substract(Vector2D v2d)
	{
		this.x=this.x-v2d.x;
		this.y=this.y-v2d.y;
		return this;
	}
	
	/**
	 * Decreases the vector by x and y.
	 * Returns itself, but apply the changes to itself, not only to the returned object.
	 * 
	 * @param x The value to decrease the vector on x axis
	 * @param y The value to decrease the vector on y axis
	 * 
	 * @return Itself
	 * 
	 * @see de.jcm.math.vector.Vector2D#remove(Vector2D)
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:30
	 */
	public Vector2D substract(double x,double y)
	{
		this.x=this.x-x;
		this.y=this.y-y;
		return this;
	}
	
	
}
