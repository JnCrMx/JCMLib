package de.jcm.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public interface Byteable
{
	public ByteBuffer write(ByteOrder order);
	public void read(ByteBuffer buffer);
}
