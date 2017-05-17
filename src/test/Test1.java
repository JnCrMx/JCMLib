package test;

import de.jcm.math.vector.Vector2D;
import de.jcm.math.vector.helpers.VectorAngle;
import de.jcm.math.vector.helpers.VectorSplitter;

public class Test1 
{

	public static void main(String[] args) 
	{
		double y=10;
		double angle1=40;
		double angle2=50;
		
		Vector2D v2d=VectorAngle.createVector2D(0, y);

		dumpVectorSplit(v2d, VectorSplitter.splitVector2D(v2d, angle1, angle2));
		System.out.println();
		dumpVectorSplit(v2d, VectorSplitter.squareSplitVector2D(v2d, angle1, angle2));
	}

	private static void dumpVectorSplit(Vector2D v2d,Vector2D[] v2ds)
	{	
		Vector2D ges=new Vector2D(0, 0);
		
		for (int i=0;i<v2ds.length;i++)
		{
			Vector2D vector2d=v2ds[i];
			System.out.print("Lenght of vector "+i+" : ");
			System.out.println(VectorAngle.getValueByVector2D(vector2d));
			ges.add(vector2d);
		}
		
		System.out.print("Difference : ");
		System.out.println(VectorAngle.getValueByVector2D(ges.substract(v2d)));
	}
}
