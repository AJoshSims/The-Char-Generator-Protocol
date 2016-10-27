package common;

/**
 * Thrown to indicate that an invalid port number has been passed.
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 * 
 * @version 27 October 2016
 */
public class InvalidPortException extends Exception 
{
	public InvalidPortException() 
	{
		super(
			"The specified port number is invalid." +
    		"\nYou must specify a port number between 0 and 65535.");
	}

	public InvalidPortException(String message) 
	{
		super(message);
	}

	public InvalidPortException(Throwable cause) 
	{
		super(cause);
	}

	public InvalidPortException(String message, Throwable cause) 
	{
		super(message, cause);
	}

	public InvalidPortException(String message, Throwable cause, 
		boolean enableSuppression, boolean writableStackTrace) 
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
