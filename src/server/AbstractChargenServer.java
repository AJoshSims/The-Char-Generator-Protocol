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
public abstract class AbstractChargenServer implements ChargenServer
{
	/**
	 * 
	 */
	private int port;
	
	/**
	 * 
	 */
	private ChargenSource<?> source;
	
	/**
	 * 
	 */
	protected AbstractChargenServer()
	{
		this(Utilities.CHARGEN_PORT_NUM, new DefactoSource());
	}
	
	/**
	 * 
	 * @param port
	 */
	protected AbstractChargenServer(int port)
	{
		this(port, new DefactoSource());
	}
	
	/**
	 * 
	 * @param source
	 */
	protected AbstractChargenServer(ChargenSource<?> source)
	{
		this(Utilities.CHARGEN_PORT_NUM, source);
	}
	
	/**
	 * 
	 * 
	 * @param port
	 * @param source
	 */
	protected AbstractChargenServer(int port, ChargenSource<?> source)
	{
		this.port = port;
		this.source = source;
	}
	
	/**
	 * 
	 * @return
	 */
	protected int getPort()
	{
		return port;
	}
	
	/**
	 * 
	 *
	 * @return
	 */
	protected ChargenSource<?> getSource()
	{
		return source;
	}
	
	/**
	 * 
	 * 
	 * @param source
	 */
	void changeSource(ChargenSource<?> source)
	{
		this.source = source;
	}
	
	/**
	 * 
	 */
	public abstract void listen();

}
