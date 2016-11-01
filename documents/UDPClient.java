import java.util.Scanner;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * Based on code from Kuros and Ross.
 * @author Dr. William Kreahling
 * @version 05/07/10, Modified 2011
 *
 * Note: Commented more than normal for class purposes.
 */
class UDPClient {

    DatagramSocket clientSocket;    

    /**
     * Creates client with a DatagramSocket
     *
     * @throws IOException if we cannot create a socket.
     */
    public UDPClient() throws IOException {
        // Does not initiate a TCP connection. The host does NOT contact the
        // server, hence the lack of hostname or port number. It creates 
        // a 'door' not a 'pipe'.
        clientSocket = new DatagramSocket();
    }

    /**
     * Get some information from the user, send it to a server, get a
     * response, and then print out the response. Make many assumptions.
     * Example purposes only!
     *
     * @throws IOException if something goes wrong with out socket.
     */
    public void go(String host, int port) throws IOException {
        Scanner     scanIn      = new Scanner(System.in);  

        System.out.print("Enter a sentence to send to the server >");
        String sendLine = scanIn.nextLine();           // data from user

        // Holds data to send to the client, byte is a primitive type that 
        // holds a byte, surprisingly enough :)
        byte[]      sendData    = new byte[1024];

        // Invoke a DNS query that translates the hostname into an IP address.
        InetAddress IPAddress   = InetAddress.getByName(host);

        // Convert a string into a byte array
        sendData = sendLine.getBytes();

        // Construct the packet to push through the socket: Data, the length
        // of the data, the Dest IP address and the port number.
        DatagramPacket sendPacket = new DatagramPacket(sendData, 
                                                       sendData.length,
                                                       IPAddress, port);

        // Push the data into the socket.
        clientSocket.send(sendPacket);

        // Create storage to receive a packet from the server.
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                                                          receiveData.length);

        // The client idles until it receives data from the server.
        clientSocket.receive(receivePacket);
        /* 
         * Get the data out of the packet and put it into a String, because I
         * know it is a String (because that is how I wrote the server) --
         * their simple protocol.
         */
        String recdLine = new String(receivePacket.getData());

        System.out.println("The server sent back:");
        System.out.println("\t" + recdLine);
        /* 
         * Close the socket, but does not have to send 
         * a transport-layer message to the server.
         * Because there is no 'pipe' to sever.
         */
        clientSocket.close(); 
    }

    public static void main(String[] args) {

        try {
            UDPClient client = new UDPClient();
            client.go("localhost", 9876);
        } 
        catch (IOException ioe ) {
            ioe.printStackTrace();
        }
    }
}
