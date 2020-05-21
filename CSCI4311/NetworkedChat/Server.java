package NetworkedChat;

import java.io.*;
import java.util.*; 
import java.net.*; 
  
// Server class 
public class Server{ 
  
    //GLOBS
    static Vector<ClientHandler> activeClientHandlers = new Vector<>(); 
    static int i = 0; 

    static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {

        if (args.length == 1) {

            serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        } else {

            serverSocket = new ServerSocket(5862);
        }
          
        Socket socket; 
          
        while(true){ 
            
            socket = serverSocket.accept(); 
  
            System.out.println("client request: " + socket); 
            
            DataInputStream inputStream = new DataInputStream(socket.getInputStream()); 
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
           
              
            System.out.println("Creating client handler..."); 

            ClientHandler newClientHandler = new ClientHandler(socket, "Guest " + i, inputStream, outputStream); 
            Thread t = new Thread(newClientHandler); 
              
            System.out.println("Adding handler to active list"); 

            activeClientHandlers.add(newClientHandler); 
  
            t.start(); 
            
            i++;
        } 
    } 
} 