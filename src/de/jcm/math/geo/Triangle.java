package de.jcm.math.geo;

public class Triangle
{
	private Point3D vertices[] = new Point3D[3];
	private Point3D normal;
	
	/**
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public Triangle(Point3D v1, Point3D v2, Point3D v3)
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
	public Triangle(Point3D v1, Point3D v2, Point3D v3, Point3D normal)
	{
		this(v1, v2, v3);
		this.normal=normal;
	}
	
	public Point3D getVertex(int i)
	{
		return vertices[i];
	}
	
	public void setVertex(int i, Point3D vertex)
	{
		this.vertices[i]=vertex; 
	}

	/**
	 * @return the normal
	 */
	public Point3D getNormal()
	{
		return normal;
	}

	/**
	 * @param normal the normal to set
	 */
	public void setNormal(Point3D normal)
	{
		this.normal = normal;
	}
}
