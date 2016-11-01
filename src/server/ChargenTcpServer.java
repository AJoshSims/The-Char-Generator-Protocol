package server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import common.Utilities;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 * 
 * @version 28 October 2016
 */
public class ChargenTcpServer extends AbstractChargenServer
{
	/**
	 * 
	 */
	ServerSocket welcomeSocket;
	
	/**
	 * 
	 * 
	 * @param port
	 * @param source
	 */
	ChargenTcpServer(int port, ChargenSource<?> source)
	{
		super(port, source);
	}
	
	/**
	 * 
	 */
	@Override
	public void listen()
	{
		Socket connectionSocket = null;
		// TODO Do not use infinite loop
		while (true)
		{
			connectionSocket = welcomeSocket.accept();
			
			DataOutputStream toClient = 
				new DataOutputStream(connectionSocket.getOutputStream());
			
			while ()
		}
	}
}
