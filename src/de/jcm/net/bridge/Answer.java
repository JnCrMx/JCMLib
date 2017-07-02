package de.jcm.net.bridge;

public class Answer
{
	private boolean success;
	private Object response;
	private String error;

	public Answer(boolean success, Object response, String error)
	{
		this.success = success;
		this.response = response;
		this.error = error;
	}

	public Answer(boolean success, Object response)
	{
		this.success = success;
		this.response = response;
		this.error = "";
	}

	public boolean isSuccess()
	{
		return success;
	}

	public Object getResponse()
	{
		return response;
	}

	public String getError()
	{
		return error;
	}

}
