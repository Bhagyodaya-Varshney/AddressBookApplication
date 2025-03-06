package com.example.AddressBookApp.controllers;


import com.example.AddressBookApp.dto.AddressBookDTO;
import com.example.AddressBookApp.model.AddressBookModel;
import com.example.AddressBookApp.services.AddressBookInterface;
import com.example.AddressBookApp.services.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AddressBookController {

    @Autowired
    AddressBookInterface addressBookInterface;

    @GetMapping("/")
    public String sayGreeting(){
        return "Welcome to Address Book Application";
    }

    @PostMapping("/addAddress")
    public ResponseEntity<String> addAddress(@RequestBody AddressBookDTO addressBookDTO){
        addressBookInterface.add(addressBookDTO);
        return ResponseEntity.ok("Address Added Successfully");
    }

    @GetMapping("/getAddress")
    public ResponseEntity<List<AddressBookModel>> getAddress(){
        return ResponseEntity.ok(addressBookInterface.getAllAddress());
    }

    @GetMapping("/getAddress/{id}")
    public AddressBookModel getAddessById(@PathVariable Long id){
        return addressBookInterface.getById(id);
    }

    @PutMapping("/updateAddress/{id}")
    public AddressBookModel updateAddressById(@PathVariable Long id,@RequestBody AddressBookDTO addressBookDTO){
        return addressBookInterface.updateAddress(id,addressBookDTO);
    }

    @DeleteMapping("/deleteAddress/{id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable Long id){
        return addressBookInterface.deleteAddress(id);
    }
}
