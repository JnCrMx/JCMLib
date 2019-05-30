package de.jcm.test;

import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import de.jcm.math.geo.vector.Vector3D;
import de.jcm.math.geo.vector.helpers.VectorAngle;

class Vector3DTest
{
	
	@Test
	void rangeTest10()
	{
		for(double x=-10;x<10;x+=0.1)
		{
			for(double y=-10;y<10;y+=0.1)
			{
				for(double z=-10;z<10;z+=0.1)
				{
					Vector3D v1 = new Vector3D(x, y, z);
					double yRot = atan2(z, x);
						
					double b = sqrt(x*x+z*z);
						
					double zRot = atan2(y, b);
					
					Vector3D v2 = VectorAngle.createVector3D(yRot, zRot, sqrt(x*x+y*y+z*z));
						
					assertEquals(v1, v2);
				}	
			}
		}
	}
	
	@Test
	void randomTest()
	{
		Random random = new Random();
		for(int i=0;i<100*100*100;i++)
		{
			double x = random.nextDouble();
			double y = random.nextDouble();
			double z = random.nextDouble();
			
			Vector3D v1 = new Vector3D(x, y, z);
			double yRot = atan2(z, x);
				
			double b = sqrt(x*x+z*z);
				
			double zRot = atan2(y, b);
			
			Vector3D v2 = VectorAngle.createVector3D(yRot, zRot, sqrt(x*x+y*y+z*z));
				
			assertEquals(v1, v2);
		}
	}
}
