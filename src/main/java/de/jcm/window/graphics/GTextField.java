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
import java.util.Arrays;

public class GTextField extends GComponent
{
	private String text;
	private Font font;
	
	private boolean focus;
	
	private int cursorPosition;
	
	public GTextField(String text, Font font, int lenght, int x, int y)
	{
		super(x, y);
		
		this.text=text;
		this.cursorPosition=text.length();
		
		this.font=font;
		
		char[] emptys=new char[lenght];
		Arrays.fill(emptys, ' ');
		Rectangle2D string2d=this.font.getStringBounds(new String(emptys), new FontRenderContext(new AffineTransform(), true, true));
		this.width=(int) string2d.getWidth();
		this.height=(int) string2d.getHeight();
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
		if (hasFocus())
			g.setColor(new Color(0, 84, 135));
		else
			g.setColor(new Color(173, 173, 173));

		g.drawRect(x, y, width, height);

		if (hasFocus())
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
		if (hasFocus())
		{
			Color bg = new Color(204, 228, 247);
			g.setColor(bg);
			g.fillRect(x, y, width, height);

			g.setColor(Color.BLACK);

			//int tw = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getWidth();
			int th = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getHeight();

			g.drawString(text, x, y + th - (th/4));
			
			char[] emptys=new char[1];
			Arrays.fill(emptys, ' ');
			Rectangle2D string2d=this.font.getStringBounds(new String(emptys), new FontRenderContext(new AffineTransform(), true, true));
			int a=(int) string2d.getWidth();
			int b=(int) string2d.getHeight();
			
			int xCursor=x+cursorPosition*a;
			g.drawLine(xCursor, y+5, xCursor, y+b-5);
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
	
	public boolean handleMouse(MouseEvent e)
	{
		int x1=e.getX();
		int y1=e.getY();
		
		if(isMouseOver(x1, y1))
		{
			if(e.getButton()==MouseEvent.BUTTON1)
			{
				if(!focus)
				{
					focus=true;
					cursorPosition=text.length();
					return true;
				}
			}
		}
		else
		{
			if(e.getButton()==MouseEvent.BUTTON1)
			{
				if(focus)
				{
					focus=false;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean handleKey(KeyEvent event)
	{
		if(hasFocus())
		{
			char ch=event.getKeyChar();			
			if(ch==KeyEvent.VK_BACK_SPACE)
			{
				if(text.length()>0 && cursorPosition>0)
				{
					String text1=text.substring(0, cursorPosition-1);
					String text2=text.substring(cursorPosition, text.length());
					text=text1+text2;
					
					cursorPosition--;
					
					return true;
				}
			}
			else if(ch==KeyEvent.VK_DELETE)
			{
				if(text.length()>0 && cursorPosition<text.length())
				{
					String text1=text.substring(0, cursorPosition);
					String text2=text.substring(cursorPosition+1, text.length());
					text=text1+text2;
					
					return true;
				}
			}
			else if(event.getKeyCode()==KeyEvent.VK_LEFT)
			{
				if(cursorPosition>0)
				{
					cursorPosition--;
					return true;
				}
			}
			else if(event.getKeyCode()==KeyEvent.VK_RIGHT)
			{
				if(cursorPosition<text.length())
				{
					cursorPosition++;
					return true;
				}
			}
			else if(ch!='\n' && !(event.isControlDown()) && Character.isDefined(ch))
			{
				String text1=text.substring(0, cursorPosition);
				String text2=text.substring(cursorPosition, text.length());
				text=text1+ch+text2;
				cursorPosition++;
				return true;
			}
		}
		return false;
	}

	public boolean hasFocus()
	{
		return focus;
	}
}
