import javax.swing.*;
import java.awt.event.ActionEvent;

/*
This class represents the gui of the ATM interface
 */
/**
 * @author Raed Massoud
 */
public class Screen extends JFrame {

    public JLabel title;
    public JLabel loginFailed;
    public JTextField cardNumberField;
    public JTextField pinField;
    public JButton loginButton;
    public JButton exitButton;

    public JRadioButton balanceButton;
    public JRadioButton withdrawButton;
    public JButton proceedButton;

    JRadioButton[] rb = new JRadioButton[6];
    public JLabel totalBalanceLabel;
    public JRadioButton withdraw20;
    public JRadioButton withdraw40;
    public JRadioButton withdraw60;
    public JRadioButton withdraw80;
    public JRadioButton withdraw100;
    public JRadioButton withdraw200;
    public JButton backButton;

    // Creates the login screen
    public void createLogin() {
        title = new JLabel("Enter Card Number and Pin");
        title.setBounds(130, 20, 160, 25);

        cardNumberField = new JTextField();
        cardNumberField.setBounds(145 , 130, 130, 25);

        pinField = new JTextField();
        pinField.setBounds(190, 165, 40, 25);

        loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        loginButton.setBounds(130, 300, 75, 25);

        exitButton = new JButton("Exit");
        exitButton.setFocusable(false);
        exitButton.setBounds(215, 300, 75, 25);

        this.setSize(420, 420);
        this.setTitle("Login");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(title);
        this.add(cardNumberField);
        this.add(pinField);
        this.add(loginButton);
        this.add(exitButton);
    }

    // Creates the menu screen
    public void createMenu() {
        title.setText("Menu");
        title.setBounds(190, 20, 50,25);

        balanceButton = new JRadioButton("Check Balance");
        balanceButton.setBounds(75, 150, 130, 25);
        balanceButton.setSelected(false);

        withdrawButton = new JRadioButton("Withdraw cash");
        withdrawButton.setBounds(75, 190, 130, 25);
        withdrawButton.setSelected(false);

        proceedButton = new JButton("proceed");
        proceedButton.setFocusable(false);
        proceedButton.setBounds(160, 300, 100, 25);

        this.getContentPane().removeAll();
        this.add(title);
        this.add(balanceButton);
        this.add(withdrawButton);
        this.add(proceedButton);
        this.repaint();
    }

    // Creates withdraw screen
    public void createWithdraw(double totalBalance) {
        this.getContentPane().removeAll();

        title.setText("Withdraw");
        title.setBounds(170, 20, 80,25);

        totalBalanceLabel = new JLabel("Total Balance " + totalBalance);
        totalBalanceLabel.setBounds(90, 110, 200, 25);

        proceedButton = new JButton("proceed");
        proceedButton.setFocusable(false);
        proceedButton.setBounds(115, 300, 85, 25);

        backButton = new JButton("back");
        backButton.setFocusable(false);
        backButton.setBounds(215, 300, 85, 25);


        rb[0] = withdraw20; rb[1] = withdraw40; rb[2] = withdraw60;
        rb[3] = withdraw80; rb[4] = withdraw100; rb[5] = withdraw200;

        int posX = 90, posY = 150, amount = 20;
        for(int x = 0; x < rb.length; x++) {
            rb[x] = new JRadioButton("$"+amount);
            rb[x].setBounds(posX, posY, 60, 25);
            this.add(rb[x]);

            amount += 20;
            posX += 90;
            if(x == 2) {
                posX = 90;
                posY += 40;
            }
        }
        rb[5].setText("$200");

        this.add(title);
        this.add(totalBalanceLabel);
        this.add(proceedButton);
        this.add(backButton);
        this.repaint();
    }


    // Returns user login card number
    public String userLoginNumber() { // Returns card number user input
        try {
            return cardNumberField.getText();
        } catch (Exception e) {
            return "1";
        }
    }

    // Returns user login pin
    public String userLoginPin() { // Return card pin user input
        try {
            return pinField.getText();
        } catch (Exception e) {
            return "1";
        }
    }

    // Invoked when login fails - card does not exist || incorrect pin
    public void loginFailed() {
        loginFailed = new JLabel("Login Failed");
        loginFailed.setBounds(170, 200, 80, 25);
        this.add(loginFailed);
        this.repaint();
    }

    // Insures single option selection
    public void singleOptionSelection(ActionEvent e) {

        if(e.getSource() == rb[0]) {
            for(JRadioButton b : rb)
                b.setSelected(false);
            rb[0].setSelected(true);
        }

        if(e.getSource() == rb[1]) {
            for(JRadioButton b : rb)
                b.setSelected(false);
            rb[1].setSelected(true);
        }

        if(e.getSource() == rb[2]) {
            for(JRadioButton b : rb)
                b.setSelected(false);
            rb[2].setSelected(true);
        }

        if(e.getSource() == rb[3]) {
            for(JRadioButton b : rb)
                b.setSelected(false);
            rb[3].setSelected(true);
        }

        if(e.getSource() == rb[4]) {
            for(JRadioButton b : rb)
                b.setSelected(false);
            rb[4].setSelected(true);
        }

        if(e.getSource() == rb[5]) {
            for(JRadioButton b : rb)
                b.setSelected(false);
            rb[5].setSelected(true);
        }
    }

}
