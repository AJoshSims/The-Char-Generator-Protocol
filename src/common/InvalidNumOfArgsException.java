package common;

/**
 * Thrown to indicate that an invalid number of arguments have been passed.
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 * 
 * @version 27 October 2016
 */
public class InvalidNumOfArgsException extends Exception
{
	public InvalidNumOfArgsException()
	{
		super("An invalid number of arguments have been passed.");
	}

	public InvalidNumOfArgsException(String message)
	{
		super(message);
	}

	public InvalidNumOfArgsException(Throwable cause)
	{
		super(cause);
	}

	public InvalidNumOfArgsException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public InvalidNumOfArgsException(String message, Throwable cause, 
		boolean enableSuppression,
		boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}