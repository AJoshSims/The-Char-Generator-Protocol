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
	 * 
	 * @return
	 */
	@Override
	public Character next()
	{
		return new Character((char) 12);
	}
	
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
