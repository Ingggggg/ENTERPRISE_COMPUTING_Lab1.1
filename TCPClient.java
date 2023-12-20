//TCPClient.java
import java.io.*; 
import java.net.*;
import java.util.*; 
class TCPClient { 
    public static void main(String argv[]) throws Exception { 
        String num1, num2;
        int resultUser, resultServer;
        Scanner inFromUser = null;
        Socket clientSocket = null;
        DataOutputStream outToServer = null;
        Scanner inFromServer = null;

        try {
            inFromUser = new Scanner(System.in);
            clientSocket = new Socket("localhost", 1667);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new Scanner(clientSocket.getInputStream());

            while (true) {
                System.out.print("enter number 1 (to end just press enter): ");
                num1 = inFromUser.nextLine(); 
                if (num1.isEmpty()) {
                    break;
                }
                // outToServer.writeInt(Integer.parseInt(num1));

                System.out.print("enter number 2 (to end just press enter): ");
                num2 = inFromUser.nextLine();
                if (num2.isEmpty()) {
                    break;
                }
                // outToServer.writeInt(Integer.parseInt(num2));

                resultUser = Integer.parseInt(num1) + Integer.parseInt(num2);
                outToServer.writeInt(resultUser);

                System.out.println("The result is " + resultUser);

                // resultServer = inFromServer.nextInt();
                // System.out.println("The result is " + resultServer);
            }
        }
        catch (IOException e) {
            System.out.println("Error occurred: Closing the connection");
        }
        finally {
            try {
                if (inFromServer != null)
                    inFromServer.close();
                if (outToServer != null)
                    outToServer.close();
                if (clientSocket != null)
                    clientSocket.close();
            }
            catch (IOException e) {
               e.printStackTrace();
            }
        } 
    } 
} 