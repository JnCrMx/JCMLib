package de.jcm.math.geo.functions;

import de.jcm.math.geo.Point2D;

public abstract class Function
{
	public abstract double getAt(double x);

	public abstract Point2D getIntersection(Function function);
}
