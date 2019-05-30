package de.jcm.util;

@FunctionalInterface
public interface Callback<R,P>
{
	public R call(P argument);
}
