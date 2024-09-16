import main.java.Address;
import main.java.BankAccount;
import main.java.Citizen;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class CitizenTest {

    @Test
    void createCitizenWithAllAttributes() {
        // Set up address and bank account
        Address rAddress = new Address("123 Uptown Street", "Glasgow", "G212EB");
        Address cAddress = new Address("38 Newtown Road", "Glasgow", "G212EX");
        BankAccount bankAccount = new BankAccount(12345678, "12-34-56");

        // Create a citizen with all attributes
        Citizen citizen = new Citizen("John Doe", LocalDate.of(1992, 1, 1), "AB123456C", rAddress, cAddress, bankAccount);

        System.out.println("Test 1 - All Attributes");
        System.out.println(citizen);

        // Assertions
        assertEquals("John Doe", citizen.getName());
        assertEquals(LocalDate.of(1992, 1, 1), citizen.getDateOfBirth());
        assertEquals("AB123456C", citizen.getNationalInsuranceNumber());
        assertEquals("123 Uptown Street, Glasgow, G212EB", rAddress.toString());
        assertEquals("38 Newtown Road, Glasgow, G212EX", cAddress.toString());
        assertEquals("12345678, 12-34-56", bankAccount.toString());
        assertEquals(false, citizen.isProspect());
    }

    @Test
    void createCitizenWithMandatoryAttributesOnly() {
        // Set up address and bank account
        Address rAddress = new Address("123 Uptown Street", "Glasgow", "G212EB");
        BankAccount bankAccount = new BankAccount(12345678, "12-34-56");

        // Create a citizen with mandatory attributes only
        Citizen citizen = new Citizen("John Doe", LocalDate.of(1992, 1, 1), rAddress, bankAccount);

        System.out.println("Test 2 - Mandatory Attributes Only");
        System.out.println(citizen);

        // Assertions
        assertEquals("John Doe", citizen.getName());
        assertEquals(LocalDate.of(1992, 1, 1), citizen.getDateOfBirth());
        assertEquals("123 Uptown Street, Glasgow, G212EB", rAddress.toString());
        assertEquals("12345678, 12-34-56", bankAccount.toString());
        assertEquals(true, citizen.isProspect());
    }

    @Test
    void createCitizenWithoutNationalInsuranceNumber() {
        // Set up address and bank account
        Address rAddress = new Address("123 Uptown Street", "Glasgow", "G212EB");
        Address cAddress = new Address("38 Newtown Road", "Glasgow", "G212EX");
        BankAccount bankAccount = new BankAccount(12345678, "12-34-56");

        // Create a citizen without a national insurance number
        Citizen citizen = new Citizen("John Doe", LocalDate.of(1992, 1, 1), rAddress, cAddress, bankAccount);

        System.out.println("Test 3 - Without National Insurance Number");
        System.out.println(citizen);

        // Assertions
        assertEquals("John Doe", citizen.getName());
        assertEquals(LocalDate.of(1992, 1, 1), citizen.getDateOfBirth());
        assertEquals("123 Uptown Street, Glasgow, G212EB", rAddress.toString());
        assertEquals("38 Newtown Road, Glasgow, G212EX", cAddress.toString());
        assertEquals("12345678, 12-34-56", bankAccount.toString());
        assertEquals(true, citizen.isProspect());
    }

    @Test
    void createCitizenWithoutCorrespondenceAddress() {
        // Set up address and bank account
        Address rAddress = new Address("123 Uptown Street", "Glasgow", "G212EB");
        BankAccount bankAccount = new BankAccount(12345678, "12-34-56");

        // Create a citizen without a correspondence address
        Citizen citizen = new Citizen("John Doe", LocalDate.of(1992, 1, 1), "AB123456C", rAddress, bankAccount);

        System.out.println("Test 4 - Without Correspondence Address");
        System.out.println(citizen);

        // Assertions
        assertEquals("John Doe", citizen.getName());
        assertEquals(LocalDate.of(1992, 1, 1), citizen.getDateOfBirth());
        assertEquals("AB123456C", citizen.getNationalInsuranceNumber());
        assertEquals("123 Uptown Street, Glasgow, G212EB", rAddress.toString());
        assertEquals("12345678, 12-34-56", bankAccount.toString());
        assertEquals(false, citizen.isProspect());
    }

    @Test
    void testRegistrationDateMatchesTodaysDate() {
        // Set up address and bank account
        Address rAddress = new Address("123 Uptown Street", "Glasgow", "G212EB");
        BankAccount bankAccount = new BankAccount(12345678, "12-34-56");

        // Create a citizen and check registration date
        Citizen citizen = new Citizen("John Doe", LocalDate.of(1992, 1, 1), rAddress, bankAccount);

        LocalDate expectedRegistrationDate = LocalDate.now();

        System.out.println("Test 5 - Check Registration Date");
        System.out.println("Expected Date of Registration: " + expectedRegistrationDate);
        System.out.println("Actual Date of Registration: " + citizen.getDateOfRegistration());

        // Assertion
        assertEquals(expectedRegistrationDate, citizen.getDateOfRegistration());
    }

    @Test
    void testCalculateAge_Success() {
        // Set up address and bank account
        Address rAddress = new Address("123 Uptown Street", "Glasgow", "G212EB");
        BankAccount bankAccount = new BankAccount(12345678, "12-34-56");

        // Create a citizen and test age calculation
        Citizen citizen = new Citizen("John Doe", LocalDate.of(1992, 1, 1), rAddress, bankAccount);

        LocalDate dateOfBirth = LocalDate.of(1992, 1, 1);
        int age = citizen.calculateAge(dateOfBirth, LocalDate.of(2024, 9, 15));

        // Assertions
        assertEquals(32, age);

        System.out.println("Test 6 - Test Age Calculation");
        System.out.println("Expected age: 32");
        System.out.println("Calculation Result: " + age);
    }

    @Test
    void updateNationalInsuranceNumberAfterCreatingUser() {
        // Set up address and bank account
        Address rAddress = new Address("123 Uptown Street", "Glasgow", "G212EB");
        BankAccount bankAccount = new BankAccount(12345678, "12-34-56");

        // Create a citizen and update national insurance number
        Citizen citizen = new Citizen("John Doe", LocalDate.of(1992, 1, 1), rAddress, bankAccount);

        System.out.println("Test 7 - Test Adding National Insurance Number");
        System.out.println("National Insurance Number: " + citizen.getNationalInsuranceNumber() +
                "\nProspect Status: " + citizen.isProspect());

        String newNationalInsuranceNumber = "AB123456C";
        citizen.setNationalInsuranceNumber(newNationalInsuranceNumber);

        // Assertions
        assertEquals(newNationalInsuranceNumber, citizen.getNationalInsuranceNumber());

        System.out.println("\nUpdated National Insurance Number: " + citizen.getNationalInsuranceNumber());
        System.out.println("Updated Prospect Status: " + citizen.isProspect());
    }

    @Test
    void testUpdateName() {
        // Set up address and bank account
        Address rAddress = new Address("123 Uptown Street", "Glasgow", "G212EB");
        BankAccount bankAccount = new BankAccount(12345678, "12-34-56");

        // Create a citizen and update name
        Citizen citizen = new Citizen("John Doe", LocalDate.of(1992, 1, 1), rAddress, bankAccount);

        System.out.println("Test 8 - Test Update Name");
        System.out.println("Original Name: " + citizen.getName());

        String updatedName = "John A. Doe";
        citizen.setName(updatedName);

        // Assertions
        assertEquals(updatedName, citizen.getName());
        System.out.println("Updated Name: " + citizen.getName());
    }

    @Test
    void testUpdateBothAddresses() {
        // Set up address and bank account
        Address rAddress = new Address("123 Uptown Street", "Glasgow", "G212EB");
        BankAccount bankAccount = new BankAccount(12345678, "12-34-56");

        // Create a citizen and update both addresses
        Citizen citizen = new Citizen("John Doe", LocalDate.of(1992, 1, 1), rAddress, bankAccount);

        System.out.println("Test 9 - Test Update Both Addresses");
        System.out.println("Original Address: " + rAddress);

        Address updatedAddress = new Address("123 Hill Street", "Glasgow", "G212EB");
        citizen.setResidentialAddress(updatedAddress, true);

        // Assertions
        assertEquals(updatedAddress, citizen.getResidentialAddress());
        System.out.println("Updated Address: " + updatedAddress);

        System.out.println("\n" + citizen);
    }

    @Test
    void updateOnlyResidentialAddress() {
        // Set up address and bank account
        Address rAddress = new Address("123 Uptown Street", "Glasgow", "G212EB");
        BankAccount bankAccount = new BankAccount(12345678, "12-34-56");

        // Create a citizen and update only residential address
        Citizen citizen = new Citizen("John Doe", LocalDate.of(1992, 1, 1), rAddress, bankAccount);

        System.out.println("Test 10 - Test Update Only Residential Address");
        System.out.println("Original Address: " + citizen.getResidentialAddress());
        Address updatedAddress = new Address("123 Hill Street", "Glasgow", "G212EB");
        citizen.setResidentialAddress(updatedAddress, false);

        // Assertions
        assertEquals(updatedAddress, citizen.getResidentialAddress());
        System.out.println("Updated Address: " + citizen.getResidentialAddress());

        System.out.println("\n" + citizen);
    }

    @Test
    void updateOnlyCorrespondenceAddress() {
        // Set up address and bank account
        Address rAddress = new Address("123 Uptown Street", "Glasgow", "G212EB");
        Address cAddress = new Address("38 Newtown Road", "Glasgow", "G212EX");
        BankAccount bankAccount = new BankAccount(12345678, "12-34-56");

        // Create a citizen and update only correspondence address
        Citizen citizen = new Citizen("John Doe", LocalDate.of(1992, 1, 1), "AB123456C", rAddress, cAddress, bankAccount);

        System.out.println("Test 11 - Test Update Only Correspondence Address");
        System.out.println("Original Correspondence Address: " + cAddress);
        Address updatedAddress = new Address("45 New Street", "Glasgow", "G212EZ");
        citizen.setCorrespondenceAddress(updatedAddress, false);

        // Assertions
        assertEquals(updatedAddress, citizen.getCorrespondenceAddress());
        System.out.println("Updated Correspondence Address: " + citizen.getCorrespondenceAddress());
    }
}
