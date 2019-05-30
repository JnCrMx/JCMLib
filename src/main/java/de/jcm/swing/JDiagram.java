package de.jcm.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;

public class JDiagram extends JPanel
{
	public static enum ScaleMode
	{
		MOVE,
		SCALE
	}
	
	public static enum Type
	{
		LINE_XY
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int viewWidth;
	private LinkedHashMap<Float, Float> points = new LinkedHashMap<>();
	private Type type;
	private ScaleMode scaleMode;
	
	private Color axisColor;
	private Color dataColor;
	
	private String xUnit = "";
	private String yUnit = "";
	
	@Override
	protected void paintComponent(Graphics g)
	{		
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if(!points.isEmpty())
		{
			@SuppressWarnings("unchecked")
			Entry<Float, Float>[] entries = (Entry<Float, Float>[]) points.entrySet().toArray(new Entry<?,?>[points.size()]);
			if(scaleMode==ScaleMode.SCALE)
			{
				float maxX = entries[points.size()-1].getKey();
				
				float maxY = points.values().stream().max(new Comparator<Float>()
				{
					@Override
					public int compare(Float o1, Float o2)
					{
						if(o1==o2)
							return 0;
						if(o1<o2)
							return -1;
						if(o1>o2)
							return 1;
						return 0;
					}
				}).get();

				float scaleX = (float) ((getWidth()-20f)/(maxX));
				float scaleY = (float) ((getHeight()-20f)/(maxY));
				
				g.setColor(axisColor);
				g.drawLine(10, getHeight()-10, getWidth()-10, getHeight()-10);
				g.drawLine(10, 10, 10, getHeight()-10);
				
				g.drawString(Float.toString(maxY)+" "+yUnit, 0, 10);
				g.drawString(Float.toString(maxX)+" "+xUnit, getWidth()-g.getFontMetrics().stringWidth(Float.toString(maxX)+" "+xUnit), getHeight());
				
				int lastX = -1;
				int lastY = -1;

				g.setColor(dataColor);
				for(int i=0;i<entries.length;i++)
				{
					Entry<Float, Float> entry = entries[i];
					
					int x = 10 + (int)(entry.getKey()*scaleX);
					int y = getHeight() - 10 - (int)(entry.getValue()*scaleY);

					if(lastX>0 && lastY>0)
						g.drawLine(lastX, lastY, x, y);
					lastX=x;
					lastY=y;
				}
			}
		}
		
		//super.paintComponent(g);
	}
	
	/**
	 * Sets the visible area if using {@link ScaleMode#MOVE}.
	 * @param viewWidth visible x positions starting from last x point
	 */
	public void setViewWidth(int viewWidth)
	{
		this.viewWidth=viewWidth;
	}
	
	public void addPoint(float x, float y)
	{
		points.put(x, y);
	}

	public LinkedHashMap<Float, Float> getPoints()
	{
		return points;
	}

	public void setPoints(LinkedHashMap<Float, Float> points)
	{
		this.points = points;
	}

	public int getViewWidth()
	{
		return viewWidth;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public ScaleMode getScaleMode()
	{
		return scaleMode;
	}

	public void setScaleMode(ScaleMode scaleMode)
	{
		this.scaleMode = scaleMode;
	}

	public Color getAxisColor()
	{
		return axisColor;
	}

	public void setAxisColor(Color axisColor)
	{
		this.axisColor = axisColor;
	}

	public Color getDataColor()
	{
		return dataColor;
	}

	public void setDataColor(Color dataColor)
	{
		this.dataColor = dataColor;
	}

	public String getXUnit()
	{
		return xUnit;
	}

	public void setXUnit(String xUnit)
	{
		this.xUnit = xUnit;
	}

	public String getYUnit()
	{
		return yUnit;
	}

	public void setYUnit(String yUnit)
	{
		this.yUnit = yUnit;
	}
}
