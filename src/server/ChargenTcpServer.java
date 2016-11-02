package server;

import java.io.BufferedReader;
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
	
	protected ChargenTcpServer()
		throws IOException
	{
		this(Utilities.CHARGEN_PORT_NUM, new DefactoSource());
	}
	
	protected ChargenTcpServer(int port)
		throws IOException
	{
		this(port, new DefactoSource());
	}
	
	protected ChargenTcpServer(ChargenSource<?> source)
		throws IOException
	{
		this(Utilities.CHARGEN_PORT_NUM, source);
	}
	
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
	
	// TODO protect against IOException
	/**
	 * 
	 */
	@Override
	public void listen()
		throws IOException
	{
		Socket connection = null;
		// TODO Do not use infinite loop
		do
		{
		connection = welcome.accept();
		toClient = new PrintWriter(connection.getOutputStream());
		fromClient = new BufferedReader(
			new InputStreamReader(connection.getInputStream()));
		
		char[] charsReceived = new char[5];
		fromClient.read(charsReceived);
		
		boolean valid = false;
		int indexOfCurrentChar = 0;
		int indexOfNextChar = 0;
		final int indexOfLastChar = 5 - 1;
		for (
			indexOfCurrentChar = 0; 
			indexOfCurrentChar < indexOfLastChar;
			++indexOfCurrentChar)
		{
			indexOfNextChar = indexOfCurrentChar + 1;
			if (
				charsReceived[indexOfCurrentChar] == '\r'
				&& charsReceived[indexOfNextChar] == '\n')
			{
				valid = true;
				break;
			}
		}
		
		if (valid == true)
		{
			while (connection.getOutputStream() != null)
			{
				toClient.print(getSource().next());
			}
		}
		
		connection.close();
		}
		while (true);
	}
}
