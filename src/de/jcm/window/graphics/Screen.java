package de.jcm.window.graphics;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Screen
{
	public abstract void paint(Graphics g);

	public void mouseClicked(MouseEvent event)
	{
	}
	public void mousePressed(MouseEvent event)
	{
	}
	public void mouseReleased(MouseEvent event)
	{
	}
	public void mouseMoved(MouseEvent event)
	{
	}
	public void keyTyped(KeyEvent event)
	{
	}
	public void keyPressed(KeyEvent event)
	{
	}
	public void keyReleased(KeyEvent event)
	{
	}
}
