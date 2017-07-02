package de.jcm.net.bridge.server;

public interface AuthMethodToken
{
	public byte[] login(String username, String password);
	public boolean isTokenValid(byte[] token);
}
