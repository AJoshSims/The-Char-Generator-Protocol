package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import common.Utilities;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 *
 * @version 24 October 2016
 */

public class ChargenUdpClient extends AbstractChargenClient 
{
	private Utilities utilities;
	
	private DatagramSocket connection;
	
	public ChargenUdpClient(InetAddress host)
		throws SocketException, SecurityException
	{	
		this(host, Utilities.CHARGEN_PORT_NUM);
	}
	
	public ChargenUdpClient(InetAddress host, int port)
		throws SocketException, SecurityException
	{
		super(host, port);
		
		connection = new DatagramSocket();
		
		utilities = new Utilities();
	}
	
	@Override
	public void sendToHost(String flag)
	{
		byte[] sentData = (flag + "\r\n").getBytes();
		
		DatagramPacket sentPacket = new DatagramPacket(
			sentData, 
			sentData.length,
			getHost(), getPort());
		
		boolean retry = false;
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
					"\n\nTrying to send data to server...");
				retry = true;
			}
		}
		while (retry == true);
	}
	
	@Override
    public void printToStream(PrintStream out) 
    {
        byte[] receivedData = new byte[1000];
        DatagramPacket receivedPacket = new DatagramPacket(
			receivedData, receivedData.length);

        boolean retry = false;
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
        			"\n\nTrying to receive data from server...");
        		retry = true;
        	}
        }
        while (retry == true);

        String receivedString = new String(receivedPacket.getData());
        
        out.println(receivedString);
        out.flush();
    }
	
	@Override
	public void closeResources()
	{
		connection.close();
	}
}
