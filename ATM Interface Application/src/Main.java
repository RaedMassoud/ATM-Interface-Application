import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Raed Massoud
 */
public class Main {
    public static void main(String[] args) {

        Screen screen = new Screen();
        Database database = new Database();

        // Creates login screen
        screen.createLogin();

        // Login Logic
        ActionListener loginActionListener = (ActionEvent e) -> {

            // Initiate login process
            if(e.getSource() == screen.loginButton) {

                // Get login card number
                long userLoginNumber = 0;
                try {
                    userLoginNumber = Long.parseLong(screen.userLoginNumber());
                }
                catch (NumberFormatException ex) {
                    screen.loginFailed();
                }

                // Validate card number
                if(database.validateCard(userLoginNumber)) {
                    int userLoginPin = 0;
                    try {
                        userLoginPin = Integer.parseInt(screen.userLoginPin());
                    }
                    catch (NumberFormatException ex) {
                        screen.loginFailed();
                    }

                    // Authenticate card pin
                    if(database.authenticateCard(userLoginNumber, userLoginPin))
                        menu(screen,database, userLoginNumber); // Launch menu method
                    else // Incorrect pin
                        screen.loginFailed();

                } else { // Card not found
                    screen.loginFailed();
                }
            }

            // Initiate exit process
            if(e.getSource() == screen.exitButton) { // Dispose of login frame
                screen.dispose();
            }
        };
        screen.loginButton.addActionListener(loginActionListener);
        screen.exitButton.addActionListener(loginActionListener);

    }

    // Menu method
    static void menu(Screen screen, Database database, long userLoginNumber) {

        screen.createMenu();

        // Menu logic
        ActionListener menuActionListener = (ActionEvent e) -> {

            // Allow for single option selection only
            if(e.getSource() == screen.balanceButton)
                screen.withdrawButton.setSelected(false);

            if(e.getSource() == screen.withdrawButton)
                screen.balanceButton.setSelected(false);

            if(e.getSource() == screen.proceedButton) {
                if(screen.balanceButton.isSelected())
                    database.showBalance(userLoginNumber);

                if(screen.withdrawButton.isSelected())
                    withdraw(screen, database, userLoginNumber);
            }

        };
        screen.withdrawButton.addActionListener(menuActionListener);
        screen.balanceButton.addActionListener(menuActionListener);
        screen.proceedButton.addActionListener(menuActionListener);
    }

    // Withdraw method
    static void withdraw(Screen screen, Database database, long userLoginNumber) {

        screen.createWithdraw(database.getCard(userLoginNumber).getTotalAmount());

        // Withdraw logic
        ActionListener withdrawActionListener = (ActionEvent e) -> {

            // Check for single option selection
            if (e.getSource() instanceof JRadioButton ) {
                screen.singleOptionSelection(e);
            }

            // Finalize withdraw
            if(e.getSource() == screen.proceedButton) {
                double amount;
                for(JRadioButton b : screen.rb) {
                    if(b.isSelected()) {
                        amount = Integer.parseInt(b.getText().substring(1));
                        if (database.creditAccount(userLoginNumber, amount)) {
                            screen.totalBalanceLabel.setText("Total Balance: " + database.getCard(userLoginNumber).getTotalAmount());
                            JOptionPane.showMessageDialog(null, "Dispensed amount: " + amount);
                        }else
                            JOptionPane.showMessageDialog(null, "Not enough balance");

                        break;
                    }
                }
            }

            // Return to menu button
            if(e.getSource() == screen.backButton)
                menu(screen, database, userLoginNumber);
        };

        screen.backButton.addActionListener(withdrawActionListener);
        screen.proceedButton.addActionListener(withdrawActionListener);
        for(JRadioButton b : screen.rb) {
            b.addActionListener(withdrawActionListener);
        }
    }

}