package de.jcm.window.graphics;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class GComponent
{
	protected int x,y;
	protected int width, height;
	
	public GComponent(int x, int y)
	{
		this.x=x;
		this.y=y;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
	
	public void paint(Graphics g)
	{		
		paintBorder(g);
		paintComponent(g);
	}
	
	protected abstract void paintBorder(Graphics g);
	protected abstract void paintComponent(Graphics g);
	
	protected abstract boolean handleMouse(MouseEvent e);
	protected abstract boolean handleKey(KeyEvent e);
}
