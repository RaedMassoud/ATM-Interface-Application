/*
This class represents a card given by a bank. It has a unique 16-digit ID number for validation and a 4 digit pin
for Authentication. It also inherits private data members that are responsible for cardholder information.
 */
/**
 * @author Raed Massoud
 */
public class Card extends Account implements CardBehavior{

    private long number;
    private int pin;
    private double totalAmount;

    public Card(String firstName, String middleName, String lastName, int age, long number, int pin, int totalAmount) {
        super(firstName, middleName, lastName, age);
        this.number = number;
        this.pin = pin;
        this.totalAmount = totalAmount;
    }

    // Getters
    public long getNumber() {
        return number;
    }
    public int getPin() {
        return pin;
    }
    public double getTotalAmount() {
        return totalAmount;
    }

    // Setters
    public void setNumber(long number) {
        this.number = number;
    }
    public void setPin(int pin) {
        this.pin = pin;
    }
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Withdraw methods
    @Override
    public void credit(double amount) {this.totalAmount -= amount;}

    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                ", pin=" + pin +
                '}';
    }
}
