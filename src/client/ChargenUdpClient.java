package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author Evan Arroyo
 * @author Joshua Sims
 *
 * @version 10-24-16
 *
 */

//TODO Errors on line 17 with AbstractChargenClient and line 26 with getHost() and getPort()
public class ChargenUdpClient extends AbstractChargenClient 
{
	private DatagramSocket socket;
	
	public ChargenUdpClient(InetAddress host, int port)
		throws SocketException, SecurityException
	{
		super(host, port);
		
		socket = new DatagramSocket();
	}
	
	@Override
	public void sendToHost(String flag)
	{
		byte[] dataToSend = (flag + "\r\n").getBytes();
		
		DatagramPacket packetToSend = new DatagramPacket(
			dataToSend, 
			dataToSend.length,
			getHost(), getPort());
		
		boolean retry = false;
		do
		{
			try
			{
				socket.send(packetToSend);			
			}
			catch (IOException e)
			{
				retry = true;
			}
		}
		while (retry == true);
	}
	
	@Override
    public void printToStream(PrintStream out) 
    {
        byte[] responseData = new byte[513];
        DatagramPacket receivePacket = new DatagramPacket(
			responseData, responseData.length);

        boolean retry = false;
        do
        {
        	try
        	{
        		socket.receive(receivePacket);        		
        	}
        	catch (IOException e)
        	{
        		retry = true;
        	}
        }
        while (retry == true);

        String responseString = new String(receivePacket.getData());
        
        out.println(responseString);
        out.flush();
    }
	
	@Override
	public void closeResources()
	{
		socket.close();
	}
}
