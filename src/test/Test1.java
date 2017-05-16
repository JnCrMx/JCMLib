package test;

import de.jcm.math.vector.Vector2D;
import de.jcm.math.vector.helpers.VectorAngle;
import de.jcm.math.vector.helpers.VectorSplitter;

public class Test1 
{

	public static void main(String[] args) 
	{
		Vector2D v2d=VectorAngle.createVector2D(0, 10);
		
		Vector2D[] v2ds=VectorSplitter.splitVector2D(v2d, 1, 1);
		
		Vector2D ges=new Vector2D(0, 0);
		
		for (Vector2D vector2d : v2ds)
		{
			System.out.println(VectorAngle.getValueByVector2D(vector2d));
			ges.add(vector2d);
		}
		
		System.out.println(VectorAngle.getValueByVector2D(v2d.substract(ges)));
	}

}
