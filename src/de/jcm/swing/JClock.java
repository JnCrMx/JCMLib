package de.jcm.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.util.GregorianCalendar;

import javax.swing.JComponent;

import de.jcm.math.geo.vector.Vector2D;
import de.jcm.math.geo.vector.helpers.VectorAngle;

public class JClock extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JClock()
	{
		setPreferredSize(new Dimension(250, 250));
		setSize(250, 250);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{		
		g.drawOval(0, 0, 250, 250);
		
		for(int z=1;z<=12;z++)
		{
			double a=z*30;
			Vector2D pos=VectorAngle.createVector2D(-a+180, 250/2-25);
			double dx=pos.getX();
			double dy=pos.getY();
			int x=(int) dx;
			int y=(int) dy;
			
			g.drawString(Integer.toString(z), 250/2+x-5, 250/2+y+5);
		}
		
		GregorianCalendar cal=new GregorianCalendar();

		g.setClip(new Ellipse2D.Double(25/2, 25/2, 225, 225));
		double sec=cal.get(GregorianCalendar.SECOND)+((double)cal.get(GregorianCalendar.MILLISECOND))/1000d;
		double secAngle=(sec/60)*360;
		Vector2D vSec=VectorAngle.createVector2D(-secAngle+180, 100000);
		g.drawLine(250/2, 250/2, (int)(vSec.getX()), (int)(vSec.getY()));
		
		
		g.setClip(new Ellipse2D.Double(50/2, 50/2, 200, 200));
		double min=cal.get(GregorianCalendar.MINUTE)+((double)cal.get(GregorianCalendar.SECOND))/60d;
		double minAngle=(min/60)*360;
		Vector2D vMin=VectorAngle.createVector2D(-minAngle+180, 100000);
		g.drawLine(250/2, 250/2, (int)vMin.getX(), (int)vMin.getY());
		

		g.setClip(new Ellipse2D.Double(75/2, 75/2, 175, 175));
		double hour=cal.get(GregorianCalendar.HOUR)+((double)cal.get(GregorianCalendar.MINUTE))/60d;
		double hourAngle=(hour/12)*360;
		Vector2D vHour=VectorAngle.createVector2D(-hourAngle+180, 100000);
		g.drawLine(250/2, 250/2, (int)vHour.getX(), (int)vHour.getY());
		
		
		repaint();
	}
}
