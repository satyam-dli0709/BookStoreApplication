package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.CustomerDetails;
import com.BookStore.BookStoreApplication.model.User;
import com.BookStore.BookStoreApplication.repository.CustomerRepository;
import com.BookStore.BookStoreApplication.repository.UserRepository;
import com.BookStore.BookStoreApplication.exception.CustomerNotFoundException;
import com.BookStore.BookStoreApplication.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomerDetails addCustomerDetails(CustomerDetails customerDetails) {
        return customerRepository.save(customerDetails);
    }

    @Override
    public CustomerDetails updateCustomerDetails(Long id, CustomerDetails customerDetails) {
        CustomerDetails existingDetails=customerRepository.findById(id).orElse(null);
        if(existingDetails!=null){
            existingDetails.setAddress(customerDetails.getAddress());
            existingDetails.setCustomerName(customerDetails.getCustomerName());
            existingDetails.setEmail(customerDetails.getEmail());
            existingDetails.setPhoneNumber(customerDetails.getPhoneNumber());
            return customerRepository.save(existingDetails);
        }
        else{
            throw new CustomerNotFoundException("Customer not found with id " + id);
        }
    }

    @Override
    public boolean DeleteCustomerDetails(Long id) {
        CustomerDetails customer_details=customerRepository.findById(id).orElse(null);
        if(customer_details==null){
            throw new CustomerNotFoundException("Customer not found with id " + id);
        }
        else{
            customerRepository.deleteById(id);
            return true;

        }

    }
}