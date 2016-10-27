package client;
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

abstract class AbstractChargenClient implements ChargenClient
{
    private InetAddress host;

    private int port;

    protected AbstractChargenClient(InetAddress host)
    {
    	this(host, ChargenClientDriver.CHARGEN_PORT_NUM);
    }

    protected AbstractChargenClient(InetAddress host, int port)
    {
    	this.host = host;
    	this.port = port;
    }

    protected InetAddress getHost()
    {
        return host;
    }

    protected int getPort()
    {
        return port;
    }

    @Override
    public void printToStream(PrintStream out)
    {
    	
    }
}
