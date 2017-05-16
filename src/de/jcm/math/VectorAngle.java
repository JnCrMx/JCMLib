package de.jcm.math;

import net.minecraft.util.MathHelper;

public class VectorAngle
{
	/**
	 * Creates a vector from angle and value.
	 * 
	 * @param angle Angle the vector´s direction is
	 * @param value Value of the vector
	 * @return The created vector
	 * 
	 * @see de.jcm.math.Vector2D
	 * @see de.jcm.math.Vector2D#Vector2D(double, double)
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:44
	 */
	public static Vector2D createVector2D(double angle, double value)
	{
		double a2=toRad(angle);
		//System.err.println(a2);
		double x=MathHelper.sin((float) a2);
		double y=MathHelper.cos((float) a2);
		//x=Math.rint(x);
		//y=Math.rint(y);
		x=x*value;
		y=y*value;
		return new Vector2D(x, y);
	}
	
	/**
	 * Gets the angle of the given vector.
	 * 
	 * @param v2d Vector to get the angle from
	 * @return Angle of the vector
	 * 
	 * @see de.jcm.math.Vector2D
	 * @see de.jcm.math.VectorAngle#getValueByVector2D(Vector2D)
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:46
	 */
	public static double getAngleByVector2D(Vector2D v2d)
	{
		double a=Math.atan(v2d.getX()/v2d.getY());
		a=toDeg(a);
		return a;
	}
	
	/**
	 * Gets the value of the given vector.
	 * 
	 * @param v2d Vector to get the value from
	 * @return Value of the vector
	 * 
	 * @see de.jcm.math.Vector2D
	 * @see de.jcm.math.VectorAngle#getAngleByVector2D(Vector2D)
	 * 
	 * @author JCM
	 * @since 22.11.2016 19:46
	 */
	public static double getValueByVector2D(Vector2D v2d)
	{
		double a=getAngleByVector2D(v2d);
		a=toRad(a);
		double pureX=MathHelper.sin((float) a);
		double value=v2d.getX()/pureX;
		return value;
	}
	
	private static double toRad(double angle)
	{
		return (angle/360)*2*Math.PI;
	}
	
	private static double toDeg(double rad)
	{
		return (rad/Math.PI)*180;
	}
	
	public static double AngleYtoAngleX(double a)
	{
		return 90-a;
	}
}
