package de.jcm.window.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GButton extends GComponent
{
	private String text;
	private Font font;
	
	private boolean pressed, hover;
	
	private ArrayList<GActionListener> listeners;
	
	public GButton(String text, Font font, int x, int y)
	{
		super(x, y);
		
		this.text=text;
		
		this.font=font;
		
		Rectangle2D string2d=this.font.getStringBounds(this.text, new FontRenderContext(new AffineTransform(), true, true));
		this.width=(int) string2d.getWidth();
		this.height=(int) string2d.getHeight();
		
		this.listeners=new ArrayList<>();
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
		Rectangle2D string2d=this.font.getStringBounds(this.text, new FontRenderContext(new AffineTransform(), true, true));
		this.width=(int) string2d.getWidth();
		this.height=(int) string2d.getHeight();
	}

	public Font getFont()
	{
		return font;
	}
	
	public boolean isMouseOver(int x, int y)
	{
		if(x>=this.x && x<=(this.x+this.width))
		{
			if(y>=this.y && y<=(this.y+this.height))
			{
				return true;
			}
		}
		return false;
	}
	
	protected void paintBorder(Graphics g)
	{
		if (isPressed())
			g.setColor(new Color(0, 84, 135));
		else if (isRollover())
			g.setColor(new Color(0, 120, 215));
		else
			g.setColor(new Color(173, 173, 173));

		g.drawRect(x, y, width, height);

		if (isPressed())
		{
			Graphics2D g2d = (Graphics2D) g.create();

			Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 1 }, 0);
			g2d.setStroke(dashed);
			g2d.drawRect(x, y, width, height);

			g2d.dispose();
		}
	}
	
	protected void paintComponent(Graphics g)
	{
		Font tFont=g.getFont();
		g.setFont(this.font);
		if (isPressed())
		{
			Color bg = new Color(204, 228, 247);
			g.setColor(bg);
			g.fillRect(x, y, width, height);

			g.setColor(Color.BLACK);

			//int tw = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getWidth();
			int th = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getHeight();

			g.drawString(text, x, y + th - (th/4));
		}
		else if (isRollover())
		{
			Color bg = new Color(228, 237, 248);
			g.setColor(bg);
			g.fillRect(x, y, width, height);

			g.setColor(Color.BLACK);

			//int tw = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getWidth();
			int th = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getHeight();

			g.drawString(text, x, y + th - (th/4));
		}
		else
		{
			Color bg = new Color(225, 225, 225);
			g.setColor(bg);
			g.fillRect(x, y, width, height);

			g.setColor(Color.BLACK);

			//int tw = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getWidth();
			int th = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getHeight();

			g.drawString(text, x, y + th - (th/4));
		}
		g.setFont(tFont);
	}

	public boolean isPressed()
	{
		return pressed;
	}
	
	public boolean handleMouse(MouseEvent e)
	{
		int x1=e.getX();
		int y1=e.getY();
		
		if(isMouseOver(x1, y1))
		{
			if(!hover)
			{
				hover=true;
				return true;
			}
			if(e.getButton()==MouseEvent.BUTTON1)
			{
				for (GActionListener gActionListener : listeners)
				{
					gActionListener.actionPerformed(this);
				}
				if(!pressed)
				{
					pressed=true;
					return true;
				}
			}
			if(e.getButton()==MouseEvent.NOBUTTON)
			{
				if(pressed)
				{
					pressed=false;
					return true;
				}
			}
		}
		else
		{
			if(hover)
			{
				hover=false;
				return true;
			}
			if(pressed)
			{
				pressed=false;
				return true;
			}
		}
		return false;
	}

	public boolean isRollover()
	{
		return hover;
	}
	
	public void addActionListener(GActionListener actionListener)
	{
		listeners.add(actionListener);
	}

	@Override
	protected boolean handleKey(KeyEvent e)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
