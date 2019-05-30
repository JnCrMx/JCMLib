package de.jcm.test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import de.jcm.image.compare.ImageCompare;
import de.jcm.image.compare.ImageCompareProperties.ImageCompareMethod;

@SuppressWarnings("deprecation")
public class Test2
{

	public static void main(String[] args) throws Exception
	{
		ImageCompare compare = new ImageCompare(ImageCompareMethod.PIXEL_BY_PIXEL, (byte) 3);

		BufferedImage img1 = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < img1.getWidth(); x++)
		{
			for (int y = 0; y < img1.getHeight(); y++)
			{
				img1.setRGB(x, y, new Color(x, y, 0).getRGB());
			}
		}
		ImageIO.write(img1, "png", new File("img1.png"));

		BufferedImage img2 = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < img2.getWidth(); x++)
		{
			for (int y = 0; y < img2.getHeight() - 5; y++)
			{
				img2.setRGB(x, y + 5, new Color(x, y, 0).getRGB());
			}
		}
		ImageIO.write(img2, "png", new File("img2.png"));

		ImageIO.write(compare.difference(img1, img2), "png", new File("diff.png"));
	}

}
