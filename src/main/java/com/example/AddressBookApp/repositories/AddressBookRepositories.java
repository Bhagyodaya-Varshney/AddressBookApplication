package com.example.AddressBookApp.repositories;


import com.example.AddressBookApp.model.AddressBookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepositories extends JpaRepository<AddressBookModel,Long> {}
