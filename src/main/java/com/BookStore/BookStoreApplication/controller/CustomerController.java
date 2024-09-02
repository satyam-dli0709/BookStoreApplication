package com.BookStore.BookStoreApplication.controller;

import com.BookStore.BookStoreApplication.model.CustomerDetails;
import com.BookStore.BookStoreApplication.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CustomerDetails> updateCustomerDetails(@RequestParam Long customerId, @RequestBody CustomerDetails customerDetails) {
        try {
            CustomerDetails updatedCustomerDetails = customerService.updateCustomerDetails(customerId, customerDetails);
            return ResponseEntity.ok(updatedCustomerDetails);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/CustomerDetails/{id}")
    public ResponseEntity<Object> deleteCustomerDetails(@PathVariable("id") Long customerId) {
         customerService.DeleteCustomerDetails(customerId);
         return new ResponseEntity<>("Customer deleted Successfully",HttpStatus.OK);
    }
}