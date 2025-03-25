package com.example.AddressBookApp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserModelTest {

    @Test
    public void testUserGettersAndSetters() {
        User user = new User();
        user.setId(1);
        user.setFullName("Bhagyodaya");
        user.setEmail("abc@gmail.com");
        user.setPassword("password123");
        user.setToken("testToken");

        assertEquals(1, user.getId());
        assertEquals("Bhagyodaya", user.getFullName());
        assertEquals("abc@gmail.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("testToken", user.getToken());
    }
}
