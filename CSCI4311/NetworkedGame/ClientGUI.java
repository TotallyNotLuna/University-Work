package NetworkedGame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

public class ClientGUI{

    JFrame mainMenu;
    JFrame game;

    JTextArea messageLog = null;
    ArrayList<Tile> gameState = null;

    //MAIN START MENU
    public void mainMenu(){

        mainMenu = new JFrame();

            JPanel pMenu = new JPanel();

                JLabel title = new JLabel("Draughts", SwingConstants.CENTER);
                title.setFont(new Font("Sans", Font.BOLD, 24));

                JPanel pButton = new JPanel();

                    JButton bNewGame = new JButton("Find New Game");
                    JButton bExit = new JButton("exit");

                pButton.setBorder(new EmptyBorder(150, 50, 150, 50));
                pButton.setLayout(new BorderLayout());
                pButton.setOpaque(false);

                pButton.add(bNewGame, BorderLayout.NORTH);
                pButton.add(bExit, BorderLayout.SOUTH);

                bNewGame.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e){

                        String playerName = (String)JOptionPane.showInputDialog(
                            bNewGame,
                            "Player Name:",
                            "Find New Game",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "New Player"
                        );

                        if ((playerName != null) && (playerName.length() > 0)){

                            if(Client.connect()){

                                mainMenu.setVisible(false);
                                game();

                                Client.startInputThread();

                                Client.sendData("N/" + playerName);
                            }
                        }
                    }
                });

                bExit.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e){

                        if (JOptionPane.showConfirmDialog(
                                bExit, 
                                "Are you sure you want to exit?", "Exit?", 
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){

                            System.exit(0);
                        }
                    }
                });

            pMenu.setBorder(new EmptyBorder(50, 50, 50, 50));
            pMenu.setBackground(Color.GRAY);
            pMenu.setLayout(new BorderLayout());

            pMenu.add(title, BorderLayout.NORTH);
            pMenu.add(pButton, BorderLayout.CENTER);

        mainMenu.setSize(400, 600);
        mainMenu.setVisible(true);

        mainMenu.add(pMenu);

        mainMenu.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){

                if (JOptionPane.showConfirmDialog(
                        mainMenu, 
                        "Are you sure you want to exit?", "Exit?", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){

                    System.exit(0);
                }
            }
        });
    }

    //GAME BOARD
    public void game(){

        game = new JFrame();

            JPanel inputPanel = new JPanel();
            
                messageLog = new JTextArea(15, 1);
                messageLog.setEditable(false);

                DefaultCaret caret = (DefaultCaret)messageLog.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

                JScrollPane messageScrollPane = new JScrollPane(messageLog);
                messageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                messageScrollPane.setPreferredSize(new Dimension(700, 600));

            inputPanel.setPreferredSize(new Dimension(300,800));
            inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            inputPanel.setLayout(new BorderLayout());

            inputPanel.add(messageScrollPane, BorderLayout.CENTER);

            JPanel board = new JPanel();
            board.setPreferredSize(new Dimension(800,800));
            board.setBackground(Color.BLACK);
            board.setBorder(new EmptyBorder(10, 10, 10, 10));
            board.setLayout(new GridLayout(8, 8));

            gameState = new ArrayList<>();

            int skew = 0;
            for(int i = 0; i < 64; i++){

                skew += ((i%8 == 0) ? 1: 0);

                if((i+skew)%2 == 0){

                    if(i < 24){
                        
                        Tile tile = new Tile(i, Color.DARK_GRAY, Color.GRAY, false, true);
                        board.add(tile);
                        gameState.add(tile);
                    }
                    else if(i >= 40){

                        Tile tile = new Tile(i, Color.DARK_GRAY, Color.WHITE, false, true);
                        board.add(tile);
                        gameState.add(tile);
                    }
                    else{

                        Tile tile = new Tile(i, Color.DARK_GRAY, Color.BLUE, false, false);
                        board.add(tile);
                        gameState.add(tile);
                    }
                }
                else{
                        
                    Tile tile = new Tile(i, Color.WHITE, Color.BLUE, false, false);
                    board.add(tile);
                    gameState.add(tile);
                }
            }

        game.setSize(1200, 900);
        game.setLayout(new BorderLayout());
        game.setVisible(true);

        
        
        game.add(inputPanel, BorderLayout.WEST);
        game.add(board, BorderLayout.CENTER);

        game.pack();

        game.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){

                if (JOptionPane.showConfirmDialog(
                        game, 
                        "Are you sure you want to exit?", "Exit?", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    
                    Client.sendData("disconnect");
                    mainMenu.setVisible(true);
                }
            }
        });
    }
}