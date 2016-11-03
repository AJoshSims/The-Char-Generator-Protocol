package server;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 * 
 * @version 28 October 2016
 */
class AlphaNumericSource implements ChargenSource<Character>
{
	/**
	 * 
	 */
	public static final int NUM_OF_PRINTABLE_ASCII_AN_CHARS = 62;
	
	/**
	 * 
	 */
	public static final int FIRST_PRINTABLE_ASCII_AN_CHAR_GROUP_01 = 48;
	
	/**
	 * 
	 */
	public static final int LAST_PRINTABLE_ASCII_AN_CHAR_GROUP_01 = 57;
	
	/**
	 * 
	 */
	public static final int FIRST_PRINTABLE_ASCII_AN_CHAR_GROUP_02 = 65;

	/**
	 * 
	 */
	public static final int LAST_PRINTABLE_ASCII_AN_CHAR_GROUP_02 = 90;

	/**
	 * 
	 */
	public static final int FIRST_PRINTABLE_ASCII_AN_CHAR_GROUP_03 = 97;
	
	/**
	 * 
	 */
	public static final int LAST_PRINTABLE_ASCII_AN_CHAR_GROUP_03 = 122;
	
	/**
	 * 
	 */
	private int nextCharToSend;
	
	AlphaNumericSource()
	{
		nextCharToSend = FIRST_PRINTABLE_ASCII_AN_CHAR_GROUP_01;
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
		if (nextCharToSend > LAST_PRINTABLE_ASCII_AN_CHAR_GROUP_03)
		{
			nextCharToSend = FIRST_PRINTABLE_ASCII_AN_CHAR_GROUP_01;
		}
		else if (
			nextCharToSend > LAST_PRINTABLE_ASCII_AN_CHAR_GROUP_02
			&& nextCharToSend < FIRST_PRINTABLE_ASCII_AN_CHAR_GROUP_03)
		{
			nextCharToSend = FIRST_PRINTABLE_ASCII_AN_CHAR_GROUP_03;
		}
		else if (
			nextCharToSend > LAST_PRINTABLE_ASCII_AN_CHAR_GROUP_01
			&& nextCharToSend < FIRST_PRINTABLE_ASCII_AN_CHAR_GROUP_02)
		{
			nextCharToSend = FIRST_PRINTABLE_ASCII_AN_CHAR_GROUP_02;
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
		return NUM_OF_PRINTABLE_ASCII_AN_CHARS;
	}
}
