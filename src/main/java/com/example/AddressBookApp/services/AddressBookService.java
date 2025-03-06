package com.example.AddressBookApp.services;


import com.example.AddressBookApp.dto.AddressBookDTO;
import com.example.AddressBookApp.model.AddressBookModel;
import com.example.AddressBookApp.repositories.AddressBookRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AddressBookService {

    @Autowired
    AddressBookRepositories addressBookRepositories;

    public void add(@RequestBody AddressBookDTO addressBookDTO){
        AddressBookModel addressBookModel = new AddressBookModel();
        addressBookModel.setName(addressBookDTO.getName());
        addressBookModel.setAddress(addressBookDTO.getAddress());
        addressBookModel.setPhoneNumber(addressBookDTO.getPhoneNumber());

        addressBookRepositories.save(addressBookModel);
    }

    public List<AddressBookModel> getAllAddress(){
        return addressBookRepositories.findAll();
    }

    public AddressBookModel getById(Long id){
        return addressBookRepositories.findById(id).orElse(null);
    }

    public AddressBookModel updateAddress(Long id, AddressBookDTO addressBookDTO){
        AddressBookModel existingAddressBookModel = addressBookRepositories.findById(id).orElse(null);
        if(existingAddressBookModel != null){
            existingAddressBookModel.setName(addressBookDTO.getName());
            existingAddressBookModel.setAddress(addressBookDTO.getAddress());
            existingAddressBookModel.setPhoneNumber(addressBookDTO.getPhoneNumber());
        }

        return existingAddressBookModel;
    }

    public ResponseEntity<String> deleteAddress(Long id){
        if(!addressBookRepositories.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address ID Not FOUND");
        }
        addressBookRepositories.deleteById(id);
        return ResponseEntity.ok("Address Deleted Successfully");
    }
}
