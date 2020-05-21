package NetworkedGame;

import java.io.*;
import java.util.*;
import java.net.*;
import java.text.SimpleDateFormat;
  
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

        System.out.println("SEVER" + date() + "Server started..."); 
          
        Socket socket; 
          
        while(true){ 
            
            socket = serverSocket.accept(); 
  
            System.out.println("SERVER" + date() + "client request: " + socket);
            
            DataInputStream inputStream = new DataInputStream(socket.getInputStream()); 
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
           
              
            System.out.println("SERVER" + date() + "Creating client handler..."); 

            ClientHandler newClientHandler = new ClientHandler(socket, "Player " + i, inputStream, outputStream); 
            Thread t = new Thread(newClientHandler); 
              
            System.out.println("SEVER" + date() + "Adding handler to active list."); 

            activeClientHandlers.add(newClientHandler); 
  
            t.start(); 
            
            i++;
        } 
    } 

    private static String date(){

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("[HH:mm:ss]");

        return formatter.format(date);
    }
} 