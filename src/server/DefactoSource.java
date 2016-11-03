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
	private int nextCharToSend;
	
	/**
	 * 
	 */
	DefactoSource()
	{
		nextCharToSend = Utilities.FIRST_PRINTABLE_ASCII_CHAR;
	}
	
	/**
	 * 
	 */
	@Override
	public Character next()
	{
		char charToSend = (char) nextCharToSend;
		
		++nextCharToSend;
		if (nextCharToSend > Utilities.LAST_PRINTABLE_ASCII_CHAR)
		{
			nextCharToSend = Utilities.FIRST_PRINTABLE_ASCII_CHAR;
		}
		
		return new Character(charToSend);
	}
	
	/**
	 * 
	 */
	@Override
	public int itemsToSend()
	{
		return Utilities.NUM_OF_PRINTABLE_ASCII_CHARS;
	}
}
