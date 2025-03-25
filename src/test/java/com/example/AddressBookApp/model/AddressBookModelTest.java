package com.example.AddressBookApp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//✅ Kya Ho Raha Hai?
//
//Entity ke getter/setter check ho rahe hain.
//Ensure kar rahe hain ki values correctly set ho rahi hain.

public class AddressBookModelTest {
    @Test
    public void testAddressBookModelGetterSetter() {
        AddressBookModel addressBookModel = new AddressBookModel();

        addressBookModel.setId(1);
        addressBookModel.setName("Bhagyodaya");
        addressBookModel.setAddress("Mathura");
        addressBookModel.setPhoneNumber("1478523691");

        assertEquals(1, addressBookModel.getId());
        assertEquals("Bhagyodaya", addressBookModel.getName());
        assertEquals("Mathura", addressBookModel.getAddress());
        assertEquals("1478523691", addressBookModel.getPhoneNumber());
    }
}
