package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.model.CustomerDetails;
import com.BookStore.BookStoreApplication.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore_user")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomerDetails")
    public ResponseEntity<CustomerDetails> addCustomerDetails(@Valid @RequestBody CustomerDetails customerDetails) {
        CustomerDetails createdCustomerDetails = customerService.addCustomerDetails(customerDetails);
        return ResponseEntity.ok(createdCustomerDetails);
    }

    @PutMapping("/EditCustomerDetails")
    public ResponseEntity<CustomerDetails> updateCustomerDetails(@RequestParam Long id, @RequestBody CustomerDetails customerDetails) {
        try {
            CustomerDetails updatedCustomerDetails = customerService.updateCustomerDetails(id, customerDetails);
            return ResponseEntity.ok(updatedCustomerDetails);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/CustomerDetails/{id}")
    public boolean getCustomerDetails(@PathVariable Long id) {
        return customerService.DeleteCustomerDetails(id);
    }
}