/**
 * @author Evan Arroyo
 * @author Joshua Sims
 *
 * @version 10-24-16
 *
 *
 * This is the driver that runs all the code
 */

public class ChargenClientDriver {

    public static void main(String args[]){

        int port = 19;

        String host = "polaris.cs.wcu.edu";

        String client = "";

        String flag = "";

        try{
            if(args.length == 1){
                System.out.println("Invalid Number Of Arguments.");
                System.out.println("Usage: <TCP/UDP> <host> (<port>) (<flag>)");
                System.exit(1);
            }

            if(args.length == 2){
                client = args[0];
                host = args[1];
            }

            if(args.length == 3){
                client = args[0];
                host = args[1];
                port = Integer.parseInt(args[2]);
            }

            if(args.length == 4){
                client = args[0];
                host = args[1];
                port = Integer.parseInt(args[2]);
                flag = args[3];
            }
        }catch(NumberFormatException e){
            System.out.println("Usage: <TCP/UDP> <host> (<port>) (<flag>)");
        }

    }
}
