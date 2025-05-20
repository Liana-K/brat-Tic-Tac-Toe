/*
 * 
 * referrenced JFrame api: https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html
 * referrenced JTextField api: https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextField.html
 * referrenced lecture slides L21 GUIs & Inner Classes
 * 
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartScreen extends JFrame {
    private JTextField player1NameField;
    private JTextField player2NameField;
    private JButton startButton;

    //Constructor
    public StartScreen() {
        setTitle("Tic Tac Toe - Start Screen");
        setSize(400, 400);
        setLocationRelativeTo(null); //so the frame is in the middle of the screen instead of the top left corner
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10)); //GridLayout object to organize input fields and buttons

        //Creating Charli xcx's "brat" green
        Color bratGreen = new Color(138,206, 0);
        getContentPane().setBackground(bratGreen);

        //player 1 Name Label and TextField
        JLabel player1Label = new JLabel("Player 1 (X) Name:");
        player1NameField = new JTextField();
        
        //player 2 Name Label and TextField
        JLabel player2Label = new JLabel("Player 2 (C) Name:");
        player2NameField = new JTextField();
        
        //start button
        startButton = new JButton("Start Game");

        //setting up the frame
        add(player1Label);
        add(player1NameField);
        add(player2Label);
        add(player2NameField);
        add(new JLabel()); //for spacing/formatting output purposes
        add(startButton);

        //ActionListener for start button
        startButton.addActionListener(new ActionListener() { //Lines 37 - 58: referenced https://stackoverflow.com/questions/4885584/how-does-an-actionlistener-work
                                                             //needed detailed description on how ActionListeners work for implementation in program 
                                                             //also referenced lecture slides from L21 GUIs & Inner Classes pdf
            @Override
            public void actionPerformed(ActionEvent e) {
                //gets names entered by the user
                String player1Name = player1NameField.getText().trim();
                String player2Name = player2NameField.getText().trim();

                //checks if both player names are entered into the name fields
                if (player1Name.isEmpty() || player2Name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter names for both players."); //if both player names are not entered, prompts user for name again
                } else {
                    //creates the TicTacToeGUI frame and then passes the player names to the GUI
                    TicTacToeGUI gameGUI = new TicTacToeGUI(player1Name, player2Name);
                    gameGUI.setVisible(true);

                    //closes the start screen
                    dispose();
                }
            }
        });
    }


}
