package client;

import java.net.InetAddress;

import common.InvalidNumOfArgsException;
import common.InvalidPortException;

/**
 * 
 * 
 * <p>Usage: java chargen/ChargenClientDriver 
 * &lt;TCP/UDP&gt; &lt;host&gt; [&lt;port&gt;] [&lt;flag&gt;]
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 *
 * @version 27 October 2016
 */

public class ChargenClientDriver 
{
	// Error codes
	/**
	 * Error code indicating an invalid number of command line arguments were
	 * passed.
	 */
	private static final int INVALID_NUM_OF_ARGS = 1;
	
	/**
	 * Error code indicating an invalid port number was passed at the command
	 * line.
	 */
	private static final int INVALID_PORT_NUMBER = 2;
	
	// Port number information
	/**
	 * The lowest number that a port can assume.
	 */
	private static final int PORT_NUM_LOWER_BOUND = 0;
	
	/**
	 * The highest number that a port can assume.
	 */
	private static final int PORT_NUM_UPPER_BOUND = 65535;
	
	/**
	 * The number assumed by the Chargen port.
	 */
	static final int CHARGEN_PORT_NUM = 19;
	
	// Command line argument indices
	/**
	 * The index of the transport protocol command line argument.
	 */
	private static final int INDEX_OF_TRANS_PROTOCOL = 0;
	
	/**
	 * The index of the domain name command line argument.
	 */
	private static final int INDEX_OF_HOST = 1;
	
	/**
	 * The index of the port command line argument.
	 */
	private static final int INDEX_OF_PORT = 2;
	
	/**
	 * The index of the flag command line argument.
	 */
	private static final int INDEX_OF_FLAG = 3;
	
	// Possible number of command line arguments
	/**
	 * Indicates that only the transmission protocol (TCP or UDP) and the host 
	 * (the server) were specified.
	 */
	private static final int TRANS_PROTOCOL_AND_HOST = 2;
	
	/**
	 * Indicates that the transmission protocol (TCP or UDP), the host 
	 * (the server), and either a port or flag were specified.
	 */
	private static final int TRANS_PROTOCOL_AND_HOST_AND_PORT_OR_FLAG = 3;
	
	/**
	 * Indicates that the transmission protocol (TCP or UDP), the host 
	 * (the server), a port, and a flag were specified.
	 */
	private static final int TRANS_PROTOCOL_AND_HOST_AND_PORT_AND_FLAG = 4;
	
	// Miscellaneous constants
    /**
     * The length of an empty array.
     */
	private static final int EMPTY = 0;
	
	// Methods
	// TODO Make sure that all errors are thrown up to main and handled there.
	/**
	 * 
	 * 
	 * @param args - &lt;TCP/UDP&gt; &lt;host&gt; [&lt;port&gt;] [&lt;flag&gt;]
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
    		System.out.println(e.getMessage());
    		printUsageAndAbortMessage();
    		System.exit(INVALID_NUM_OF_ARGS);
    	}
    	
    	catch (InvalidPortException e)
    	{
    		System.out.println(e.getMessage());
    		printUsageAndAbortMessage();
    		System.exit(INVALID_PORT_NUMBER);
    	}
    	
    	final String transProtocol = examinedArgs[INDEX_OF_TRANS_PROTOCOL];
    	final String host = examinedArgs[INDEX_OF_HOST];
    	final String port = examinedArgs[INDEX_OF_PORT];
    	final String flag = examinedArgs[INDEX_OF_FLAG];
    	
    	// TODO May need a try catch block here
    	ChargenClient chargenClient = null;
    	switch (transProtocol)
    	{
    		case "UDP":
    			chargenClient = new ChargenUdpClient(
    					InetAddress.getByName(host), 		
    					Integer.parseInt(port));
    			break;
    		case "TCP":
    			chargenClient = new ChargenTcpClient(
    					InetAddress.getByName(host), 
    					Integer.parseInt(port));
    	}
    }
    
    /**
     * Examines the provided command line arguments for validity, assigns 19 
     * (the number assumed by the Chargen port) as the port number argument if 
     * no other value was provided, and assigns "" as the flag argument if no 
     * other value was provided.
     * 
	 * @param args - &lt;TCP/UDP&gt; &lt;host&gt; [&lt;port&gt;] [&lt;flag&gt;]
     */
    private static String[] examineArgs(String[] args) 
    	throws InvalidNumOfArgsException, InvalidPortException
    {	
    	// The default arguments.
    	// (Value of null are assigned as the default transport protocol and
    	// default host, however, the program will never use it because a 
    	// transport protocol and the host must be specified by the user.)
    	String[] examinedArgs = 
    		{null, null, "" + CHARGEN_PORT_NUM, ""};
    		
    	// Assigns values to the program arguments based on what the user 
    	// specified via the command line.
    	switch (args.length)
    	{
    		// The user has specified a transport protocol, the host, a port, 
    		// and a flag.
    		case TRANS_PROTOCOL_AND_HOST_AND_PORT_AND_FLAG:
    			examinedArgs[INDEX_OF_FLAG] = args[INDEX_OF_FLAG];
    			
    		// The user has specified a host and either a flag or a port.
    		case TRANS_PROTOCOL_AND_HOST_AND_PORT_OR_FLAG:
    			// If the user specified a port, uses it.
    			if (isValidPortNum(args[INDEX_OF_PORT].toCharArray()))
    			{
        			examinedArgs[INDEX_OF_PORT] = args[INDEX_OF_PORT];
    			}
    			// The user has specified a flag only once and has not
    			// specified a port
    			else if (examinedArgs[INDEX_OF_FLAG] == "")
    			{
    				examinedArgs[INDEX_OF_FLAG] = args[INDEX_OF_PORT];
    			}
    			// The user has not specified a valid port and has already
    			// specified a flag, so aborts the program.
    			else
    			{
            		throw new InvalidPortException();
    			}
    			
    		// The user has specified only the transport protocol and the host.
    		case TRANS_PROTOCOL_AND_HOST:
    			examinedArgs[INDEX_OF_TRANS_PROTOCOL] = 
    				args[INDEX_OF_TRANS_PROTOCOL];
    			examinedArgs[INDEX_OF_HOST] = 
					args[INDEX_OF_HOST];
    			break;
    		
    		default:
    			throw new InvalidNumOfArgsException();
    	}
    	
    	return examinedArgs;
    }
    
    /**
     * Determines whether or not the third command line argument is a valid 
     * port number.
     * 
     * @param maybePortNum - the third command line argument specified by
     *     the user
     * 
     * @return true if the third command line argument is a port
     *     number or false if otherwise
     */
    private static boolean isValidPortNum(char[] maybePortNum)
    {
    	// If the third command line argument is an empty string, it cannot
    	// be a port number.
    	if (maybePortNum.length == EMPTY)
    	{
    		return false;
    	}
    	
    	for (char character : maybePortNum)
    	{
    		// If part of the third command line argument is not a digit, 
    		// then the third command line argument cannot be a port number.
    		if (!Character.isDigit(character))
    		{
    			return false;
    		}
    	}
    	
        String portNumString = new String(maybePortNum);
        
        // Determines if specified the port number is valid (between 0 and
        // 65535).
    	int portNum = Integer.parseInt(portNumString);
    	if (portNum < PORT_NUM_LOWER_BOUND 
    		|| portNum > PORT_NUM_UPPER_BOUND)
    	{
    		return false;
    	}
    	
    	// The third command line argument must be a port number.
    	return true;
    }
    
    static void printUsageAndAbortMessage()
    {
    	System.out.println(
			"\nUsage: java chargen/ChargenClientDriver " +
			"<TCP/UDP> <host> [<port>] [<flag>]" +
			"\nAborting program...");
    }
}
