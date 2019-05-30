package de.jcm.image.compare;

import java.awt.image.BufferedImage;

import de.jcm.image.compare.ImageCompareProperties.IamgeCompareTransformMode;
import de.jcm.image.compare.ImageCompareProperties.ImageCompareMethod;

@Deprecated
public class ImageCompare
{
	private ImageCompareMethod method;

	@SuppressWarnings("unused")
	private boolean allowTranslate;
	@SuppressWarnings("unused")
	private boolean allowRotate;
	@SuppressWarnings("unused")
	private boolean allowScale;

	public ImageCompare(ImageCompareMethod method, byte transformMode)
	{
		System.out.println(Integer.toBinaryString(transformMode) + " & " + Integer.toBinaryString(7));

		allowTranslate = (transformMode & IamgeCompareTransformMode.ALLOW_TRANSLATE) - IamgeCompareTransformMode.ALLOW_TRANSLATE + 1 == 1;
		allowRotate = (transformMode & IamgeCompareTransformMode.ALLOW_ROTATE) - IamgeCompareTransformMode.ALLOW_ROTATE + 1 == 1;
		allowScale = (transformMode & IamgeCompareTransformMode.ALLOW_SCALE) - IamgeCompareTransformMode.ALLOW_SCALE + 1 == 1;

		this.method = method;
	}

	public BufferedImage difference(BufferedImage image1, BufferedImage image2)
	{
		BufferedImage img = new BufferedImage(image1.getWidth(), image1.getHeight(), image1.getType());

		if (method == ImageCompareMethod.PIXEL_BY_PIXEL)
		{
			for (int x = 0; x < img.getWidth(); x++)
			{
				for (int y = 0; y < img.getHeight(); y++)
				{
					img.setRGB(x, y, image1.getRGB(x, y) - image2.getRGB(x, y));
				}
			}
		}

		return img;
	}
}
