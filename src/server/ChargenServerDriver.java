package server;

import common.InvalidNumOfArgsException;
import common.InvalidPortException;
import common.InvalidTransProtocolException;
import common.Utilities;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 * 
 * @version 28 October 2016
 */
public class ChargenServerDriver
{
	// Possible number of command line arguments
	/**
	 * Indicates that only the transmission protocol (TCP or UDP) was 
	 * specified.
	 */
	private static final int TRANS_PROTOCOL = 1;
	
	/**
	 * Indicates that the transmission protocol (TCP or UDP) and a port were 
	 * specified.
	 */
	private static final int TRANS_PROTOCOL_AND_PORT = 2;

	// Command line argument indices
	/**
	 * The index of the transport protocol command line argument.
	 */
	private static final int INDEX_OF_TRANS_PROTOCOL = 0;
	
	/**
	 * The index of the port command line argument.
	 */
	private static final int INDEX_OF_PORT = 1;
	
	/**
	 * 
	 * 
	 * @param args - &lt;TCP/UDP&rt; [&lt;port&gt;]
	 */
	public static void main(String args[])
	{
		String[] examinedArgs = null;
		try
		{
			examinedArgs = examineArgs(args);
		}
		catch (InvalidNumOfArgsException e)
		{
			Utilities.exitCode = Utilities.INVALID_NUM_OF_ARGS_EXCEPTION;
			System.err.println(e.getMessage());
		}
		catch (InvalidTransProtocolException e)
		{
			Utilities.exitCode = Utilities.INVALID_TRANS_PROTOCOL_EXCEPTION;
			System.err.println(e.getMessage());
		}
		catch (InvalidPortException e)
		{
			Utilities.exitCode = Utilities.INVALID_PORT_EXCEPTION;
			System.err.println(e.getMessage());
		}
		
		final String transProtocol = examinedArgs[INDEX_OF_TRANS_PROTOCOL];
		final String port = examinedArgs[INDEX_OF_PORT];
		
		ChargenServer chargenServer = null;

		switch (transProtocol)
		{
			case "TCP":
				chargenServer = new ChargenTcpServer(Integer.parseInt(port));
				break;
//				case "UDP"
		}
		
		
	}
	
    /**
     * Examines the provided command line arguments for validity and assigns 19 
     * (the number assumed by the Chargen port) as the port number argument if 
     * no other value was provided.
     * 
	 * @param args - &lt;TCP/UDP&gt; &lt;host&gt; [&lt;port&gt;] [&lt;flag&gt;]
     */
    private static String[] examineArgs(String[] args) 
    	throws 
    	InvalidNumOfArgsException, 
    	InvalidTransProtocolException, 
    	InvalidPortException
    {	
    	// The default arguments.
    	// (Value of null is assigned as the default transport protocol,
    	// however, the program will never use it because a 
    	// transport protocol must be specified by the user.)
    	String[] examinedArgs = {null, "" + Utilities.CHARGEN_PORT_NUM};
    	
    	String transProtocol = null;
    	String port = null;
    	switch(args.length)
    	{
    		case TRANS_PROTOCOL_AND_PORT:
    			port = args[INDEX_OF_PORT];
    			
    			// If the user specified a port, uses it.
    			if (Utilities.isValidPortNum(port.toCharArray()))
    			{
        			examinedArgs[INDEX_OF_PORT] = port;
    			}
    			
    			else
    			{
    				throw new InvalidPortException();
    			}
    			break;
    			
    		case TRANS_PROTOCOL:
    			transProtocol = args[INDEX_OF_TRANS_PROTOCOL].toUpperCase();
    			
    			if (!(transProtocol.equals("TCP") 
    							|| transProtocol.equals("UDP")))
    			{
    				throw new InvalidTransProtocolException();
    			}
    			
    			examinedArgs[INDEX_OF_TRANS_PROTOCOL] = transProtocol;
    			break;
    			
			default:
				throw new InvalidNumOfArgsException();
    	}
    	
    	return examinedArgs;
    }
}
