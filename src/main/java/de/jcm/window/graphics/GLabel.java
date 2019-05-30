package de.jcm.window.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class GLabel extends GComponent
{
	private String text;
	private Font font;

	public GLabel(String text, Font font, int x, int y)
	{
		super(x, y);
		
		this.text=text;
		
		this.font=font;
		
		Rectangle2D string2d=this.font.getStringBounds(this.text, new FontRenderContext(new AffineTransform(), true, true));
		this.width=(int) string2d.getWidth();
		this.height=(int) string2d.getHeight();
	}

	@Override
	protected void paintBorder(Graphics g)
	{

	}

	@Override
	protected void paintComponent(Graphics g)
	{
		Font fFont=g.getFont();
		g.setFont(font);
		
		g.drawString(text, x, y);
		
		g.setFont(fFont);
	}

	@Override
	protected boolean handleMouse(MouseEvent e)
	{
		return false;
	}

	@Override
	protected boolean handleKey(KeyEvent e)
	{
		return false;
	}

}
