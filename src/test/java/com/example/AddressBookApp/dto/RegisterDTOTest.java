package com.example.AddressBookApp.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterDTOTest {

    private Validator validator;
    RegisterDTO registerDTO = new RegisterDTO();

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidRegisterDTO() {
        registerDTO.setFullName("Bhagyodaya");
        registerDTO.setEmail("abc@gmail.com");
        registerDTO.setPassword("Password123");
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(registerDTO);
        assertTrue(violations.isEmpty(), "DTO should be valid");
    }

    @Test
    public void testInvalidRegisterDTO_EmptyName() {
        registerDTO.setFullName("");
        registerDTO.setEmail("abc@gmail.com");
        registerDTO.setPassword("Password123");
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(registerDTO);
        assertFalse(violations.isEmpty(), "Full Name should not be empty");
    }

    @Test
    public void testInvalidRegisterDTO_NameWithoutCapitalLetter() {
        registerDTO.setFullName("bhagyodaya");
        registerDTO.setEmail("abc@gmail.com");
        registerDTO.setPassword("Password123");
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(registerDTO);
        assertFalse(violations.isEmpty(), "Name must start with a capital letter");
    }

    @Test
    public void testInvalidRegisterDTO_InvalidEmail() {
        registerDTO.setFullName("Bhagyodaya");
        registerDTO.setEmail("abc-gmail.com");
        registerDTO.setPassword("Password123");
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(registerDTO);
        assertFalse(violations.isEmpty(), "Email should be valid");
    }

    @Test
    public void testInvalidRegisterDTO_EmptyPassword() {
        registerDTO.setFullName("Bhagyodaya");
        registerDTO.setEmail("abc@gmail.com");
        registerDTO.setPassword("");
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(registerDTO);
        assertFalse(violations.isEmpty(), "Password should not be empty");
    }

    @Test
    public void testInvalidRegisterDTO_ShortPassword() {
        registerDTO.setFullName("Bhagyodaya");
        registerDTO.setEmail("abc@gmail.com");
        registerDTO.setPassword("P123");
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(registerDTO);
        assertFalse(violations.isEmpty(), "Password must be at least 6 characters long");
    }

    @Test
    public void testInvalidRegisterDTO_PasswordWithoutUppercase() {
        registerDTO.setFullName("Bhagyodaya");
        registerDTO.setEmail("abc@gmail.com");
        registerDTO.setPassword("password123");
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(registerDTO);
        assertFalse(violations.isEmpty(), "Password must have at least 1 uppercase letter");
    }

    @Test
    public void testInvalidRegisterDTO_PasswordWithoutNumber() {
        registerDTO.setFullName("Bhagyodaya");
        registerDTO.setEmail("abc@gmail.com");
        registerDTO.setPassword("Password");
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(registerDTO);
        assertFalse(violations.isEmpty(), "Password must have at least 1 digit");
    }
}
