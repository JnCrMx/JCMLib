package de.jcm.math.functions;

import de.jcm.math.Point2D;

public class LinearFunction extends Function
{
	private double m;
	private double n;
	
	public LinearFunction(double m, double n)
	{
		this.m=m;
		this.n=n;
	}

	@Override
	public double getAt(double x) 
	{
		return (m*x)+n;
	}

	@Override
	public Point2D getIntersection(Function function) 
	{
		if(!(function instanceof LinearFunction))
			return null;
		
		LinearFunction f=(LinearFunction) function;
		
		double m1=this.m;
		double n1=this.n;
		
		double m2=f.m;
		double n2=f.n;
		
		double a=n2-n1;
		double b=m1-m2;
		
		double x=a/b;
		double y=this.getAt(x);
		
		return new Point2D(x, y);
	}
	
}
