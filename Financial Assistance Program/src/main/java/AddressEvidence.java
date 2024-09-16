package main.java;

import java.time.LocalDate;

public class AddressEvidence implements Evidence {
    // Attributes to hold the address and the date it was recorded
    private final Address address;
    private final LocalDate dateRecorded;

    // Constructor to initialize AddressEvidence with an Address and a date
    public AddressEvidence(Address address, LocalDate dateRecorded) {
        this.address = address;
        this.dateRecorded = dateRecorded;
    }

    // Getter method to retrieve the address
    public Address getAddress() {
        return address;
    }

    // Getter method to retrieve the date when the evidence was recorded
    @Override
    public LocalDate getDateRecorded() {
        return dateRecorded;
    }

    // Override the toString() method to return a readable string representation of the evidence
    @Override
    public String toString() {
        return "Address: " + address + " (Recorded on: " + dateRecorded + ")"; // Format: "Address: [address details] (Recorded on: [date])"
    }
}
