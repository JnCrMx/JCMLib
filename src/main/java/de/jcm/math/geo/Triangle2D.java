package de.jcm.math.geo;

import java.util.Arrays;
import java.util.Iterator;

import de.jcm.math.geo.vector.Vector2D;

public class Triangle2D implements Iterable<Vector2D>
{
	private Vector2D vertices[] = new Vector2D[3];
	
	/**
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public Triangle2D(Vector2D v1, Vector2D v2, Vector2D v3)
	{
		this.vertices[0] = v1;
		this.vertices[1] = v2;
		this.vertices[2] = v3;
	}
	
	public Vector2D getVertex(int i)
	{
		return vertices[i];
	}
	
	public void setVertex(int i, Vector2D vertex)
	{
		this.vertices[i]=vertex; 
	}
	
	@Override
	public String toString()
	{
		return super.toString()+" v=["+Arrays.toString(vertices)+"]";
	}

	@Override
	public Iterator<Vector2D> iterator()
	{
		return Arrays.asList(vertices).iterator();
	}
}
