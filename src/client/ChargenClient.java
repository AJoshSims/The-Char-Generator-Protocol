package client;

import java.io.PrintStream;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 *
 * @version 24 October 2016
 *
 */

interface ChargenClient 
{
	public void sendToHost(String flag);
	
	public void printToStream(PrintStream out);
	
	public void closeResources();
}
