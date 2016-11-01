import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;
import java.io.IOException;
/**
 * Based on code from Kuros and Ross.
 * This is an example sequential server (most servers do not behave like this).
 *
 * @author William Kreahling
 *
 * Note: Commented more than normal for class purposes.
 */
public class UDPServer {
    /** Socket to the known universe (A.k.a. the internet).
    DatagramSocket serverSocket; 
                                 

    /**
     * Create a Server with a socket.
     *
     * @param port port where the server is listening
     *
     * @throws IOException if we cannot create a socket.
     */
    public UDPServer(int port) throws IOException {
        /* 
         * Note there is no listening socket, just a nice all purpose socket through which
         * data, from possibly multiple different clients, will come through.
         */
        serverSocket = new DatagramSocket(port);
    }

    /**
     * Get data from a client, modify it and send it back modified. Makes MANY assumptions. --
     * example puposes only!
     *
     * @throws IOException if we have problems with our sockets.
     */
    public void go() throws IOException {

        for (; ;) { // SADNESS :(

            // create a packet to get some tasty data.
            byte[] receiveData = new byte[1024];

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // get some data through the socket
            serverSocket.receive(receivePacket);

            // change the data portion byte array, into a string, I assume that this is legal.
            String clientLine = new String(receivePacket.getData());

            /* 
             * Extract the return address, IP address and port number, if we need this to send
             * data back to whoever sent it.
             */
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            // Modify the data and send it back though the socket.
            String modLine  = clientLine.toUpperCase();
            byte[] sendData = modLine.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 
                                                           port);
            serverSocket.send(sendPacket);
        }
    }

    public static void main(String[] args){

        try {
            UDPServer server = new UDPServer(9876);
            server.go();
        }
        catch(IOException ioe) {
            ioe.printStackTrace(); // Only for debugging purposes. Remove these and put nice
                                   // informative error messages.
        }
    }
}
