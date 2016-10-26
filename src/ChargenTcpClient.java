import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
/**
 * @author Evan Arroyo
 * @author Joshua Sims
 *
 * @version 10-24-16
 *
 */

public class ChargenTcpClient extends AbstractChargenClient{

    public void printToStream(PrintStream out){

        try{
            Socket s = new Socket(getHost(), getPort());
            out  = new PrintStream((s.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println();
            System.out.println(in.readLine());
            String line = in.readLine();
            while(line != null){
                System.out.println(line);
                line = in.readLine();
            }
            s.close();
        }catch(IOException e){
            System.out.println("I/O Exception was thrown while reading in the " +
                    "the data");
        }

    }
}
