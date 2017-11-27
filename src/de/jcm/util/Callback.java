package de.jcm.util;

public interface Callback<R,P>
{
	public R call(P argument);
}
