package NetworkedGame;

import java.awt.Color;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Client {

    static ClientGUI gui = null;

    static InetAddress ip = null;
    static int serverPort;
    
    static Socket socket = null;
    static DataInputStream inputStream = null;
    static DataOutputStream outputStream = null;

    static Thread readData;

    public static void main(String[] args) {

        // START GUI
        gui = new ClientGUI();
        gui.mainMenu();

        // SET HOST
        if (args.length == 2) {

            try {

                ip = InetAddress.getByName(args[0]);
                System.out.println("CLIENT" + date() + "Valid IP");

            } catch (UnknownHostException e) {

                System.out.println("CLIENT" + date() + "Invalid IP");
                e.printStackTrace();
            }

            serverPort = Integer.parseInt(args[1]);
        } else {

            try {

                ip = InetAddress.getByName("localhost");
                System.out.println("CLIENT" + date() + "Valid IP");

            } catch (UnknownHostException e) {

                System.out.println("CLIENT" + date() + "Invalid IP");
                e.printStackTrace();
            }

            serverPort = 5862;
        }
    }

    public static boolean connect(){

         //ATTEMPT CONNECTION
         try {

            socket = new Socket(ip, serverPort);
            System.out.println("CLIENT" + date() + "Connected to server");

        } catch (IOException e) {

            System.out.println("CLIENT" + date() + "Server connection failed");
            e.printStackTrace();
            return false;
        }

        try {
            inputStream = new DataInputStream(socket.getInputStream());
            System.out.println("CLIENT" + date() + "IO connected");

        } catch (IOException e) {

            System.out.println("CLIENT" + date() + "IO failed");
            e.printStackTrace();
            return false;
        }

        try {
            outputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("CLIENT" + date() + "IO connected");

        } catch (IOException e) {

            System.out.println("CLIENT" + date() + "IO failed");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void startInputThread(){

        readData = new Thread(new InputThread(inputStream, gui));
        readData.start();
    }

    //SEND STRING OF DATA TO SERVER
    public static void sendData(String data) {
        if (!data.isEmpty()) {
            try {

                outputStream.writeUTF(data);
            } catch (IOException e) {

                e.printStackTrace();
            }

            System.out.println("CLIENT" + date() +"Sending..." + data);
        }
    }

    public static void disconnect() {

        try {
            inputStream.close();
            outputStream.close();
            socket.close();
            

        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println("CLIENT" + date() +"Disconnected");
    }

    //HELPER METHODS
    private static String date(){

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("[HH:mm:ss]");

        return formatter.format(date);
    }
}

//CLASS FOR INPUT THREAD
class InputThread implements Runnable{

    Boolean state = false;

    DataInputStream inputStream;
    ClientGUI gui;

    public InputThread(DataInputStream inputStream, ClientGUI gui){

        this.inputStream = inputStream;
        this.gui = gui;

    }

    @Override
    public void run() {

        while (true) {

            try {
                String received = inputStream.readUTF();

                System.out.println("CLIENT" + date() + "Recieving..." + received);

                StringTokenizer args = new StringTokenizer(received, "/");
                String protocol = args.nextToken();

                if (received.equals("disconnect")) {

                    Client.disconnect();
                    return;
                }  
                else if (protocol.equals("N")) {

                    gui.messageLog.append(args.nextToken() + "\n");
                } else if (protocol.equals("M")) {
                    
                    int index = Integer.parseInt(args.nextToken());
                    String color = args.nextToken();
                    String king = args.nextToken();

                    if(color.equals("none")){

                        gui.gameState.get(index).setPDraw(false);
                        gui.gameState.get(index).repaint();
                    }
                    else if(color.equals("white")){

                        gui.gameState.get(index).setPDraw(true);
                        gui.gameState.get(index).setPColor(Color.WHITE);

                        if(king.equals("yes")){

                            gui.gameState.get(index).setPKing(true);
                            gui.gameState.get(index).repaint();
                        }
                        else{

                            gui.gameState.get(index).setPKing(false);
                            gui.gameState.get(index).repaint();
                        }

                    }
                    else if(color.equals("black")){

                        gui.gameState.get(index).setPDraw(true);
                        gui.gameState.get(index).setPColor(Color.GRAY);

                        if(king.equals("yes")){

                            gui.gameState.get(index).setPKing(true);
                            gui.gameState.get(index).repaint();
                        }
                        else{

                            gui.gameState.get(index).setPKing(false);
                            gui.gameState.get(index).repaint();
                        }
                    }
                }
                
            } catch (IOException e){

                e.printStackTrace();
            }
        }
    }

    //HELPER METHOD
    private static String date(){

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("[HH:mm:ss]");

        return formatter.format(date);
    }
}