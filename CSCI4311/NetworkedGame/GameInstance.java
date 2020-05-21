package NetworkedGame;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;

public class GameInstance implements Runnable {

    ClientHandler player1;
    ClientHandler player2;

    private boolean gameActive;
    private boolean whiteTurn;
    private int whiteGuardCount;
    private int blackGuardCount;
    private ArrayList<String> gameState;

    private int playerC1;
    private int playerC2;

    public GameInstance(ClientHandler player1, ClientHandler player2) {

        this.player1 = player1;
        this.player2 = player2;

        this.gameActive = false;
        this.whiteTurn = true;

        this.whiteGuardCount = 12;
        this.blackGuardCount = 12;

        this.gameState = new ArrayList<String>();

        //BUILD GAME STATE
        int skew = 0;
        for(int i = 0; i < 64; i++){

            skew += ((i%8 == 0) ? 1: 0);

            if((i+skew)%2 == 0){

                if(i < 24){
                    
                    gameState.add("black/no");
                }
                else if(i >= 40){

                    gameState.add("white/no");
                }
                else{

                    gameState.add("none/no");
                }
            }
            else{

                gameState.add("none/no");
            }
        }

        this.playerC1 = -1;
        this.playerC2 = -1;
    }

    @Override
    public void run() {

        notice(this.player1.name + " has connected.");
        notice("Waiting for aditional players...");

        //SLEEP FOR A RANDOM AMOUNT OF TIME TO REDUCE WASTE CLOCKS
        while (this.player2 == null) {

            try {

                Thread.sleep(new Random().nextInt(1000));

            } catch (InterruptedException e) {

                e.printStackTrace();
            }  
        }

        notice(this.player2.name + " has connected.");

        notice(this.player1.name + " is captain of the White Guard.");
        notice(this.player2.name + " is captain of the Black Guard.");

        notice("White Guard has initiative.");
        notice("---Let the game begin!---");

        this.gameActive = true;
        //START GAME MECHANICS
    }

    //TAKES DATA FROM CLIENT AND STORES IT FOR THE UPCOMMING MOVE
    public synchronized void move(ClientHandler player, String cord){

        if(whiteTurn && player == this.player1){

            if(playerC1 == -1){

                this.playerC1 = Integer.parseInt(cord);
            }else{

                this.playerC2 = Integer.parseInt(cord);

                if(validateMove()){

                    whiteTurn = !whiteTurn;
                }

                this.playerC1 = -1;
                this.playerC2 = -1;

                if(blackGuardCount == 0){

                    notice(this.player1.name + " has won the bout!");
                    this.gameActive = !this.gameActive;
                }
            }
        }
        else if(!whiteTurn && this.player1 == player){

            System.out.println("SERVER" + date() + this.player1.name + " tried moving out of turn.");
        }
        else if(!whiteTurn && this.player2 == player){

            if(playerC1 == -1){

                this.playerC1 = Integer.parseInt(cord);
            }else{

                this.playerC2 = Integer.parseInt(cord);

                if(validateMove()){

                    whiteTurn = !whiteTurn;
                }

                this.playerC1 = -1;
                this.playerC2 = -1;

                if(whiteGuardCount == 0){

                    notice(this.player2.name + " has won the bout!");
                    this.gameActive = !this.gameActive;
                }
            }
        }
        else{

            System.out.println("SERVER" + date() + this.player2.name + " tried moving out of turn.");
        }
    }

    //VALITATED DATA AND PERFORMS MOVE
    private boolean validateMove(){

        StringTokenizer c1 = new StringTokenizer(gameState.get(this.playerC1), "/");
        StringTokenizer c2 = new StringTokenizer(gameState.get(this.playerC2), "/");

        System.out.println("Tile: " + this.playerC1 + "State: " + gameState.get(this.playerC1));
        System.out.println("Tile: " + this.playerC2 + "State: " + gameState.get(this.playerC2));

        //TESTING FOR WHITE PIECE
        if(whiteTurn){

            if(c1.nextToken().equals("white") && c2.nextToken().equals("none")){

                if(c1.nextToken().equals("no")){

                    //NOT KING TESTING
                    //SLIDE
                    if(Math.abs(this.playerC2 - this.playerC1 + 8) == 1){

                        gameState.set(this.playerC1, "none/no");

                        if(this.playerC2 < 8){

                            gameState.set(this.playerC2, "white/yes");
                            update(this.playerC2 + "/white/yes");
                        }
                        else{

                            gameState.set(this.playerC2, "white/no");
                            update(this.playerC2 + "/white/no");
                        }
                        
                        notice(this.player1.name + " has moved from tile " + this.playerC1 + " to " + this.playerC2);

                        update(this.playerC1 + "/none/no");
                        return true;
                    }
                    //JUMP
                    else if((this.playerC1 > this.playerC2) && (this.playerC1+ this.playerC2)%((this.playerC1 + this.playerC2)/2) == 0 && new StringTokenizer(gameState.get((this.playerC1 + this.playerC2)/2), "/").nextToken().equals("black")){

                        gameState.set(this.playerC1, "none/no");

                        if(this.playerC2 < 8){

                            gameState.set(this.playerC2, "white/yes");
                            update(this.playerC2 + "/white/yes");
                        }
                        else{

                            gameState.set(this.playerC2, "white/no");
                            update(this.playerC2 + "/white/no");
                        }

                        gameState.set((this.playerC1 + this.playerC2)/2, "none/no");

                        update(this.playerC1 + "/none/no");
                        update((this.playerC1 + this.playerC2)/2 + "/none/no");

                        blackGuardCount --;

                        notice(this.player1.name + " has moved from tile " + this.playerC1 + " to " + this.playerC2 + " jumping piece in tile " + (this.playerC1 + this.playerC2)/2);
                        notice("Black Guard remaining: " + blackGuardCount);
                        return true;
                    }
                    else{

                        System.out.println("SERVER" + date() + this.player1.name + " tried making an illegal move as non-king.");
                        return false;
                    }
                }
                else{

                    //KING TESTING
                    //SLIDE
                    if(Math.abs(this.playerC2 - this.playerC1 + 8) == 1 || Math.abs(this.playerC2 - this.playerC1 - 8) == 1){

                        gameState.set(this.playerC1, "none/no");
                        gameState.set(this.playerC2, "white/yes");
                        
                        notice(this.player1.name + " has moved from tile " + this.playerC1 + " to " + this.playerC2);

                        update(this.playerC1 + "/none/no");
                        update(this.playerC2 + "/white/yes");
                        return true;
                    }
                    //JUMP
                    else if((this.playerC1+ this.playerC2)%((this.playerC1 + this.playerC2)/2) == 0 && new StringTokenizer(gameState.get((this.playerC1 + this.playerC2)/2), "/").nextToken().equals("black")){

                        gameState.set(this.playerC1, "none/no");
                        gameState.set(this.playerC2, "white/yes");
                        gameState.set((this.playerC1 + this.playerC2)/2, "none/no");

                        update(this.playerC1 + "/none/no");
                        update(this.playerC2 + "/white/yes");
                        update((this.playerC1 + this.playerC2)/2 + "/none/no");

                        blackGuardCount --;

                        notice(this.player1.name + " has moved from tile " + this.playerC1 + " to " + this.playerC2 + " jumping piece in tile " + (this.playerC1 + this.playerC2)/2);
                        notice("Black Guard remaining: " + blackGuardCount);
                        return true;
                    }
                    else{

                        System.out.println("SERVER" + date() + this.player1.name + " tried making an illegal move as king.");
                        return false;
                    }
                }
            }
            else{

                System.out.println("SERVER" + date() + this.player1.name + " tried making an illegal move.");
                return false;
            }  
        }
        else{

            //TESTING FOR BLACK PIECE
            if(c1.nextToken().equals("black") && c2.nextToken().equals("none")){

                if(c1.nextToken().equals("no")){

                    //NOT KING TESTING
                    //SLIDE
                    if(Math.abs(this.playerC2 - this.playerC1 - 8) == 1){

                        gameState.set(this.playerC1, "none/no");

                        if(this.playerC2 > 56){

                            gameState.set(this.playerC2, "black/yes");
                            update(this.playerC2 + "/black/yes");
                            
                        }
                        else{

                            gameState.set(this.playerC2, "black/no");
                            update(this.playerC2 + "/black/no");
                        }
                    
                        notice(this.player2.name + " has moved from tile " + this.playerC1 + " to " + this.playerC2);

                        update(this.playerC1 + "/none/no");
                        return true;
                    }
                    //JUMP
                    else if((this.playerC1 < this.playerC2) && (this.playerC1+ this.playerC2)%((this.playerC1 + this.playerC2)/2) == 0 && new StringTokenizer(gameState.get((this.playerC1 + this.playerC2)/2), "/").nextToken().equals("white")){

                        gameState.set(this.playerC1, "none/no");

                        if(this.playerC2 > 56){

                            gameState.set(this.playerC2, "black/yes");
                            update(this.playerC2 + "/black/yes");
                        }
                        else{

                            gameState.set(this.playerC2, "black/no");
                            update(this.playerC2 + "/black/no");
                        }

                        gameState.set((this.playerC1 + this.playerC2)/2, "none/no");

                        

                        update(this.playerC1 + "/none/no");
                        update((this.playerC1 + this.playerC2)/2 + "/none/no");

                        whiteGuardCount --;

                        notice(this.player2.name + " has moved from tile " + this.playerC1 + " to " + this.playerC2 + " jumping piece in tile " + (this.playerC1 + this.playerC2)/2);
                        notice("White Guard remaining: " + whiteGuardCount);
                        return true;
                    }
                    else{

                        System.out.println("SERVER" + date() + this.player2.name + " tried making an illegal move as non-king.");
                        return false;
                    }
                }
                else{

                    //KING TESTING
                    //SLIDE
                    if(Math.abs(this.playerC2 - this.playerC1 + 8) == 1 || Math.abs(this.playerC2 - this.playerC1 - 8) == 1){

                        gameState.set(this.playerC1, "none/no");
                        gameState.set(this.playerC2, "black/yes");
                        
                        notice(this.player2.name + " has moved from tile " + this.playerC1 + " to " + this.playerC2);
                        
                        update(this.playerC1 + "/none/no");
                        update(this.playerC2 + "/black/yes");
                        return true;
                    }
                    //JUMP
                    else if((this.playerC1 + this.playerC2)%((this.playerC1 + this.playerC2)/2) == 0 && new StringTokenizer(gameState.get((this.playerC1 + this.playerC2)/2), "/").nextToken().equals("white")){

                        gameState.set(this.playerC1, "none/no");
                        gameState.set(this.playerC2, "black/yes");
                        gameState.set((this.playerC1 + this.playerC2)/2, "none/no");

                        update(this.playerC1 + "/none/no");
                        update(this.playerC2 + "/black/yes");
                        update((this.playerC1 + this.playerC2)/2 + "/none/no");

                        whiteGuardCount --;

                        notice(this.player2.name + " has moved from tile " + this.playerC1 + " to " + this.playerC2 + " jumping piece in tile " + (this.playerC1 + this.playerC2)/2);
                        notice("White Guard remaining: " + whiteGuardCount);
                        return true;
                    }
                    else{

                        System.out.println("SERVER" + date() + this.player2.name + " tried making an illegal move as king.");
                        return false;
                    }
                }
            }
            else{

                System.out.println("SERVER" + date() + this.player2.name + " tried making an illegal move.");
                return false;
            }
        }
    }

    //GETTER METHOD FOR GAME STATE
    public boolean isActive(){

        return gameActive;
    }
    //HELPER METHODS(Should probably package)
    //METHOD TO POST INFORMATION TO BOTH PLAYERS
    public void notice(String message){

        try{

            if(this.player1 != null){
                
                this.player1.outputStream.writeUTF("N/SERVER" + date() + ": " + message);
                System.out.println("SERVER -> " + this.player1.name + date() + message);
            }   
        }
        catch(IOException e){

            e.printStackTrace();
        }

        try{

            if(this.player2 != null){
                
                this.player2.outputStream.writeUTF("N/SERVER" + date() + ": " + message);
                System.out.println("SERVER -> " + this.player2.name + date() + message);
            }
        }
        catch(IOException e){

            e.printStackTrace();
        }  
    }

    //SENDS GAME UPDATES TO CLIENT
    public void update(String message){

        try{

            if(this.player1 != null){
                
                this.player1.outputStream.writeUTF("M/" + message);
                System.out.println("SERVER -> " + this.player1.name + date() + message);
            }   
        }
        catch(IOException e){

            e.printStackTrace();
        }

        try{

            if(this.player2 != null){
                
                this.player2.outputStream.writeUTF("M/" + message);
                System.out.println("SERVER -> " + this.player2.name + date() + message);
            }
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
}