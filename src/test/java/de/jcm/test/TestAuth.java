package de.jcm.test;

import de.jcm.net.bridge.server.AuthMethod;

public class TestAuth implements AuthMethod
{

	@Override
	public boolean auth(Object[] arguments)
	{		
		String username = arguments[0].toString();
		String password = arguments[1].toString();

		System.out.println("Auth:");
		System.out.println("\tUsername: " + username);
		System.out.println("\tPassword: " + password);
		
		return true;
	}

}
