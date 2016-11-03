package server;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 * 
 * @version 28 October 2016
 */
class NumericSource implements ChargenSource<Character>
{
	/**
	 * 
	 */
	public static final int NUM_OF_PRINTABLE_ASCII_N_CHARS = 10;
	
	/**
	 * 
	 */
	public static final int FIRST_PRINTABLE_ASCII_N_CHAR = 48;
	
	/**
	 * 
	 */
	public static final int LAST_PRINTABLE_ASCII_N_CHAR = 57;
	
	/**
	 * 
	 */
	private int nextCharToSend;
	
	/**
	 * 
	 */
	NumericSource()
	{
		nextCharToSend = FIRST_PRINTABLE_ASCII_N_CHAR;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	@Override
	public Character next()
	{
		char charToSend = (char) nextCharToSend;
		
		++nextCharToSend;
		if (nextCharToSend > LAST_PRINTABLE_ASCII_N_CHAR)
		{
			nextCharToSend = FIRST_PRINTABLE_ASCII_N_CHAR;
		}
		
		return new Character(charToSend);
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	@Override
	public int itemsToSend()
	{
		return NUM_OF_PRINTABLE_ASCII_N_CHARS;
	}
}
