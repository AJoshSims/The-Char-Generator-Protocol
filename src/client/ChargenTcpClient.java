package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;

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
	// Client to server communication resources
	/**
	 * The endpoint between the client and the server
	 */
	private Socket socket;
	
	/**
	 * The stream through which the client sends data to the server.
	 */
	private PrintWriter toHost;
	
	/**
	 * The stream through which the server sends data to the client.
	 */
	private BufferedReader fromHost;
	
	// Constructors
	protected ChargenTcpClient(InetAddress host, int port)
		throws UnknownHostException, IOException, SecurityException
	{
		super(host, port);
		
		try 
        {	
        	// Creates the streams and socket necessary to enable the client
        	// to server communication.
        	socket = new Socket(host, port);
            toHost = new PrintWriter(socket.getOutputStream());
            fromHost = new BufferedReader(new InputStreamReader(
            	socket.getInputStream()));
        }
		
        // The IP address of the server could not be determined.
        catch (UnknownHostException e) 
        {
            System.err.println("The IP address of the specified host, \"" + 
            	hostName + "\", could not be determined.");
            System.exit(UNKNOWN_HOST_EXCEPTION);
        } 
        // An IO exception occurred during the creation of the 
        // client to server communication resources.
        catch (IOException e) 
        {
            System.err.println(e.getMessage());
            System.exit(IO_EXCEPTION);
        } 
        // The security manager indicates that there is a security violation.
        catch (SecurityException e)
        {
        	System.err.println(e.getMessage());
        	System.exit(SECURITY_EXCEPTION);
        }
	}
	
	// Methods
	@Override
    public void printToStream(PrintStream out)
    {

    }
}
