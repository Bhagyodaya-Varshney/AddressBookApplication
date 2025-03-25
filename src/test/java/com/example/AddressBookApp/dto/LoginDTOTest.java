package com.example.AddressBookApp.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LoginDTOTest {

    private Validator validator;
    LoginDTO loginDTO = new LoginDTO();

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidLoginDTO() {
        loginDTO.setEmail("abc@gmail.com");
        loginDTO.setPassword("Password123");
        Set<ConstraintViolation<LoginDTO>> violations = validator.validate(loginDTO);
        assertTrue(violations.isEmpty(), "DTO should be valid");
    }

    @Test
    public void testInvalidLoginDTO_EmptyEmail() {
        loginDTO.setEmail("");
        loginDTO.setPassword("Password123");
        Set<ConstraintViolation<LoginDTO>> violations = validator.validate(loginDTO);
        assertFalse(violations.isEmpty(), "Email should not be empty");
    }

    @Test
    public void testInvalidLoginDTO_InvalidEmail() {
        loginDTO.setEmail("abc-gmail.com");
        loginDTO.setPassword("Password123");
        Set<ConstraintViolation<LoginDTO>> violations = validator.validate(loginDTO);
        assertFalse(violations.isEmpty(), "Email should be valid");
    }

    @Test
    public void testInvalidLoginDTO_EmptyPassword() {
        loginDTO.setEmail("abc@gmail.com");
        loginDTO.setPassword("");
        Set<ConstraintViolation<LoginDTO>> violations = validator.validate(loginDTO);
        assertFalse(violations.isEmpty(), "Password should not be empty");
    }

    @Test
    public void testInvalidLoginDTO_ShortPassword() {
        loginDTO.setEmail("abc@gmail.com");
        loginDTO.setPassword("P123");
        Set<ConstraintViolation<LoginDTO>> violations = validator.validate(loginDTO);
        assertFalse(violations.isEmpty(), "Password must be at least 6 characters long");
    }
}
