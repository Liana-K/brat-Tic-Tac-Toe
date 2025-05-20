/*
 * 
 * referrenced JFrame api: https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html
 * referrenced JOptionPane api: https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html
 * referrenced lecture slides L21 GUIs & Inner Classes
 * 
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToeGUI extends JFrame {
    private JButton[][] buttons;
    private String currentPlayer;
    private String player1Name;
    private String player2Name;
    private String currentPlayerSymbol; //X or O

    //Constructor
    public TicTacToeGUI(String player1Name, String player2Name) { //parameters takes player names
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.currentPlayer = player1Name; //player 1 starts the game
        this.currentPlayerSymbol = "X";   //player 1 is "X"
        
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setBackground(Color.GREEN);
        setLocationRelativeTo(null); //so the frame is in the middle of the screen instead of the top left corner
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        
        buttons = new JButton[3][3];

        //"brat" green
        Color bratGreen = new Color(138, 206, 0);
        
        //setting up the buttons on the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setPreferredSize(new Dimension(100, 100));
                buttons[i][j].setBackground(bratGreen); //sets background for buttons to "brat" green
                buttons[i][j].addActionListener(new ActionListener() { //Lines 39 - 58: referenced https://stackoverflow.com/questions/4885584/how-does-an-actionlistener-work
                    @Override                                          //needed detailed description on how ActionListeners work for implementation in program 
                    public void actionPerformed(ActionEvent e) {       //also referenced lecture slides from L21 GUIs & Inner Classes pdf
                        JButton buttonClicked = (JButton) e.getSource();
                        if (buttonClicked.getText().equals("-")) {
                            buttonClicked.setText(currentPlayerSymbol);
                            //if-statement checks for a win in the game
                            if (checkWin()) {
                                JOptionPane.showMessageDialog(null, currentPlayer + " wins!"); //if there is a win, outputs winner alert
                                //closes the gameboard screen
                                dispose();
                                displayCongratulationsScreen(currentPlayer);
                            } else if (isBoardFull()) {
                                JOptionPane.showMessageDialog(null, "The game ended in a tie!"); //if the whole board is full
                                                                                                                        //and no winner is found, outputs 'tie' alert
                                resetBoard(); //resets board after the tie is found
                            } else {
                                switchPlayer();
                            }
                        }
                    }
                });
                add(buttons[i][j]);
            }
        }
    }

    //method for switching player after every turn
    private void switchPlayer() {
        if (currentPlayer.equals(player1Name)) {
            currentPlayer = player2Name;
            currentPlayerSymbol = "C";
        } else {
            currentPlayer = player1Name;
            currentPlayerSymbol = "X";
        }
    }

    //method checks the rows, columns, and the diagonals for a win
    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(currentPlayerSymbol) && //checking the rows
                buttons[i][1].getText().equals(currentPlayerSymbol) &&
                buttons[i][2].getText().equals(currentPlayerSymbol)) {
                return true;
            }
            if (buttons[0][i].getText().equals(currentPlayerSymbol) && //checking the columns
                buttons[1][i].getText().equals(currentPlayerSymbol) &&
                buttons[2][i].getText().equals(currentPlayerSymbol)) {
                return true;
            }
        }
        if (buttons[0][0].getText().equals(currentPlayerSymbol) && //checking all diagonals
            buttons[1][1].getText().equals(currentPlayerSymbol) &&
            buttons[2][2].getText().equals(currentPlayerSymbol)) {
            return true;
        }
        if (buttons[0][2].getText().equals(currentPlayerSymbol) &&
            buttons[1][1].getText().equals(currentPlayerSymbol) &&
            buttons[2][0].getText().equals(currentPlayerSymbol)) {
            return true;
        }
        return false;
    }

    //checks if the whole board is full
    private boolean isBoardFull() {
        //iterates through board to check for "-" text value (placeholder for X and O)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("-")) { //if a button stillhas the text value, "-", then the method returns false
                    return false;
                }
            }
        }
        return true; //returns true if there are no "-" values for any of the buttons
    }

    //when called, the board's buttons are all reset to text value "-" 
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("-");
            }
        }
        currentPlayer = player1Name;  //game resets to player 1
        currentPlayerSymbol = "X";    //player 1 is X
    }

    //displays the CongratulationsScreen with the winner's name
    private void displayCongratulationsScreen(String winnerName) {
        CongratulationsScreen congratsScreen = new CongratulationsScreen(winnerName);
        congratsScreen.setVisible(true);
    }
}