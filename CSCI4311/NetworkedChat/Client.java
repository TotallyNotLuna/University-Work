package NetworkedChat;

import java.io.*;
import java.net.*;

public class Client {

    static ClientGUI gui;

    static DataInputStream inputStream;
    static DataOutputStream outputStream;

    static Socket socket;

    /**
     * Main Method
     * 
     * Connects client and starts the read and write threads.
     * 
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {

        gui = new ClientGUI();
        gui.createGUI();

        InetAddress ip;
        int serverPort;

        if(args.length == 2){

            ip = InetAddress.getByName(args[0]);
            serverPort = Integer.parseInt(args[1]);
        }
        else{

            ip = InetAddress.getByName("localhost");
            serverPort = 5862; 
        }

        socket = new Socket(ip, serverPort);

        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());

        /*
        READ THREAD
        
        waits for data from the server
        */
        Thread readData = new Thread(new Runnable(){ 

            @Override
            public void run(){ 
                while (true){

                    try {
        
                        String recieved = inputStream.readUTF();
                        gui.messageLog.append(recieved + "\n");
                    } catch (IOException e) {
        
                        e.printStackTrace();
                    }
                } 
            } 
        }); 

        readData.start();
    }

    /*
    sendMessage Method

    sends input recieved by the GUI
    */
    public static void sendMessage(String message){
        if(!message.isEmpty()){
            try{

                outputStream.writeUTF(message);
            }
            catch(IOException e){
    
                e.printStackTrace();
            }
    
            //Check for client logout
            if (message.equals("logout")){
    
                terminate();
            }
        }  
    }

    /*
    Terminate Method

    closes datastreams and socket.
    */
    public static void terminate(){

        try{

            inputStream.close();
            outputStream.close();
            
            socket.close();
        }
        catch(IOException e){

            e.printStackTrace();
        }

        System.exit(0);
    }
} 