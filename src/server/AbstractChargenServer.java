package server;

import java.io.IOException;

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
	 * 
	 * @throws
	 */
	public abstract void listen() throws IOException;

}
