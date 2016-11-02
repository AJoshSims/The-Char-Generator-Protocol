package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import common.InvalidNumOfArgsException;
import common.InvalidPortException;
import common.InvalidTransProtocolException;
import common.Utilities;

/**
 * 
 * 
 * <p>Usage: java client/ChargenClientDriver 
 * &lt;TCP/UDP&gt; &lt;host&gt; [&lt;port&gt;] [&lt;flag&gt;]
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 *
 * @version 27 October 2016
 */

public class ChargenClientDriver 
{		
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
	
	// Methods
	// TODO Make sure that all errors are thrown up to main and handled there.
	/**
	 * 
	 * 
	 * @param args - &lt;TCP/UDP&gt; &lt;host&gt; [&lt;port&gt;] [&lt;flag&gt;]
	 */
    public static void main(String args[]) 
    {
    	Utilities utilities = new Utilities();
    	
    	String[] examinedArgs = null;
    	try
    	{
    		examinedArgs = examineArgs(args);
    	}
    	catch (InvalidNumOfArgsException e)
    	{
    		utilities.exitCode = Utilities.INVALID_NUM_OF_ARGS_EXCEPTION;
    		System.err.println(e.getMessage());
    	}
    	catch (InvalidPortException e)
    	{
    		utilities.exitCode = Utilities.INVALID_PORT_EXCEPTION;
    		System.err.println(e.getMessage());
    	}
    	catch (InvalidTransProtocolException e)
    	{
    		utilities.exitCode = Utilities.INVALID_TRANS_PROTOCOL_EXCEPTION;
    		System.err.println(e.getMessage());
    	}
    	finally
    	{
    		if (utilities.exitCode != Utilities.NO_ERROR)
    		{
	    		printUsageMessageAndAbortProgram(utilities.exitCode);
    		}
    	}
    	
    	final String transProtocol = examinedArgs[INDEX_OF_TRANS_PROTOCOL];
    	final String host = examinedArgs[INDEX_OF_HOST];
    	final String port = examinedArgs[INDEX_OF_PORT];
    	final String flag = examinedArgs[INDEX_OF_FLAG];
    	
    	try
    	{
    		ChargenClient chargenClient = null;
    		InetAddress hostInetAddress = InetAddress.getByName(host);
    		int portInt = Integer.parseInt(port);
	    	switch (transProtocol)
	    	{
	    		case "TCP":
	    			chargenClient = new ChargenTcpClient(
						hostInetAddress, portInt);
	    			break;
	    		case "UDP":
	    			chargenClient = new ChargenUdpClient(
	    				hostInetAddress, portInt);
	    	}
	    	
	    	chargenClient.sendToHost(flag);
	    	
	    	chargenClient.printToStream(System.out);
	    	
	        chargenClient.closeResources();
    	}
        // The IP address of the server could not be determined.
        catch (UnknownHostException e) 
        {
        	utilities.exitCode = Utilities.UNKNOWN_HOST_EXCEPTION;
            System.err.println(
				"The IP address of \"" + host + "\" could not be determined.");
        } 
        // An IO exception occurred during the creation of the 
        // client to server communication resources.
        catch (IOException e) 
        {
        	utilities.exitCode = Utilities.IO_EXCEPTION;
            System.err.println(e.getMessage());
        } 
        // The security manager indicates that there is a security violation.
        catch (SecurityException e)
        {
        	utilities.exitCode = Utilities.SECURITY_EXCEPTION;
        	System.err.println(e.getMessage());
        }
    	finally
    	{
    		if (utilities.exitCode != Utilities.NO_ERROR)
    		{
	    		printUsageMessageAndAbortProgram(utilities.exitCode);
    		}
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
    	throws 
    	InvalidNumOfArgsException, 
    	InvalidTransProtocolException, 
    	InvalidPortException
    {	
    	// The default arguments.
    	// (Values of null are assigned as the default transport protocol and
    	// default host, however, the program will never use it because a 
    	// transport protocol and the host must be specified by the user.)
    	String[] examinedArgs = 
    		{null, null, "" + Utilities.CHARGEN_PORT_NUM, ""};
    		
    	String transProtocol = null;
    	String host = null;
    	String port = null;
    	String flag = null;
    	
    	// Assigns values to the program arguments based on what the user 
    	// specified via the command line.
    	switch (args.length)
    	{
    		// The user has specified a transport protocol, the host, a port, 
    		// and a flag.
    		case TRANS_PROTOCOL_AND_HOST_AND_PORT_AND_FLAG:
    			flag = args[INDEX_OF_FLAG];
    			
    			examinedArgs[INDEX_OF_FLAG] = flag;
    			
    		// The user has specified a host and either a flag or a port.
    		case TRANS_PROTOCOL_AND_HOST_AND_PORT_OR_FLAG:
    			port = args[INDEX_OF_PORT];
    			
    			// If the user specified a port, uses it.
    			if (Utilities.isValidPortNum(port.toCharArray()))
    			{
        			examinedArgs[INDEX_OF_PORT] = port;
    			}
    			// Must be the flag (not the port) so assign it as the flag.
    			else if (examinedArgs[INDEX_OF_FLAG] == "")
    			{
    				examinedArgs[INDEX_OF_FLAG] = port;
    			}
    			// The user has not specified a valid port and has already
    			// specified a flag, so aborts the program.
    			else
    			{
            		throw new InvalidPortException();
    			}
    			
    		// The user has specified only the transport protocol and the host.
    		case TRANS_PROTOCOL_AND_HOST:
    			transProtocol = args[INDEX_OF_TRANS_PROTOCOL].toUpperCase();
    			host = args[INDEX_OF_HOST];
    			
    			if (!(transProtocol.equals("TCP") 
    				|| transProtocol.equals("UDP")))
    			{
    				throw new InvalidTransProtocolException();
    			}
    			
    			examinedArgs[INDEX_OF_TRANS_PROTOCOL] = transProtocol;
    			examinedArgs[INDEX_OF_HOST] = host;
    			break;
    		
    		default:
    			throw new InvalidNumOfArgsException();
    	}
    	
    	return examinedArgs;
    }
    
    static void printUsageMessageAndAbortProgram(int exitCode)
    {
    	System.err.println(
			"Usage: java client/ChargenClientDriver " +
			"<TCP/UDP> <host> [<port>] [<flag>]" +
			"\nAborting program...");
    	System.exit(exitCode);
    }
}
