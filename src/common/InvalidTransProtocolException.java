package common;

public class InvalidTransProtocolException extends Exception
{
	public InvalidTransProtocolException()
	{
		super("The specified transport layer protocol is invalid." +
			"\nYou must specify \"TCP\" or \"UDP\" " + 
			"as the transport layer protocol.");
	}
	
	public InvalidTransProtocolException(String message)
	{
		super(message);
	}

	public InvalidTransProtocolException(Throwable cause)
	{
		super(cause);
	}

	public InvalidTransProtocolException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public InvalidTransProtocolException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
