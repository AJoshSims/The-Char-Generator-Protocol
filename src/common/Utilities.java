package common;

public class Utilities
{
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
	public static final int CHARGEN_PORT_NUM = 19;
	
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
