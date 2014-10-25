import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

class TalkClient{
	public static void main(String args[]) throws IOException {
		Socket client = null;
        PrintWriter toServer = null;
        BufferedReader fromServer = null;
        String userInput=null;
        String serverOrder=null;
        try {
            client = new Socket("127.0.0.1", 4444);
            toServer = new PrintWriter(client.getOutputStream(), true); //auto flush
            fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: 127.0.0.1.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to:127.0.0.1.");
            System.exit(1);
        }
       
        BufferedReader fromUser=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("User Input:");
        userInput=fromUser.readLine();
        toServer.println(userInput);
        
        
        serverOrder=fromServer.readLine();
        System.out.println(serverOrder);
        
        toServer.close();
        fromServer.close();
        fromUser.close();
        client.close();
        
        
	}
}