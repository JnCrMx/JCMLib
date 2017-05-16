package de.jcm.math.vector.helpers;

import de.jcm.math.vector.Vector2D;

public class VectorFunction
{
	public static double getPitch(Vector2D v2d)
	{
		return v2d.getY()/v2d.getX();
	}
}
