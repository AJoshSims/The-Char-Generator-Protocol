package server;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 * 
 * @version 28 October 2016
 */
class NonAlphaNumericSource implements ChargenSource<Character>
{	
	/**
	 * 
	 */
	public static final int NUM_OF_PRINTABLE_ASCII_NAN_CHARS = 33;
	
	/**
	 * 
	 */
	public static final int FIRST_PRINTABLE_ASCII_NAN_CHAR_GROUP_01 = 32;
	
	/**
	 * 
	 */
	public static final int LAST_PRINTABLE_ASCII_NAN_CHAR_GROUP_01 = 47;
	
	/**
	 * 
	 */
	public static final int FIRST_PRINTABLE_ASCII_NAN_CHAR_GROUP_02 = 58;
	
	/**
	 * 
	 */
	public static final int LAST_PRINTABLE_ASCII_NAN_CHAR_GROUP_02 = 64;
	
	/**
	 * 
	 */
	public static final int FIRST_PRINTABLE_ASCII_NAN_CHAR_GROUP_03 = 91;
	
	/**
	 * 
	 */
	public static final int LAST_PRINTABLE_ASCII_NAN_CHAR_GROUP_03 = 96;
	
	/**
	 * 
	 */
	public static final int FIRST_PRINTABLE_ASCII_NAN_CHAR_GROUP_04 = 123;
	
	/**
	 * 
	 */
	public static final int LAST_PRINTABLE_ASCII_NAN_CHAR_GROUP_04 = 126;
	
	/**
	 * 
	 */
	private int nextCharToSend;
	
	NonAlphaNumericSource()
	{
		nextCharToSend = FIRST_PRINTABLE_ASCII_NAN_CHAR_GROUP_01;
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
		if (nextCharToSend > LAST_PRINTABLE_ASCII_NAN_CHAR_GROUP_04)
		{
			nextCharToSend = FIRST_PRINTABLE_ASCII_NAN_CHAR_GROUP_01;
		}
		else if (
			nextCharToSend > LAST_PRINTABLE_ASCII_NAN_CHAR_GROUP_03
			&& nextCharToSend < FIRST_PRINTABLE_ASCII_NAN_CHAR_GROUP_04)
		{
			nextCharToSend = FIRST_PRINTABLE_ASCII_NAN_CHAR_GROUP_04;
		}
		else if (
			nextCharToSend > LAST_PRINTABLE_ASCII_NAN_CHAR_GROUP_02
			&& nextCharToSend < FIRST_PRINTABLE_ASCII_NAN_CHAR_GROUP_03)
		{
			nextCharToSend = FIRST_PRINTABLE_ASCII_NAN_CHAR_GROUP_03;
		}
		else if (
			nextCharToSend > LAST_PRINTABLE_ASCII_NAN_CHAR_GROUP_01
			&& nextCharToSend < FIRST_PRINTABLE_ASCII_NAN_CHAR_GROUP_02)
		{
			nextCharToSend = FIRST_PRINTABLE_ASCII_NAN_CHAR_GROUP_02;
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
		return NUM_OF_PRINTABLE_ASCII_NAN_CHARS;
	}
}
