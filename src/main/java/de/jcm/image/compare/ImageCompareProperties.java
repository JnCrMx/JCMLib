package de.jcm.image.compare;

@Deprecated
public class ImageCompareProperties
{
	public static class IamgeCompareTransformMode
	{
		public static final int ALLOW_TRANSLATE = 0b00000001;
		public static final int ALLOW_ROTATE = 0b00000010;
		public static final int ALLOW_SCALE = 0b00000100;
	}

	public enum ImageCompareMethod
	{
		PIXEL_BY_PIXEL
	};
}
