package chargen;

/**
 * Thrown to indicate that an invalid number of arguments have been passed.
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 * 
 * @version 27 October 2016
 */
class InvalidNumOfArgsException extends Exception
{
	InvalidNumOfArgsException()
	{
		super("An invalid number of arguments have been passed.");
	}

	InvalidNumOfArgsException(String message)
	{
		super(message);
	}

	InvalidNumOfArgsException(Throwable cause)
	{
		super(cause);
	}

	InvalidNumOfArgsException(String message, Throwable cause)
	{
		super(message, cause);
	}

	InvalidNumOfArgsException(String message, Throwable cause, 
		boolean enableSuppression,
		boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
