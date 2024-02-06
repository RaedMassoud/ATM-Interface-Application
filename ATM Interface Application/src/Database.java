import javax.swing.*;
import java.util.ArrayList;
/**
 * @author Raed Massoud
 */
public class Database {

    ArrayList<Card> cards = new ArrayList<>();

    public Database() { // Sample Cards
        Card card0 = new Card("Raed", "Ihab", "Massoud", 20, 999_999_999, 1234, 1_100);
        Card card1 = new Card("Daniel", "Charbel", "Fares", 19, 888_888_888, 2468, 12_000);
        cards.add(card0); cards.add(card1);
    }

    // Return the card with matching Card Number
    public Card getCard(long cardNumber) {
        for(Card card : cards) {
            if(card.getNumber() == cardNumber)
                return card;
        }
        return null; // Returns null if no match is found
    }
    // Validate that card exists
    public boolean validateCard(long cardNumber) {
        for(Card card : cards) {
            if(card.getNumber() == cardNumber)
                return true;
        }
        return false;
    }

    // Returns true if pin matches pin of account number
    public Boolean authenticateCard(long cardNumber, int pin) {
        Card card = getCard(cardNumber);
        return card.getPin() == pin;
    }


    // Credits card total. Return true for successful transaction
    public Boolean creditAccount(long cardNumber, double amount) {
        Card card = getCard(cardNumber);
        if (card.getTotalAmount() >= amount) {
            card.credit(amount);
            return true;
        }else
            return false;
    }

    // Invokes a Message Dialog with card information
    public void showBalance(long userLoginNumber) {
        Card card = getCard(userLoginNumber);
        String message = String.format("Card holder: %s%nCard number: %s%nTotal amount: %.2f", card.getFirstName().
        concat(" " + card.getMiddleName()).concat(" " + card.getLastName()), card.getNumber(), card.getTotalAmount());
        JOptionPane.showMessageDialog(null, message);
    }
}

