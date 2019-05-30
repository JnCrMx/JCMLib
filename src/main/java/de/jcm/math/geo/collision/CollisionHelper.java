package de.jcm.math.geo.collision;

import java.util.ArrayList;

import de.jcm.math.geo.Dimension;
import de.jcm.math.geo.Triangle2D;
import de.jcm.math.geo.Triangle3D;
import de.jcm.math.geo.TriangularMesh;
import de.jcm.math.geo.vector.Vector2D;
import de.jcm.math.geo.vector.Vector3D;
import de.jcm.math.geo.vector.helpers.VectorAngle;

public class CollisionHelper
{
	public static final boolean DEBUG = true;
	
	public static boolean containsPoint(TriangularMesh mesh, Vector3D point)
	{
		ArrayList<Triangle3D> errors = new ArrayList<>();
		for(Triangle3D triangle : mesh)
		{
			if(DEBUG)
				System.out.println("### Triangle: " + triangle + "###");
			Vector3D normal = triangle.getNormal();
			
			//get rotation of normal = triangle
			double normalRotY = normal.getRotationY();
			double normalRotZ = normal.getRotationZ();
			
			if(DEBUG)
			{
				System.out.print("Normal  : " + normal + " -> ");
				System.out.print("ry = " + Math.toDegrees(normalRotY));
				System.out.print(" rz = " + Math.toDegrees(normalRotZ));
			}
			
			//rotate the normal by its negative rotation => v3d<1.0 0.0 0.0>
			Vector3D newNormal = VectorAngle.createVector3D(normalRotY - normalRotY, normalRotZ - normalRotZ, 1);
			if(DEBUG)
				System.out.println(" => " + newNormal);
			
			//get rotation of point
			double pointRotY = point.getRotationY();
			double pointRotZ = point.getRotationZ();
			
			if(DEBUG)
			{
				System.out.print("Point   : " + point + " -> ");
				System.out.print("ry = " + Math.toDegrees(pointRotY));
				System.out.print(" rz = " + Math.toDegrees(pointRotZ));
			}
			//rotate the point by the normal's negative rotation -> "points" into the same direction (= rotate coordinate system)
			Vector3D newPoint =
					VectorAngle.createVector3D(pointRotY - normalRotY, pointRotZ - normalRotZ, point.getValue());
			if(DEBUG)
				System.out.println(" => " + newPoint);
			
			double x = 0;
			
			Triangle3D newTriangle = new Triangle3D(null, null, null);
			
			for(int i = 0; i < 3; i++)
			{
				Vector3D vector = triangle.getVertex(i);
				
				//get rotation of triangle's point i
				double vertexRotY = vector.getRotationY();
				double vertexRotZ = vector.getRotationZ();
				
				if(DEBUG)
				{
					System.out.print("Vertex#" + i + ": " + vector + " -> ");
					System.out.print("ry = " + Math.toDegrees(vertexRotY));
					System.out.print(" rz = " + Math.toDegrees(vertexRotZ));
				}
				
				//rotate triangle's point i by the normal's negative rotation -> "points" into the same direction (= rotate coordinate system)
				Vector3D newVertex =
						VectorAngle.createVector3D(vertexRotY - normalRotY, vertexRotZ - normalRotZ, vector.getValue());
				if(DEBUG)
					System.out.println(" => " + newVertex);
				
				//some errors might occur (x might be negative for some reason)
				if(newVertex.getX() > x)
					x = newVertex.getX();
				
				newTriangle.setVertex(i, new Vector3D(newVertex.getX(), newVertex.getY(), newVertex.getZ()));
			}
			
			//fix x for every new point
			final double xx = x;
			newTriangle.forEach(p->p.setX(xx));
			
			if(DEBUG)
				System.out.println("Triangle: "+newTriangle);
			
			Triangle2D newTriangle2d = newTriangle.removeDimension(Dimension.X);
			if(DEBUG)
				System.out.println("Triangle: "+newTriangle2d);
			
			Vector2D newPoint2d = newPoint.removeDimension(Dimension.X);
			if(DEBUG)
				System.out.println("Point   : "+newPoint2d);
			
			
			
			//check if point is more right (bigger x) than triangle -> not in triangle
			if(newPoint.getX() > x)
				errors.add(triangle);
		}
		
		System.out.println(errors);
		
		return errors.isEmpty();
	}
}
