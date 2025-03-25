package com.example.AddressBookApp.dto;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AddressBookDTOTest {
    private Validator validator;

    AddressBookDTO addressBookDTO = new AddressBookDTO();


//    @BeforeEach ka matlab hai yeh method har test ke run hone se pehle execute hoga.
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

//        Validator initialize ho raha hai taaki har test se pehle naye validation rules set ho sakein.
//        ValidatorFactory create ho raha hai jo Validator object return karta hai.
    }

    @Test
    public void testValidDTO() {
        addressBookDTO.setName("Bhagyodaya");
        addressBookDTO.setAddress("Mathura");
        addressBookDTO.setPhoneNumber("1478523690");

        Set<ConstraintViolation<AddressBookDTO>> violations = validator.validate(addressBookDTO);
        assertTrue(violations.isEmpty(), "DTO should be valid");

//        ✅ validator.validate(addressBookDTO) → isse check hota hai ki DTO valid hai ya nahi.
//        ✅ assertTrue(violations.isEmpty()) → Agar koi validation error nahi aaya, toh test pass ho jayega.
    }

    @Test
    public void testInvalidDTO_EmptyName() {
        addressBookDTO.setName("");
        addressBookDTO.setAddress("Mathura");
        addressBookDTO.setPhoneNumber("1478523690");

        Set<ConstraintViolation<AddressBookDTO>> violations = validator.validate(addressBookDTO);
        assertFalse(violations.isEmpty(), "Name should not be empty");
    }

    @Test
    public void testInvalidDTO_InvalidPhoneNumber() {
        addressBookDTO.setName("Bhagyodaya");
        addressBookDTO.setAddress("Mathura");
        addressBookDTO.setPhoneNumber("147852369");
        Set<ConstraintViolation<AddressBookDTO>> violations = validator.validate(addressBookDTO);
        assertFalse(violations.isEmpty(), "Phone number must be 10 digits");
    }
}
