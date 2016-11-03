package server;

import common.Utilities;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 * 
 * @version 28 October 2016
 */
class DefactoSource implements ChargenSource<Character>
{	
	/**
	 * 
	 */
	public static final int NUM_OF_PRINTABLE_ASCII_CHARS = 95;
	
	/**
	 * 
	 */
	public static final int FIRST_PRINTABLE_ASCII_CHAR = 32;
	
	/**
	 * 
	 */
	public static final int LAST_PRINTABLE_ASCII_CHAR = 126;
	
	/**
	 * 
	 */
	private int nextCharToSend;
	
	/**
	 * 
	 */
	DefactoSource()
	{
		nextCharToSend = FIRST_PRINTABLE_ASCII_CHAR;
	}
	
	/**
	 * 
	 */
	@Override
	public Character next()
	{
		char charToSend = (char) nextCharToSend;
		
		++nextCharToSend;
		if (nextCharToSend > LAST_PRINTABLE_ASCII_CHAR)
		{
			nextCharToSend = FIRST_PRINTABLE_ASCII_CHAR;
		}
		
		return new Character(charToSend);
	}
	
	/**
	 * 
	 */
	@Override
	public int itemsToSend()
	{
		return NUM_OF_PRINTABLE_ASCII_CHARS;
	}
}
