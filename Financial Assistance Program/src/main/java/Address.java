package main.java;

public class Address {

    // Attributes for street, city, and postcode
    private String street;
    private String city;
    private String postcode;

    // Constructor to initialize the Address object with street, city, and postcode
    // Validates that none of the fields are null or empty
    public Address(String street, String city, String postcode) {
        Validation.validateNotNullOrEmpty(street, "Street");
        Validation.validateNotNullOrEmpty(city, "City");
        Validation.validateNotNullOrEmpty(postcode, "PostCode");

        // Assign the validated values to the object's attributes
        this.street = street;
        this.city = city;
        this.postcode = postcode;
    }

    // Getter method to retrieve the street
    public String getStreet() {
        return street;
    }

    // Getter method to retrieve the city
    public String getCity() {
        return city;
    }

    // Getter method to retrieve the postcode
    public String getPostcode() {
        return postcode;
    }

    // Override the toString() method to return the address in a readable format
    @Override
    public String toString() {
        return street + ", " + city + ", " + postcode; // e.g., "123 Main St, Cityville, AB12 3CD"
    }
}
