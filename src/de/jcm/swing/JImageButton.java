package de.jcm.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;

import javax.swing.ButtonModel;
import javax.swing.JButton;

public class JImageButton extends JButton
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image image;
	private String text;

	public JImageButton(Image image, String text)
	{
		this.image = image;
		this.text = text;

		setContentAreaFilled(false);
	}

	@Override
	public boolean isFocusable()
	{
		return true;
	}

	@Override
	protected void paintBorder(Graphics g)
	{
		ButtonModel model = this.getModel();

		int w = getSize().width;
		int h = getSize().height;

		if (model.isPressed())
			g.setColor(new Color(0, 84, 135));
		else if (model.isRollover())
			g.setColor(new Color(0, 120, 215));
		else
			g.setColor(new Color(173, 173, 173));

		g.drawRect(1, 1, w - 3, h - 3);

		if (model.isPressed())
		{
			Graphics2D g2d = (Graphics2D) g.create();

			Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 1 }, 0);
			g2d.setStroke(dashed);
			g2d.drawRect(3, 3, w - 7, h - 7);

			g2d.dispose();
		}
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		ButtonModel model = this.getModel();

		int w = getSize().width;
		int h = getSize().height;

		if (model.isPressed())
		{
			Color bg = new Color(204, 228, 247);
			g.setColor(bg);
			g.fillRect(1, 1, w - 3, h - 3);

			g.setColor(getForeground());

			int x = w / 2 - ((int) (h / 1.7)) / 2;
			int y = 10;

			g.drawImage(image, x, y, (int) (h / 1.7), (int) (h / 1.7), null);

			int tw = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getWidth();
			int th = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getHeight();

			g.drawString(text, w / 2 - tw / 2, y + (int) (h / 1.7) + th);
		}
		else if (model.isRollover())
		{
			Color bg = new Color(228, 237, 248);
			g.setColor(bg);
			g.fillRect(1, 1, w - 3, h - 3);

			g.setColor(getForeground());

			int x = w / 2 - ((int) (h / 1.7)) / 2;
			int y = 10;

			g.drawImage(image, x, y, (int) (h / 1.7), (int) (h / 1.7), null);

			int tw = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getWidth();
			int th = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getHeight();

			g.drawString(text, w / 2 - tw / 2, y + (int) (h / 1.7) + th);
		}
		else
		{
			Color bg = new Color(225, 225, 225);
			g.setColor(bg);
			g.fillRect(1, 1, w - 3, h - 3);

			g.setColor(getForeground());

			int x = w / 2 - ((int) (h / 1.7)) / 2;
			int y = 10;

			g.drawImage(image, x, y, (int) (h / 1.7), (int) (h / 1.7), null);

			int tw = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getWidth();
			int th = (int) g.getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getHeight();

			g.drawString(text, w / 2 - tw / 2, y + (int) (h / 1.7) + th);
		}
	}
}
