package de.jcm.math.geo;

import java.util.Arrays;
import java.util.Iterator;

import de.jcm.math.geo.vector.Vector2D;
import de.jcm.math.geo.vector.Vector3D;

public class Triangle3D implements Iterable<Vector3D>
{
	private Vector3D vertices[] = new Vector3D[3];
	private Vector3D normal;
	
	/**
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public Triangle3D(Vector3D v1, Vector3D v2, Vector3D v3)
	{
		this.vertices[0] = v1;
		this.vertices[1] = v2;
		this.vertices[2] = v3;
	}
	
	/**
	 * @param v1
	 * @param v2
	 * @param v3
	 * @param normal
	 */
	public Triangle3D(Vector3D v1, Vector3D v2, Vector3D v3, Vector3D normal)
	{
		this(v1, v2, v3);
		this.normal=normal;
	}
	
	public Vector3D getVertex(int i)
	{
		return vertices[i];
	}
	
	public void setVertex(int i, Vector3D vertex)
	{
		this.vertices[i]=vertex; 
	}

	/**
	 * @return the normal
	 */
	public Vector3D getNormal()
	{
		return normal;
	}

	/**
	 * @param normal the normal to set
	 */
	public void setNormal(Vector3D normal)
	{
		this.normal = normal;
	}
	
	@Override
	public String toString()
	{
		return super.toString()+" v=["+Arrays.toString(vertices)+"]"+(normal!=null?(" n="+normal.toString()):"");
	}

	@Override
	public Iterator<Vector3D> iterator()
	{
		return Arrays.asList(vertices).iterator();
	}
	
	public Triangle2D removeDimension(Dimension dimension)
	{
		Vector2D[] points = new Vector2D[3];
		for(int i=0;i<3;i++)
		{
			points[i]=getVertex(i).removeDimension(dimension);
		}
		return new Triangle2D(points[0], points[1], points[2]);
	}
}
