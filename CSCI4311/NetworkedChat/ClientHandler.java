package NetworkedChat;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

class ClientHandler implements Runnable {

    private String name;
    final  DataInputStream inputStream;
    final  DataOutputStream outputStream;
    Socket socket;

    public ClientHandler(Socket socket, String name,  DataInputStream inputStream,  DataOutputStream outputStream){

        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.name = name;
        this.socket = socket;
    }

    private void global(String message) {

        String date = date();

        for (ClientHandler client : Server.activeClientHandlers){

            try{

                client.outputStream.writeUTF(this.name + date + ": " + message);
            }
            catch(IOException e){

                e.printStackTrace();
            }
        }
    }

    private void whisper(String reciever, String message){

        String date = date();

        for(ClientHandler client : Server.activeClientHandlers){
            // if the recipient is found, write on its
            // output stream
            if(client.name.equals(reciever)){
                try{

                    client.outputStream.writeUTF("Whisper: " + this.name + date + ": " + message);
                }
                catch (IOException e){

                    e.printStackTrace();
                }

                break;
            }
        }
    }

    private void notice(String message){

        String date = date();

        try{

            outputStream.writeUTF("SERVER" + date + ": " + message);
        }
        catch(IOException e){

            e.printStackTrace();
        }

        System.out.println("SERVER -> " + this.name + date() + message);
    }

    private void globalNotice(String message){

        String date = date();

        for (ClientHandler client : Server.activeClientHandlers) {

            try {

                client.outputStream.writeUTF("SERVER" + date + ": " + message);
            }
            catch(IOException e){

                e.printStackTrace();
            }
        }

        System.out.println("SERVER -> ALL" + date() + message);
    }

    private void userList(){

        notice("Connected users:");

        for (ClientHandler client : Server.activeClientHandlers) {

            try {

                outputStream.writeUTF(client.name);
            }
            catch(IOException e){

                e.printStackTrace();
            }
        }
    }

    private void logout(){

        try{

            inputStream.close(); 
            outputStream.close(); 
            
            this.socket.close();
            Server.activeClientHandlers.remove(this);

            globalNotice(this.name + " has dissconected.");
        }
        catch(IOException e){ 

            e.printStackTrace(); 
        }
    }

    private String date(){

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("[HH:mm:ss]");

        return formatter.format(date);
    }

    private void wellcome() {

        notice("You are Logged in as " + this.name + ".");
        notice("As a Guest you may view messages.");
        notice("To send messages, Please enter a USERNAME:");

        boolean invalidName = true;

        while(invalidName){

            try{

                String username = inputStream.readUTF();

                if(!username.equals("\n")){

                    for(ClientHandler client : Server.activeClientHandlers){

                        if(client.name.equals(username)){

                            notice("Name already taken, please try again.");
                            break;
                        }
                    }

                    this.name = username;
                    globalNotice(this.name + " has joined the chat!");
                    invalidName = false;
                }
                else{

                    notice("Name invalid, please try again.");
                }
            }
            catch(IOException e){

                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {

        wellcome();

        String received;

        while (true) {

            try {
                received = inputStream.readUTF();
                  
                System.out.println(this.name + date() + received); 
                  
                StringTokenizer args = new StringTokenizer(received, "/"); 
                String protocol = args.nextToken(); 

                if(received.equals("logout")){

                    logout(); 
                } 
                else if(received.equals("users")){
  
                    userList();
                }
                else if(protocol.equals("G")){

                    global(args.nextToken());
                }
                else if(protocol.equals("W")){

                    whisper(args.nextToken(), args.nextToken());
                }
                else{

                    notice("Input not understood.");
                }
            }
            catch(IOException e){ 
                  
                e.printStackTrace(); 
            }      
        }   
    } 
} 