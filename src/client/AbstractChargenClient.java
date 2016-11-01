package client;

import java.io.PrintStream;
import java.net.InetAddress;

import common.Utilities;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 *
 * @version 24 October 2016
 */

abstract class AbstractChargenClient implements ChargenClient
{
    private InetAddress host;

    private int port;

    public AbstractChargenClient(InetAddress host, int port)
    {
    	this.host = host;
    	this.port = port;
    }

    public InetAddress getHost()
    {
        return host;
    }

    public int getPort()
    {
        return port;
    }
    
    public abstract void sendToHost(String flag);

    public abstract void printToStream(PrintStream out);
    
    public abstract void closeResources();
}
