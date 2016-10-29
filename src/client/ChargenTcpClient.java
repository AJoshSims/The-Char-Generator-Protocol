package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
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
class ChargenTcpClient extends AbstractChargenClient
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
	public ChargenTcpClient(InetAddress host, int port)
		throws IOException, SecurityException
	{
		super(host, port);
		
		socket = new Socket(getHost(), getPort());
        toHost = new PrintWriter(socket.getOutputStream());
        fromHost = new BufferedReader(new InputStreamReader(
        	socket.getInputStream()));
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
    	toHost.print(flag + "\r\n");
    	toHost.flush();
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
        		response = fromHost.readLine();
        	}
        	
        	catch (IOException e)
        	{
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
        	try 
        	{
        		toHost.close();
    			fromHost.close();
    			socket.close();
    		}
        	catch (IOException e) 
        	{
        		retry = true;
    		}
    	}
    	while (retry == true);
    }
}
