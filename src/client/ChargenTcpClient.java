package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import common.Utilities;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 *
 * @version 24 October 2016
 */
class ChargenTcpClient extends AbstractChargenClient
{	
	// Client to server communication resources
	/**
	 * The connection between the client and the server.
	 */
	private Socket connection;
	
	/**
	 * The stream through which the client sends data to the server.
	 */
	private PrintWriter toServer;
	
	/**
	 * The stream through which the client reads data from the server.
	 */
	private BufferedReader fromServer;
	
	// Constructors
	
	public ChargenTcpClient(InetAddress host)
		throws IOException, SecurityException
	{
		this(host, Utilities.CHARGEN_PORT_NUM);
	}
	
	public ChargenTcpClient(InetAddress host, int port)
		throws IOException, SecurityException
	{
		super(host, port);
		
		connection = new Socket(getHost(), getPort());
        toServer = new PrintWriter(connection.getOutputStream());
        fromServer = new BufferedReader(new InputStreamReader(
        	connection.getInputStream()));
	}
	
	// Methods
    /**
     * 
     * 
     * @param flag -
     */
	@Override
    public void sendToHost(String flag)
    {
    	toServer.print(flag + "\r\n");
    	toServer.flush();
    }
	
	/**
	 * 
	 * 
	 * @param out - 
	 */
	@Override
	public void printToStream(PrintStream out)
	{
        String response = "";
        do 
        {
        	out.println(response);
        	out.flush();
        	
        	try 
        	{
        		response = fromServer.readLine();
        	}
        	
        	catch (IOException e)
        	{
        		System.err.println(
        			e.getMessage() +
					"\n\nTrying to receive data from server...");
        		response = "";
        	}
        }   
        while (response != null);    
	}
    
    /**
     * 
     */
	@Override
    public void closeResources()
    {
    	boolean retry = false;
    	do
    	{
    		retry = false;
        	try 
        	{
        		toServer.close();
    			fromServer.close();
    			connection.close();
    		}
        	catch (IOException e) 
        	{
        		System.err.println(
					e.getMessage() +
					"\n\nTrying to close client-side resources...");
        		retry = true;
    		}
    	}
    	while (retry == true);
    }
}
