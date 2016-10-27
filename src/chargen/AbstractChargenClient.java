package chargen;
import java.io.PrintStream;
import java.net.InetAddress;


/**
 * @author Evan Arroyo
 * @author Joshua Sims
 *
 * @version 10-24-16
 *
 */

public class AbstractChargenClient implements ChargenClient {

    private InetAddress host;

    private int port;

    public AbstractChargenClient(){

    }

    public AbstractChargenClient(InetAddress host){

    }

    public AbstractChargenClient(InetAddress host, int port){

    }

    public InetAddress getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    @Override
    public void printToStream(PrintStream out) {

    }
}
