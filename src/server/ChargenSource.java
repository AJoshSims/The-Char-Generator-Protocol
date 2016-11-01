package server;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 * 
 * @version 28 October 2016
 */
interface ChargenSource<T>
{
	/**
	 * 
	 * 
	 * @return
	 */
	public T next();
	
	/**
	 * 
	 * 
	 * @return
	 */
	public int itemsToSend();
}
