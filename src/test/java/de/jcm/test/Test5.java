package de.jcm.test;

import de.jcm.math.geo.vector.Vector2D;
import de.jcm.math.geo.vector.helpers.VectorAngle;
import de.jcm.math.geo.vector.helpers.VectorSplitter;

public class Test5
{

	public static void main(String[] args)
	{
		Vector2D v2d=new Vector2D(0, 100);
		long time1 = System.nanoTime();
		
		for(int i=0;i<10000;i++)
		{
			VectorSplitter.splitVector2D(v2d, 30, 60);
		}
		
		long time2 = System.nanoTime();
		long difference=time2-time1;
		
		double diff=difference;
		double millis=diff/1000000;
		double secs=millis/1000;
		
		System.out.println("The operation took "+difference+" nanos ("+millis+" millis / "+secs+" seconds)");
		
		dumpVectorSplit(v2d, VectorSplitter.splitVector2D(v2d, 30, 60));
	}

	private static void dumpVectorSplit(Vector2D v2d, Vector2D[] v2ds)
	{
		Vector2D ges = new Vector2D(0, 0);

		for (int i = 0; i < v2ds.length; i++)
		{
			Vector2D vector2d = v2ds[i];
			
			System.out.println("Vector dump:");
			System.out.println("\t"+"X="+vector2d.getX()+" Y="+vector2d.getY());
			
			ges.add(vector2d);
		}

		System.out.print("Difference=");
		System.out.println(VectorAngle.getValueByVector2D(ges.substract(v2d)));
	}
}
