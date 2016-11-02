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
					retry = true;
				}
			}
			while (retry == true);
			
			// TODO no declarations inside of loops >:(
			String receivedString = "message was " + new String(receivedPacket.getData());
			receivedString = receivedString.substring(0, receivedString.length() - 3);
			System.out.println("print result from server: " + receivedString);
			
			byte[] sentData = receivedString.getBytes();
	//		byte[] sentData = new byte[513];
			
			DatagramPacket sentPacket = new DatagramPacket(
				sentData, sentData.length, 
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
					retry = true;
				}
			}
			while (retry == true);
		}
	}
}
