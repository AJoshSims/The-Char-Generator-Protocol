package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

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
	
	/**
	 * 
	 * 
	 * @param port
	 * @param source
	 */
	ChargenUdpServer(int port, ChargenSource<?> source)
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
		byte[] receivedData = new byte[3];
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
			
			String receivedString = "yo";
			
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
