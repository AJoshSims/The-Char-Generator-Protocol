package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 *
 * @version 24 October 2016
 */
public class ChargenTcpClient extends AbstractChargenClient
{
	private Socket socket;
	
	// Constructors
	protected ChargenTcpClient(InetAddress host, int port)
		throws IOException, SecurityException
	{
		super(host, port);
		
		socket = new Socket(host, port);
	}
	
	// Methods
	/**
	 * 
	 * 
	 * @param out - 
	 */
	@Override
	public void printToStream(PrintStream out)
	{
		
	}
	
	void closeSocket()
		throws IOException
	{
		socket.close();
	}

}
