package main.java;

import java.time.LocalDate;

// Class representing evidence related to a bank account.
public class BankAccountEvidence implements Evidence {

    // Attributes to hold the bank account and the date when the evidence was recorded.
    private final BankAccount bankAccount;
    private final LocalDate dateRecorded;

    // Constructor to initialize the BankAccountEvidence with a bank account and the recording date.
    public BankAccountEvidence(BankAccount bankAccount, LocalDate dateRecorded) {
        this.bankAccount = bankAccount;
        this.dateRecorded = dateRecorded;
    }

    // Getter method to retrieve the bank account associated with this evidence.
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    // Getter method to retrieve the date when the evidence was recorded.
    @Override
    public LocalDate getDateRecorded() {
        return dateRecorded;
    }

    // Override the toString() method to return a readable string representation of the evidence.
    @Override
    public String toString() {
        // Format: "Bank Account: [bank account details] (Recorded on: [date])"
        return "Bank Account: " + bankAccount + " (Recorded on: " + dateRecorded + ")";
    }
}
