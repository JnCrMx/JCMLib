package de.jcm.math.functions;

import de.jcm.math.Point2D;

public abstract class Function
{
	public abstract double getAt(double x);
	public abstract Point2D getIntersection(Function function);
}
