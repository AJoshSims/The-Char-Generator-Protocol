package server;

import java.util.Random;

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
	static final Random RANDOM_INT_GENERATOR = new Random();
	
	/**
	 * 
	 * 
	 * @return
	 */
	@Override
	public Character next()
	{
		return new Character((char) (RANDOM_INT_GENERATOR.nextInt(95) + 32));
	}
	
	// TODO I think that this is used for formatting of server response
	/**
	 * 
	 * 
	 * @return
	 */
	@Override
	public int itemsToSend()
	{
		return -1;
	}
}
