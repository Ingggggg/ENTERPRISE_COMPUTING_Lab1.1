//EchoThread.java
import java.io.*; 
import java.net.*; 
import java.util.*;
public class EchoThread extends Thread {
    private Socket connectionSocket;

    public EchoThread(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }
    
    public void run() {
        Scanner inFromClient = null;
        DataOutputStream outToClient = null;

        try {
            inFromClient = new Scanner(connectionSocket.getInputStream());
            outToClient = new DataOutputStream(connectionSocket.getOutputStream()); 

            // int num1 = inFromClient.nextInt();
            // int num2 = inFromClient.nextInt();

            // int result = num1 + num2;
            int result = inFromClient.nextInt();
            outToClient.writeInt(result);
		}
        catch (IOException e) {
            System.err.println("Closing Socket connection");
        }
        finally {
            try {
                if (inFromClient != null)
                    inFromClient.close();
                if (outToClient != null)
                    outToClient.close();
                if (connectionSocket != null)
                    connectionSocket.close();
            }
            catch (IOException e) {
               e.printStackTrace();
            }
        }
    }
}