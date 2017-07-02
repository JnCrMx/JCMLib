package de.jcm.math.phys;

import de.jcm.math.geo.Point2D;

public class PhysThrow
{
	private double angle;
	private double velocity0;
	private static final double g = 9.80665;
	
	public PhysThrow(double angle, double velocity0)
	{
		this.angle=Math.toRadians(angle);
		this.velocity0=velocity0;
	}
	
	public double getLenghtAtTime(double time)
	{
		return velocity0*Math.cos(angle)*time;
	}

	public double getHeightAtTime(double time)
	{
		return ((-g*time*time)/2)+(velocity0*Math.sin(angle)*time);
	}
	
	public Point2D getEndPoint()
	{
		double time=getFalldownTime();
		
		double x=getLenghtAtTime(time);
		double y=getHeightAtTime(time);
		
		return new Point2D(x, y);
	}
	
	public double getFalldownTime()
	{		
		double z=2*velocity0*Math.sin(angle);
		double n=g;
		
		return z/n;
	}
	
	public double getAngle()
	{
		return Math.toDegrees(angle);
	}

	public double getVelocity0()
	{
		return velocity0;
	}
}
