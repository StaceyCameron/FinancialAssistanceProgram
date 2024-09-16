package main.java;

public class BankAccount {

    // Attributes to hold the account number and sort code
    private int accountNumber;
    private String sortCode;

    // Constructor to initialize the BankAccount with account number and sort code
    public BankAccount(int accountNumber, String sortCode) {
        // Validate the account number and sort code using the Validation class
        Validation.validateAccountNumber(accountNumber);
        Validation.validateSortCode(sortCode);

        // Assign validated account number and sort code to the attributes
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
    }

    // Getter method to retrieve the account number
    public int getAccountNumber() {
        return accountNumber;
    }

    // Getter method to retrieve the sort code
    public String getSortCode() {
        return sortCode;
    }

    // Override the toString() method to return a readable string representation of the bank account
    @Override
    public String toString() {
        return accountNumber + ", " + sortCode; // Format: "[account number], [sort code]"
    }
}
