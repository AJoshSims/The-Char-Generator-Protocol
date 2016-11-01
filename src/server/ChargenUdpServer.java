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
	 */
	ChargenUdpServer()
	{
		super();
	}
	
	/**
	 * 
	 * 
	 * @param port
	 */
	ChargenUdpServer(int port)
	{
		super(port);
	}
	
	/**
	 * 
	 * 
	 * @param source
	 */
	ChargenUdpServer(ChargenSource<?> source)
	{
		super(source);
	}
	
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
