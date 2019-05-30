package de.jcm.net.bridge;

public class Answer
{
	private boolean success;
	private Object response;
	private String error;

	public Answer(boolean success, Object response)
	{
		this.success = success;
		this.response = response;
		this.error = "";
	}

	public Answer(boolean success, Object response, String error)
	{
		this.success = success;
		this.response = response;
		this.error = error;
	}

	public String getError()
	{
		return error;
	}

	public Object getResponse()
	{
		return response;
	}

	public boolean isSuccess()
	{
		return success;
	}

}
