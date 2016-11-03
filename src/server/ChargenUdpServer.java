package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import common.Utilities;

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
	private static final int NUM_OF_CHARS_TO_SEND_UPPER_BOUND = 513;
	
	/**
	 * 
	 */
	DatagramSocket connection;
	
	protected ChargenUdpServer()
		throws SocketException
	{
		this(Utilities.CHARGEN_PORT_NUM, new DefactoSource());
	}
	
	protected ChargenUdpServer(int port)
		throws SocketException
	{
		this(port, new DefactoSource());
	}
	
	protected ChargenUdpServer(ChargenSource<?> source)
		throws SocketException
	{
		this(Utilities.CHARGEN_PORT_NUM, source);
	}
	
	/**
	 * 
	 * 
	 * @param port
	 * @param source
	 */
	protected ChargenUdpServer(int port, ChargenSource<?> source)
		throws SocketException
	{
		super(port, source);
		
		connection = new DatagramSocket(port);
	}
	
	/**
	 * 
	 */
	@Override
	public void listen()
	{
		byte[] receivedData = new byte[100];
		DatagramPacket receivedPacket = 
			new DatagramPacket(receivedData, receivedData.length);
		
		boolean retry = false;
		
		// TODO no infinite loops
		while (true)
		{
			do
			{
				retry = false;
				try
				{
					connection.receive(receivedPacket);
				}
				catch (IOException e)
				{
					System.err.println(
						e.getMessage() +
						"\n\nTrying to receive data from client...");
					retry = true;
				}
			}
			while (retry == true);
			
			String receivedString = new String(receivedPacket.getData());
			
			boolean valid = false;
			int indexOfCurrentChar = 0;
			int indexOfNextChar = 0;
			final int indexOfLastChar = receivedString.length() - 1;
			for (
				indexOfCurrentChar = 0; 
				indexOfCurrentChar < indexOfLastChar;
				++indexOfCurrentChar)
			{
				indexOfNextChar = indexOfCurrentChar + 1;
				if (
					receivedString.charAt(indexOfCurrentChar) == '\r'
					&& receivedString.charAt(indexOfNextChar) == '\n')
				{
					valid = true;
					break;
				}
			}
			
			if (valid == true)
			{
				String stringToSend = "";
				int numOfCharsToSend = 
					Utilities.RANDOM.nextInt(NUM_OF_CHARS_TO_SEND_UPPER_BOUND);
				
				int numOfCharsPrepared = 0;
				for (
					numOfCharsPrepared = 0; 
					numOfCharsPrepared < numOfCharsToSend - 2;
					++numOfCharsPrepared)
				{
					stringToSend += getSource().next();
					if ((numOfCharsPrepared % 72) == 0)
					{
						stringToSend += "\r\n";
						numOfCharsPrepared = numOfCharsPrepared + 2;
					}
				}
				
				if (numOfCharsPrepared < (numOfCharsToSend - 2))
				{
					stringToSend += "\r\n";
				}
				
				byte[] dataToSend = stringToSend.getBytes();
				DatagramPacket sentPacket = new DatagramPacket(
					dataToSend, dataToSend.length, 
					receivedPacket.getAddress(), receivedPacket.getPort());
				
				do
				{
					retry = false;
					try
					{
						connection.send(sentPacket);
					}
					catch (IOException e)
					{
						System.err.println(
							e.getMessage() +
							"\n\nTrying to send data to client...");
						retry = true;
					}
				}
				while (retry == true);	
			}
		}
	}
}
