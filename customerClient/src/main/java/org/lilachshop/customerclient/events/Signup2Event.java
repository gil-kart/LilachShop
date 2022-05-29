package org.lilachshop.customerclient.events;


//Event to pass from SignUp2 to final stage to create new costumer
public class Signup2Event {
    String firstName;
    String lastName;
    String phoneNumber;
    String city;
    String address;


    public Signup2Event(String firstName, String lastName, String phoneNumber, String city, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }
}
