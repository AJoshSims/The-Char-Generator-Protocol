package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;

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
	// Constructors
	protected ChargenTcpClient(InetAddress host, int port)
		throws IOException, SecurityException
	{
		super(host, port);
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
		out.print("NAN");
		out.flush();
	}
}
