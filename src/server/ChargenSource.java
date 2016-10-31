package server;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 * 
 * @version 28 October 2016
 */
interface ChargenSource
{
	/**
	 * 
	 * 
	 * @return
	 */
	public Character next();
	
	/**
	 * 
	 * 
	 * @return
	 */
	public int itemsToSend();
}
