package chargen;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author Evan Arroyo
 * @author Joshua Sims
 *
 * @version 10-24-16
 *
 */

//TODO Errors on line 17 with AbstractChargenClient and line 26 with getHost() and getPort()
public class ChargenUdpClient extends AbstractChargenClient {

    public void printToStream(PrintStream out) {

        DatagramSocket sock = null;
        DatagramPacket packet;

        try {
            sock = new DatagramSocket();
            byte[] buff = new byte[256];
            packet = new DatagramPacket(buff, buff.length, getHost(), getPort());

            sock.send(packet);

            packet = new DatagramPacket(buff, buff.length);
            sock.receive(packet);
            String in = new String(packet.getData(), 0, packet.getLength());
            System.out.println(in);

        } catch (IOException e) {
            System.out.println("IOException was caught");
        }
    }
}
