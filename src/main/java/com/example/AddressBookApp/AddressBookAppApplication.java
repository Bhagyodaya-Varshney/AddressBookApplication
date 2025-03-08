package com.example.AddressBookApp;

import com.example.AddressBookApp.controllers.UserController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressBookAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(AddressBookAppApplication.class, args);
	}

}
