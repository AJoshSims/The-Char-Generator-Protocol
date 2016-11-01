package server;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 * 
 * @version 28 October 2016
 */
public class ChargenUdpServer extends AbstractChargenServer
{	
	/**
	 * 
	 * 
	 * @param port
	 * @param source
	 */
	ChargenUdpServer(int port, ChargenSource<?> source)
	{
		super(port, source);
	}
	
	/**
	 * 
	 */
	@Override
	public void listen()
	{
		
	}
}
