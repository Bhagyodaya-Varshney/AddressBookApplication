package com.example.AddressBookApp.services;

import com.example.AddressBookApp.dto.AddressBookDTO;
import com.example.AddressBookApp.dto.ResponseDTO;
import com.example.AddressBookApp.exception.AddressBookException;
import com.example.AddressBookApp.model.AddressBookModel;
import com.example.AddressBookApp.repositories.AddressBookRepositories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressBookServiceTest {

    @Mock
    private AddressBookRepositories addressBookRepositories;

    @InjectMocks
    private AddressBookService addressBookService;

    AddressBookDTO addressBookDTO = new AddressBookDTO();
    AddressBookModel addressBookModel = new AddressBookModel();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAddress_Success() {
        addressBookDTO.setName("Bhagyodaya");
        addressBookDTO.setAddress("Mathura");
        addressBookDTO.setPhoneNumber("1478523690");

        addressBookModel.setId(1);
        addressBookModel.setName("Bhagyodaya");
        addressBookModel.setAddress("Mathura");
        addressBookModel.setPhoneNumber("1478523690");


        when(addressBookRepositories.save(any(AddressBookModel.class))).thenReturn(addressBookModel);

        ResponseDTO<String, String> response = addressBookService.add(addressBookDTO);

        assertEquals("message", response.getMessage());
        assertEquals("Address Added Successfully", response.getMessageData());
    }

    @Test
    void testGetAllAddress() {
        addressBookModel.setId(1);
        addressBookModel.setName("Bhagyodaya");
        addressBookModel.setAddress("Mathura");
        addressBookModel.setPhoneNumber("1478523690");

        AddressBookModel addressBookModel1 = new AddressBookModel();
        addressBookModel1.setId(2);
        addressBookModel1.setName("Kunal");
        addressBookModel1.setAddress("Mathura");
        addressBookModel1.setPhoneNumber("1208523690");


        when(addressBookRepositories.findAll()).thenReturn(Arrays.asList(addressBookModel, addressBookModel1));

        List<AddressBookModel> addresses = addressBookService.getAllAddress();

        assertNotNull(addresses);
        assertEquals(2, addresses.size());
    }

    @Test
    void testGetById_Success() {
        addressBookModel.setId(1);
        addressBookModel.setName("Bhagyodaya");
        addressBookModel.setAddress("Mathura");
        addressBookModel.setPhoneNumber("1478523690");

        when(addressBookRepositories.findById(1L)).thenReturn(Optional.of(addressBookModel));

        AddressBookModel found = addressBookService.getById(1L);

        assertNotNull(found);
        assertEquals("Bhagyodaya", found.getName());
    }

    @Test
    void testGetById_NotFound() {
        when(addressBookRepositories.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(AddressBookException.class, () -> addressBookService.getById(1L));

        assertEquals("Address Not Found for ID: 1", exception.getMessage());
    }

    @Test
    void testUpdateAddress_Success() {
        addressBookDTO.setName("Bhagyodaya");
        addressBookDTO.setAddress("Mumbai");
        addressBookDTO.setPhoneNumber("1478523690");

        addressBookModel.setId(1);
        addressBookModel.setName("Bhagyodaya");
        addressBookModel.setAddress("Mathura");
        addressBookModel.setPhoneNumber("1478523690");

        when(addressBookRepositories.findById(1L)).thenReturn(Optional.of(addressBookModel));
        when(addressBookRepositories.save(any(AddressBookModel.class))).thenReturn(addressBookModel);

        ResponseDTO<String, String> response = addressBookService.updateAddress(1L, addressBookDTO);

        assertEquals("message", response.getMessage());
        assertEquals("Update DONE Successfully", response.getMessageData());
    }

    @Test
    void testUpdateAddress_NotFound() {
        addressBookDTO.setName("Bhagyodaya");
        addressBookDTO.setAddress("Mumbai");
        addressBookDTO.setPhoneNumber("1478523690");

        when(addressBookRepositories.findById(1L)).thenReturn(Optional.empty());

        ResponseDTO<String, String> response = addressBookService.updateAddress(1L, addressBookDTO);

        assertEquals("error", response.getMessage());
        assertEquals("Address Not Found", response.getMessageData());
    }

    @Test
    void testDeleteAddress_Success() {
        when(addressBookRepositories.existsById(1L)).thenReturn(true);
        doNothing().when(addressBookRepositories).deleteById(1L);

        ResponseDTO<String, String> response = addressBookService.deleteAddress(1L);

        assertEquals("message", response.getMessage());
        assertEquals("Address Deleted Successfully", response.getMessageData());
    }

    @Test
    void testDeleteAddress_NotFound() {
        when(addressBookRepositories.existsById(1L)).thenReturn(false);

        ResponseDTO<String, String> response = addressBookService.deleteAddress(1L);

        assertEquals("error", response.getMessage());
        assertEquals("Address ID Not FOUND", response.getMessageData());
    }
}
