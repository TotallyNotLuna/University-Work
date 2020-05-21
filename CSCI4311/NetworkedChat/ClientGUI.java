package NetworkedChat;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.*;

public class ClientGUI implements KeyListener{

    JTextArea messageLog;
    JTextField userInput;

    /**
     * This is the main construction of the GUI.
     */
    public void createGUI(){

        JFrame frame = new JFrame();

            JPanel inputPanel = new JPanel();

                JLabel messageLogLabel = new JLabel("Message Log");
            
                messageLog = new JTextArea(15, 1);
                messageLog.setEditable(false);

                DefaultCaret caret = (DefaultCaret)messageLog.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

                JScrollPane messageScrollPane = new JScrollPane(messageLog);
                messageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                messageScrollPane.setPreferredSize(new Dimension(700, 600));

            inputPanel.setSize(800,600);
            inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            inputPanel.setVisible(true);
            inputPanel.setLayout(new BorderLayout());

            inputPanel.add(messageLogLabel, BorderLayout.NORTH);
            inputPanel.add(messageScrollPane, BorderLayout.CENTER);

            JPanel outputPanel = new JPanel();

                JPanel commandTextPanel = new JPanel();

                    JLabel commandTextLabel = new JLabel("Commands");

                    JTextArea commandText = new JTextArea(15, 1);
                    commandText.setEditable(false);
                    commandText.append("-COMMANDS-\nLogout: logout\nUsers: users\nGlobal: G/Message\nWhisper: W/User/Message\n");

                    JScrollPane commandPane = new JScrollPane(commandText);
                    commandPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                    commandPane.setPreferredSize(new Dimension(200, 200));

                commandTextPanel.setSize(200, 200);
                commandTextPanel.setVisible(true);
                commandTextPanel.setLayout(new BorderLayout());

                commandTextPanel.add(commandTextLabel, BorderLayout.NORTH);
                commandTextPanel.add(commandPane, BorderLayout.CENTER);

                JPanel userInputPanel = new JPanel();

                    JLabel userInputLabel = new JLabel("Input [ENTER]");

                    userInput = new JTextField();
                    userInput.setColumns(50);
                    userInput.addKeyListener(this);

                userInputPanel.setSize(600, 200);
                userInputPanel.setVisible(true);
                userInputPanel.setLayout(new BorderLayout());

                userInputPanel.add(userInputLabel, BorderLayout.NORTH);
                userInputPanel.add(userInput, BorderLayout.CENTER);

            outputPanel.setSize(600,200);
            outputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            outputPanel.setVisible(true);
            outputPanel.setLayout(new BorderLayout());
            
            outputPanel.add(commandTextPanel, BorderLayout.WEST);
            outputPanel.add(userInputPanel, BorderLayout.EAST);

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(new WindowAdapter(){
            
            public void windowClosing(WindowEvent e){

                Client.sendMessage("logout");
            }
        });

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(outputPanel, BorderLayout.CENTER);

    }

    @Override
    public void keyTyped(KeyEvent e){
        //NOT USED
    }

    @Override
    public void keyPressed(KeyEvent e){
        //NOT USED
    }
    
    /**
     * KeyListener to determine when the user is ready to submit input.
     */
    @Override
    public void keyReleased(KeyEvent e){
        
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_ENTER){

            Client.sendMessage(userInput.getText());
            this.userInput.setText("");
        }
    }
}