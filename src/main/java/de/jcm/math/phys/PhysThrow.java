package de.jcm.math.phys;

import de.jcm.math.geo.vector.Vector2D;

public class PhysThrow
{
	private static final double G = 9.80665;
	private double angle;
	private double velocity0;

	public PhysThrow(double angle, double velocity0)
	{
		this.angle = Math.toRadians(angle);
		this.velocity0 = velocity0;
	}

	public double getAngle()
	{
		return Math.toDegrees(angle);
	}

	public Vector2D getEndPoint()
	{
		double time = getFalldownTime();

		double x = getLenghtAtTime(time);
		double y = getHeightAtTime(time);

		return new Vector2D(x, y);
	}

	public double getFalldownTime()
	{
		double z = 2 * velocity0 * Math.sin(angle);
		double n = G;

		return z / n;
	}

	public double getHeightAtTime(double time)
	{
		return ((-G * time * time) / 2) + (velocity0 * Math.sin(angle) * time);
	}

	public double getLenghtAtTime(double time)
	{
		return velocity0 * Math.cos(angle) * time;
	}

	public double getVelocity0()
	{
		return velocity0;
	}
}
