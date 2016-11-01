package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
	ServerSocket welcome;
	
	/**
	 * The stream through which the server sends data to the client.
	 */
	private PrintWriter toClient;
	
	/**
	 * The stream through which the server reads data from the client.
	 */
	private BufferedReader fromClient;
	
	/**
	 * 
	 * 
	 * @param port
	 * @param source
	 */
	ChargenTcpServer(int port, ChargenSource<?> source)
		throws IOException
	{
		super(port, source);
		
		welcome = new ServerSocket(port);
	}
	
	/**
	 * 
	 */
	@Override
	public void listen()
		throws IOException
	{
		Socket connection = null;
		String receivedData = null;
		// TODO Do not use infinite loop
		do
		{
		connection = welcome.accept();
		toClient = new PrintWriter(connection.getOutputStream());
		fromClient = new BufferedReader(
			new InputStreamReader(connection.getInputStream()));
		
		receivedData = fromClient.readLine();
		
		// TODO do more with this
		System.out.println("Your message was " + receivedData);
		
		connection = null;
		}
		while (connection == null);
	}
}
