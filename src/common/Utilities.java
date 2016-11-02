package common;

public class Utilities
{
	// Port number information
	/**
	 * The lowest number that a port can assume.
	 */
	public static final int PORT_NUM_LOWER_BOUND = 0;
	
	/**
	 * The highest number that a port can assume.
	 */
	public static final int PORT_NUM_UPPER_BOUND = 65535;
	
	/**
	 * The number assumed by the Chargen port.
	 */
	public static final int CHARGEN_PORT_NUM = 19;
		
	// Exit codes
	/**
	 * Exit code indicating that the program terminated without error.
	 */
	public static final int NO_ERROR = 0;

	/**
	 * Exit code indicating that an invalid number of command line arguments 
	 * were passed.
	 */
	public static final int INVALID_NUM_OF_ARGS_EXCEPTION = 1;
	
	/**
	 * Exit code indicating that an invalid port was passed at the command 
	 * line.
	 */
	public static final int INVALID_PORT_EXCEPTION = 2;
	
	/**
	 * Exit code indicating that neither TCP nor UDP was specified as the
	 * transport layer protocol.
	 */
	public static final int INVALID_TRANS_PROTOCOL_EXCEPTION = 3;
	
	/**
	 * Exit code indicating that an IO exception occurred during the 
	 * creation/management of the client to server resources.
	 */
	public static final int IO_EXCEPTION = 4;
	
	/**
	 * Exit code indicating that the IP address of the server could not be
	 * determined.
	 */
	public static final int UNKNOWN_HOST_EXCEPTION = 5;
	
	/**
	 * Exit code indicating that the security manager has thrown an exception
	 * to indicate that a security violation has arisen from the attempt to 
	 * create the socket.
	 */
	public static final int SECURITY_EXCEPTION = 6;
	
	/**
	 * Exit code associated with the termination of a process of this program.
	 */
	public int exitCode = NO_ERROR;
	
	// Miscellaneous constants
    /**
     * The length of an empty array.
     */
	private static final int EMPTY = 0;
	
	// Methods
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
    public static boolean isValidPortNum(char[] maybePortNum)
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
}
