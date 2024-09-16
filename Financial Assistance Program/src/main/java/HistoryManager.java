package main.java;

import java.util.ArrayList;
import java.util.List;

// Manages a list of evidence records, where each record is of a type that extends Evidence.
public class HistoryManager<T extends Evidence> {
    // List to store the history of evidence records.
    private final List<T> history = new ArrayList<>();

    // Adds a new evidence record to the history.
    public void addRecord(T evidence) {
        history.add(evidence);
    }

    // Retrieves a copy of the list of evidence records.
    public List<T> getHistory() {
        return new ArrayList<>(history);
    }
}
