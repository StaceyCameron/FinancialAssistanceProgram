package main.java;

import java.time.LocalDate;

// Interface representing a generic piece of evidence.
public interface Evidence {

    // Method to retrieve the date when the evidence was recorded.
    LocalDate getDateRecorded();
}
