package de.jcm.test;

import de.jcm.math.geo.TriangularMesh;
import de.jcm.math.geo.collision.CollisionHelper;
import de.jcm.math.geo.vector.Vector3D;

import java.io.IOException;

public class TestCollision
{
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		TriangularMesh mesh = TriangularMesh.readFromStream(TestCollision.class.getResourceAsStream("/test_sphere.obj"));
		
		boolean result = CollisionHelper.containsPoint(mesh, new Vector3D(0.1, 0.1, 0.1));
		System.out.println(result);
	}
	
}
