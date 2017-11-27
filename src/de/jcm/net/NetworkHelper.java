package de.jcm.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NetworkHelper
{
	/** Don't let anyone instantiate this class */
	private NetworkHelper() {}

	public static Boolean[] readBooleanArray(DataInputStream in) throws IOException
	{
		int count = in.readInt();
		Boolean[] booleans = new Boolean[count];

		for (int i = 0; i < count; i++)
		{
			booleans[i] = (Boolean) in.readBoolean();
		}

		return booleans;
	}

	public static Byte[] readByteArray(DataInputStream in) throws IOException
	{
		int count = in.readInt();
		Byte[] bytes = new Byte[count];

		for (int i = 0; i < count; i++)
		{
			bytes[i] = (Byte) in.readByte();
		}

		return bytes;
	}

	public static Character[] readCharArray(DataInputStream in) throws IOException
	{
		int count = in.readInt();
		Character[] chars = new Character[count];

		for (int i = 0; i < count; i++)
		{
			chars[i] = (Character) in.readChar();
		}

		return chars;
	}

	public static Double[] readDoubleArray(DataInputStream in) throws IOException
	{
		int count = in.readInt();
		Double[] doubles = new Double[count];

		for (int i = 0; i < count; i++)
		{
			doubles[i] = (Double) in.readDouble();
		}

		return doubles;
	}

	public static Object readField(DataInputStream in) throws IOException
	{
		Object field;

		byte type = in.readByte();

		if (type >= 10)
		{
			type = (byte) (type - 10);
			switch (type)
			{
			case 0:
				field = readBooleanArray(in);
				break;
			case 1:
				field = readByteArray(in);
				break;
			case 2:
				field = readCharArray(in);
				break;
			case 3:
				field = readIntArray(in);
				break;
			case 4:
				field = readLongArray(in);
				break;
			case 5:
				field = readFloatArray(in);
				break;
			case 6:
				field = readDoubleArray(in);
				break;
			case 7:
				field = readStringArray(in);
				break;

			default:
				field = null;
				break;
			}
		}
		else
		{
			switch (type)
			{
			case 0:
				field = (Boolean) in.readBoolean();
				break;
			case 1:
				field = (Byte) in.readByte();
				break;
			case 2:
				field = (Character) in.readChar();
				break;
			case 3:
				field = (Integer) in.readInt();
				break;
			case 4:
				field = (Long) in.readLong();
				break;
			case 5:
				field = (Float) in.readFloat();
				break;
			case 6:
				field = (Double) in.readDouble();
				break;
			case 7:
				field = (String) NetworkHelper.readString(in);
				break;

			default:
				field = null;
				break;
			}
		}

		return field;
	}

	public static Object[] readFields(DataInputStream in) throws IOException
	{
		int argumentCount = in.readInt();
		Object[] arguments = new Object[argumentCount];

		for (int i = 0; i < argumentCount; i++)
		{
			arguments[i] = readField(in);
		}

		return arguments;
	}

	public static Float[] readFloatArray(DataInputStream in) throws IOException
	{
		int count = in.readInt();
		Float[] floats = new Float[count];

		for (int i = 0; i < count; i++)
		{
			floats[i] = (Float) in.readFloat();
		}

		return floats;
	}

	public static Integer[] readIntArray(DataInputStream in) throws IOException
	{
		int count = in.readInt();
		Integer[] ints = new Integer[count];

		for (int i = 0; i < count; i++)
		{
			ints[i] = (Integer) in.readInt();
		}

		return ints;
	}

	public static Long[] readLongArray(DataInputStream in) throws IOException
	{
		int count = in.readInt();
		Long[] longs = new Long[count];

		for (int i = 0; i < count; i++)
		{
			longs[i] = (Long) in.readLong();
		}

		return longs;
	}

	public static String readString(DataInputStream input) throws IOException
	{
		int lenght = input.readInt();
		byte[] bytes = new byte[lenght];
		input.read(bytes, 0, lenght);

		return new String(bytes);
	}

	public static String readString(DataInputStream input, int lenght) throws IOException
	{
		byte[] bytes = new byte[lenght];
		input.read(bytes, 0, lenght);

		return new String(bytes);
	}

	public static String[] readStringArray(DataInputStream in) throws IOException
	{
		int count = in.readInt();
		String[] strings = new String[count];

		for (int i = 0; i < count; i++)
		{
			strings[i] = NetworkHelper.readString(in);
		}

		return strings;
	}

	public static void writeArray(DataOutputStream out, int type, Object[] array) throws IOException
	{
		out.writeByte(type);

		int size = array.length;
		out.writeInt(size);

		for (int i = 0; i < size; i++)
		{
			Object field = array[i];
			if (type == 10)
			{
				out.writeBoolean((Boolean) field);
			}
			else if (type == 11)
			{
				out.writeByte((Byte) field);
			}
			else if (type == 12)
			{
				out.writeChar((Character) field);
			}
			else if (type == 13)
			{
				out.writeInt((Integer) field);
			}
			else if (type == 14)
			{
				out.writeLong((Long) field);
			}
			else if (type == 15)
			{
				out.writeFloat((Float) field);
			}
			else if (type == 16)
			{
				out.writeDouble((Double) field);
			}
			else if (type == 17)
			{
				NetworkHelper.writeString(out, (String) field, StandardCharsets.UTF_8);
			}
		}
	}

	public static void writeField(DataOutputStream out, Object field) throws IOException
	{
		if (field != null)
		{
			if (field.getClass() == Boolean.class)
			{
				out.writeByte(0);
				out.writeBoolean((Boolean) field);
			}
			else if (field.getClass() == Byte.class)
			{
				out.writeByte(1);
				out.writeByte((Byte) field);
			}
			else if (field.getClass() == Character.class)
			{
				out.writeByte(2);
				out.writeChar((Character) field);
			}
			else if (field.getClass() == Integer.class)
			{
				out.writeByte(3);
				out.writeInt((Integer) field);
			}
			else if (field.getClass() == Long.class)
			{
				out.writeByte(4);
				out.writeLong((Long) field);
			}
			else if (field.getClass() == Float.class)
			{
				out.writeByte(5);
				out.writeFloat((Float) field);
			}
			else if (field.getClass() == Double.class)
			{
				out.writeByte(6);
				out.writeDouble((Double) field);
			}
			else if (field.getClass() == String.class)
			{
				out.writeByte(7);
				NetworkHelper.writeString(out, (String) field, StandardCharsets.UTF_8);
			}
			else if (field.getClass() == Boolean[].class)
			{
				writeArray(out, 10, ((Boolean[]) field));
			}
			else if (field.getClass() == Byte[].class)
			{
				writeArray(out, 11, ((Byte[]) field));
			}
			else if (field.getClass() == Character[].class)
			{
				writeArray(out, 12, ((Character[]) field));
			}
			else if (field.getClass() == Integer[].class)
			{
				writeArray(out, 13, ((Integer[]) field));
			}
			else if (field.getClass() == Long[].class)
			{
				writeArray(out, 14, ((Long[]) field));
			}
			else if (field.getClass() == Float[].class)
			{
				writeArray(out, 15, ((Float[]) field));
			}
			else if (field.getClass() == Double[].class)
			{
				writeArray(out, 16, ((Double[]) field));
			}
			else if (field.getClass() == String[].class)
			{
				writeArray(out, 17, ((String[]) field));
			}
			else
			{
				out.writeByte(-1);
			}
		}
		else
		{
			out.writeByte(-1);
		}
	}

	public static void writeFields(DataOutputStream out, Object[] fields) throws IOException
	{
		int count = fields.length;
		out.writeInt(count);

		for (int i = 0; i < count; i++)
		{
			writeField(out, fields[i]);
		}
	}

	public static void writeString(DataOutputStream out, String string, Charset charset) throws IOException
	{
		byte[] bytes = string.getBytes(charset);
		out.writeInt(bytes.length);
		out.write(bytes);
	}
}
