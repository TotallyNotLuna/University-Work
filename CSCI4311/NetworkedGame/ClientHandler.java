package NetworkedGame;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.StringTokenizer;

class ClientHandler implements Runnable {

    String name;
    final DataInputStream inputStream;
    final DataOutputStream outputStream;
    Socket socket;

    GameInstance game;
    int player;
    static Stack<GameInstance> waitingGames = new Stack<>();

    public ClientHandler(Socket socket, String name, DataInputStream inputStream, DataOutputStream outputStream) {

        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.name = name;
        this.socket = socket;

        this.game = null;
    }

    // TRIES TO TAKE A QUEUED GAME, OTHERWISE CREATES A QUEUED GAME
    private void findGame() {

        while (game == null) {
            
            if(!waitingGames.empty()){

                this.game = waitingGames.pop();
                this.game.player2 = this;

                System.out.println("SERVER" + date() + game.player1.name + " and " + game.player2.name + " are about to begin a match...");

            }
            else{

                waitingGames.push(this.game = new GameInstance(this, null));
                Thread t = new Thread(this.game);
                t.start();

                System.out.println("SERVER" + date() + game.player1.name + " is now queued for a match...");
            }
        }
    }

    //DISCONNECTS SOCKET AND DATA STREAMS
    private void disconnect() {

        try {

            outputStream.writeUTF("disconnect");

            this.inputStream.close();
            this.outputStream.close();
            this.socket.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        finally{

            if(this.game != null){

                if(this.game.player1 == this){

                    this.game.player1 = null;
                }
                else{
    
                    this.game.player2 = null;
                }
    
                game.notice("SERVER" + date() + this.name + " has dissconected.");

                this.game = null;
            }


            System.out.println("SERVER" + date() + this.name + " has dissconected from server.");
            Server.activeClientHandlers.remove(this);
        }
    }

    //HELPER METHODS
    private String date(){

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("[HH:mm:ss]");

        return formatter.format(date);
    }

    @Override
    public void run() {

        String received;

        //RECIEVING LOOP
        while (true) {

            try {
                received = inputStream.readUTF();

                StringTokenizer args = new StringTokenizer(received, "/");
                String protocol = args.nextToken();

                if (received.equals("disconnect")) {

                    disconnect();
                    return;
                }
                else if(protocol.equals("N")){

                    this.name = args.nextToken();
                    System.out.println("SERVER" + date() + "User registered as:" + this.name);
                    findGame();
                }
                else if (protocol.equals("M")) {
                    
                    if(this.game.isActive()){

                        this.game.move(this, args.nextToken());
                    }
                }
                else{

                    System.out.println("SERVER" + date() + "Error in inputDataStream.");
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        }   
    } 
} 