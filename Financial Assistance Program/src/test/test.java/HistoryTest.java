import main.java.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class HistoryTest {

    private Citizen citizen;

    @BeforeEach
    public void setUp() {
        // Set up test data
        Address residentialAddress = new Address("123 Elm St", "Springfield", "12345");
        BankAccount bankAccount = new BankAccount(12345678, "12-34-56");

        // Create a citizen with initial data
        citizen = new Citizen("John Doe", LocalDate.of(1990, 1, 1), residentialAddress, bankAccount);
    }

    @Test
    void testAddNameEvidence() {
        System.out.println("Test 1 - Add Name Evidence");
        System.out.println("Citizen Original Name: " + citizen.getName());

        // Set up: Update name
        String newName = "John A. Doe";
        citizen.setName(newName);

        // Act & Assert: Verify name is updated and history is maintained
        assertEquals(newName, citizen.getName(), "The name should be updated.");
        assertFalse(citizen.getNameHistory().getHistory().isEmpty(), "Name history should not be empty.");

        // Check the latest name in history (should be the second record)
        NameEvidence latestEvidence = citizen.getNameHistory().getHistory().get(1);
        assertEquals(newName, latestEvidence.getName(), "The latest name in history should match the new name.");
        assertNotNull(latestEvidence.getDateRecorded(), "The date recorded should not be null.");

        // Print name history
        System.out.println("*****Name History*****");
        citizen.getNameHistory().getHistory().forEach(record ->
                System.out.println(record.toString())
        );
        System.out.println("\n" + citizen);
    }

    @Test
    void testAddAddressEvidence() {
        // Set up: Update address
        Address newAddress = new Address("123 Hill Street", "Glasgow", "G212EB");
        citizen.setResidentialAddress(newAddress, true);

        // Act & Assert: Verify address is updated and history is maintained
        System.out.println("Test 2 - Add Address Evidence");
        System.out.println(citizen);

        assertEquals(newAddress, citizen.getResidentialAddress(), "The address should be updated.");
        assertFalse(citizen.getAddressHistory().getHistory().isEmpty(), "Address history should not be empty.");

        // Check the latest address in history
        AddressEvidence latestAddressEvidence = citizen.getAddressHistory().getHistory().get(1);
        assertEquals(newAddress, latestAddressEvidence.getAddress(), "The latest address in history should match the new address.");
        assertNotNull(latestAddressEvidence.getDateRecorded(), "The date recorded should not be null.");

        // Print address history
        System.out.println("*****Address History*****");
        citizen.getAddressHistory().getHistory().forEach(record ->
                System.out.println(record.toString())
        );
        System.out.println("\n" + citizen);
    }

    @Test
    void testAddBankAccountEvidence() {
        // Set up: Update bank account
        BankAccount newBankAccount = new BankAccount(87654321, "65-43-21");
        citizen.setBankAccount(newBankAccount); // Assuming setBankAccount handles evidence tracking

        // Act & Assert: Verify bank account is updated and history is maintained
        System.out.println("Test 3 - Add Bank Account Evidence");
        System.out.println(citizen);

        assertEquals(newBankAccount, citizen.getBankAccount(), "The bank account should be updated.");
        assertFalse(citizen.getBankAccountHistory().getHistory().isEmpty(), "Bank account history should not be empty.");

        // Check the latest bank account in history
        BankAccountEvidence latestBankAccountEvidence = citizen.getBankAccountHistory().getHistory().get(1);
        assertEquals(newBankAccount, latestBankAccountEvidence.getBankAccount(), "The latest bank account in history should match the new bank account.");
        assertNotNull(latestBankAccountEvidence.getDateRecorded(), "The date recorded should not be null.");

        // Print bank account history
        System.out.println("*****Bank Account History*****");
        citizen.getBankAccountHistory().getHistory().forEach(record ->
                System.out.println(record.toString())
        );
        System.out.println("\n" + citizen);
    }
}
