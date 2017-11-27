package de.jcm.window.graphics;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GScreen extends Screen
{
	private ArrayList<GComponent> components;
	private Graphics g;
	
	public GScreen(Graphics g)
	{
		this.components=new ArrayList<>();
		this.g=g;
	}
	
	public void add(GComponent component)
	{
		components.add(component);
	}
	
	public void remove(GComponent component)
	{
		components.remove(component);
	}
	
	public void removeAll()
	{
		components.clear();
	}
	
	@Override
	public void paint(Graphics g)
	{
		paintBackground(g);
		for (GComponent gComponent : components)
		{
			gComponent.paint(g);
		}
	}
	
	public void paintBackground(Graphics g)
	{
		
	}

	@Override
	public void mouseClicked(MouseEvent event)
	{
		for (GComponent gComponent : components)
		{
			if(gComponent.handleMouse(event))
				gComponent.paint(g);
		}
	}

	@Override
	public void mousePressed(MouseEvent event)
	{
		for (GComponent gComponent : components)
		{
			if(gComponent.handleMouse(event))
				gComponent.paint(g);
		}
	}

	@Override
	public void mouseReleased(MouseEvent event)
	{
		for (GComponent gComponent : components)
		{
			if(gComponent.handleMouse(event))
				gComponent.paint(g);
		}
	}

	@Override
	public void mouseMoved(MouseEvent event)
	{
		for (GComponent gComponent : components)
		{
			if(gComponent.handleMouse(event))
				gComponent.paint(g);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent event)
	{
		for (GComponent gComponent : components)
		{
			if(gComponent.handleKey(event))
				gComponent.paint(g);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent event)
	{	
		for (GComponent gComponent : components)
		{
			if(gComponent.handleKey(event))
				gComponent.paint(g);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent event)
	{
		for (GComponent gComponent : components)
		{
			if(gComponent.handleKey(event))
				gComponent.paint(g);
		}
	}
}
