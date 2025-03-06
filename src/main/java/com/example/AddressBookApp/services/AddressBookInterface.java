package com.example.AddressBookApp.services;

import com.example.AddressBookApp.dto.AddressBookDTO;
import com.example.AddressBookApp.model.AddressBookModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface AddressBookInterface {
    void add(@RequestBody AddressBookDTO addressBookDTO);
    List<AddressBookModel> getAllAddress();
    AddressBookModel getById(Long id);
    AddressBookModel updateAddress(Long id, AddressBookDTO addressBookDTO);
    ResponseEntity<String> deleteAddress(Long id);
}
