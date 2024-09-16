package main.java;

import java.time.LocalDate;
import java.time.Period;

public class Citizen {

    //*****CITIZEN ATTRIBUTES*****
    // Attributes of a citizen, some are final (immutable) while others can be updated
    private final LocalDate dateOfRegistration;  // Registration date is set once when the citizen is created
    private String name;  // Citizen's name, can be updated later
    private final LocalDate dateOfBirth;  // Date of birth, can't be changed once set
    private String nationalInsuranceNumber;  // Optional, can be updated later if needed
    private Address residentialAddress;  // Main address of the citizen, can be updated
    private Address correspondenceAddress;  // Optional, can differ from residential address
    private BankAccount bankAccount;  // Bank account details, can be updated
    private boolean isProspect;  // Flag to indicate if the citizen is a prospect (based on whether they have an NI number)

    // History managers for tracking changes to key attributes (name, address, bank account)
    private final HistoryManager<NameEvidence> nameHistory = new HistoryManager<>();
    private final HistoryManager<AddressEvidence> addressHistory = new HistoryManager<>();
    private final HistoryManager<BankAccountEvidence> bankAccountHistory = new HistoryManager<>();

    //*****OVERLOADED CONSTRUCTORS*****
    // Constructor for all attributes including optional attributes - NI number and correspondence address
    public Citizen(String name, LocalDate dateOfBirth, String nationalInsuranceNumber, Address residentialAddress, Address correspondenceAddress, BankAccount bankAccount) {
        // Validate mandatory fields during construction
        Validation.validateNotNullOrEmpty(name, "Name");
        Validation.validateDateOfBirth(dateOfBirth);
        Validation.validateAddress(residentialAddress);
        Validation.validateBankAccount(bankAccount.getAccountNumber(), bankAccount.getSortCode());

        // Validate optional fields if they are provided
        if(nationalInsuranceNumber != null) {
            Validation.validateNationalInsuranceNumber(nationalInsuranceNumber);
        }
        if(correspondenceAddress != null) {
            Validation.validateAddress(correspondenceAddress);
        }

        // Assign values to fields
        this.dateOfRegistration = LocalDate.now();  // Registration date is set to current date
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.nationalInsuranceNumber = nationalInsuranceNumber;
        this.residentialAddress = residentialAddress;
        this.correspondenceAddress = correspondenceAddress;
        this.bankAccount = bankAccount;

        // If there's no NI number, mark the citizen as a prospect
        this.isProspect = nationalInsuranceNumber == null;

        // Add initial evidence records (i.e., the state of the object at creation)
        nameHistory.addRecord(new NameEvidence(name, LocalDate.now()));
        addressHistory.addRecord(new AddressEvidence(residentialAddress, LocalDate.now()));
        bankAccountHistory.addRecord(new BankAccountEvidence(bankAccount, LocalDate.now()));
    }

    // Constructor without NI number and correspondence address
    public Citizen(String name, LocalDate dateOfBirth, Address residentialAddress, BankAccount bankAccount) {
        this(name, dateOfBirth, null, residentialAddress, null, bankAccount);
        if(correspondenceAddress == null) {
            correspondenceAddress = residentialAddress;  // If no correspondence address is provided, default to residential address
        }
    }

    // Constructor with correspondence address but without NI number
    public Citizen(String name, LocalDate dateOfBirth, Address residentialAddress, Address correspondenceAddress, BankAccount bankAccount) {
        this(name, dateOfBirth, null, residentialAddress, correspondenceAddress, bankAccount);
    }

    // Constructor without correspondence address
    public Citizen(String name, LocalDate dateOfBirth, String nationalInsuranceNumber, Address residentialAddress, BankAccount bankAccount) {
        this(name, dateOfBirth, nationalInsuranceNumber, residentialAddress, null, bankAccount);
    }

    //*****GETTERS*****
    // Getter for registration date
    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for date of birth
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    // Calculate and return the citizen's age based on their date of birth
    public int getAge() {
        return calculateAge(dateOfBirth, dateOfRegistration);
    }

    // Getter for residential address
    public Address getResidentialAddress() {
        return residentialAddress;
    }

    // Getter for correspondence address
    public Address getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    // Getter for bank account
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    // Getter to check if the citizen is a prospect (i.e., they lack an NI number)
    public boolean isProspect() {
        return isProspect;
    }

    // Getter for national insurance number
    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    // Get the history of name changes
    public HistoryManager<NameEvidence> getNameHistory() {
        return nameHistory;
    }

    // Get the history of address changes
    public HistoryManager<AddressEvidence> getAddressHistory() {
        return addressHistory;
    }

    // Get the history of bank account changes
    public HistoryManager<BankAccountEvidence> getBankAccountHistory() {
        return bankAccountHistory;
    }

    //*****SETTERS*****
    // Update the name and record it in the name history
    public void setName(String newName) {
        Validation.validateNotNullOrEmpty(newName, "Name");
        this.name = newName;
        nameHistory.addRecord(new NameEvidence(name, LocalDate.now()));
    }

    // Update the NI number and mark the citizen as no longer a prospect
    public void setNationalInsuranceNumber (String nationalInsuranceNumber) {
        Validation.validateNationalInsuranceNumber(nationalInsuranceNumber);
        this.nationalInsuranceNumber = nationalInsuranceNumber;
        this.isProspect = false;
    }

    // Update the residential address and optionally update the correspondence address
    public void setResidentialAddress(Address newResidentialAddress, boolean updateCorrespondence) {
        Validation.validateAddress(newResidentialAddress);
        this.residentialAddress = newResidentialAddress;
        if (updateCorrespondence) {
            this.correspondenceAddress = newResidentialAddress;  // If required, update correspondence address to match residential
        }
        addressHistory.addRecord(new AddressEvidence(residentialAddress, LocalDate.now()));
    }

    // Update the correspondence address and optionally update the residential address
    public void setCorrespondenceAddress(Address newCorrespondenceAddress, boolean updateResidential) {
        Validation.validateAddress(newCorrespondenceAddress);
        this.correspondenceAddress = newCorrespondenceAddress;
        if (updateResidential) {
            this.residentialAddress = newCorrespondenceAddress;  // If required, update residential address to match correspondence
        }
        addressHistory.addRecord(new AddressEvidence(correspondenceAddress, LocalDate.now()));
    }

    // Update the bank account and record it in the bank account history
    public void setBankAccount(BankAccount newBankAccount) {
        this.bankAccount = newBankAccount;
        bankAccountHistory.addRecord(new BankAccountEvidence(bankAccount, LocalDate.now()));
    }

    //*****METHODS*****
    // Method to calculate the citizen's age based on date of birth and current date
    public int calculateAge(LocalDate dateOfBirth, LocalDate dateOfRegistration) {
        Period age = Period.between(dateOfBirth, dateOfRegistration);
        return age.getYears();
    }

    // Override the toString method to provide a readable summary of the citizen's details
    @Override
    public String toString() {
        return "Registration Date: " + getDateOfRegistration() +
                "\nName: " + getName() +
                "\nDOB: " + getDateOfBirth() +
                "\nAge: " + getAge() +
                "\nNI Number: " + getNationalInsuranceNumber() +
                "\nResidential Address: " + residentialAddress +
                "\nCorrespondence Address: " + correspondenceAddress +
                "\nBank Details: " + bankAccount +
                "\nProspect: " + isProspect;
    }
}
