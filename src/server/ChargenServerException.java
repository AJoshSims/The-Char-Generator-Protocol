package server;

public class ChargenServerException extends Exception
{
	public ChargenServerException()
	{
	}

	public ChargenServerException(String message)
	{
		super(message);
	}

	public ChargenServerException(Throwable cause)
	{
		super(cause);
	}

	public ChargenServerException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ChargenServerException(String message, Throwable cause,
					boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
