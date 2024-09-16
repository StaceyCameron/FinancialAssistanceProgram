package main.java;

import java.time.LocalDate;

// A utility class containing static methods for validating various attributes.
public class Validation {

    // Validates that a string value is not null and not empty.
    public static void validateNotNullOrEmpty(String value, String attributeName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(attributeName + " cannot be null or empty.");
        }
    }

    // Validates that a given date of birth is not null and is not a future date.
    public static void validateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth must be provided.");
        }
        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be a future date.");
        }
    }

    // Validates that a national insurance number is not null and is exactly 9 characters long.
    public static void validateNationalInsuranceNumber(String nationalInsuranceNumber) {
        if (nationalInsuranceNumber == null || nationalInsuranceNumber.length() != 9) {
            throw new IllegalArgumentException("National insurance number must be 9 digits.");
        }
    }

    // Validates that an address is not null and that all its components (street, city, postcode) are not empty.
    public static void validateAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null.");
        }
        validateNotNullOrEmpty(address.getStreet(), "Street");
        validateNotNullOrEmpty(address.getCity(), "City");
        validateNotNullOrEmpty(address.getPostcode(), "PostCode");
    }

    // Validates that an account number is exactly 8 digits long.
    public static void validateAccountNumber(int accountNumber) {
        String accountNumberStr = Integer.toString(accountNumber);
        if (accountNumberStr.length() != 8) {
            throw new IllegalArgumentException("Account number must be 8-digits.");
        }
    }

    // Validates that a sort code is not null and matches the required format (e.g., 12-34-56).
    public static void validateSortCode(String sortCode) {
        if (sortCode == null || !sortCode.matches("\\d{2}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Sort code must be provided in the format 12-34-56.");
        }
    }

    // Validates that both account number and sort code are valid.
    public static void validateBankAccount(int accountNumber, String sortCode) {
        validateAccountNumber(accountNumber);
        validateSortCode(sortCode);
    }
}
