package main.java;

import java.time.LocalDate;

// Represents evidence related to a person's name, implementing the Evidence interface.
public class NameEvidence implements Evidence {
    // The name associated with this evidence.
    private final String name;

    // The date when this evidence was recorded.
    private final LocalDate dateRecorded;

    // Constructor to initialize NameEvidence with name and date recorded.
    public NameEvidence(String name, LocalDate dateRecorded) {
        this.name = name;
        this.dateRecorded = dateRecorded;
    }

    // Getter for the name.
    public String getName() {
        return name;
    }

    // Getter for the date recorded.
    @Override
    public LocalDate getDateRecorded() {
        return dateRecorded;
    }

    // Provides a string representation of the NameEvidence.
    @Override
    public String toString() {
        return "Name: " + name + " (Recorded on: " + dateRecorded + ")";
    }
}
