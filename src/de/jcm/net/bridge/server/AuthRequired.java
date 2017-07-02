package de.jcm.net.bridge.server;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthRequired 
{
	AuthMethodType type();
	String auth();
	
	public static enum AuthMethodType
	{
		TOKEN,
		ALWAYS_AGAIN
	}
}
