import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Based on Code from Kuros and Ross.
 * @author William Kreahling
 * This is an example of a simple sequential server. Most servers do not behave like this!
 *
 *
 * Note: Commented more than normal for class purposes.  There is not enough error checking in
 * this code!
 */
public class TCPServer {
    /** Listen for clients knocking at the door (port) */
    ServerSocket welcomeSocket;

    /**
     * Create a Server with a listening socket to accept client connections
     *
     * @param port port where the server is listening
     *
     * @throws IOException if we cannot create a socket.
     */
    public TCPServer(int port) throws IOException {
        // socket listens for incoming connections.
        welcomeSocket = new ServerSocket(port);
    }

    /**
     * Accept connections from clients, Get data they send and send it back
     * modified. Makes MANY assumptions. -- <b>For example puposes only!</b>
     *
     * @throws IOException if we have problems with our sockets.
     */
    public void go() throws IOException {

        // THIS IS NOT A VALID WAY TO WAIT FOR SOCKET CONNECTIONS!, You should
        // not have a forever loop or while(true) 
        for (; ;) {
            // Accept a connection, and create a new 'direct' socket
            // This socket has the same port as the welcome socket.
            Socket connectionSocket = welcomeSocket.accept();

            // create a Scanner (stream) connected to the client's socket
            Scanner clientIn = new Scanner(connectionSocket.getInputStream());
            DataOutputStream clientOut = new DataOutputStream(connectionSocket.getOutputStream());

            // read from the socket
            String clientLine = clientIn.nextLine();

            // modify the data and send it back though the socket.
            // don't forget the newline, the client expects one!
            String modLine = clientLine.toUpperCase();
            clientOut.writeBytes(modLine + "\n");

        }
    }

    /**
     * Main is a method that should be fully commented!
     *
     * @param args not used.
     */
    public static void main(String[] args){

        try {
            TCPServer server = new TCPServer(6789);
            server.go();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        
    }
}
