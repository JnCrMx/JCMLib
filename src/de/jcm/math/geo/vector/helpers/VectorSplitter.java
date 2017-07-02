package de.jcm.math.geo.vector.helpers;

import de.jcm.math.geo.Point2D;
import de.jcm.math.geo.functions.LinearFunction;
import de.jcm.math.geo.vector.Vector2D;

public class VectorSplitter 
{
	/**
	 * Splits a vector in two by two angles using functions <br>
	 * <b> No given angle may be bigger or same than 90 </b> <p>
	 * 
	 * Example: <br>
	 * <code>
	 * Vector2D vector2d = new Vector2D(0, 10); <br>
	 * Vector2D[] vectors = VectorSplitter.splitVector2D(vector2d, 20, 20);
	 * </code>
	 * 
	 * @param v2d Vector to split (x will be ignored)
	 * @param angle1 First angle
	 * @param angle2 Second angle
	 * @return An array of vectors
	 * 
	 * @see de.jcm.math.geo.vector.Vector2D
	 * @see de.jcm.math.geo.vector.helpers.VectorFunction
	 * @see de.jcm.math.geo.functions.LinearFunction
	 * @see de.jcm.math.geo.functions.LinearFunction#getIntersection(de.jcm.math.geo.functions.Function)
	 * 
	 * @author JCM
	 * @since 16.05.2017 18:57
	 */
	public static Vector2D[] splitVector2D(Vector2D v2d, double angle1, double angle2)
	{		
		if(angle1>=90 || angle2>=90)
		{
			throw new IllegalArgumentException("an angle is bigger as 90 or is 90");
		}
		
		Vector2D par1=VectorAngle.createVector2D(-angle1, 1);
		Vector2D par2=VectorAngle.createVector2D(-angle2, 1);
		
		Vector2D v1=VectorAngle.createVector2D(angle1, 1);
		Vector2D v2=VectorAngle.createVector2D(angle2, 1);
		
		double m1s1=VectorFunction.getPitch(par1);
		double m2s1=VectorFunction.getPitch(v2);
		LinearFunction f1s1=new LinearFunction(m1s1, v2d.getY());
		LinearFunction f2s1=new LinearFunction(m2s1, 0);
		Point2D insection1=f1s1.getIntersection(f2s1);
		Vector2D ev1=new Vector2D(insection1.getX(), insection1.getY());
		

		double m1s2=VectorFunction.getPitch(par2);
		double m2s2=VectorFunction.getPitch(v1);
		LinearFunction f1s2=new LinearFunction(m1s2, v2d.getY());
		LinearFunction f2s2=new LinearFunction(m2s2, 0);
		Point2D insection2=f1s2.getIntersection(f2s2);
		Vector2D ev2=new Vector2D(-insection2.getX(), insection2.getY());
		
		return new Vector2D[]{ev1,ev2};
	}
	
	/**
	 * Splits a vector in two by two angles using functions <br>
	 * <b> angle1 + angle2 must be 90 </b> <p>
	 * 
	 * Example: <br>
	 * <code>
	 * Vector2D vector2d = new Vector2D(0, 10); <br>
	 * Vector2D[] vectors = VectorSplitter.squareSplitVector2D(vector2d, 20, 70);
	 * </code>
	 * 
	 * @param v2d Vector to split (x will be ignored)
	 * @param angle1 First angle
	 * @param angle2 Second angle
	 * @return An array of vectors
	 * 
	 * @see de.jcm.math.geo.vector.Vector2D
	 * @see java.lang.Math#cos(double)
	 * 
	 * @author JCM
	 * @since 16.05.2017 18:57
	 */
	public static Vector2D[] squareSplitVector2D(Vector2D v2d, double angle1, double angle2)
	{
		if(angle1+angle2!=90)
		{
			throw new IllegalArgumentException("angle1 + angle2 is not 90");
		}
		
		Vector2D ev1=VectorAngle.createVector2D(90-angle1, v2d.getY()*Math.cos(toRad(angle2)));
		Vector2D ev2=VectorAngle.createVector2D(-90+angle2, v2d.getY()*Math.cos(toRad(angle1)));
		
		return new Vector2D[]{ev1,ev2};
	}
	
	private static double toRad(double angle)
	{
		return (angle/360)*2*Math.PI;
	}
}
