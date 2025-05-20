//referrenced lecture slides L21 GUIs & Inner Classes

import java.awt.*;
import javax.swing.*;
public class CongratulationsScreen extends JFrame {
    private String winnerName;

    //Constructor
    public CongratulationsScreen(String winnerName) {
        this.winnerName = winnerName;

        //JFrame set-up
        setTitle("Congratulations!");
        setSize(400, 400);
        setLocationRelativeTo(null);  //centers the frame on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Creating Charli xcx's "brat" green
        Color bratGreen = new Color(138,206, 0);
        getContentPane().setBackground(bratGreen);

        //JLabel with the congratulations message
        JLabel messageLabel = new JLabel("Congratulations, " + winnerName + "!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        messageLabel.setForeground(Color.BLACK); 
        messageLabel.setBackground(bratGreen);
        add(messageLabel, BorderLayout.CENTER);
    }
}