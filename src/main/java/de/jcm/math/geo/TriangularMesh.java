package de.jcm.math.geo;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jcm.math.geo.vector.Vector3D;

public class TriangularMesh implements Iterable<Triangle3D>
{
	private List<Triangle3D> triangles;

	/**
	 * @param triangles
	 */
	public TriangularMesh(List<Triangle3D> triangles)
	{
		this.triangles = triangles;
	}

	/**
	 * @return the triangles
	 */
	public List<Triangle3D> getTriangles()
	{
		return triangles;
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean addTriangle(Triangle3D e)
	{
		return triangles.add(e);
	}

	@Override
	public Iterator<Triangle3D> iterator()
	{
		return triangles.iterator();
	}
	
	public static TriangularMesh readFromStream(InputStream in) throws NumberFormatException, IOException
	{
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));

		ArrayList<Vector3D> points = new ArrayList<>();
		ArrayList<Vector3D> normals = new ArrayList<>();
		ArrayList<Triangle3D> triangles = new ArrayList<>();
		
		while(reader.ready())
		{
			String line=reader.readLine();
			
			if(!line.startsWith("#"))
			{
				String key = line.substring(0, line.indexOf(' '));
				line=line.substring(line.indexOf(' ')+1);
				String[] arguments=line.split(" ");
				
				if(key.equals("o"))
				{
					
				}
				else if(key.equals("v"))
				{
					double x=Double.parseDouble(arguments[0]);
					double y=Double.parseDouble(arguments[1]);
					double z=Double.parseDouble(arguments[2]);
					
					points.add(new Vector3D(x, y, z));
				}
				else if(key.equals("vn"))
				{
					double x=Double.parseDouble(arguments[0]);
					double y=Double.parseDouble(arguments[1]);
					double z=Double.parseDouble(arguments[2]);
					
					normals.add(new Vector3D(x, y, z));
				}
				else if(key.equals("f"))
				{
					int[] pointIndices=new int[arguments.length];
					int[] normalIndices=new int[arguments.length];
					for(int i=0;i<arguments.length;i++)
					{				
						String arg=arguments[i];		
						if(arg.contains("//"))
						{
							String point=arg.substring(0, arg.indexOf("//"));
							String normal=arg.substring(arg.indexOf("//")+2, arg.length());
							
							pointIndices[i]=Integer.parseInt(point);
							normalIndices[i]=Integer.parseInt(normal);
						}
					}
					triangles.add(new Triangle3D(
							points.get(pointIndices[0]-1), 
							points.get(pointIndices[1]-1), 
							points.get(pointIndices[2]-1),
							normals.get(normalIndices[0]-1)));
				}
				else
				{
				}
			}
		}
		
		reader.close();
		
		return new TriangularMesh(triangles);
	}
}
